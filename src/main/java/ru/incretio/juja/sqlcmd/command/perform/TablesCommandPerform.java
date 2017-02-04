package ru.incretio.sqlcmd.command.perform;

import ru.incretio.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public class TablesCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW TABLES");
        String result = "";
        while (resultSet.next()){
            result += resultSet.getString(1) + "\n";
        }
        return result;
    }
}
