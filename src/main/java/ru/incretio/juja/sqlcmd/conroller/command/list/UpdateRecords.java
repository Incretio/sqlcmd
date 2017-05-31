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
        int tableNameInd = 0;
        int whereColumnNameInd = 1;
        int whereColumnValueInd = 2;
        int setColumnNameInd = 3;
        int setColumnValueInd = 4;
        String tableName = params.get(tableNameInd);
        String whereColumnName = params.get(whereColumnNameInd);
        String whereColumnValue = params.get(whereColumnValueInd);
        String setColumnName = params.get(setColumnNameInd);
        String setColumnValue = params.get(setColumnValueInd);

        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);
        new MissingColumnHelper(model, view)
                .throwExceptionIfColumnNotExist(tableName, whereColumnName, setColumnName);

        model.executeUpdateRecords(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);

        view.write(String.format(takeCaption("recordUpdated"), tableName));
    }
}