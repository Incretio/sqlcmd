package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.Checkable;
import java.util.List;

public class ClearCommandCheck implements Checkable {
    @Override
    public boolean isCorrectParams(List<String> params) {
        return params.size() == 1;
    }
}
