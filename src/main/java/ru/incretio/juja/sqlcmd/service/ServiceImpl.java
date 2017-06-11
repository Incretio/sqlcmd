package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.query.QueryFactory;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ServiceImpl implements Service {

    private Model model;

    public ServiceImpl() {
        model = new Model(QueryFactory.makePostgreSQLQuery());
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect");
    }

    @Override
    public void connect(String serverName, String dbName, String userName, String password) throws SQLException, MissingConnectionException, ClassNotFoundException {
        model.connect(serverName, dbName, userName, password);
    }
}
