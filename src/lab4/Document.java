package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String title){
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
        Section newSection = new Section(sectionTitle){
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
        out.printf("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        out.printf("<!DOCTYPE html\n" +
                "        PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
        out.printf("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"pl\" lang=\"pl\">\n");
        out.printf("<head>\n" +
                "    <title>%s</title>\n" +
                "</head>\n", title);
        out.printf("<body>\n");
        out.printf("<div>\n");
        for (Section s : sections){
            s.writeHTML(out);
        }
        out.printf("</div>\n");
        out.printf("</body>\n");
    }
}
