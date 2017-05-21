package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ExecutePerform implements Performable {
    private final static String TEMP_DELIMITER = "_&_";
    private final static String ORIGINAL_DELIMITER = " ";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        int queryTextInd = 0;
        // ToDo: Для чего тут replace? он скорее всего лишний.
        String queryText = params.get(queryTextInd).replace(TEMP_DELIMITER, ORIGINAL_DELIMITER);

        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            statement.execute(queryText);
        }

        return takeCaption("queryExecuted");
    }
}
