package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 01.05.2017.
 */
public class TableExistsCommandNotation implements Notationable {
    @Override
    public String getNotation() {
        return "\t" + CommandTypes.TABLEEXISTS.toString() + " tableName:\n" +
                "\t\tпроверить наличие указанной таблицы в базе данных;";
    }
}
