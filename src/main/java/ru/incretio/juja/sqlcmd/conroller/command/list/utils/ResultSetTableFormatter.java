package ru.incretio.juja.sqlcmd.conroller.command.list.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetTableFormatter {
    private ResultSet resultSet;
    private final int firstColumnIndex = 1;
    private int lastColumnIndex;
    private ResultSetMetaData metaData;
    private List<List<String>> data;
    private List<String> columnsNames;

    public void setResultSet(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        initAll();
    }

    private void initAll() throws SQLException {
        initMetaData();
        initLastColumnIndex();
        fillData();
        fillColumnsNames();
    }


    private void initMetaData() throws SQLException {
        metaData = resultSet.getMetaData();
    }

    private void initLastColumnIndex() throws SQLException {
        lastColumnIndex = metaData.getColumnCount();
    }

    private void fillData() throws SQLException {
        List<List<String>> data = new ArrayList<>();

        while (resultSet.next()) {
            List<String> line = new ArrayList<>();

            for (int i = firstColumnIndex; i <= lastColumnIndex; i++) {
                String value = resultSet.getString(i);
                value = (value == null) ? "" : value;

                line.add(value);
            }
            data.add(line);
        }

        this.data = data;
    }

    private void fillColumnsNames() throws SQLException {
        List<String> columnsNames = new ArrayList<>();
        for (int i = firstColumnIndex; i <= lastColumnIndex; i++) {
            columnsNames.add(metaData.getColumnLabel(i));
        }

        this.columnsNames = columnsNames;
    }

    public List<List<String>> getData() {
        return data;
    }

    public List<String> getColumnsNames() {
        return columnsNames;
    }

}
