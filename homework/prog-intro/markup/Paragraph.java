package markup;

import java.util.List;

public class Paragraph implements lists{
    private List<element> res;

    public Paragraph(List<element> s) {
        this.res = s;
    }

    public void toMarkdown(StringBuilder sb) {
        for (element k : res) {
            k.toMarkdown(sb);
        }
    }

    public void toDocBook(StringBuilder sb) {
        sb.append("<para>");
        for (element k: res){
            k.toDocBook(sb);
        }
        sb.append("</para>");
    }

}