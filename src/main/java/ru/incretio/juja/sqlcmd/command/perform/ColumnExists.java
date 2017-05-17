package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;

import java.util.Arrays;
import java.util.List;

public class ColumnExists implements Performable {
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

        List<String> newParams = Arrays.asList(tableName);
        String columnList = new ColumnsList().perform(connectionConfig, newParams);

        boolean columnFound = false;
        for (String curColumnName : columnList.split("\n")) {
            if (curColumnName.equals(columnName)) {
                columnFound = true;
                break;
            }
        }

        if (!columnFound) {
            throw new MissingColumnException(columnName);
        }

        return String.format(OUTPUT_TEXT, columnName, tableName);
    }
}
