package ru.incretio.juja.sqlcmd.command.perform.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResultSetFormatter {
    private final ResultSet resultSet;
    private int firstColumnInd = 1;
    private int lastColumnInd;
    private ResultSetMetaData metaData;

    public ResultSetFormatter(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        initMetaData();
        initLastColumnInd();
    }

    private void initMetaData() throws SQLException {
        metaData = resultSet.getMetaData();
    }

    private void initLastColumnInd() throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        lastColumnInd = metaData.getColumnCount();
    }

    public String getFormattedTable() throws SQLException {
        List<List<String>> data = getData();
        List<String> columnsNames = getColumnsNames();
        List<Integer> columnsWidth = getColumnsWidth(data, columnsNames);

        return getFormattedTable(data, columnsNames, columnsWidth);
    }

    private List<List<String>> getData() throws SQLException {
        List<List<String>> result = new ArrayList<>();

        while (resultSet.next()) {
            List<String> line = new ArrayList<>();

            for (int i = firstColumnInd; i <= lastColumnInd; i++) {
                String value = resultSet.getString(i);
                value = (value == null) ? "" : value;

                line.add(value);
            }
            result.add(line);
        }

        return result;
    }

    private List<String> getColumnsNames() throws SQLException {
        List<String> columnsNames = new ArrayList<>();
        for (int i = firstColumnInd; i <= lastColumnInd; i++) {
            columnsNames.add(metaData.getColumnLabel(i));
        }

        return columnsNames;
    }

    private List<Integer> getColumnsWidth(List<List<String>> data, List<String> columns) {
        Integer[] maxLengthColumnArr = new Integer[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            maxLengthColumnArr[i] = columns.get(i).length();
        }
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                int curLength = data.get(i).get(j).length();
                if (curLength > maxLengthColumnArr[j]) {
                    maxLengthColumnArr[j] = curLength;
                }
            }
        }
        List<Integer> columnsWidth = Arrays.asList(maxLengthColumnArr);
        columnsWidth = columnsWidth.stream().map(value -> value + 1).collect(Collectors.toList());
        return columnsWidth;
    }

    private String getFormattedTable(List<List<String>> data, List<String> columns, List<Integer> columnsWidth) throws SQLException {
        StringBuilder result = new StringBuilder();

        result.append(getSeparatorLine(columns.size(), columnsWidth))
                .append("+\n")
                .append(getColumnsTitleLine(columns, columnsWidth).concat("\n"))
                .append(getSeparatorLine(columns.size(), columnsWidth).concat("+\n"))
                .append(getDataLines(data, columnsWidth))
                .append(getSeparatorLine(columns.size(), columnsWidth).concat("+\n"));

        return result.toString();
    }

    private String getColumnsTitleLine(List<String> columns, List<Integer> columnsWidth) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            int width = columnsWidth.get(i) - columns.get(i).length();
            String pattern = String.format("+ %%s%%%ds", width); // Example: "+ %s%1s+"
            result.append(String.format(pattern, columns.get(i), ""));
        }
        result.append("+");
        return result.toString();
    }

    private String getSeparatorLine(int columnCount, List<Integer> columnsWidth) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < columnCount; i++) {
            String pattern = String.format("+-%%%ds", columnsWidth.get(i)); // Example: "+-%1s"
            result.append(String.format(pattern, "").replace(" ", "-"));
        }
        return result.toString();
    }

    private String getDataLines(List<List<String>> data, List<Integer> columnsWidth) {
        StringBuilder result = new StringBuilder();
        for (List<String> line : data) {
            for (int i = 0; i < line.size(); i++) {
                int valueLength = (line.get(i) == null) ? 0 : line.get(i).length();
                String value = (valueLength == 0) ? "" : line.get(i);
                String pattern = String.format("+ %%s%%%ds", (columnsWidth.get(i) - valueLength)); // Example: "+ %s%1s"
                result.append(String.format(pattern, value, ""));
            }
            result.append("+\n");
        }
        return result.toString();
    }
}
