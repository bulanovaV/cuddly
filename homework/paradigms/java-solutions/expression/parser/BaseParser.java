package expression.parser;

public class BaseParser {
    private static final char END = '\0';
    private StringSource source;
    private char ch = 0xffff;

    public void ready(StringSource source){
        this.source = source;
        take();
    }

    protected char get(){
        return ch;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean isCorrect(){
        if (ch == '*' || ch == '+' ||ch == '-' ||ch == '/' ||  ch == END || ch == ')' || ch == ']' || ch == '}'){
            return true;
        }
        return false;
    }

}
