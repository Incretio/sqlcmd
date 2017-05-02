package ru.incretio.juja.sqlcmd.command.perform;


import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateCommandPerform implements Performable {
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String tableName = params.get(0);
        List<String> columns = params.subList(1, params.size());

        String result = "";

        List<String> newParams = new ArrayList<>();
        newParams.add(tableName);
        try {
            new TableExistCommandPerform().perform(connectionConfig, newParams);
            result = "Таблица " + tableName + " уже существует.";
        } catch (MissingTableException e) {
            try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
                statement.execute(connectionConfig.getQuerable().getCreateTableQuery(tableName, columns));
                result = "Таблица " + tableName + " добавлена.";
            }
        }

        return result;
    }
}
