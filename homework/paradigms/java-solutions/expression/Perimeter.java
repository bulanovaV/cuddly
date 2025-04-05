package expression;

public class Perimeter extends BinOperation {
    public Perimeter(BaseExpression val1, BaseExpression val2) {
        super(val1, val2);
    }

    @Override
    protected int calculate(final int x,final int y) {
        return x * 2 + 2 * y;
    }

    @Override
    protected long calculate(final long x,final long y) {
        return x * 2 + 2 * y;
    }

    @Override
    protected String getOperand() {
        return "perimeter";
    }
}
