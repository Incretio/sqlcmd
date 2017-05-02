package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;

import java.sql.SQLException;
import java.util.List;

public class ExitCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {

        String result = "";
        if (connectionConfig.isConnected()) {
            connectionConfig.testAndGetConnection().close();
            connectionConfig.setConnection(null);
            result = "Отключились от БД. ";
        }

        result += "Программа будет закрыта.";
        throw new NeedExitException(result);
    }
}