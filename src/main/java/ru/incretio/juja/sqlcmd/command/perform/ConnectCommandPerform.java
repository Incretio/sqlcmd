package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.data.JDBCConnectableFactory;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import java.sql.SQLException;
import java.util.List;

public class ConnectCommandPerform implements Performable {
    private final static String CONNECTION_SUCCESS_TEXT = "Вы успешно подключились к базе данных %s.";
    private final static String DRIVER_LOADING_ERROR_TEXT = "Ошибка подключения драйвера.";


    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String serverName = params.get(0);
        String dbName = params.get(1);
        String userName = params.get(2);
        String password = params.get(3);

        String result;
        if (connectionConfig.isConnected()) {
            connectionConfig.testAndGetConnection().close();
        }
        try {
            connectionConfig.setConnection(
                    JDBCConnectableFactory.makeJdbcConnection(
                            connectionConfig.getQueryable().getJdbcConnectionType(),
                            serverName, dbName).getConnection(userName, password));
            result = String.format(CONNECTION_SUCCESS_TEXT, dbName);
        } catch (ClassNotFoundException e) {
            result = DRIVER_LOADING_ERROR_TEXT;
        }

        return result;
    }

}
