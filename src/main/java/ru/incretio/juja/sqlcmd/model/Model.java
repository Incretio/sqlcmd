package ru.incretio.juja.sqlcmd.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.model.data.JDBCConnectable;
import ru.incretio.juja.sqlcmd.model.query.Queryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private JdbcTemplate jdbcTemplate;
    private Queryable queryable;

    public void setQueryable(Queryable queryable) {
        this.queryable = queryable;
    }

    public boolean isConnected() {
        try {
            return getObjectConnection() != null && !getObjectConnection().isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    private Connection getObjectConnection() throws SQLException {
        if (jdbcTemplate != null && jdbcTemplate.getDataSource() != null) {
            return jdbcTemplate.getDataSource().getConnection();
        } else {
            return null;
        }
    }

    private Connection getConnection() throws MissingConnectionException, SQLException {
        if (!isConnected()) {
            throw new MissingConnectionException();
        }
        return getObjectConnection();
    }

    public void closeConnection() throws MissingConnectionException, SQLException {
        getConnection().close();
    }

    public void executeCreateDB(String dbName) throws SQLException, MissingConnectionException {
        execute(queryable.takeCreateDBQuery(dbName));
    }

    public void executeClearTable(String tableName) throws SQLException, MissingConnectionException {
        execute(queryable.takeDeleteAllRecordsQuery(tableName));
    }

    public void execute(String query) throws SQLException, MissingConnectionException {
        throwExceptionIfConnectionClose();
        jdbcTemplate.execute(query);
    }

    public void connect(String serverName, String dbName, String userName, String password) throws MissingConnectionException, SQLException, ClassNotFoundException {
        if (isConnected()) {
            closeConnection();
        }

        JDBCConnectable jdbcConnectable = new JDBCConnectable(
                queryable.getJdbcConnectionType(), serverName, dbName);
        Connection connection = jdbcConnectable.getConnection(userName, password);
        jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
    }

    public void executeCreateTable(String tableName, List<String> columns) throws SQLException, MissingConnectionException {
        execute(queryable.takeCreateTableQuery(tableName, columns));
    }

    public void executeDeleteRecords(String tableName, String whereColumnName, String whereColumnValue) throws SQLException, MissingConnectionException {
        execute(queryable.takeDeleteQuery(tableName, whereColumnName, whereColumnValue));
    }

    public void executeDropDB(String dbName) throws SQLException, MissingConnectionException {
        execute(queryable.takeDropDBQuery(dbName));
    }

    public void executeDropTable(String tableName) throws SQLException, MissingConnectionException {
        execute(queryable.takeDropTableQuery(tableName));
    }

    public void executeInsertRecord(String tableName, List<String> columns, List<String> values) throws SQLException, MissingConnectionException {
        execute(queryable.takeInsertQuery(tableName, columns, values));
    }

    public List<String> takeTablesList() throws SQLException, MissingConnectionException {
        return takeDataFromFirstColumn(queryable.takeSelectTablesQuery());
    }

    public List<String> takeTableColumns(String tableName) throws SQLException, MissingConnectionException {
        return takeDataFromFirstColumn(queryable.takeSelectTableColumnsQuery(tableName));
    }

    private List<String> takeDataFromFirstColumn(String query) throws SQLException, MissingConnectionException {
        throwExceptionIfConnectionClose();
        return jdbcTemplate.query(query, (resultSet, i) -> resultSet.getString(1));
    }

    public List<List<String>> find(String tableName) throws SQLException, MissingConnectionException {
        throwExceptionIfConnectionClose();
        List<String> columns = jdbcTemplate.query(
                queryable.takeSelectQuery(tableName),
                this::getColumnsNames);

        List<List<String>> result = jdbcTemplate.query(
                queryable.takeSelectQuery(tableName),
                (resultSet, i) -> getLineFromData(resultSet));

        result.add(0, columns);

        return result;
    }

    private List<String> getColumnsNames(ResultSet resultSet) throws SQLException {
        int firstColumnIndex = 1;
        int lastColumnIndex = resultSet.getMetaData().getColumnCount();

        List<String> columnsNames = new ArrayList<>();
        for (int i = firstColumnIndex; i <= lastColumnIndex; i++) {
            columnsNames.add(resultSet.getMetaData().getColumnLabel(i));
        }

        return columnsNames;
    }

    private List<String> getLineFromData(ResultSet resultSet) throws SQLException {
        List<String> line = new ArrayList<>();

        int firstColumnIndex = 1;
        int lastColumnIndex = resultSet.getMetaData().getColumnCount();
        for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
            String value = resultSet.getString(columnIndex);
            value = (value == null) ? "" : value;

            line.add(value);
        }

        return line;
    }

    public void executeUpdateRecords(String tableName,
                                     String whereColumnName, String whereColumnValue,
                                     String setColumnName, String setColumnValue) throws SQLException, MissingConnectionException {
        execute(queryable.takeUpdateQuery(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue));
    }

    private void throwExceptionIfConnectionClose() throws MissingConnectionException {
        if (!isConnected()) {
            throw new MissingConnectionException();
        }
    }
}

