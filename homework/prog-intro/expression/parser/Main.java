package expression.parser;

public class Main {
    public static void main(String[] args) {
        TripleParser r = new ExpressionParser();
        System.out.println(r.parse("((z)²)²").toMiniString());
        System.out.println(r.parse("((z)²)²").toMiniString());
        System.out.println(r.parse("((z)²)²").toMiniString());
        System.out.println(r.parse("((z)²)²").toMiniString());
    }
}
