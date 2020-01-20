package lab7;

import java.io.IOException;
import java.util.Comparator;

public class TestLab7 {
    public static void main(String[] args) throws IOException {
        AdminUnitList aul = new AdminUnitList();
        aul.read();
        //        aul.list(System.out);
//        aul.selectByName(".*mal.*", true).list(System.out);
//        aul.filter(a -> a.name.startsWith("Ż")).sortInplaceByArea().list(System.out);
//        aul.getNeighbours(aul.units.get(10), 25).units.forEach(au -> {
//            System.out.println(au.toString());
//        });
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(aul)
                .where(a -> a.area > 10)
                .or(a -> a.name.startsWith("Sz"))
                .sort((a, b) -> Double.compare(a.area, b.area))
                .limit(100);
        query.execute().list(System.out);
    }
}
