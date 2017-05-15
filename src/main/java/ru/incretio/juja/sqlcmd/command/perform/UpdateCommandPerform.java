package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import java.sql.Statement;
import java.util.List;

public class UpdateCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "В таблице %s обновлена запись.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        int tableNameInd = 0;
        int whereColumnNameInd = 1;
        int whereColumnValueInd = 2;
        int setColumnNameInd = 3;
        int setColumnValueInd = 4;
        String tableName = params.get(tableNameInd);
        String whereColumnName = params.get(whereColumnNameInd);
        String whereColumnValue = params.get(whereColumnValueInd);
        String setColumnName = params.get(setColumnNameInd);
        String setColumnValue = params.get(setColumnValueInd);

        CommandPerformHelper commandPerformHelper = new CommandPerformHelper(connectionConfig);
        commandPerformHelper.checkTableExist(tableName);
        commandPerformHelper.checkColumnExist(tableName, whereColumnName, setColumnName);

        String result;
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQueryable().takeUpdateQuery(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue));
            result = String.format(OUTPUT_TEXT, tableName);
        }

        return result;
    }
}