package markup;

import java.util.List;

public class ListItem{
    private List<lists> s;

    public ListItem(List<lists> s) {
        this.s = s;
    }

    public void toDocBook(StringBuilder sb) {
        sb.append("<listitem>");
        for (lists k : s) {
            k.toDocBook(sb);
        }
        sb.append("</listitem>");
    }
}
