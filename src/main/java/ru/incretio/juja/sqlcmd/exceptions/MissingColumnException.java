package ru.incretio.juja.sqlcmd.exceptions;

public class MissingColumnException extends Exception{
    public MissingColumnException(String columnName) {
        super("Колонка " + columnName + " отсутствует.");
    }
}
