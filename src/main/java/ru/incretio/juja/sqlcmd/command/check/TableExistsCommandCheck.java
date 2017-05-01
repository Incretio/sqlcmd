package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

import java.util.List;

/**
 * Created by incre on 01.05.2017.
 */
public class TableExistsCommandCheck implements Checkable{
    @Override
    public boolean checkParams(List<String> params) {
        return params != null && params.size() == 1;
    }
}
