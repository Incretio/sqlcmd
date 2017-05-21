package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;

import java.util.Collections;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ColumnExistsPerform implements Performable {

    /**
     * Кидает исключение MissingColumnException, если поле не найдено.;
     */
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        int tableNameInd = 0;
        int columnNameInd = 1;
        String tableName = params.get(tableNameInd);
        String columnName = params.get(columnNameInd);

        List<String> newParams = Collections.singletonList(tableName);
        String columnList = new ColumnsPerform().perform(connectionConfig, newParams);

        boolean columnFound = CommandPerformHelper.contains(columnList, columnName);

        if (!columnFound) {
            throw new MissingColumnException(columnName);
        }

        return String.format(takeCaption("tableContainsColumnPattern"), columnName, tableName);
    }
}
