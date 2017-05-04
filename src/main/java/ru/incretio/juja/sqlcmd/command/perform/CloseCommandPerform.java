package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.List;

public class CloseCommandPerform implements Performable {
    private final String OUT_PUT_TEXT = "Отключились от БД.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        connectionConfig.testAndGetConnection().close();
        connectionConfig.setConnection(null);

        return OUT_PUT_TEXT;
    }
}