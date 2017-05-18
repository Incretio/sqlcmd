package ru.incretio.juja.sqlcmd.exceptions;

public class MissingColumnException extends MissingAnyDataException {
    public MissingColumnException(String columnName) {
        super(String.format("Колонка %s отсутствует.", columnName));
    }
}
