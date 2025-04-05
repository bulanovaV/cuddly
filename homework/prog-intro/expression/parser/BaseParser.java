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

    protected void goBack(int k){
        source.goBack(k + 1);
        ch = source.next();
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

}
