package lab6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    public CSVReader(String filename, String delimiter, boolean hasHeader) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.delimiter = delimiter;
        this.hasHeader = hasHeader;

        if (hasHeader) {
            try {
                parseHeader();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public CSVReader(String filename, String delimiter) {
        this(filename, delimiter, false);
    }

    public CSVReader(String filename) {
        this(filename, ";");
    }


    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }

        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            header[i] = header[i].trim();
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }


    public boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }

        current = line.split(delimiter);
        for (int i = 0; i < current.length; i++) {
            current[i] = current[i].trim();
        }
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

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) {
        return columnIndex > current.length - 1 || current[columnIndex].equals("");
    }

    boolean isMissing(String columnLabel) {
        return current[columnLabelsToInt.get(columnLabel)].equals("");
    }
}