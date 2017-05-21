package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import java.sql.Statement;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class DeletePerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        int tableNameInd = 0;
        int whereColumnNameInd = 1;
        int whereColumnValueInd = 2;
        String tableName = params.get(tableNameInd);
        String whereColumnName = params.get(whereColumnNameInd);
        String whereColumnValue = params.get(whereColumnValueInd);

        CommandPerformHelper commandPerformHelper = new CommandPerformHelper(connectionConfig);
        commandPerformHelper.checkTableExist(tableName);
        commandPerformHelper.checkColumnExist(tableName, whereColumnName);

        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            statement.execute(connectionConfig.getQueryable().takeDeleteQuery(tableName, whereColumnName, whereColumnValue));
            result = String.format(takeCaption("recordDeletedPattern"), tableName);
        }

        return result;
    }
}
