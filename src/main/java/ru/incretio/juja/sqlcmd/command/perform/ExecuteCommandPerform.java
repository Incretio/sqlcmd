package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by incre on 02.03.2017.
 */
public class ExecuteCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String queryText = params.get(0).replace("_&_", " ");

        String result = "";
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            statement.execute(queryText);
            result = "Пользовательский запрос выполнен.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
