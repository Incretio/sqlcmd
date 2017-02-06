package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.*;
import java.util.List;

public class FindCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        Statement statement = connection.createStatement();
        String tableName = params.get(0);
        String result = "";
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM public.\"" + tableName + "\"");
            ResultSetMetaData metaData = resultSet.getMetaData();
            result = "";
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                result += metaData.getColumnLabel(i + 1) + "\t";
            }
            result += "\n";
            while (resultSet.next()) {
                result += resultSet.getString(1) + "\t" + resultSet.getString(2) + "\n";
            }
            result = (result.trim().isEmpty()) ? "В таблице " + tableName + " отсутствуют данные." : result;
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }

}
