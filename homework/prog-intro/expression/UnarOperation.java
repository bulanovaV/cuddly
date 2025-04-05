package expression;

import java.util.Map;
import java.util.Objects;

public abstract class UnarOperation implements BaseExpression {
    protected final BaseExpression val;
    protected final boolean flag;

    public UnarOperation(BaseExpression val, boolean flag) {
        this.val = val;
        this.flag = flag;
    }

    protected abstract int calculate(int value);

    protected abstract long calculate(long value);

    protected abstract String getOperand();

    protected abstract boolean getFlag();

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(val.evaluate(x, y, z));
    }

    @Override
    public long evaluateL(Map<String, Long> variables) {
        return calculate(val.evaluateL(variables));
    }

    @Override
    public int evaluate(int x) {
        return calculate(val.evaluate(x));
    }

    @Override
    public String toMiniString() {
        if (getOperand().equals("²")) {
            if (val instanceof BinOperation) {
                return "(" + val.toMiniString() + ")" + getOperand();
            }
            if (val instanceof UnarOperation && ((UnarOperation) val).getFlag()) {
                return "(" + val.toMiniString() + ")" + getOperand();
            }
            return val.toMiniString() + getOperand();
        }
        if (val instanceof BinOperation) {
            return getOperand() + "(" + val.toMiniString() + ")";
        }
        if (flag) {
            return getOperand() + " " + val.toMiniString();
        }
        return getOperand() + val.toMiniString();
    }

    @Override
    public String toString() {
        if (getOperand().equals("²")) {
            return "(" + val.toString() + ")" + getOperand();
        }
        if (flag) {
            return getOperand() + "(" + val.toString() + ")";
        }
        return getOperand() + val.toString();
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BinOperation object)) {
            return false;
        }
        return Objects.equals(getClass(), object.getClass()) &&
                Objects.equals(val, object.val1);
    }

    @Override
    public int hashCode() {
        return 17 * Objects.hash(getClass()) + 18 * Objects.hash(val);
    }


}
