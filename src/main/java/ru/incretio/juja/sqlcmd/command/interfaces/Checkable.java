package ru.incretio.juja.sqlcmd.command.interfaces;

import java.util.List;

public interface Checkable {
    boolean checkParams(List<String> params);
}
