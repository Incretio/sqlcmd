package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by incre on 19.02.2017.
 */
public class Command implements Checkable, Performable, Notationable {
    private final Checkable checkable;
    private final Performable perform;
    private final Notationable notation;

    public Command(Checkable checkable, Performable perform, Notationable notation) {
        this.checkable = checkable;
        this.perform = perform;
        this.notation = notation;
    }

    @Override
    public boolean checkParams(List<String> params) {
        return checkable.checkParams(params);
    }

    @Override
    public String getNotation() {
        return notation.getNotation();
    }

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        return perform.perform(connectionConfig, params);
    }
}
