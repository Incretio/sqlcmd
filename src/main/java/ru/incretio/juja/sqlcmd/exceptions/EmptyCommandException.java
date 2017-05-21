package ru.incretio.juja.sqlcmd.exceptions;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class EmptyCommandException extends CommandException {
    public EmptyCommandException() {
        super(takeCaption("emptyCommand"));
    }
}
