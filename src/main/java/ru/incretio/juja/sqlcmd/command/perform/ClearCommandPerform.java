package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.CommandCheckFactory;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClearCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "Таблица %s очищена.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        new TableExistsCommandPerform().perform(connectionConfig, params);

        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            int tableNameInd = 0;
            String tableName = params.get(tableNameInd);
            statement.execute(connectionConfig.getQueryable().takeDeleteAllRecordsQuery(tableName));
            result = String.format(OUTPUT_TEXT, tableName);
        }

        return result;
    }
}
