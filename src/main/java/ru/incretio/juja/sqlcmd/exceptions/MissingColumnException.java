package ru.incretio.juja.sqlcmd.exceptions;

public class MissingColumnException extends MissingAnyDataException {
    public MissingColumnException(String columnName) {
        super("Колонка " + columnName + " отсутствует.");
    }
}
