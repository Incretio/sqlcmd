package ru.incretio.juja.sqlcmd.command.perform.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import ru.incretio.juja.sqlcmd.view.TableFormatter;

public class ResultSetTableFormatter {
    private final ResultSet resultSet;
    private final int firstColumnInd = 1;
    private int lastColumnInd;
    private ResultSetMetaData metaData;

    public ResultSetTableFormatter(ResultSet resultSet) throws SQLException {
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
        TableFormatter tableFormatter =  new TableFormatter(data, columnsNames, columnsWidth);
        return tableFormatter.getFormattedTable();
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
        return columnsWidth;
    }
}
