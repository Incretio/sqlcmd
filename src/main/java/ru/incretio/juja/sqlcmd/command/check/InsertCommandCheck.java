package ru.incretio.sqlcmd.command.check;

import ru.incretio.sqlcmd.command.Checkable;

import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public class InsertCommandCheck implements Checkable {
    @Override
    public boolean isCorrectParams(List<String> params) {
        return false;
    }
}
