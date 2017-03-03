package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 03.03.2017.
 */
public class CreateDBCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\tcreatedb dbName:\n" +
                "\t\tдобавить новую базу данных;";
    }
}
