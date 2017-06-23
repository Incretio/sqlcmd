package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.conroller.utils.HelpCommand;

import java.util.List;

public interface Service {
    List<String> commandsList();

    void connect(String serverName, String dbName, String userName, String password) throws ServiceException;

    void closeConnection() throws ServiceException;

    void createDB(String dbName) throws ServiceException;

    void dropDB(String dbName) throws ServiceException;

    List<String> takeTablesList() throws ServiceException;

    void createTable(String tableName, List<String> columns) throws ServiceException;

    void insert(String tableName, List<String> columns, List<String> values) throws ServiceException;

    void update(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue)
            throws ServiceException;

    void delete(String tableName, String whereColumnName, String whereColumnValue) throws ServiceException;

    List<List<String>> select(String tableName) throws ServiceException;

    void clear(String tableName) throws ServiceException;

    void dropTable(String tableName) throws ServiceException;

    HelpCommand getHelp();
}
