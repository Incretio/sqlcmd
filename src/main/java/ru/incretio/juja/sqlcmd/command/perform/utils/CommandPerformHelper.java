package ru.incretio.juja.sqlcmd.command.perform.utils;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.perform.ColumnExists;
import ru.incretio.juja.sqlcmd.command.perform.TableExists;

import java.util.Arrays;
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
        List<String> columnList = Arrays.asList(columns);
        checkColumnExist(tableName, columnList);
    }

    public void checkColumnExist(String tableName, List<String> columns) throws Exception {
        for (String curColumnName : columns) {
            List<String> newParams = Arrays.asList(tableName, curColumnName);
            new ColumnExists().perform(connectionConfig, newParams);
        }
    }

    public void checkTableExist(String tableName) throws Exception {
        List<String> newParams = Collections.singletonList(tableName);
        new TableExists().perform(connectionConfig, newParams);
    }

    public static boolean contains(String source, String value){
        return Arrays.asList(source.split("\n"))
                .contains(value);
    }
}
