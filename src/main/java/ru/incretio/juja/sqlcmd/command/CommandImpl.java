package ru.incretio.sqlcmd.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public class CommandImpl implements Command, Checkable {
    private final Checkable checkable;
    private final Command performable;

    CommandImpl(Checkable checkable, Command performable) {
        this.checkable = checkable;
        this.performable = performable;
    }

    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        if (isCorrectParams(params)) {
            return performable.perform(connection, params);
        }

        return "Введены некорректные параметры!";
    }

    @Override
    public boolean isCorrectParams(List<String> params) {
        return checkable.isCorrectParams(params);
    }
}
