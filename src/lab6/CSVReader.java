package lab6;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;


    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();
    String[] current;

    public CSVReader(String filename, String delimiter, boolean hasHeader, boolean needToParse) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.delimiter = delimiter;
        if (needToParse) this.delimiter = String.format("%s(?=([^\"]|\"[^\"]*\")*$)", delimiter);
        this.hasHeader = hasHeader;


        if (hasHeader) {
            try {
                parseHeader();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) {
        this(filename, delimiter, hasHeader, false);
    }

    public CSVReader(String filename, String delimiter) {
        this(filename, delimiter, false);
    }

    public CSVReader(String filename) {
        this(filename, ";");
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
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
        try {
            int i = columnLabelsToInt.get(colName);
            return Integer.parseInt(current[i]);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getInt(int i) {
        try {
            return Integer.parseInt(current[i]);
        } catch (Exception e) {
            return 0;
        }
    }

    public double getDouble(String colName) {
        try {
            int i = columnLabelsToInt.get(colName);
            return Double.parseDouble(current[i]);
        } catch (Exception e) {
            return 0;
        }
    }

    public double getDouble(int index) {
        try {
            return Double.parseDouble(current[index]);
        } catch (Exception e) {
            return 0;
        }
    }

    public long getLong(String colName) {
        try {
            return Long.parseLong(current[columnLabelsToInt.get(colName)]);
        } catch (Exception e) {
            return 0;
        }
    }

    public long getLong(int i) {
        try {
            return Long.parseLong(current[i]);
        } catch (Exception e) {
            return 0;
        }
    }


    public String get(int index) {
        try {
            return current[index];
        } catch (Exception e) {
            return "";
        }
    }

    public String get(String column) {
        return current[columnLabelsToInt.get(column)];
    }


    public LocalTime getTime(int index, String format) {
        return LocalTime.parse(get(index), DateTimeFormatter.ofPattern(format));
    }


    public LocalTime getTime(String column, String format) {
        return LocalTime.parse(get(column), DateTimeFormatter.ofPattern(format));
    }


    public LocalDate getDate(int index, String format) {
        return LocalDate.parse(get(index), DateTimeFormatter.ofPattern(format));
    }


    public LocalDate getDate(String column, String format) {
        return LocalDate.parse(get(column), DateTimeFormatter.ofPattern(format));
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