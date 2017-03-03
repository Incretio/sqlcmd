package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by incre on 03.03.2017.
 */
public class CreateDBCommandPerform implements Performable {
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String dbName = params.get(0);

        Statement statement = connectionConfig.getConnection().createStatement();
        String result = "";
        try {
            statement.execute(connectionConfig.getQuerable().getCreateDBQuery(dbName));
            result = "База данных " + dbName + " добавлена.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
