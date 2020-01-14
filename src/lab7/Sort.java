package lab7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sort implements Comparator<AdminUnit> {
    List<AdminUnit> listToSort = new ArrayList<>();

    Sort() {
    }

    Sort(List<AdminUnit> lts) {
        listToSort = lts;
    }

    AdminUnitList sortInplaceByName() {
        listToSort.sort(this);
        return new AdminUnitList(listToSort);
    }

    @Override
    public int compare(AdminUnit o1, AdminUnit o2) {
        return 0;
    }
}
