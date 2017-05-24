package ru.incretio.juja.sqlcmd.conroller.command.list.utils;

import ru.incretio.juja.sqlcmd.conroller.command.Command;
import ru.incretio.juja.sqlcmd.conroller.command.list.ColumnExists;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class MissingColumnHelper extends PerformHelper {
    public MissingColumnHelper(Model model, View view) {
        super(model, view);
    }

    public void throwExceptionIfColumnNotExist(String tableName, String... columns) throws Exception {
        checkColumnExist(tableName, columns);
    }

    public void throwExceptionIfColumnNotExist(String tableName, List<String> columns) throws Exception {
        checkColumnExist(tableName, columns);
    }

    public void throwExceptionIfColumnNotExist(String tableName, String columnName) throws Exception {
        checkColumnExist(tableName, columnName);
    }

    private void checkColumnExist(String tableName, String... columns) throws Exception {
        List<String> columnList = Arrays.asList(columns);
        checkColumnExist(tableName, columnList);
    }

    private void checkColumnExist(String tableName, List<String> columns) throws Exception {
        for (String curColumnName : columns) {
            List<String> newParams = Arrays.asList(tableName, curColumnName);
            Command.takeByName(Command.COLUMN_EXIST.toString())
                    .perform(model, view, newParams);
        }
    }
}
