package ru.incretio.juja.sqlcmd.exceptions;

public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String currentCommandName) {
        super(String.format("Команда '%s' не найдена.", currentCommandName));
    }
}
