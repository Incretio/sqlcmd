package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import java.sql.Statement;
import java.util.List;

public class DeleteCommandPerform implements Performable {
    private final String outputText = "Из таблицы %s удалена запись.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        String tableName = params.get(0);
        String whereColumnName = params.get(1);
        String whereColumnValue = params.get(2);
        String result;

        CommandPerformHelper commandPerformHelper = new CommandPerformHelper(connectionConfig);
        commandPerformHelper.checkTableExist(tableName);
        commandPerformHelper.checkColumnExist(tableName, whereColumnName);

        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            statement.execute(connectionConfig.getQuerable().getDeleteQuery(tableName, whereColumnName, whereColumnValue));
            result = String.format(outputText, tableName);
        }

        return result;
    }
}
