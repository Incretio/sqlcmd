package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

public class ColumnsList extends Base {

    public ColumnsList(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameIndex = 0;
        String tableName = params.get(tableNameIndex);

        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);

        List<String> tableColumns = model.takeTableColumns(tableName);
        view.write(tableColumns);
    }
}
