package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExitCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String result = "";
        if (connectionConfig.isConnected()) {
            connectionConfig.getConnection().close();
            connectionConfig.setConnection(null);
            result = "Отключились от БД. ";
        }

        return result + "Программа будет закрыта.";
    }
}