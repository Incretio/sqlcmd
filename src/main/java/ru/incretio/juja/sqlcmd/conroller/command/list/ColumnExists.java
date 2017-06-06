package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

public class ColumnExists extends Base {

    public ColumnExists(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    /**
     * Кидает исключение MissingColumnException, если поле не найдено.
     */
    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameIndex = 0;
        int columnNameIndex = 1;
        String tableName = params.get(tableNameIndex);
        String columnName = params.get(columnNameIndex);

        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);

        boolean columnFound = model.takeTableColumns(tableName).contains(columnName);
        if (!columnFound) {
            throw new MissingColumnException(columnName);
        }
    }
}
