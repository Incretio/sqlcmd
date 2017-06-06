package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingColumnHelper;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class DeleteRecords extends Base {

    public DeleteRecords(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameIndex = 0;
        int whereColumnNameIndex = 1;
        int whereColumnValueIndex = 2;
        String tableName = params.get(tableNameIndex);
        String whereColumnName = params.get(whereColumnNameIndex);
        String whereColumnValue = params.get(whereColumnValueIndex);

        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);
        new MissingColumnHelper(model, view)
            .throwExceptionIfColumnNotExist(tableName, whereColumnName);

        model.executeDeleteRecords(tableName, whereColumnName, whereColumnValue);

        view.write(String.format(takeCaption("recordDeletedPattern"), tableName));
    }
}
