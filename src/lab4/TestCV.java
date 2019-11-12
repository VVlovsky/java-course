package lab4;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class TestCV {
    public static void main(String[] args) throws IOException {

        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("http://images2.fanpop.com/image/photos/10900000/HOVER-CAT-random-10957136-640-445.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        cv.writeHTML(new PrintStream("cv.html", StandardCharsets.UTF_8));
    }
}