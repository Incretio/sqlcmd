package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TableExistCommandPerform implements Performable {

    /**
     Кидает исключение MissingTableException, если таблица не найдена. Если найдена, то вернёт TRUE.toString();
     */
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);
        String tablesList = new TablesCommandPerform().perform(connectionConfig, Collections.emptyList());
        boolean tableFound = false;

        for (String curTableName : tablesList.split("\n")) {
            if (curTableName.equals(tableName)){
                tableFound = true;
                break;
            }
        }

        if (!tableFound){
            throw new MissingTableException(tableName);
        }

        return Boolean.TRUE.toString();
    }
}
