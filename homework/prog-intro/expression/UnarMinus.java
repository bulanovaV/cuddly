package expression;

import java.util.Map;
import java.util.Objects;

public class UnarMinus extends UnarOperation {
    public UnarMinus(BaseExpression n, boolean flag) {
        super(n, flag);
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

    @Override
    protected boolean getFlag() {
        return flag;
    }
}
