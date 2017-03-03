package ru.incretio.juja.sqlcmd.query;

import java.util.List;

/**
 * Created by incre on 20.02.2017.
 */
public interface Querable {
    String getSelectQuery(String tableName);
    String getInsertQuery(String tableName, List<String> columns, List<String> values);
    String getUpdateQuery(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue);
    String getDeleteQuery(String tableName, String whereColumn, String whereValue);
    String getDeleteAllRecordsQuery(String tableName);
    String getSelectTablesQuery();
    String getCreateTableQuery(String tableName, List<String> columns);
    String getDropTableQuery(String tableName);
    String getDropDBQuery(String dbName);
    String getCreateDBQuery(String dbName);
}
