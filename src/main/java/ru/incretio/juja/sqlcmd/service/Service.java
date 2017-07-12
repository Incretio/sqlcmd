package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.model.UserAction;
import ru.incretio.juja.sqlcmd.service.exceptions.ServiceException;

import java.util.List;

public interface Service {
    List<String> commandsList();

    String connect(String serverName, String dbName, String userName, String password) throws ServiceException;

    String closeConnection() throws ServiceException;

    void createDB(String dbName) throws ServiceException;

    void dropDB(String dbName) throws ServiceException;

    List<String> takeTablesList() throws ServiceException;

    String createTable(String tableName, List<String> columns) throws ServiceException;

    String insert(String tableName, List<String> columns, List<String> values) throws ServiceException;

    String update(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue)
            throws ServiceException;

    String delete(String tableName, String whereColumnName, String whereColumnValue) throws ServiceException;

    List<List<String>> select(String tableName) throws ServiceException;

    String clear(String tableName) throws ServiceException;

    String dropTable(String tableName) throws ServiceException;

    String help();

    List<UserAction> getAllFor(String userName);

    List<UserAction> getAllForCurrentUser();
}
