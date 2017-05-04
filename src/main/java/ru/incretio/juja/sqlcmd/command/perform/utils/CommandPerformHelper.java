package ru.incretio.juja.sqlcmd.command.perform.utils;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.perform.ColumnExistCommandPerform;
import ru.incretio.juja.sqlcmd.command.perform.TableExistCommandPerform;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для выполнения команд
 */
public class CommandPerformHelper {
    private final ConnectionConfig connectionConfig;

    public CommandPerformHelper(ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    public Boolean checkColumnExist(String tableName, String... columns) throws Exception {
        List<String> columnList = new ArrayList<>();
        for (String curColumnName : columns) {
            columnList.add(curColumnName);
        }
        return checkColumnExist(tableName, columnList);
    }

    public Boolean checkColumnExist(String tableName, List<String> columns) throws Exception {
        boolean result = true;

        for (String curColumnName : columns) {
            List<String> newParams = new ArrayList<>();
            newParams.add(tableName);
            newParams.add(curColumnName);
            new ColumnExistCommandPerform().perform(connectionConfig, newParams);
        }

        return result;
    }

    public Boolean checkTableExist(String tableName) throws Exception {
        boolean result = true;

        List<String> newParams = new ArrayList<>();
        newParams.add(tableName);
        new TableExistCommandPerform().perform(connectionConfig, newParams);

        return result;
    }
}
