import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Lexer {
	private final String source;
	private final List<Token> tokens = new ArrayList<>();
	private int start = 0;
	private int current = 0;
	private int line = 1;
	public static final Map<String, TokenType> keywords = new HashMap<String, TokenType>();

	Lexer(String source) {
		this.source = source;
	}

	List<Token> scanTokens() {
		while (!isAtEnd()) {
			start = current;
			scanToken();
		}

		tokens.add(new Token(TokenType.EOF, "", null, line));
		return tokens;
	}

	private boolean isAtEnd() {
		return current >= source.length();
	}

	private void scanToken() {
        char c = advance();

        switch (c) {
            case '(' -> addToken(TokenType.LEFT_PAREN);
            case ')' -> addToken(TokenType.RIGHT_PAREN);
            case '{' -> addToken(TokenType.LEFT_BRACE);
            case '}' -> addToken(TokenType.RIGHT_BRACE);
            case ',' -> addToken(TokenType.COMMA);
            case '.' -> addToken(TokenType.DOT);
            case '+' -> addToken(TokenType.PLUS);
            case '-' -> addToken(TokenType.MINUS);
            case ';' -> addToken(TokenType.SEMICOLON);
            case '*' -> addToken(TokenType.STAR);
            case '=' -> addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
            case '<' -> addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
            case '>' -> addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
            case '!' -> addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
            case '/' -> {
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(TokenType.SLASH);
                }
            }
            case 'o' -> {
                if (match('r')) addToken(TokenType.OR);
            }
            case ' ' -> {}
            case '\r' -> {}
            case '\t' -> {}
            case '\n' -> line++;
            case '"' -> string();
            default -> {
                if (isDigit(c)) number();
                else if (isAlpha(c)) identifier();
                else Bmo.error(line, "Unexpected character.");
            }
        }
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    private char advance() {
        return source.charAt(current++);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }

        if (isAtEnd()) {
            System.out.println("Unterminated string.");
            return;
        }
        advance();
        
        String value = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, value);
    }

    private void number() {
        while (isDigit(peek())) advance();

        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }

        double n =  Double.parseDouble(source.substring(start, current));
        addToken(TokenType.NUMBER, n);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '_';
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    public boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    public void identifier() {
        while (isAlphaNumeric(peek())) {
        	advance();
        }

        String text = source.substring(start, current);
        TokenType type = keywords.get(text);

        if (type == null) {
        	type = TokenType.IDENTIFIER;
        }

        addToken(type);
    }

    static {
        keywords.put("and",    TokenType.AND);
        keywords.put("class",  TokenType.CLASS);
        keywords.put("else",   TokenType.ELSE);
        keywords.put("false",  TokenType.FALSE);
        keywords.put("for",    TokenType.FOR);
        keywords.put("fun",    TokenType.FUN);
        keywords.put("if",     TokenType.IF);
        keywords.put("nil",    TokenType.NIL);
        keywords.put("or",     TokenType.OR);
        keywords.put("print",  TokenType.PRINT);
        keywords.put("return", TokenType.RETURN);
        keywords.put("super",  TokenType.SUPER);
        keywords.put("this",   TokenType.THIS);
        keywords.put("true",   TokenType.TRUE);
        keywords.put("var",    TokenType.VAR);
        keywords.put("while",  TokenType.WHILE);
    } 
}