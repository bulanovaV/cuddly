package expression.exceptions;

import expression.*;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(final BaseExpression a, final BaseExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(final int n1, final int n2) {
        if ((n2 > 0 && n1 < Integer.MIN_VALUE + n2) || (n2 < 0 && n1 > Integer.MAX_VALUE + n2)) {
            throw new OverflowException("Overflow in subtract");
        }
        return n1 - n2;
    }
}
