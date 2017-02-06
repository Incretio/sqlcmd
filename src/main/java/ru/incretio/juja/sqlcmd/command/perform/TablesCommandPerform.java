package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TablesCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select table_name\n" +
                        "from information_schema.tables\n" +
                        "where table_schema='public'");
        String result = "";
        while (resultSet.next()) {
            result += resultSet.getString(1) + "\n";
        }
        return result;
    }
}
