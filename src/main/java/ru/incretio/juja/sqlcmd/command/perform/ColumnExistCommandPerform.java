package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;

import java.util.ArrayList;
import java.util.List;

public class ColumnExistCommandPerform implements Performable {

    /**
     Кидает исключение MissingColumnException, если поле не найдено. Если найдено, то вернёт TRUE.toString();
     */
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        int tableNameInd = 0;
        int columnNameInd = 1;
        String tableName = params.get(tableNameInd);
        String columnName = params.get(columnNameInd);

        List<String> newParams = new ArrayList<>();
        newParams.add(tableName);
        String columnList = new ColumnsCommandPerform().perform(connectionConfig, newParams);
        boolean columnFound = false;

        for (String curColumnName : columnList.split("\n")) {
            if (curColumnName.equals(columnName)){
                columnFound = true;
                break;
            }
        }

        if (!columnFound){
            throw new MissingColumnException(columnName);
        }

        return Boolean.TRUE.toString();
    }
}
