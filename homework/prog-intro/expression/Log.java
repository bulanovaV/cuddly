package expression;

public class Log extends BinOperation {
    public Log(BaseExpression val1, BaseExpression val2) {
        super(val1, val2);
    }

    @Override
    protected int calculate(final int value, final int base) {
        if (value == 0 && base >= 1) {
            return Integer.MIN_VALUE;
        }
        if (value <= 1|| base <= 0) {
            return 0;
        }
        if (base == 1) {
            return Integer.MAX_VALUE;
        }
        int result = 0;
        int current = 1;
        while (current <= value / base) {
            current *= base;
            result++;
        }

        return result;
    }

    @Override
    protected long calculate(final long x, final long y) {
        if (x == 0 && y >= 1) {
            return Integer.MIN_VALUE;
        }
        if (x <= 1|| y <= 0) {
            return 0;
        }
        if (y == 1) {
            return Integer.MAX_VALUE;
        }
        long res = 0;
        long cur = 1;
        while (cur <= x / y) {
            cur *= y;
            res++;
        }

        return res;
    }

    @Override
    protected boolean LeftCommutative() {
        return false;
    }

    @Override
    protected boolean RightCommutative() {
        return true;
    }

    @Override
    protected String getOperand() {
        return "//";
    }

    @Override
    protected int getPrior() {
        return 400;
    }

}
