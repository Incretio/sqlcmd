package ru.incretio.juja.sqlcmd.commands;

import java.util.List;


public interface Command {
    String perform();
    boolean isCorrectCommand();
    void setParams(List<String> params);
}
