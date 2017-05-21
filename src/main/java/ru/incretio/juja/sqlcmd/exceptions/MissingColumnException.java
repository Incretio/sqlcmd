package ru.incretio.juja.sqlcmd.exceptions;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class MissingColumnException extends MissingAnyDataException {
    public MissingColumnException(String columnName) {
        super(String.format(takeCaption("missingColumnPattern"), columnName));
    }
}
