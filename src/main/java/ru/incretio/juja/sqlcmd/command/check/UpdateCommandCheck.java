package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public class UpdateCommandCheck implements Checkable {
    @Override
    public boolean checkParams(List<String> params) {
        return params.size() == 5;
    }
}
