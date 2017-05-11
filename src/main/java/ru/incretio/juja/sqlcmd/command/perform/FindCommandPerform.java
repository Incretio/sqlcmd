package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;
import java.sql.*;
import java.util.List;

public class FindCommandPerform implements Performable {
    private final String emptyData = "В таблице %s отсутствуют данные.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {

        String tableName = params.get(0);

        new TableExistCommandPerform().perform(connectionConfig, params);

        String result = "";
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(connectionConfig.getQuerable().getSelectQuery(tableName));
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


            result = (result.trim().isEmpty()) ? String.format(emptyData, tableName) : result;
        }

        return result;
    }

}
