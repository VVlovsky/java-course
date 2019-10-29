package lab4;

import java.io.PrintStream;

public class ListItem {
    String content;

    void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>\n", content);
    }
}
