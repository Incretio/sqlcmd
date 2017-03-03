package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

import java.util.List;

/**
 * Created by incre on 03.03.2017.
 */
public class CreateDBCommandCheck implements Checkable {
    @Override
    public boolean checkParams(List<String> params) {
        return params.size() == 1;
    }
}
