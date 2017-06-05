package ru.incretio.juja.sqlcmd.conroller.command.list.utils;

import ru.incretio.juja.sqlcmd.conroller.command.Command;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.Collections;
import java.util.List;

public class MissingTableHelper extends PerformHelper {
    public MissingTableHelper(Model model, View view) {
        super(model, view);
    }

    public void throwExceptionIfTableNotExist(String tableName) throws Exception {
        checkTableExist(tableName);
    }

    public boolean isTableExist(String tableName) throws Exception {
        boolean tableExist = true;
        try {
            checkTableExist(tableName);
        } catch (MissingTableException e) {
            tableExist = false;
        }
        return tableExist;
    }

    private void checkTableExist(String tableName) throws Exception {
        List<String> newParams = Collections.singletonList(tableName);
        Command.takeByName(Command.TABLE_EXIST.toString())
                .perform(model, view, newParams);
    }
}
