package ru.incretio.juja.sqlcmd.exceptions;

public class MissingTableException extends Exception{
    public MissingTableException(String tableName) {
        super("Таблица " + tableName + " отсутствует.");
    }
}
