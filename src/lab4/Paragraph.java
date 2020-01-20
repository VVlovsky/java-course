package lab4;

import java.io.PrintStream;

public class Paragraph {

    Paragraph(String content) {
        this.setContent(content);
    }

    Paragraph() {
    }

    String content;

    Paragraph setContent(String newContent) {
        this.content = newContent;
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<p>\n");
        out.printf("%s\n", content);
        out.printf("</p>\n");
    }
}
