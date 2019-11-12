package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph {
    List<UnorderedList> unorderedLists = new ArrayList<>();

    ParagraphWithList() {
    }

    ParagraphWithList(String content) {
        this.content = content;
    }

    ParagraphWithList setContent(ParagraphWithList newContent) {
        this.unorderedLists = newContent.unorderedLists;
        return this;
    }

    UnorderedList setContent(String newList) {
        UnorderedList tmp = new UnorderedList(newList);
        unorderedLists.add(tmp);
        return tmp;
    }

    @Override
    void writeHTML(PrintStream out) {
        out.printf("<p>\n");
        out.printf("%s\n", content);
        for (UnorderedList ul : unorderedLists) {
            ul.writeHTML(out);
        }
        out.printf("</p>\n");
    }
}
