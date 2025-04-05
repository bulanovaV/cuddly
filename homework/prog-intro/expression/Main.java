package expression;

//javac --class-path src src\expression\LongMapExpression.java
//java  -ea --class-path src expression.LongMapExpression hard LongMap

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println(new Square(new Const(23)));
    }
}
