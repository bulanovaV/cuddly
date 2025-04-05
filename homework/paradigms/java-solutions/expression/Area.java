package expression;

public class Area extends BinOperation {
    public Area(BaseExpression val1, BaseExpression val2) {
        super(val1, val2);
    }

    @Override
    protected int calculate(final int x,final int y) {
        return (x * y) / 2;
    }

    @Override
    protected long calculate(final long x,final long y) {
        return (x * y) / 2;
    }

    @Override
    protected String getOperand() {
        return "area";
    }
}
