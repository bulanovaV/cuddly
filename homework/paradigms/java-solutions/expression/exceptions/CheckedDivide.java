package expression.exceptions;

import expression.*;

public class CheckedDivide extends Divide {
    public CheckedDivide(final BaseExpression a, final BaseExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(final int n1, final int n2) {
        if (n2 == 0) {
            throw new DivByZeroError("Division by zero");
        }
        if (n1 == Integer.MIN_VALUE && n2 == -1) {
            throw new OverflowException("Overflow in division");
        }
        return n1 / n2;
    }
}