package ru.incretio.juja.sqlcmd.command;

import java.util.List;

public interface Checkable {
    boolean isCorrectParams(List<String> params);
}
