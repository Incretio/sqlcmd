package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import java.sql.Statement;
import java.util.List;

public class DeleteCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "Из таблицы %s удалена запись.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        int tableNameInd = 0;
        int whereColumnNameInd = 1;
        int whereColumnValueInd = 2;
        String tableName = params.get(tableNameInd);
        String whereColumnName = params.get(whereColumnNameInd);
        String whereColumnValue = params.get(whereColumnValueInd);
        String result;

        CommandPerformHelper commandPerformHelper = new CommandPerformHelper(connectionConfig);
        commandPerformHelper.checkTableExist(tableName);
        commandPerformHelper.checkColumnExist(tableName, whereColumnName);

        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQueryable().getDeleteQuery(tableName, whereColumnName, whereColumnValue));
            result = String.format(OUTPUT_TEXT, tableName);
        }

        return result;
    }
}
