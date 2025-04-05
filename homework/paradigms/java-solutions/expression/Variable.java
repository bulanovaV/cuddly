package expression;

import java.util.Map;
import java.util.Objects;

public class Variable implements BaseExpression {
    private final String var;
    private final String name;

    public Variable(final String var) {
        this.var = var.substring(var.length() - 1);
        this.name = var;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public int evaluate(final int m) {
        return m;
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }

    @Override
    public long evaluateL(final Map<String, Long> values) {
        return values.get(name);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Variable value)) {
            return false;
        }
        return Objects.equals(this.name, value.name);
    }

    @Override
    public int hashCode() {
        return 21 * Objects.hash(name);
    }
}
