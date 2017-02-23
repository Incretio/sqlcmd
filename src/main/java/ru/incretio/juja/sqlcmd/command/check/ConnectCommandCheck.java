package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

import java.util.List;

public class ConnectCommandCheck implements Checkable {
    @Override
    public boolean checkParams(List<String> params) {
        return params.size() == 4;
    }
}
