package expression.exceptions;

import expression.*;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(final BaseExpression a, final BaseExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(final int n1, final int n2) {
        if (n1 > 0 && n2 > 0 && n1 > Integer.MAX_VALUE / n2 ||
                n1 > 0 && n2 < 0 && n2 < Integer.MIN_VALUE / n1 ||
                n1 < 0 && n2 > 0 && n1 < Integer.MIN_VALUE / n2 ||
                n1 < 0 && n2 < 0 && n1 < Integer.MAX_VALUE / n2) {
            throw new OverflowException("Overflow in multiplication");
        }
        return n1 * n2;
    }
}
