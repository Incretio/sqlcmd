package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.*;

public class CreatePerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);
        List<String> columns;
        if (params.size() > 1) {
            columns = params.subList(1, params.size());
        } else {
            columns = Collections.emptyList();
        }

        String result;
        try {
            List<String> newParams = Collections.singletonList(tableName);
            new TableExistsPerform().perform(connectionConfig, newParams);
            result = String.format(takeCaption("tableAlreadyExistsPattern"), tableName);
        } catch (MissingTableException e) {
            try (Statement statement = connectionConfig.getConnection().createStatement()) {
                statement.execute(connectionConfig.getQueryable().takeCreateTableQuery(tableName, columns));
                result = String.format(takeCaption("tableAddedPattern"), tableName);
            }
        }

        return result;
    }
}
