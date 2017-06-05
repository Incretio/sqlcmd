package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

public class TableExists extends Base {

    public TableExists(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    /**
     * Кидает исключение MissingTableException, если таблица не найдена.
     */
    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);

        boolean tableFound = model.takeTables().contains(tableName);

        if (!tableFound) {
            throw new MissingTableException(tableName);
        }
    }
}
