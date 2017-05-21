package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.ResultSetTableFormatter;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.*;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class FindPerform implements Performable {

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
                result = String.format(takeCaption("tableEmptyPattern"), tableName);
            }
        }
        return result;
    }
}
