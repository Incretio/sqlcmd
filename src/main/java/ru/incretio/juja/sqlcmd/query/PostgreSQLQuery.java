package ru.incretio.juja.sqlcmd.query;

import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;
import java.util.List;

public class PostgreSQLQuery implements Queryable {
    private final JDBCConnectionType jdbcConnectionType = JDBCConnectionType.PostgreSQL;

    @Override
    public String takeSelectQuery(String tableName) {
        return "SELECT * FROM public.\"" + tableName + "\"";
    }

    @Override
    public String takeInsertQuery(String tableName, List<String> columns, List<String> values) {
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
    public String takeUpdateQuery(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue) {
        return "UPDATE \"" + tableName + "\"" +
                " SET " + setColumnName + "=" + "'" + setColumnValue + "'" +
                " WHERE " + whereColumnName + "=" + "'" + whereColumnValue + "'";
    }

    @Override
    public String takeDeleteQuery(String tableName, String whereColumn, String whereValue) {
        return "DELETE FROM \"" + tableName + "\"" +
                " WHERE " + whereColumn + "=" + "'" + whereValue + "'";
    }

    @Override
    public String takeDeleteAllRecordsQuery(String tableName) {
        return "DELETE FROM public.\"" + tableName + "\"";
    }

    @Override
    public String takeSelectTablesQuery() {
        return "SELECT table_name\n" +
                "FROM information_schema.tables\n" +
                "WHERE table_schema = 'public'\n" +
                "ORDER BY table_name";
    }

    @Override
    public String takeSelectTableColumnsQuery(String tableName) {
        return "SELECT column_name\n" +
                "FROM information_schema.columns\n" +
                "WHERE table_schema='public' AND table_name='"+ tableName + "'";
    }

    @Override
    public String takeCreateTableQuery(String tableName, List<String> columns) {
        String columnsString = "";
        for (String column : columns) {
            columnsString += column + " varchar(20),";
        }
        columnsString = columnsString.substring(0, columnsString.length() - 1);
        return "CREATE TABLE " + "\"" + tableName + "\"" + " ( " + columnsString + ");";
    }

    @Override
    public String takeDropTableQuery(String tableName) {
        return "DROP TABLE \"" + tableName + "\"";
    }

    @Override
    public String takeDropDBQuery(String dbName) {
        return "DROP DATABASE IF EXISTS " + dbName;
    }

    @Override
    public String takeCreateDBQuery(String dbName) {
        return "CREATE DATABASE " + dbName + "\n" +
                "  WITH OWNER = postgres\n" +
                "       ENCODING = 'UTF8'\n" +
                "       TABLESPACE = pg_default\n" +
                "       LC_COLLATE = 'Russian_Russia.1251'\n" +
                "       LC_CTYPE = 'Russian_Russia.1251'\n" +
                "       CONNECTION LIMIT = -1;";
    }

    @Override
    public JDBCConnectionType getJdbcConnectionType() {
        return jdbcConnectionType;
    }


}
