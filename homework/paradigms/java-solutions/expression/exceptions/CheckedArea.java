package expression.exceptions;

import expression.*;

public class CheckedArea extends Area {
    public CheckedArea(final BaseExpression a, final BaseExpression b) {
        super(a, b);
    }

    @Override
    protected int calculate(int x, int y) {
        if (x < 0 || y < 0) {
            throw new OverflowException("Overflow in area");
        }

        if (x % 2 != 0 && y % 2 != 0) {
            if (check(x, y)) {
                return x * y / 2;
            }
            throw new OverflowException("Overflow in area");
        }

        if (x % 2 == 0) {
            if (check(x / 2, y)) {
                return x / 2 * y;
            }
            throw new OverflowException("Overflow in area");
        }

        if (check(x, y / 2)) {
            return x * y / 2;
        }
        throw new OverflowException("Overflow in area");

    }

    private boolean check(int x, int y) {
        if (x == Integer.MIN_VALUE && y == -1) {
            return false;
        }
        if (x == -1 && y == Integer.MIN_VALUE) {
            return false;
        }
        if (y == 0) {
            return true;
        }
        return (x * y) / y == x;
    }
}
