package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateCommandPerform implements Performable {
    private final static String TABLE_EXIST_TEXT = "Таблица %s уже существует.";
    private final static String TABLE_ADDED_TEXT = "Таблица %s добавлена.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);
        List<String> columns = Collections.emptyList();
        if (params.size() > 1) {
            columns = params.subList(1, params.size());
        }

        String result;

        List<String> newParams = new ArrayList<>();
        newParams.add(tableName);
        try {
            new TableExistCommandPerform().perform(connectionConfig, newParams);
            result = String.format(TABLE_EXIST_TEXT, tableName);
        } catch (MissingTableException e) {
            try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
                statement.execute(connectionConfig.getQueryable().takeCreateTableQuery(tableName, columns));
                result = String.format(TABLE_ADDED_TEXT, tableName);
            }
        }

        return result;
    }
}
