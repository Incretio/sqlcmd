package ru.incretio.juja.sqlcmd.model.query;

import ru.incretio.juja.sqlcmd.model.data.JDBCConnectionType;

import java.util.List;

public interface Queryable {
    JDBCConnectionType getJdbcConnectionType();
    String takeSelectQuery(String tableName);
    String takeInsertQuery(String tableName, List<String> columns, List<String> values);
    String takeUpdateQuery(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue);
    String takeDeleteQuery(String tableName, String whereColumn, String whereValue);
    String takeDeleteAllRecordsQuery(String tableName);
    String takeSelectTablesQuery();
    String takeSelectTableColumnsQuery(String tableName);
    String takeCreateTableQuery(String tableName, List<String> columns);
    String takeDropTableQuery(String tableName);
    String takeDropDBQuery(String dbName);
    String takeCreateDBQuery(String dbName);
}
