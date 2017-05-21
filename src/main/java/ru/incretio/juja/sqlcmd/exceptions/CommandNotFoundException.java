package ru.incretio.juja.sqlcmd.exceptions;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String currentCommandName) {
        super(String.format(takeCaption("commandNotFoundPattern"), currentCommandName));
    }
}
