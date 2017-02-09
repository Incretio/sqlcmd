package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.Checkable;

import java.util.List;

public class InsertCommandCheck implements Checkable {
    @Override
    public boolean isCorrectParams(List<String> params) {
        return params.size() > 2 && (params.size() %2 == 1);
    }
}
