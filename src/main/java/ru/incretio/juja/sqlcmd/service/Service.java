package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<String> commandsList();

    void connect(String serverName, String dbName, String userName, String password) throws SQLException, MissingConnectionException, ClassNotFoundException;

    List<List<String>> find(String tableName) throws Exception;
}
