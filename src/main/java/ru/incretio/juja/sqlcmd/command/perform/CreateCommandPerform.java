package ru.incretio.juja.sqlcmd.command.perform;


import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CreateCommandPerform implements Command {
    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        String tableName = params.get(0);
        String fields = "";
        for (String param : params) {
            fields += param + " varchar(100),";
        }
        fields = fields.substring(0, fields.length() - 1);

        Statement statement = connection.createStatement();
        String result = "";
        try {
            statement.execute("CREATE TABLE " + tableName + " ( " + fields + ");");
            result = "Таблица " + tableName + " добавлена.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
