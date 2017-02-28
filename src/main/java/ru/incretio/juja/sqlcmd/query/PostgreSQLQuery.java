package ru.incretio.juja.sqlcmd.query;

import java.util.List;

/**
 * Created by incre on 20.02.2017.
 */
public class PostgreSQLQuery implements Querable {

    @Override
    public String getSelectQuery(String tableName) {
        return "SELECT * FROM public.\"" + tableName + "\"";
    }

    @Override
    public String getInsertQuery(String tableName, List<String> columns, List<String> values) {
        String columnsString = "";
        String valuesString = "";
        for (int i = 0; i < columns.size(); i++) {
            columnsString += columns.get(i) + ", ";
            valuesString += "'" + values.get(i) + "'" + ", ";
        }
        columnsString = columnsString.substring(0, columnsString.length() - 2);
        valuesString = valuesString.substring(0, valuesString.length() - 2);
        return "INSERT INTO \"" + tableName + "\"" +
                " (" + columnsString + ") " +
                " VALUES (" + valuesString + ")";
    }

    @Override
    public String getUpdateQuery(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue) {
        return "UPDATE \"" + tableName + "\"" +
                " SET " + setColumnName + "=" + "'" + setColumnValue + "'" +
                " WHERE " + whereColumnName + "=" + "'" + whereColumnValue + "'";
    }

    @Override
    public String getDeleteQuery(String tableName, String whereColumn, String whereValue) {
        return "DELETE FROM \"" + tableName + "\"" +
                " WHERE " + whereColumn + "=" + "'" + whereValue + "'";
    }

    @Override
    public String getDeleteAllRecordsQuery(String tableName) {
        return "DELETE FROM public.\"" + tableName + "\"";
    }

    @Override
    public String getSelectTablesQuery() {
        return "select table_name\n" +
                "from information_schema.tables\n" +
                "where table_schema='public'";
    }

    @Override
    public String getCreateTableQuery(String tableName, List<String> columns) {
        String columnsString = "";
        for (String column : columns) {
            columnsString += column + " varchar(20),";
        }
        columnsString = columnsString.substring(0, columnsString.length() - 1);
        return "CREATE TABLE " + tableName + " ( " + columnsString + ");";
    }

    @Override
    public String getDropTableQuery(String tableName) {
        return "DROP TABLE \"" + tableName + "\"";
    }
}