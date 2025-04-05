package expression.exceptions;

public class DivByZeroError extends RuntimeException {
    public DivByZeroError(String message) {
        super(message);
    }
}
