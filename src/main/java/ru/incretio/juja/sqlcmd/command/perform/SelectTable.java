package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.ResultSetFormatter;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SelectTable implements Performable {
    private final static String EMPTY_DATA = "В таблице %s отсутствуют данные.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);

        new TableExists().perform(connectionConfig, params);

        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(connectionConfig.getQueryable().takeSelectQuery(tableName));

            result = new ResultSetFormatter(resultSet).getFormattedTable();

            if (result.trim().isEmpty()) {
                result = String.format(EMPTY_DATA, tableName);
            }
        }

        return result;
    }


}
