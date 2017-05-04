package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnExistCommandPerform implements Performable {

    /**
     Кидает исключение MissingColumnException, если поле не найдено. Если найдено, то вернёт TRUE.toString();
     */
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        String tableName = params.get(0);
        String columnName = params.get(1);

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
