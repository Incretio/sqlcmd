package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;

import java.sql.Statement;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.*;

public class ClearPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        new TableExistsPerform().perform(connectionConfig, params);

        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            int tableNameInd = 0;
            String tableName = params.get(tableNameInd);
            statement.execute(connectionConfig.getQueryable().takeDeleteAllRecordsQuery(tableName));
            result = String.format(takeCaption("tableCleared"), tableName);
        }

        return result;
    }
}
