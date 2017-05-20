package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import java.util.List;

public class Command implements Checkable, Performable, Notationable {
    private final Checkable checkable;
    private final Performable performable;
    private final Notationable notationable;

    public Command(Checkable checkable, Performable performable, Notationable notationable) {
        this.checkable = checkable;
        this.performable = performable;
        this.notationable = notationable;
    }

    @Override
    public boolean checkParams(List<String> params) {
        return checkable.checkParams(params);
    }

    @Override
    public String getNotation() {
        return notationable.getNotation();
    }

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        return performable.perform(connectionConfig, params);
    }
}
