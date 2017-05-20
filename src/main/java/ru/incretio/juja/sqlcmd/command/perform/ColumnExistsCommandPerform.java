package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;

import java.util.Collections;
import java.util.List;

public class ColumnExistsCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "Колонка %s имеется в наличии у таблицы %s.";

    /**
     * Кидает исключение MissingColumnException, если поле не найдено. Если найдено, то вернёт TRUE.toString();
     */
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        int tableNameInd = 0;
        int columnNameInd = 1;
        String tableName = params.get(tableNameInd);
        String columnName = params.get(columnNameInd);

        List<String> newParams = Collections.singletonList(tableName);
        String columnList = new ColumnsCommandPerform().perform(connectionConfig, newParams);

        boolean columnFound = CommandPerformHelper.contains(columnList, columnName);

        if (!columnFound) {
            throw new MissingColumnException(columnName);
        }

        return String.format(OUTPUT_TEXT, columnName, tableName);
    }
}
