package markup;

import java.util.List;

public class OrderedList extends AbstractList{
    public OrderedList(List<ListItem> s) {
        super(s, "ordered");
    }
}
