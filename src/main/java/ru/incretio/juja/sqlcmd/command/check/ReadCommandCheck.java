package ru.incretio.juja.sqlcmd.command.check;

import ru.incretio.juja.sqlcmd.command.Checkable;

import java.util.List;


public class ReadCommandCheck implements Checkable {
    @Override
    public boolean isCorrectParams(List<String> params) {
        return false;
    }
}