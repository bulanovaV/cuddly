package expression;

public class Square extends UnarOperation {
    public Square(BaseExpression val1) {
        super(val1, false);
    }

    @Override
    protected int calculate(int value) {
        return value * value;
    }

    @Override
    protected long calculate(long value) {
        return value * value;
    }

    @Override
    protected String getOperand() {
        return "²";
    }

    @Override
    protected boolean getFlag() {
        return flag;
    }
}
