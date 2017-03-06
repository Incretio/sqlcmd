package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String tableName = params.get(0);
        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (int i = 1; i < params.size(); i += 2) {
            columns.add(params.get(i));
            values.add(params.get(i + 1));
        }

        String result = "";
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            try {
                statement.execute(connectionConfig.getQuerable().getInsertQuery(tableName, columns, values));
                result = "В таблицу " + tableName + " добавлена запись.";
            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }
}
