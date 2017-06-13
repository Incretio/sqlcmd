package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.ResultSetTableFormatter;
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

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ServiceImpl implements Service {

    private Model model;

    public ServiceImpl() {
        model = new Model(QueryFactory.makePostgreSQLQuery());
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect", "find");
    }

    @Override
    public void connect(String serverName, String dbName, String userName, String password) throws SQLException, MissingConnectionException, ClassNotFoundException {
        model.connect(serverName, dbName, userName, password);
    }

    @Override
    public List<List<String>> find(String tableName) throws Exception {
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
            List<List<String>> data = resultSetTableFormatter.getData();
            List<String> columnsNames = resultSetTableFormatter.getColumnsNames();
            List<List<String>> table = data;
            table.add(0, columnsNames);
            return data;
//            view.writeSelectTable(data, columnsNames);
        } else

        {
//            view.write(String.format(takeCaption("tableEmptyPattern"), tableName));
        }

        return Collections.emptyList();
    }
}
