package ru.incretio.juja.sqlcmd.controls;

import java.util.List;

/**
 * Created by incre on 01.02.2017.
 */
public class CommandImpl implements Command {
    private final CommandType commandType;
    private final List<String> params;

    public CommandImpl(CommandType commandType, List<String> params) {
        this.commandType = commandType;
        this.params = params;
    }

    @Override
    public String perform() {
        return null;
    }
}
