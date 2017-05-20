package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;

import java.sql.Statement;
import java.util.List;

public class ClearPerform implements Performable {
    private final static String OUTPUT_TEXT = "Таблица %s очищена.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        new TableExistsPerform().perform(connectionConfig, params);

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
