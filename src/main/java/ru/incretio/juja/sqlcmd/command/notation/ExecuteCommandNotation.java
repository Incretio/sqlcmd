package ru.incretio.juja.sqlcmd.command.notation;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 02.03.2017.
 */
public class ExecuteCommandNotation implements Notationable{
    @Override
    public String getNotation() {
        return "\texecute:\n" +
                "\t\tвыполнить пользовательский запрос (должен быть указан в одинарных ковычках);";
    }
}
