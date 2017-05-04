package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import java.util.List;

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
