package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.data.JDBCConnectable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ConnectPerform implements Performable {

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
                    new JDBCConnectable(
                            connectionConfig.getQueryable().getJdbcConnectionType(),
                            serverName, dbName).getConnection(userName, password));
            result = String.format(takeCaption("connectionSuccessPattern"), dbName);
        } catch (ClassNotFoundException e) {
            result = takeCaption("driverLoadingErrorText");
        }

        return result;
    }

}
