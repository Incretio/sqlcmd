package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class InsertCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\tinsert tableName column1 value1 [column2 value2] [columnN valueN]:\n" +
                "\t\tдобавить запись в указанную таблицу;";
    }
}
