package markup;

import java.util.List;
import java.util.Map;

public class AbstractList implements lists {
    private final Map<String, String> open = Map.of(
            "ordered", "<orderedlist>",
            "unordered", "<itemizedlist>"
    );
    private final Map<String, String> close = Map.of(
            "ordered", "</orderedlist>",
            "unordered", "</itemizedlist>"
    );

    private List<ListItem> s;
    private String type;

    public AbstractList(List<ListItem> s, String type) {
        this.s = s;
        this.type = type;
    }

    public void toDocBook(StringBuilder sb) {
        sb.append(open.get(type));
        for (ListItem k : s) {
            k.toDocBook(sb);
        }
        sb.append(close.get(type));
    }

}
