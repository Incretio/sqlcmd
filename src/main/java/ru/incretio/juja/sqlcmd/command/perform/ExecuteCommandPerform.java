package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExecuteCommandPerform implements Performable {
    private final String OUT_PUT_TEXT = "Пользовательский запрос выполнен.";
    private final String TEMP_DELIMITER = "_&_";
    private final String ORIGINAL_DELIMITER = " ";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String queryText = params.get(0).replace(TEMP_DELIMITER, ORIGINAL_DELIMITER);

        String result = "";
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(queryText);
            result = OUT_PUT_TEXT;
        }

        return result;
    }
}
