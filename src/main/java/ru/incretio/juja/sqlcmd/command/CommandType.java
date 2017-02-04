package ru.incretio.sqlcmd.command;

import ru.incretio.sqlcmd.command.check.*;
import ru.incretio.sqlcmd.command.perform.*;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public enum CommandType {
    TABLES("table", new TablesCommandCheck(), new TablesCommandPerform()),
    CLEAR("clear", new ClearCommandCheck(), new ClearCommandPerform()),
    INSERT("insert", new InsertCommandCheck(), new InsertCommandPerform()),
    READ("read", new ReadCommandCheck(), new ReadCommandPerform()),
    UPDATE("update", new UpdateCommandCheck(), new UpdateCommandPerform()),
    DELETE("delete", new DeleteCommandCheck(), new DeleteCommandPerform());


    private final String commandName;
    private final Checkable checkable;
    private final Command command;


    CommandType(String commandName, Checkable checkable, Command command) {
        this.commandName = commandName;
        this.checkable = checkable;
        this.command = command;
    }

    public Checkable getCheckable() {
        return checkable;
    }

    public Command getCommand() {
        return command;
    }
}
