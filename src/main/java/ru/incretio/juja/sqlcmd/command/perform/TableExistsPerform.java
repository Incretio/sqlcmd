package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.utils.CommandPerformHelper;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class TableExistsPerform implements Performable {

    /**
     Кидает исключение MissingTableException, если таблица не найдена. Если найдена, то вернёт TRUE.toString();
     */
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException, MissingTableException {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);
        String tablesList = new TablesPerform().perform(connectionConfig, Collections.emptyList());

        boolean tableFound = CommandPerformHelper.contains(tablesList, tableName);

        if (!tableFound){
            throw new MissingTableException(tableName);
        }

        return String.format(takeCaption("tableExistsPattern"), tableName);
    }
}
