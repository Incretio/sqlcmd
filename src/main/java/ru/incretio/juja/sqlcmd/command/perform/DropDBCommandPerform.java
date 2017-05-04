package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DropDBCommandPerform implements Performable {
    private final String OUT_PUT_TEXT = "База данных %s удалена.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String dbName = params.get(0);

        String result;
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQuerable().getDropDBQuery(dbName));
            result = String.format(OUT_PUT_TEXT, dbName);
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
