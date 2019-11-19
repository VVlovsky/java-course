package lab6;

import java.io.IOException;
import java.util.Locale;

public class TestCSV {

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("/home/vvlovsky/Work/JavaProjects/JavaLabs/src/lab6/with-header.csv", ";", true);
        while (reader.next()) {
            int id = reader.getInt("id");
            String name = reader.get("nazwisko");
            System.out.printf("%d %s", id, name);
            System.out.println();
        }


    }


}
