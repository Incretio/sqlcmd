package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.data.JDBCConnectableFactory;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.List;

public class ConnectCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String serverName = params.get(0);
        String dbName = params.get(1);
        String userName = params.get(2);
        String password = params.get(3);

        String result = "";
        if (connectionConfig.isConnected()) {
            connectionConfig.testAndGetConnection().close();
        }
        try {
            connectionConfig.setConnection(
                    JDBCConnectableFactory.makeJdbcConnection(
                            connectionConfig.getJDBCConnectionType(),
                            serverName, dbName).getConnection(userName, password));
            result = "Вы успешно подключились к базе данных " + dbName;
        } catch (ClassNotFoundException e) {
            result = "Ошибка подключения драйвера";
        }

        return result;
    }

}