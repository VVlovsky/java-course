package lab7;

import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<>();

    AdminUnit(String name, int adminLevel, double population, double area, double density) {
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
    }

    AdminUnit(String name, int adminLevel, double population, double area, double density, double x1, double y1,
              double x2, double y2, double x3, double y3, double x4, double y4) {
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
        bbox.addPoint(x1, y1);
        bbox.addPoint(x2, y2);
        bbox.addPoint(x3, y3);
        bbox.addPoint(x4, y4);
    }

    public AdminUnit() {
        adminLevel = 0;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("name: " + name + "\npopulation: " + population + "\narea: " + area + "\ndensity: " + density + "\n");
        b.append(bbox.toString());
        b.append("To map:\n");
        b.append("LINESTRING(");
        b.append(bbox.xmin + " " + bbox.ymin + ", " + bbox.xmin + " " + bbox.ymax + ", " + bbox.xmax + " " + bbox.ymax + ", " + bbox.xmax + " " + bbox.ymin + ", " + bbox.xmin + " " + bbox.ymin + ")\n\n");
        return b.toString();
    }

    public void fixMissingValues() {
        if (density == 0) {
            if (parent.density != 0) {
                density = parent.density;
            } else {
                parent.fixMissingValues();
            }
        }
        if (population == 0) {
            population = area * density;
        }
    }
}