package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingColumnHelper;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.ArrayList;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class InsertRecord extends Base {

    public InsertRecord(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        fillColumnsAndValues(params, columns, values);

        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);
        new MissingColumnHelper(model, view)
                .throwExceptionIfColumnNotExist(tableName, columns);

        model.executeInsertRecord(tableName, columns, values);

        view.write(String.format(takeCaption("recordInsertedPattern"), tableName));
    }


    private static void fillColumnsAndValues(
            List<String> params, List<String> columns, List<String> values) {
        int secondElement = 1;
        // Берём значение через одно, так как они чередуются [column][value][column][value]
        for (int i = secondElement; i < params.size(); i += 2) {
            columns.add(params.get(i));
            int nextValueInd = i + 1;
            values.add(params.get(nextValueInd));
        }
    }
}
