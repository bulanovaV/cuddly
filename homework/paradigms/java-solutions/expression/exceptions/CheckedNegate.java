package expression.exceptions;

import expression.*;

public class CheckedNegate extends UnarMinus {
    public CheckedNegate(BaseExpression a) {
        super(a);
    }

    @Override
    protected int calculate(final int n) {
        if (n == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in negation");
        }
        return -n;
    }
}