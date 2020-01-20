package lab6;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

public class TestCSV {

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("/home/vvlovsky/Work/JavaProjects/java-university-classes/src/lab6/csv_examples/titanic-part.csv", ",", true, true);
        while (reader.next()) {
            String id = reader.get("Name");
            System.out.printf("%s", id);
            System.out.println();
        }

        String text = "a,b,c\n123.4,567.8,91011.12";
        reader = new CSVReader(new StringReader(text), ",", true);
        while (reader.next()) {
            double id = reader.getDouble("b");
            System.out.printf("%f", id);
            System.out.println();
        }
    }


}
