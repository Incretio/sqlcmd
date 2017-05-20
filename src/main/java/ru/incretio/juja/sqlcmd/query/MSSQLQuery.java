package ru.incretio.juja.sqlcmd.query;

import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;

import java.util.List;

public class MSSQLQuery implements Queryable {
    @Override
    public JDBCConnectionType getJdbcConnectionType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeSelectQuery(String tableName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeInsertQuery(String tableName, List<String> columns, List<String> values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeUpdateQuery(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeDeleteQuery(String tableName, String whereColumn, String whereValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeDeleteAllRecordsQuery(String tableName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeSelectTablesQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeSelectTableColumnsQuery(String tableName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeCreateTableQuery(String tableName, List<String> columns) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeDropTableQuery(String tableName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeDropDBQuery(String dbName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String takeCreateDBQuery(String dbName) {
        throw new UnsupportedOperationException();
    }
}
