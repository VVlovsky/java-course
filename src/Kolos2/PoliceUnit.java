package Kolos2;

import lab6.CSVReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PoliceUnit {
    static List<PoliceUnit> units = new ArrayList<>();
    static String filepath = "/home/vvlovsky/Work/JavaProjects/java-classes/src/Kolos2/policja.csv";

    int id;
    int id_gl;
    String nazwa;
    String rodzaj;
    String kod;
    String miasto;
    String ulica;
    int kier;
    int tel;
    int fax;
    String internet;
    String mail;

    public PoliceUnit(int id, int id_gl, String nazwa, String rodzaj, String kod, String miasto, String ulica, int kier, int tel, int fax, String internet, String mail) {
        this.id = id;
        this.id_gl = id_gl;
        this.nazwa = nazwa;
        this.rodzaj = rodzaj;
        this.kod = kod;
        this.miasto = miasto;
        this.ulica = ulica;
        this.kier = kier;
        this.tel = tel;
        this.fax = fax;
        this.internet = internet;
        this.mail = mail;
    }

    static void read() throws IOException {
        CSVReader csvr = new CSVReader(filepath, ";", true);
        while (csvr.next()) {

            units.add(new PoliceUnit(csvr.getInt(0), csvr.getInt(1), csvr.get(2), csvr.get(3), csvr.get(4),
                    csvr.get(5), csvr.get(6), csvr.getInt(7), csvr.getInt(8), csvr.getInt(9), csvr.get(10), csvr.get(11)));
        }

    }

    public static void main(String[] args) throws IOException {
        read();

        units.stream()
                .sorted(Comparator.comparing(policeUnit -> policeUnit.ulica))
                .sorted(Comparator.comparing(policeUnit -> policeUnit.miasto))
                .forEach(policeUnit -> System.out.println(policeUnit.nazwa + policeUnit.miasto + policeUnit.ulica));

        List<PoliceUnit> withWww = units.stream()
                .filter(policeUnit -> policeUnit.internet.matches("^www.*"))
                .sorted(Comparator.comparing(policeUnit -> policeUnit.miasto)).collect(Collectors.toList());

//        withWww.forEach(policeUnit -> System.out.println(policeUnit.miasto));

        units.stream()
                .filter(policeUnit -> policeUnit.fax == 0)
                .forEach(policeUnit -> System.out.println(policeUnit.nazwa));

        Map<String, Integer> rodzajCount = new HashMap<>();
        units.forEach(policeUnit -> {
            if (rodzajCount.get(policeUnit.rodzaj) == null) {
                rodzajCount.put(policeUnit.rodzaj, 1);
            } else {
                rodzajCount.replace(policeUnit.rodzaj, rodzajCount.get(policeUnit.rodzaj) + 1);
            }

        });
        rodzajCount.forEach((String k, Integer v)-> System.out.printf("%s: %d\n", k, v));

    }

}
