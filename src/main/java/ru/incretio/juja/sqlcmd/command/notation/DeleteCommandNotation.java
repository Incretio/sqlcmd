package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class DeleteCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\tdelete tableName whereColumn whereValue:\n" +
                "\t\tудалить записи, удовлетворяющие условию;";
    }
}
