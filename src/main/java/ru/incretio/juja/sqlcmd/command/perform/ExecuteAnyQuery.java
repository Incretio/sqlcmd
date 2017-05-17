package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExecuteAnyQuery implements Performable {
    private final static String OUTPUT_TEXT = "Пользовательский запрос выполнен.";
    private final static String TEMP_DELIMITER = "_&_";
    private final static String ORIGINAL_DELIMITER = " ";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        int queryTextInd = 0;
        String queryText = params.get(queryTextInd).replace(TEMP_DELIMITER, ORIGINAL_DELIMITER);

        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            statement.execute(queryText);
            result = OUTPUT_TEXT;
        }

        return result;
    }
}
