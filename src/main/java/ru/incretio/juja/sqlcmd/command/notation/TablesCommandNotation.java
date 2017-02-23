package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class TablesCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\ttables:\n" +
                "\t\tпоказать список таблиц базы данных;";
    }
}
