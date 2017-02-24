package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.check.*;
import ru.incretio.juja.sqlcmd.command.notation.*;
import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.exceptions.command.CommandNotFoundException;

public class CommandFactory {
    public static Command makeCommand(String commandName) throws CommandNotFoundException {
        return CommandTypes.getCommand(commandName);
    }
}
