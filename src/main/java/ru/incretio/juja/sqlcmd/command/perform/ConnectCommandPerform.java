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
        int serverNameInd = 0;
        int dbNameInd = 1;
        int userNameInd = 2;
        int passwordInd = 3;
        String serverName = params.get(serverNameInd);
        String dbName = params.get(dbNameInd);
        String userName = params.get(userNameInd);
        String password = params.get(passwordInd);

        if (connectionConfig.isConnected()) {
            connectionConfig.closeConnection();
        }

        String result;
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
