package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DropCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "Таблица %s удалена.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);
        String result;

        new TableExistCommandPerform().perform(connectionConfig, params);

        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQueryable().getDropTableQuery(tableName));
            result = String.format(OUTPUT_TEXT, tableName);
        }

        return result;
    }
}