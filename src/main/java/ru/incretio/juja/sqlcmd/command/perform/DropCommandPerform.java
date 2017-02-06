package ru.incretio.juja.sqlcmd.command.perform;


import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DropCommandPerform implements Command {
    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        String tableName = params.get(0);
        Statement statement = connection.createStatement();
        String result = "";
        try {
            statement.execute("DROP TABLE \"" + tableName + "\"");
            result = "Таблица " + tableName + " удалена.";
        } catch (SQLException e){
            result = e.getMessage();
        }

        return result;
    }
}
