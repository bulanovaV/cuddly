package expression;

import java.util.Map;
import java.util.Objects;

public abstract class UnarOperation implements BaseExpression {
    protected final BaseExpression val;

    public UnarOperation(BaseExpression val) {
        this.val = val;
    }

    protected abstract int calculate(int value);

    protected abstract long calculate(long value);

    protected abstract String getOperand();

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
        return getOperand() + "(" + val.toString() + ")";
    }

    @Override
    public String toString() {
        return getOperand() + "(" + val.toString() + ")";
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
