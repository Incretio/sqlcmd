package ru.incretio.juja.sqlcmd.model.query;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.incretio.juja.sqlcmd.model.data.JDBCConnectionType;

import java.util.List;

import static java.lang.String.format;

@Component
public class PostgreSQLQuery implements Queryable {
    private final JDBCConnectionType jdbcConnectionType = JDBCConnectionType.PostgreSQL;
    private final static String SELECT_QUERY_PATTERN = "SELECT * FROM public.\"%s\" ORDER BY 1";
    private final static String INSERT_QUERY_PATTERN = "INSERT INTO \"%s\" (%s) VALUES (%s)";
    private final static String UPDATE_QUERY_PATTERN = "UPDATE \"%s\" SET %s='%s' WHERE %s='%s'";
    private final static String DELETE_QUERY_PATTERN = "DELETE FROM \"%s\" WHERE %s='%s'";
    private final static String DELETE_ALL_QUERY_PATTERN = "DELETE FROM public.\"%s\"";
    private final static String SELECT_TABLES_QUERY_PATTERN =
            "SELECT table_name " +
                    "FROM information_schema.tables " +
                    "WHERE table_schema = 'public' " +
                    "ORDER BY table_name";
    private final static String TABLE_COLUMNS_QUERY_PATTERN =
            "SELECT column_name " +
                    "FROM information_schema.columns " +
                    "WHERE table_schema='public' AND table_name='%s'";
    private final static String CREATE_TABLE_QUERY_PATTERN = "CREATE TABLE \"%s\" ( %s);";
    private final static String DROP_TABLE_PATTERN = "DROP TABLE \"%s\"";
    private final static String DROP_DB_PATTERN = "DROP DATABASE IF EXISTS %s";
    private final static String CREATE_DB_PATTERN =
            "CREATE DATABASE %s\n" +
                    "  WITH OWNER = postgres " +
                    "       ENCODING = 'UTF8' " +
                    "       TABLESPACE = pg_default " +
                    "       CONNECTION LIMIT = -1;";


    @Override
    public JDBCConnectionType getJdbcConnectionType() {
        return jdbcConnectionType;
    }

    @Override
    public String takeSelectQuery(String tableName) {
        return format(SELECT_QUERY_PATTERN, tableName);
    }

    @Override
    public String takeInsertQuery(String tableName, List<String> columns, List<String> values) {
        String columnsString = StringUtils.collectionToDelimitedString(columns, ", ");
        String valuesString = StringUtils.collectionToDelimitedString(values, ", ", "\'", "\'");

        return format(INSERT_QUERY_PATTERN, tableName, columnsString, valuesString);
    }

    @Override
    public String takeUpdateQuery(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue) {
        return format(UPDATE_QUERY_PATTERN, tableName,
                setColumnName, setColumnValue,
                whereColumnName, whereColumnValue);
    }

    @Override
    public String takeDeleteQuery(String tableName, String whereColumn, String whereValue) {
        return format(DELETE_QUERY_PATTERN, tableName, whereColumn, whereValue);
    }

    @Override
    public String takeDeleteAllRecordsQuery(String tableName) {
        return format(DELETE_ALL_QUERY_PATTERN, tableName);
    }

    @Override
    public String takeSelectTablesQuery() {
        return SELECT_TABLES_QUERY_PATTERN;
    }

    @Override
    public String takeSelectTableColumnsQuery(String tableName) {
        return format(TABLE_COLUMNS_QUERY_PATTERN, tableName);
    }

    @Override
    public String takeCreateTableQuery(String tableName, List<String> columns) {
        String columnsString = StringUtils.collectionToDelimitedString(columns, ", ", "", " varchar(20)");
        return format(CREATE_TABLE_QUERY_PATTERN, tableName, columnsString);
    }

    @Override
    public String takeDropTableQuery(String tableName) {
        return format(DROP_TABLE_PATTERN, tableName);
    }

    @Override
    public String takeDropDBQuery(String dbName) {
        return format(DROP_DB_PATTERN, dbName);
    }

    @Override
    public String takeCreateDBQuery(String dbName) {
        return format(CREATE_DB_PATTERN, dbName);
    }


}
