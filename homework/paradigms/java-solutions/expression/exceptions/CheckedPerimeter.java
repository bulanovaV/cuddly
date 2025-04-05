package expression.exceptions;

import expression.*;

public class CheckedPerimeter extends Perimeter {
    public CheckedPerimeter(final BaseExpression a, final BaseExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(final int n1, final int n2) {
        if (n1 < 0 || n1 > Integer.MAX_VALUE / 2) {
            throw new OverflowException("Overflow in perimeter");
        }
        if (n2 < 0 || n2 > Integer.MAX_VALUE / 2) {
            throw new OverflowException("Overflow in perimeter");
        }
        if (n1 * 2 > Integer.MAX_VALUE - n2 * 2){
            throw new OverflowException("Overflow in perimeter");
        }
        return n1 * 2 + 2 * n2;
    }
}
