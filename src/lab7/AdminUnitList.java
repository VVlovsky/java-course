package lab7;

import lab6.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList {
    String filepath = "/home/vvlovsky/Work/JavaProjects/java-university-classes/src/lab7/admin-units.csv";
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> id2AdminUnit = new HashMap<>();
    Map<AdminUnit, Long> adminUnit2ParentId = new HashMap<>();
    Map<Long, List<AdminUnit>> parentid2child = new HashMap<>();
    AdminUnit root = new AdminUnit();


    AdminUnitList(List<AdminUnit> lst) {
        units = lst;
    }

    public AdminUnitList() {

    }

    AdminUnitList(AdminUnit au) {
        this.units.add(au);
    }

    public void read() throws IOException {
        CSVReader reader = new CSVReader(filepath, ",", true);
        int counter = 0;
        parentid2child.put(Long.MIN_VALUE, new AdminUnitList().units);

        while (reader.next()) {
            units.add(new AdminUnit(reader.get("name"), reader.getInt("admin_level"),
                    reader.getDouble("population"), (double) reader.getDouble("area"),
                    reader.getDouble("density"), reader.getDouble("x1"), reader.getDouble("y1"),
                    reader.getDouble("x2"), reader.getDouble("y2"), reader.getDouble("x3"),
                    reader.getDouble("y3"), reader.getDouble("x4"), reader.getDouble("y4")));
            System.out.println(counter);
            counter++;
            id2AdminUnit.put(reader.getLong("id"), units.get(units.size() - 1));
            if (reader.getLong("parent") != 0) {
                adminUnit2ParentId.put(units.get(units.size() - 1), reader.getLong("parent"));
            } else {
                System.out.println("test");
            }
        }
        id2AdminUnit.put(Long.MIN_VALUE, root);
        for (AdminUnit au : units) {
            au.parent = id2AdminUnit.get(adminUnit2ParentId.get(au));
            if (au.parent == null) {
                System.out.println("test");
                au.parent = root;
//                parentid2child.get(Long.MIN_VALUE).add(au);
                root.children.add(au);
            }
        }
        for (AdminUnit au : units) {
            au.fixMissingValues();
        }


        adminUnit2ParentId.forEach((au, parId) -> {
            if (!parentid2child.containsKey(parId)) {
                parentid2child.put(parId, new AdminUnitList(au).units);
            }
            parentid2child.get(parId).add(au);
            id2AdminUnit.get(parId).children.add(au);
        });

    }

    void list(PrintStream out) {
        for (AdminUnit au : units) {
            System.out.println(au.toString());
        }

    }

    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        if (regex) {
            for (AdminUnit au : units) {
                if (au.name.matches(pattern)) {
                    ret.units.add(au);
                }
            }
        } else {
            for (AdminUnit au : units) {
                if (au.name.contains(pattern)) {
                    ret.units.add(au);
                }
            }
        }

        return ret;

    }

    AdminUnitList getNeighbours(AdminUnit unit, double maxDistance) {
        AdminUnit current;
        List<AdminUnit> potentialNeighbours = new ArrayList<>();

        potentialNeighbours.add(root);

        while (true) {
            current = potentialNeighbours.remove(0);
            if (current.adminLevel < unit.adminLevel) {
                potentialNeighbours.addAll(current.children);
            }

            if (potentialNeighbours.isEmpty()) {
                break;
            }

            if (potentialNeighbours.get(0).adminLevel >= unit.adminLevel) {
                break;
            }
        }

        AdminUnitList aul = new AdminUnitList();
        for (AdminUnit pn : potentialNeighbours) {
            if (pn.bbox.distanceTo(unit.bbox) <= maxDistance) {
                aul.units.add(pn);
            }
        }
        return aul;
    }


    AdminUnitList sortInplaceByName() {
        class NameComparator implements Comparator<AdminUnit> {
            @Override
            public int compare(AdminUnit a, AdminUnit b) {
                return a.name.compareTo(b.name);
            }
        }
        units.sort(new NameComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea() {
        units.sort(
                new Comparator<AdminUnit>() {
                    @Override
                    public int compare(AdminUnit a, AdminUnit b) {
                        return Double.compare(a.area, b.area);
                    }
                });
        return this;
    }

    AdminUnitList sortInplaceByPopulation() {
        units.sort((a, b) -> Double.compare(a.population, b.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        AdminUnitList aul = new AdminUnitList();
        aul.units.addAll(this.units);
        return aul.sortInplace(cmp);
    }

    /**
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList aul = new AdminUnitList();
        this.units.forEach(au -> {
            if (pred.test(au)) aul.units.add(au);
        });
        return aul;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        AdminUnitList aul = new AdminUnitList();
        int counter = 0;
        for (AdminUnit au : this.units) {
            if (counter > limit) break;
            if (pred.test(au)) aul.units.add(au);
            counter++;

        }
        return aul;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit, int offset) {
        AdminUnitList aul = new AdminUnitList();
        int counter = 0;
        for (AdminUnit au : this.units) {
            if (counter - offset > limit) break;
            if (pred.test(au) && counter >= offset) aul.units.add(au);
            counter++;

        }
        return aul;
    }

}