package ru.incretio.juja.sqlcmd.exceptions;

public class MissingConnectionException extends MissingAnyDataException {
    public MissingConnectionException() {
        super("Отсутствует подключение к базе данных.");
    }
}
