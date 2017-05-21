package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ExitPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        String result = "";
        if (connectionConfig.isConnected()) {
            connectionConfig.closeConnection();
            result = takeCaption("dbDisconnected").concat(" ");
        }

        result += takeCaption("appClose");
        throw new NeedExitException(result);
    }
}