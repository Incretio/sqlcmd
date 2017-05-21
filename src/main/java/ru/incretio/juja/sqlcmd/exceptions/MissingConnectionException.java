package ru.incretio.juja.sqlcmd.exceptions;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class MissingConnectionException extends MissingAnyDataException {
    public MissingConnectionException() {
        super(takeCaption("missingConnection"));
    }
}
