package ru.incretio.juja.sqlcmd.exceptions;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class MissingTableException extends MissingAnyDataException {
    public MissingTableException(String tableName) {
        super(String.format(takeCaption("missingTablePattern"), tableName));
    }
}
