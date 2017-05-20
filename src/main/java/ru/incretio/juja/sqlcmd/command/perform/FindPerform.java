package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.ResultSetTableFormatter;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.*;
import java.util.List;

public class FindPerform implements Performable {
    private final static String EMPTY_DATA = "В таблице %s отсутствуют данные.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);

        new TableExistsPerform().perform(connectionConfig, params);

        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(connectionConfig.getQueryable().takeSelectQuery(tableName));

            result = new ResultSetTableFormatter(resultSet).getFormattedTable();

            if (result.trim().isEmpty()) {
                result = String.format(EMPTY_DATA, tableName);
            }
        }

        return result;
    }


}
