package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;

import java.sql.SQLException;
import java.util.List;

public class ExitCommandPerform implements Performable {
    private final String DB_DISCONNECTED_TEXT = "Отключились от БД. ";
    private final String APP_WILL_BE_CLOSE = "Программа будет закрыта.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {

        String result = "";
        if (connectionConfig.isConnected()) {
            connectionConfig.testAndGetConnection().close();
            connectionConfig.setConnection(null);
            result = DB_DISCONNECTED_TEXT;
        }

        result += APP_WILL_BE_CLOSE;
        throw new NeedExitException(result);
    }
}