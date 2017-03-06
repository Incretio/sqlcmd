package ru.incretio.juja.sqlcmd.command.perform;


import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CreateCommandPerform implements Performable {
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String tableName = params.get(0);
        List<String> columns = params.subList(1, params.size());

        String result = "";

        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            statement.execute(connectionConfig.getQuerable().getCreateTableQuery(tableName, columns));
            result = "Таблица " + tableName + " добавлена.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
