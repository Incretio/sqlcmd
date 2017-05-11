package ru.incretio.juja.sqlcmd.command.perform.utils;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.perform.ColumnExistCommandPerform;
import ru.incretio.juja.sqlcmd.command.perform.TableExistCommandPerform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Вспомогательный класс для выполнения команд
 */
public class CommandPerformHelper {
    private final ConnectionConfig connectionConfig;

    public CommandPerformHelper(ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    public void checkColumnExist(String tableName, String... columns) throws Exception {
        List<String> columnList = new ArrayList<>();
        Collections.addAll(columnList, columns);
        checkColumnExist(tableName, columnList);
    }

    public void checkColumnExist(String tableName, List<String> columns) throws Exception {
        for (String curColumnName : columns) {
            List<String> newParams = new ArrayList<>();
            newParams.add(tableName);
            newParams.add(curColumnName);
            new ColumnExistCommandPerform().perform(connectionConfig, newParams);
        }
    }

    public void checkTableExist(String tableName) throws Exception {
        List<String> newParams = new ArrayList<>();
        newParams.add(tableName);
        new TableExistCommandPerform().perform(connectionConfig, newParams);
    }
}
