package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class ExitCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\texit:\n" +
                "\t\tзакрыть соединение и выйти из программы;";
    }
}
