package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.ResultSetTableFormatter;
import ru.incretio.juja.sqlcmd.conroller.utils.HelpCommand;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.query.QueryFactory;
import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ServiceImpl implements Service {

    private final Model model;

    public ServiceImpl() {
        model = new Model(QueryFactory.makePostgreSQLQuery());
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList(
                "connect", "closeConnection",
                "takeTablesList", "createTable", "insert", "update", "delete", "select", "clear", "dropTable",
                "help");
    }

    @Override
    public void connect(String serverName, String dbName, String userName, String password) throws SQLException, MissingConnectionException, ClassNotFoundException {
        model.connect(serverName, dbName, userName, password);
    }

    @Override
    public void closeConnection() throws MissingConnectionException, SQLException {
        model.closeConnection();
    }

    @Override
    public void createDB(String dbName) throws MissingConnectionException, SQLException {
        model.executeCreateDB(dbName);
    }

    @Override
    public void dropDB(String dbName) throws MissingConnectionException, SQLException {
        model.executeDropDB(dbName);
    }

    @Override
    public List<String> takeTablesList() throws MissingConnectionException, SQLException {
        return model.takeTablesList();
    }

    @Override
    public void createTable(String tableName, List<String> columns) throws MissingConnectionException, SQLException {
        model.executeCreateTable(tableName, columns);
    }

    @Override
    public void insert(String tableName, List<String> columns, List<String> values)
            throws MissingConnectionException, SQLException {
        model.executeInsertRecord(tableName, columns, values);
    }

    @Override
    public void update(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue)
            throws MissingConnectionException, SQLException {
        model.executeUpdateRecords(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
    }

    @Override
    public void delete(String tableName, String whereColumnName, String whereColumnValue)
            throws MissingConnectionException, SQLException {
        model.executeDeleteRecords(tableName, whereColumnName, whereColumnValue);
    }

    @Override
    public List<List<String>> select(String tableName) throws Exception {
        new MissingTableHelper(model)
                .throwExceptionIfTableNotExist(tableName);

        ResultSetTableFormatter resultSetTableFormatter = new ResultSetTableFormatter();
        Consumer<ResultSet> resultSetConsumer = resultSet -> {
            try {
                resultSetTableFormatter.setResultSet(resultSet);
            } catch (SQLException e) {
                AppLogger.warning(e);
            }
        };

        model.find(resultSetConsumer, tableName);

        if (resultSetTableFormatter.getColumnsNames() != null &&
                !resultSetTableFormatter.getColumnsNames().isEmpty()) {
            List<List<String>> table = resultSetTableFormatter.getData();
            List<String> columnsNames = resultSetTableFormatter.getColumnsNames();
            table.add(0, columnsNames);
            return table;
        }

        return Collections.emptyList();
    }

    @Override
    public void clear(String tableName) throws MissingConnectionException, SQLException {
        model.executeClearTable(tableName);
    }

    @Override
    public void dropTable(String tableName) throws MissingConnectionException, SQLException {
        model.executeDropTable(tableName);
    }

    @Override
    public HelpCommand getHelp(){
        return new HelpCommand();
    }

}
