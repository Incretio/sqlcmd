package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;
import java.util.List;

public class ExitCommandPerform implements Performable {
    private final String dbDisconnectedText = "Отключились от БД. ";
    private final String closeText = "Программа будет закрыта.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {

        String result = "";
        if (connectionConfig.isConnected()) {
            connectionConfig.testAndGetConnection().close();
            connectionConfig.setConnection(null);
            result = dbDisconnectedText;
        }

        result += closeText;
        throw new NeedExitException(result);
    }
}