package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CreateDBCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "База данных %s добавлена.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {

        String dbName = params.get(0);

        String result;
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQuerable().getCreateDBQuery(dbName));
            result = String.format(OUTPUT_TEXT, dbName);
        }

        return result;
    }
}
