package expression;

public class Divide extends BinOperation {

    public Divide(BaseExpression val1, BaseExpression val2) {
        super(val1, val2);
    }

    @Override
    protected int calculate(final int x, final int y) {
        return x / y;
    }

    @Override
    protected long calculate(final long x, final long y) {
        return x / y;
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
        return "/";
    }

    @Override
    protected int getPrior() {
        return 200;
    }
}
