package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class HelpCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\thelp:\n" +
                "\t\tпоказать список команд и их описаниями;";
    }
}
