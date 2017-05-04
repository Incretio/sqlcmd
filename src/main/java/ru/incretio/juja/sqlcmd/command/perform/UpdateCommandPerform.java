package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UpdateCommandPerform implements Performable {
    private final String OUT_PUT_TEXT = "В таблице %s обновлена запись.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        String tableName = params.get(0);
        String whereColumnName = params.get(1);
        String whereColumnValue = params.get(2);
        String setColumnName = params.get(3);
        String setColumnValue = params.get(4);

        CommandPerformHelper commandPerformHelper = new CommandPerformHelper(connectionConfig);
        commandPerformHelper.checkTableExist(tableName);
        commandPerformHelper.checkColumnExist(tableName, whereColumnName, setColumnName);

        String result;
        try (Statement statement = connectionConfig.testAndGetConnection().createStatement()) {
            try {
                statement.execute(connectionConfig.getQuerable().getUpdateQuery(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue));
                result = String.format(OUT_PUT_TEXT);
            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }
}