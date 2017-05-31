package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.Collections;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class CreateTable extends Base {

    public CreateTable(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);
        List<String> columns = Collections.emptyList();
        if (params.size() > 1) {
            columns = params.subList(1, params.size());
        }

        boolean isTableExist =
                new MissingTableHelper(model, view)
                        .isTableExist(tableName);

        String result;
        if (isTableExist) {
            result = String.format(takeCaption("tableAlreadyExistsPattern"), tableName);
        } else {
            model.executeCreateTable(tableName, columns);
            result = String.format(takeCaption("tableAddedPattern"), tableName);
        }

        view.write(result);
    }
}
