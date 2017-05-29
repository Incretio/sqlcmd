package ru.incretio.juja.sqlcmd.view;

import ru.incretio.juja.sqlcmd.exceptions.IncorrectDataTableFormatterException;
import ru.incretio.juja.sqlcmd.exceptions.EmptyColumnsNamesTableFormatterException;
import ru.incretio.juja.sqlcmd.exceptions.EmptyDataTableFormatterException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableFormatter {
    private final List<List<String>> data;
    private final List<String> columnsNames;
    private List<Integer> columnsWidth;
    private final int columnsCount;

    public TableFormatter(List<List<String>> data, List<String> columnsNames)
            throws EmptyDataTableFormatterException, EmptyColumnsNamesTableFormatterException, IncorrectDataTableFormatterException {
        throwExceptionIfDataEmptyOrIncorrect(data, columnsNames);
        this.data = data;
        this.columnsNames = columnsNames;
        this.columnsCount = columnsNames.size();
        fillColumnsWidth(data, columnsNames);
    }

    private void throwExceptionIfDataEmptyOrIncorrect(List<List<String>> data, List<String> columnsNames) throws EmptyDataTableFormatterException, EmptyColumnsNamesTableFormatterException, IncorrectDataTableFormatterException {
        if (data == null) {
            throw new EmptyDataTableFormatterException();
        }
        if (columnsNames == null || columnsNames.size() == 0) {
            throw new EmptyColumnsNamesTableFormatterException();
        }
        int firstColumnInd = 0;
        if (data.size() > 0 &&
                (data.get(firstColumnInd).size() != columnsNames.size())) {
            throw new IncorrectDataTableFormatterException();
        }
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

    private void fillColumnsWidth(List<List<String>> data, List<String> columns) {
        Integer[] maxLengthColumnArr = new Integer[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            maxLengthColumnArr[i] = columns.get(i).length();
        }
        for (List<String> line : data) {
            for (int j = 0; j < line.size(); j++) {
                int curLength = line.get(j).length();
                if (curLength > maxLengthColumnArr[j]) {
                    maxLengthColumnArr[j] = curLength;
                }
            }
        }
        List<Integer> columnsWidth = Arrays.asList(maxLengthColumnArr);
        columnsWidth = columnsWidth.stream().map(value -> value + 1).collect(Collectors.toList());
        this.columnsWidth = columnsWidth;
    }
}
