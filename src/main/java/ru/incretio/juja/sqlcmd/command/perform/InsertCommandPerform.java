package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        String tableName = params.get(0);
        String fields = "";
        String values = "";
        for (int i = 1; i < params.size(); i += 2) {
            fields += params.get(i) + ", ";
            values += "'" + params.get(i + 1) + "'" + ", ";
        }
        fields = fields.substring(0, fields.length() - 2);
        values = values.substring(0, values.length() - 2);

        Statement statement = connection.createStatement();
        String result = "";
        try {
            statement.execute(
                    "INSERT INTO \"" + tableName + "\"" +
                            " (" + fields + ") " +
                            " VALUES (" + values + ")");
            result = "В таблицу " + tableName + " добавлена запись.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
