package lab7;

import lab6.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnitList {
    String filepath = "/home/vvlovsky/Work/JavaProjects/JavaLabs/src/lab7/admin-units.csv";
    List<AdminUnit> units = new ArrayList<>();

    public void read() throws IOException {
        CSVReader reader = new CSVReader(filepath, ",", true);
        int counter = 0;
        while (reader.next()) {
            units.add(new AdminUnit(reader.get("name"), reader.getInt("admin_level"),
                    reader.getDouble("population"), (double) reader.getDouble("area"),
                    reader.getDouble("density"), reader.getDouble("x1"), reader.getDouble("y1"),
                    reader.getDouble("x2"), reader.getDouble("y2"), reader.getDouble("x3"),
                    reader.getDouble("y3"), reader.getDouble("x4"), reader.getDouble("y4")));
            System.out.println(counter);
            counter++;
        }
    }

    void list(PrintStream out) {
        for (AdminUnit au : units) {
            System.out.println(au.toString());
        }

    }

}
