package expression;

public class Pow extends BinOperation {
    public Pow(BaseExpression val1, BaseExpression val2) {
        super(val1, val2);
    }

    @Override
    protected int calculate(final int x, final int y) {
        if (x == 0 && y >= 1){
            return 0;
        }
        if (x == 0 || y == 0) {
            return 1;
        }
        int res = 1;
        int base = x;
        int cur = y;
        while (cur > 0) {
            if ((cur & 1) == 1) {
                res *= base;
            }
            cur >>= 1;
            base *= base;
        }

        return res;
    }


    @Override
    protected long calculate(final long x, final long y) {
        if (x == 0 && y >= 1){
            return 0;
        }
        if (x == 0 || y == 0) {
            return 1;
        }
        long res = 1;
        long base = x;
        long cur = y;
        while (cur > 0) {
            if ((cur & 1) == 1) {
                res *= base;
            }
            cur >>= 1;
            base *= base;
        }

        return res;
    }

    @Override
    protected boolean LeftCommutative(){
        return false;
    }

    @Override
    protected boolean RightCommutative(){
        return false;
    }

    @Override
    protected String getOperand() {
        return "**";
    }

    @Override
    protected int getPrior() {
        return 400;
    }

}
