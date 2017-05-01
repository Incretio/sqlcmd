package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 03.03.2017.
 */
public class DropDBCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\t" + CommandTypes.DROPDB.toString() + " dbName:\n" +
                "\t\tудалить базу данных;";
    }
}
