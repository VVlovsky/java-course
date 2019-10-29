package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph {
    List<UnorderedList> unorderedLists = new ArrayList<>();

    ParagraphWithList(String content) {
        super(content);
    }

    ParagraphWithList setContent(ParagraphWithList newContent) {
        this.unorderedLists = newContent.unorderedLists;
        return this;
    }

    @Override
    void writeHTML(PrintStream out) {
        out.printf("<p>\n");
        out.printf("%s\n", content);
        for (UnorderedList ul : unorderedLists){
            ul.writeHTML(out);
        }
        out.printf("</p>\n");
    }
}
