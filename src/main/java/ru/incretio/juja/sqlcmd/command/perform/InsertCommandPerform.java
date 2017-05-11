package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertCommandPerform implements Performable {
    private final static String OUT_PUT_TEXT = "В таблицу %s добавлена запись.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        String tableName = params.get(0);

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (int i = 1; i < params.size(); i += 2) {
            columns.add(params.get(i));
            values.add(params.get(i + 1));
        }

        CommandPerformHelper commandPerformHelper = new CommandPerformHelper(connectionConfig);
        commandPerformHelper.checkTableExist(tableName);
        commandPerformHelper.checkColumnExist(tableName, columns);

        String result;
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQueryable().getInsertQuery(tableName, columns, values));
            result = String.format(OUT_PUT_TEXT, tableName);
        }

        return result;
    }
}
