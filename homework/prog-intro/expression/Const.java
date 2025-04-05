package expression;

import java.util.Map;
import java.util.Objects;

public class Const implements BaseExpression {
    private final long n;

    public Const(final int n) {
        this.n = n;
    }

    public Const(final long n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }

    @Override
    public String toMiniString() {
        return String.valueOf(n);
    }

    @Override
    public int evaluate(final int m) {
        return (int) n;
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return (int) n;
    }

    @Override
    public long evaluateL(final Map<String, Long> values) {
        return n;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Const value)) {
            return false;
        }
        return Objects.equals(this.n, value.n);
    }

    @Override
    public int hashCode() {
        return 12 * Objects.hash((int) n);
    }
}
