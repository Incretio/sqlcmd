package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

import java.util.List;

public class DeleteCommandCheck implements Checkable {
    @Override
    public boolean checkParams(List<String> params) {
        return params != null && params.size() == 3;
    }
}
