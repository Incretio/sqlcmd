package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UpdateCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        String tableName = params.get(0);
        String whereColumnName = params.get(1);
        String whereColumnValue = params.get(2);
        String setColumnName = params.get(3);
        String setColumnValue = params.get(4);


        Statement statement = connection.createStatement();
        String result = "";
        try {
            statement.execute(
                    "UPDATE \"" + tableName + "\"" +
                            " SET " + setColumnName + "=" + "'" + setColumnValue + "'" +
                            " WHERE " + whereColumnName + "=" + "'" + whereColumnValue + "'");
            result = "В таблице " + tableName + " обновлена запись.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}