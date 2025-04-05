package markup;

import java.util.List;
import java.util.Map;

public class AbstractElement implements element {
    private final Map<String, String> markdown = Map.of(
            "none", "",
            "emphasis", "*",
            "strong", "__",
            "strikeout", "~"
    );

    private final Map<String, String> docbook = Map.of(
            "paragraph", "<para>",
            "emphasis", "<emphasis>",
            "strong", "<emphasis role='bold'>",
            "strikeout", "<emphasis role='strikeout'>"

    );
    private final Map<String, String> docbookclose = Map.of(
            "paragraph", "</para>",
            "emphasis", "</emphasis>",
            "strong", "</emphasis>",
            "strikeout", "</emphasis>"

    );

    private List<element> s;
    private String type;

    public AbstractElement(List<element> s, String type) {
        this.s = s;
        this.type = type;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(markdown.get(type));
        for (element k : s) {
            k.toMarkdown(sb);
        }
        sb.append(markdown.get(type));
    }

    @Override
    public void toDocBook(StringBuilder sb) {
        sb.append(docbook.get(type));
        for (element k : s) {
            k.toDocBook(sb);
        }
        sb.append(docbookclose.get(type));
    }

}
