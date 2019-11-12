package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String title) {
        this.title = title;
    }


    Document setTitle(String title) {
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl) {
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle) {
        Section newSection = new Section(sectionTitle) {
            @Override
            Section addParagraph(String paragraphText) {
                return super.addParagraph(paragraphText);
            }
        };
        sections.add(newSection);
        return newSection;
    }

    Document addSection(Section s) {
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out) {
        out.printf("<!DOCTYPE html>\n" +
                "<html lang=\"pl\">");
        out.printf("<head>\n" + "    <meta charset=\"UTF-8\">\n" +
                "    <title>%s</title>\n" +
                "</head>\n", title);
        out.printf("<body>\n");
        out.printf("<div>\n");
        out.printf("<h1>%s<h1>\n", this.title);
        photo.writeHTML(out);
        for (Section s : sections) {
            s.writeHTML(out);
        }
        out.printf("</div>\n");
        out.printf("</body>\n");
    }
}
