package ru.incretio.juja.sqlcmd.exceptions;

public class MissingTableException extends MissingAnyDataException {
    public MissingTableException(String tableName) {
        super(String.format("Таблица %s отсутствует.", tableName));
    }
}
