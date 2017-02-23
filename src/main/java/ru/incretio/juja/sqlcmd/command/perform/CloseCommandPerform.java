package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;

import java.sql.SQLException;
import java.util.List;

public class CloseCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        connectionConfig.getConnection().close();
        connectionConfig.setConnection(null);

        return "Отключились от БД.";
    }
}