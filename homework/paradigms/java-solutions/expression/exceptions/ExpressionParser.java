package expression.exceptions;

import expression.parser.*;
import expression.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser extends BaseParser implements TripleParser {

    private List<Character> brackets = new ArrayList<>();

    public BaseExpression parse(String s) throws ParserErrors {
        ready(new StringSource(s));
        brackets = new ArrayList<>();
        return parse();
    }

    public BaseExpression parse() throws ParserErrors {
        return operation3();
    }

    private BaseExpression operation3() throws ParserErrors {
        BaseExpression first = operation2();
        while (test('a') || test('p')) {
            String operand = parseOperand(get());
            BaseExpression second = operation2();
            first = createExpression(operand, first, second);
        }
        if (isCloseBrack(get()) && brackets.isEmpty()) {
            throw new InvalidBracketsSequence("No opening parenthesis for: " + get());
        }
        if (!isCorrect()) {
            throw new OperandError("UnsupportedOperand: '" + get() + "'");
        }
        return first;
    }

    private BaseExpression operation2() throws ParserErrors {
        BaseExpression first = operation1();
        while (test('+') || test('-')) {
            String operand = String.valueOf(take());
            BaseExpression second = operation1();
            first = createExpression(operand, first, second);
        }
        return first;
    }

    private BaseExpression operation1() throws ParserErrors {
        BaseExpression first = operation0();
        while (test('*') || test('/')) {
            String operand = String.valueOf(take());
            BaseExpression second = operation0();
            first = createExpression(operand, first, second);
        }
        skipWhitespace();
        return first;
    }

    private boolean checkBrack(char type) throws ParserErrors {
        return !test(oppositeBrack(type)) || brackets.getLast() != type;
    }

    private BaseExpression operation0() throws ParserErrors {
        skipWhitespace();
        char next = take();
        if (isOpenBrack(next)) {
            return checkBracSeq(next);
        }
        if (next == '-') {
            // :NOTE: сократить
            char cur = get();
            if (Character.isDigit(cur)) {
                String str = '-' + parseCharacter(next);
                return parseConst(str);
            }
            if (isOpenBrack(cur)) {
                take();
                return new CheckedNegate(checkBracSeq(cur));
            }
            BaseExpression val = operation0();
            skipWhitespace();
            return new CheckedNegate(val);
        }
        String str = parseCharacter(next);
        if (!str.isEmpty() && str.charAt(0) != next) {
            throw new OperandError("Not Support operand: " + str);
        }
        BaseExpression res;
        skipWhitespace();
        if (str.length() - 1 >= 0 && Character.isDigit(str.charAt(str.length() - 1))) {
            return parseConst(str);
        } else if (Character.isLetter(next)) {
            if (!(str.equals("x") || str.equals("y") || str.equals("z"))) {
                throw new OperandError("Is not correct variable: " + str);
            }
            res = new Variable(str);
            skipWhitespace();
            return res;
        } else {
            throw new StructError("Invalid structure: " + next);
        }
    }

    private BaseExpression parseConst(String str){
        final int n;
        try {
            n = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new OverflowException("Overflow in const: " + str);
        }
        skipWhitespace();
        return new Const(n);
    }
    private BaseExpression checkBracSeq(char next) throws ParserErrors {
        BaseExpression result;
        brackets.add(next);
        result = operation3();
        if (checkBrack(next)) {
            throw new InvalidBracketsSequence("Not PSP: " + next);
        }
        brackets.removeLast();
        take();
        skipWhitespace();
        return result;
    }

    private String parseCharacter(char next) {
        StringBuilder sb = new StringBuilder();
        if (Character.isDigit(next) || Character.isLetter(next)) {
            sb.append(next);
        }
        while (Character.isDigit(get()) || Character.isLetter(get())) {
            sb.append(take());
        }
        return sb.toString();
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(get()) || test('\t') || test('\r') || test('\n')) {
            take();
        }
    }

    public BaseExpression createExpression(String operand, BaseExpression first, BaseExpression second) throws ParserErrors {
        return switch (operand) {
            case "+" -> new CheckedAdd(first, second);
            case "-" -> new CheckedSubtract(first, second);
            case "*" -> new CheckedMultiply(first, second);
            case "/" -> new CheckedDivide(first, second);
            case "area" -> new CheckedArea(first, second);
            case "perimeter" -> new CheckedPerimeter(first, second);
            default -> throw new CharacterError("Unexpected operand: " + operand);
        };
    }


    public char oppositeBrack(char type) throws ParserErrors {
        return switch (type) {
            case ')' -> '(';
            case ']' -> '[';
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            default -> throw new CharacterError("Unexpected brack: " + type);
        };
    }

    public boolean isOpenBrack(char b) {
        return b == '(' || b == '[' || b == '{';
    }

    public boolean isCloseBrack(char b) {
        return b == ')' || b == ']' || b == '}';
    }

    public String parseOperand(char type) throws OperandError {
        String operand = switch (type) {
            case 'a' -> "area";
            case 'p' -> "perimeter";
            default -> throw new OperandError("Unexpected operand: " + type);
        };
        int i = 0;
        while (i < operand.length()) {
            if (operand.charAt(i) != get()) {
                throw new OperandError("Unexpected operand: " + get());
            }
            i++;
            take();
        }
        if (!Character.isWhitespace(get()) && get() != '(' && get() != '-') {
            throw new OperandError("Unexpected operand: " + get());
        }
        return operand;
    }
}
