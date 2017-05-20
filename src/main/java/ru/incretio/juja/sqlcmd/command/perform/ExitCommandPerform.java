package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;
import java.util.List;

public class ExitCommandPerform implements Performable {
    private final static String DB_DISCONNECTED_TEXT = "Отключились от БД. ";
    private final static String CLOSE_TEXT = "Закрытие программы...";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        String result = "";
        if (connectionConfig.isConnected()) {
            connectionConfig.closeConnection();
            result = DB_DISCONNECTED_TEXT;
        }

        result += CLOSE_TEXT;
        throw new NeedExitException(result);
    }
}