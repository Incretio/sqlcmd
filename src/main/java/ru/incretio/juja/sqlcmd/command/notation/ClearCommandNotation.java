package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class ClearCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\tclear tableName:\n\t\tочистить содержимое указанной таблицы;";
    }
}
