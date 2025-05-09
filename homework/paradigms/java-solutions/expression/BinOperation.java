package expression;

import java.util.Map;
import java.util.Objects;

public abstract class BinOperation implements BaseExpression {
    protected final BaseExpression val1;
    protected final BaseExpression val2;

    public BinOperation(final BaseExpression val1, final BaseExpression val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    protected abstract int calculate(int x, int y);

    protected abstract long calculate(long x, long y);

    protected abstract String getOperand();

    public int evaluate(final int n) {
        return calculate(val1.evaluate(n), val2.evaluate(n));
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return calculate(val1.evaluate(x, y, z), val2.evaluate(x, y, z));
    }

    @Override
    public long evaluateL(final Map<String, Long> values) {
        return calculate(val1.evaluateL(values), val2.evaluateL(values));
    }

    public String toString() {
        return "(" + val1.toString() + " " + getOperand() + " " + val2.toString() + ")";
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
                Objects.equals(val1, object.val1) &&
                Objects.equals(val2, object.val2);
    }

    @Override
    public int hashCode() {
        return 12 * Objects.hash(val1) + 345 * Objects.hash(getClass()) + 1572 * Objects.hash(val2);
    }

    @Override
    public String toMiniString() {
        return "(" + val1.toString() + " " + getOperand() + " " + val2.toString() + ")";
    }

}
