package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class DropPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);

        new TableExistsPerform().perform(connectionConfig, params);

        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            statement.execute(connectionConfig.getQueryable().takeDropTableQuery(tableName));
            result = String.format(takeCaption("tableDeletedPattern"), tableName);
        }

        return result;
    }
}