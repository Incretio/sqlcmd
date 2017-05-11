package ru.incretio.juja.sqlcmd.query;

import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;
import java.util.List;

public interface Querable {
    JDBCConnectionType getJdbcConnectionType();
    String getSelectQuery(String tableName);
    String getInsertQuery(String tableName, List<String> columns, List<String> values);
    String getUpdateQuery(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue);
    String getDeleteQuery(String tableName, String whereColumn, String whereValue);
    String getDeleteAllRecordsQuery(String tableName);
    String getSelectTablesQuery();
    String getSelectTableColumnsQuery(String tableName);
    String getCreateTableQuery(String tableName, List<String> columns);
    String getDropTableQuery(String tableName);
    String getDropDBQuery(String dbName);
    String getCreateDBQuery(String dbName);
}
