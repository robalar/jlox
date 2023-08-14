import java.util.List;

public interface LoxCallable {
    Object call(Interpreter interpreter, List<Object> arguments) throws RuntimeError;

    int arity();
}
