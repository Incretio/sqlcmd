package ru.incretio.juja.sqlcmd.model;

import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.model.data.JDBCConnectable;
import ru.incretio.juja.sqlcmd.model.query.Queryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Model {
    private Connection connection;
    private final Queryable queryable;

    public Model(Queryable queryable) {
        this.queryable = queryable;
    }


    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() throws MissingConnectionException {
        if (!isConnected()) {
            throw new MissingConnectionException();
        }
        return connection;
    }

    public void closeConnection() throws MissingConnectionException, SQLException {
        getConnection().close();
        connection = null;
    }

    public void executeCreateDB(String dbName) throws SQLException, MissingConnectionException {
        execute(queryable.takeCreateDBQuery(dbName));
    }

    public void executeClearTable(String tableName) throws SQLException, MissingConnectionException {
        execute(queryable.takeDeleteAllRecordsQuery(tableName));
    }

    public void execute(String query) throws SQLException, MissingConnectionException {
        throwExceptionIfConnectionClose();
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public void connect(String serverName, String dbName, String userName, String password) throws MissingConnectionException, SQLException, ClassNotFoundException {
        if (isConnected()) {
            closeConnection();
        }

        JDBCConnectable jdbcConnectable = new JDBCConnectable(
                queryable.getJdbcConnectionType(), serverName, dbName);
        setConnection(jdbcConnectable.getConnection(userName, password));
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
        List<String> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        }
        return result;
    }

    public void find(Consumer<ResultSet> resultSetConsumer, String tableName) throws SQLException, MissingConnectionException {
        throwExceptionIfConnectionClose();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queryable.takeSelectQuery(tableName));
            resultSetConsumer.accept(resultSet);
        }
    }

    public void executeUpdateRecords(String tableName,
                                     String whereColumnName, String whereColumnValue,
                                     String setColumnName, String setColumnValue) throws SQLException, MissingConnectionException {
        execute(queryable.takeUpdateQuery(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue));
    }

    private void throwExceptionIfConnectionClose() throws MissingConnectionException {
        if (!isConnected()){
            throw new MissingConnectionException();
        }
    }
}

