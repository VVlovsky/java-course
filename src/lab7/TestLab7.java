package lab7;

import java.io.IOException;

public class TestLab7 {
    public static void main(String[] args) throws IOException {
        AdminUnitList aul = new AdminUnitList();
        aul.read();
        aul.list(System.out);
    }
}
