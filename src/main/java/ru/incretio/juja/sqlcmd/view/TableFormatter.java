package ru.incretio.juja.sqlcmd.view;

import java.util.List;

public class TableFormatter {
    private final List<List<String>> data;
    private final List<String> columnsNames;
    private final List<Integer> columnsWidth;
    private final int columnsCount;

    public TableFormatter(List<List<String>> data, List<String> columnsNames, List<Integer> columnsWidth) {
        this.data = data;
        this.columnsNames = columnsNames;
        this.columnsWidth = columnsWidth;
        this.columnsCount = columnsNames.size();
    }

    public String getFormattedTable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(getSeparatorLine()).append(System.lineSeparator())
                .append(getColumnsTitleLine()).append(System.lineSeparator())
                .append(getSeparatorLine()).append(System.lineSeparator())
                .append(getDataLines())
                .append(getSeparatorLine()).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private String getColumnsTitleLine() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < columnsCount; i++) {
            String value = columnsNames.get(i);
            int width = columnsWidth.get(i) - value.length();
            String pattern = String.format("+ %%s%%%ds", width); // Example: "+ %s%1s+"
            result.append(String.format(pattern, value, ""));
        }
        result.append("+");
        return result.toString();
    }

    private String getSeparatorLine() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < columnsCount; i++) {
            String pattern = String.format("+-%%%ds", columnsWidth.get(i)); // Example: "+-%1s"
            result.append(String.format(pattern, "").replace(" ", "-"));
        }
        result.append("+");
        return result.toString();
    }

    private String getDataLines() {
        StringBuilder result = new StringBuilder();
        for (List<String> line : data) {
            for (int i = 0; i < line.size(); i++) {
                String value = (line.get(i) == null) ? "" : line.get(i);
                int valueLength = value.length();
                String pattern = String.format("+ %%s%%%ds", (columnsWidth.get(i) - valueLength)); // Example: "+ %s%1s"
                result.append(String.format(pattern, value, ""));
            }
            result.append("+".concat(System.lineSeparator()));
        }
        return result.toString();
    }
}
