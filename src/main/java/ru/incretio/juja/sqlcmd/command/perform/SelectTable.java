package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.*;
import java.util.List;

public class SelectTable implements Performable {
    private final static String EMPTY_DATA = "В таблице %s отсутствуют данные.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);

        new TableExists().perform(connectionConfig, params);

        String result = "";
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(connectionConfig.getQueryable().takeSelectQuery(tableName));
            ResultSetMetaData metaData = resultSet.getMetaData();

            for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                result += String.format("+-%" + (metaData.getColumnDisplaySize(i)) + "s", "").replace(" ", "-");
            }
            result += "+\n";
            for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                result += String.format("+ %s%" + (metaData.getColumnDisplaySize(i) - metaData.getColumnLabel(i).length()) + "s", metaData.getColumnLabel(i), "");
            }
            result += "+\n";
            for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                result += String.format("+-%" + (metaData.getColumnDisplaySize(i)) + "s", "").replace(" ", "-");
            }
            result += "+\n";

            while (resultSet.next()) {
                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    int columnSize = metaData.getColumnDisplaySize(i);
                    int valueLength = (resultSet.getString(i) == null) ? 0 : resultSet.getString(i).length();
                    String value = (resultSet.getString(i) == null) ? "" : resultSet.getString(i);
                    result += String.format("+ %s%" + (columnSize - valueLength) + "s", value, "");
                }
                result += "+\n";
            }

            for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                result += String.format("+-%" + (metaData.getColumnDisplaySize(i)) + "s", "").replace(" ", "-");
            }
            result += "+\n";


            result = (result.trim().isEmpty()) ? String.format(EMPTY_DATA, tableName) : result;
        }

        return result;
    }

}