package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingColumnHelper;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class UpdateRecords extends Base {

    public UpdateRecords(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameIndex = 0;
        int whereColumnNameIndex = 1;
        int whereColumnValueIndex = 2;
        int setColumnNameIndex = 3;
        int setColumnValueIndex = 4;
        String tableName = params.get(tableNameIndex);
        String whereColumnName = params.get(whereColumnNameIndex);
        String whereColumnValue = params.get(whereColumnValueIndex);
        String setColumnName = params.get(setColumnNameIndex);
        String setColumnValue = params.get(setColumnValueIndex);

        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);
        new MissingColumnHelper(model, view)
                .throwExceptionIfColumnNotExist(tableName, whereColumnName, setColumnName);

        model.executeUpdateRecords(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);

        view.write(String.format(takeCaption("recordUpdated"), tableName));
    }
}