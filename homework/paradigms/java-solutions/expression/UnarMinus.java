package expression;


public class UnarMinus extends UnarOperation {
    public UnarMinus(BaseExpression n) {
        super(n);
    }


    @Override
    protected int calculate(int value) {
        return -value;
    }

    @Override
    protected long calculate(long value) {
        return -value;
    }

    protected String getOperand(){
        return "-";
    }

}
