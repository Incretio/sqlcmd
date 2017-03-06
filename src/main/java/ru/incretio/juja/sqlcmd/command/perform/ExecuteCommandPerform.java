package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by incre on 02.03.2017.
 */
public class ExecuteCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String queryText = params.get(0).replace("_&_", " ");

        String result = "";
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(queryText);
            result = "Пользовательский запрос выполнен.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
