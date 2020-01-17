package lab6;

import java.io.IOException;
import java.util.Locale;

public class TestCSV {

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("/home/vvlovsky/Work/JavaProjects/JavaLabs/src/lab6/csv_examples/missing-values.csv", ";", true);
        while (reader.next()) {
            int id = reader.getInt("population");
//            String name = reader.get("nazwisko");
            System.out.printf("%d", id);
            System.out.println();
        }


    }


}
