package markup;

public class Text implements element{
    private String s;

    public Text(String s) {
        this.s = s;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(s);
    }

    @Override
    public void toDocBook(StringBuilder sb) {
        sb.append(s);
    }

}
