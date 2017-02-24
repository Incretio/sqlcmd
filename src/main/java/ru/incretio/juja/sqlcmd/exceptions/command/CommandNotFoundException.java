package ru.incretio.juja.sqlcmd.exceptions.command;

public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String currentCommandName) {
        super("Команда '" + currentCommandName + "' не найдена.");
    }
}
