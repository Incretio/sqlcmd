package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 17.02.2017.
 */
public class ConnectCommandNotation implements Notationable {
    @Override
    public String getNotation() {
        return "\t" + CommandTypes.CONNECT.toString() + " serverName dbName username password:\n" +
                "\t\tподключиться к базе данных;";
    }
}
