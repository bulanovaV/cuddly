package expression;

public class Subtract extends BinOperation {
    public Subtract(BaseExpression val1, BaseExpression val2) {
        super(val1, val2);
    }

    @Override
    protected int calculate(final int x, final int y) {
        return x - y;
    }

    @Override
    protected long calculate(final long x, final long y) {
        return x - y;
    }

    @Override
    protected String getOperand() {
        return "-";
    }
}
