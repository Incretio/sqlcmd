package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExecuteCommandPerform implements Performable {
    private final String outputText = "Пользовательский запрос выполнен.";
    private final String tempDelimiter = "_&_";
    private final String originalDelimiter = " ";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String queryText = params.get(0).replace(tempDelimiter, originalDelimiter);

        String result;
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(queryText);
            result = outputText;
        }

        return result;
    }
}
