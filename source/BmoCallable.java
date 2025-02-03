import java.util.List;

interface BmoCallable {
	int arity();
	Object call(Interpreter interpreter, List<Object> arguments);
}