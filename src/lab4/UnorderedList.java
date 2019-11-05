package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList extends ParagraphWithList{
    List<ListItem> listItems = new ArrayList<>();

    UnorderedList(String content) {
        super(content);
    }

    UnorderedList addItem(ListItem li) {
        listItems.add(li);
        return this;
    }
    void writeHTML(PrintStream out) {
        out.printf("<ul>\n");
        for (ListItem li : listItems){
            li.writeHTML(out);
        }
        out.printf("</ul>\n");
    }
}
