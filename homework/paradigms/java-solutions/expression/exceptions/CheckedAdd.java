package expression.exceptions;

import expression.*;

public class CheckedAdd extends Add {
    public CheckedAdd(final BaseExpression a, final BaseExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(final int n1, final int n2) {
        if ((n1 > 0 && n2 > 0 && n1 > Integer.MAX_VALUE - n2) ||
                (n1 < 0 && n2 < 0 && n1 < Integer.MIN_VALUE - n2)) {
            throw new OverflowException("Overflow in add");
        }
        return n1 + n2;
    }
}
