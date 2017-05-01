package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class CreateCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\t" + CommandTypes.CREATE.toString() + " tableName column1 [column2] [columnN]:\n" +
                "\t\tдобавить новую таблицу;";
    }
}
