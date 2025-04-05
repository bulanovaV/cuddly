package expression;

public class Multiply extends BinOperation {
    public Multiply(BaseExpression val1, BaseExpression val2) {
        super(val1, val2);
    }

    @Override
    protected int calculate(final int x, final int y) {
        return x * y;
    }

    @Override
    protected long calculate(final long x, final long y) {
        return x * y;
    }

    @Override
    protected boolean LeftCommutative(){
        return true;
    }

    @Override
    protected boolean RightCommutative(){
        return true;
    }

    @Override
    protected String getOperand() {
        return "*";
    }

    @Override
    protected int getPrior() {
        return 200;
    }

}
