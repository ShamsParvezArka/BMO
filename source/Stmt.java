import java.util.List;

abstract class Stmt {
	interface Visitor<T> {
		T visitBlockStmt(Block stmt);
		T visitExpressionStmt(Expression stmt);
		T visitFunctionStmt(Function stmt);
		T visitIfStmt(If stmt);
		T visitPrintStmt(Print stmt);
		T visitReturnStmt(Return stmt);
		T visitVarStmt(Var stmt);
		T visitWhileStmt(While stmt);
	}

	static class Block extends Stmt {
		Block(List<Stmt> statements) {
			this.statements = statements;
		}

		final List<Stmt> statements;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitBlockStmt(this);
		}
	}
	static class Expression extends Stmt {
		Expression(Expr expression) {
			this.expression = expression;
		}

		final Expr expression;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitExpressionStmt(this);
		}
	}
	static class Function extends Stmt {
		Function(Token name, List<Token> params, List<Stmt> body) {
			this.name = name;
			this.params = params;
			this.body = body;
		}

		final Token name;
		final List<Token> params;
		final List<Stmt> body;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitFunctionStmt(this);
		}
	}
	static class If extends Stmt {
		If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
			this.condition = condition;
			this.thenBranch = thenBranch;
			this.elseBranch = elseBranch;
		}

		final Expr condition;
		final Stmt thenBranch;
		final Stmt elseBranch;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitIfStmt(this);
		}
	}
	static class Print extends Stmt {
		Print(Expr expression) {
			this.expression = expression;
		}

		final Expr expression;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitPrintStmt(this);
		}
	}
	static class Return extends Stmt {
		Return(Token keyword, Expr value) {
			this.keyword = keyword;
			this.value = value;
		}

		final Token keyword;
		final Expr value;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitReturnStmt(this);
		}
	}
	static class Var extends Stmt {
		Var(Token name, Expr initializer) {
			this.name = name;
			this.initializer = initializer;
		}

		final Token name;
		final Expr initializer;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitVarStmt(this);
		}
	}
	static class While extends Stmt {
		While(Expr condition, Stmt body) {
			this.condition = condition;
			this.body = body;
		}

		final Expr condition;
		final Stmt body;

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitWhileStmt(this);
		}
	}

	abstract <T> T accept(Visitor<T> visitor);
}
