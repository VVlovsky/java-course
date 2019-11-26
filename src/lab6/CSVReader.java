package lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();
    String[] current;

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }


    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }


    public boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        String[] tmpCurrent = line.split(delimiter);
        int addCounter = 0;
        while (tmpCurrent.length + addCounter <= columnLabels.size()) {
            addCounter++;
        }
//        int addCounter = columnLabels.size() - tmpCurrent.length;
        List<String> tmp = new ArrayList<String>(Arrays.asList(tmpCurrent));
        for (int i = 0; i < addCounter; i++){
            tmp.add("");
        }
        current = tmp.toArray(new String[tmp.size()]);
//        current = line.split(delimiter);
        return true;
    }

    public int getInt(String colName) {
        int i = columnLabelsToInt.get(colName);
        if (current[i].equals("")) {
            return 0;
        }
        return Integer.parseInt(current[i]);
    }

    public double getDouble(String colName) {
        int i = columnLabelsToInt.get(colName);
        if (current[i].equals("")) {
            return 0;
        }
        return Double.parseDouble(current[i]);
    }

    public String get(int index) {
        return current[index];

    }

    public String get(String column) {
        return current[columnLabelsToInt.get(column)];
    }
}