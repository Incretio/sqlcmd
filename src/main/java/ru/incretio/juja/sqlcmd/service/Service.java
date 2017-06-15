package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.conroller.utils.HelpCommand;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<String> commandsList();

    void connect(String serverName, String dbName, String userName, String password) throws SQLException, MissingConnectionException, ClassNotFoundException;

    void closeConnection() throws MissingConnectionException, SQLException;

    void createDB(String dbName) throws MissingConnectionException, SQLException;

    void dropDB(String dbName) throws MissingConnectionException, SQLException;

    List<String> takeTablesList() throws MissingConnectionException, SQLException;

    void createTable(String tableName, List<String> columns) throws MissingConnectionException, SQLException;

    void insert(String tableName, List<String> columns, List<String> values)
            throws MissingConnectionException, SQLException;

    void update(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue)
            throws MissingConnectionException, SQLException;

    void delete(String tableName, String whereColumnName, String whereColumnValue) throws MissingConnectionException, SQLException;

    List<List<String>> select(String tableName) throws Exception;

    void clear(String tableName) throws MissingConnectionException, SQLException;

    void dropTable(String tableName) throws MissingConnectionException, SQLException;

    HelpCommand help();
}
