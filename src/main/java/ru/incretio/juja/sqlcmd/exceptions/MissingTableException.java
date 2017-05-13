package ru.incretio.juja.sqlcmd.exceptions;

public class MissingTableException extends MissingAnyDataException {
    public MissingTableException(String tableName) {
        super("Таблица " + tableName + " отсутствует.");
    }
}
