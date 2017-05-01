package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class UpdateCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\t" + CommandTypes.UPDATE.toString() + " tableName whereColumn whereValue setColumn setValue:\n" +
                "\t\tобновить записи, удовлетворяющие условию в указанной таблице;";
    }
}
