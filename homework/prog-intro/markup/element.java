package markup;

public interface element {
    void toMarkdown(StringBuilder sb);
    void toDocBook(StringBuilder sb);
}
