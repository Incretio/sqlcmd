package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DropDBCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "База данных %s удалена.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        int dbNameInd = 0;
        String dbName = params.get(dbNameInd);

        String result;
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQueryable().takeDropDBQuery(dbName));
            result = String.format(OUTPUT_TEXT, dbName);
        }

        return result;
    }
}
