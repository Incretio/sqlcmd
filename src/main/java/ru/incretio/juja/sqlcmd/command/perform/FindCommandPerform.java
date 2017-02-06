package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FindCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        Statement statement = connection.createStatement();
        String tableName = params.get(0);
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM \"" + tableName + "\"");
        String result = "";
        while (resultSet.next()) {
            result += resultSet.getString(1) + "\n";
        }
        return result;
    }

}
