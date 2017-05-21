package ru.incretio.juja.sqlcmd.exceptions;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class MissingJDBCConnectionTypeException extends MissingAnyDataException {
    public MissingJDBCConnectionTypeException(String s) {
        super(String.format(takeCaption("missingJDBCConnectionPattern"), s));
    }
}
