package ru.incretio.sqlcmd.command;

import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public interface Checkable {
    boolean isCorrectParams(List<String> params);
}
