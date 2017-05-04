package ru.incretio.juja.sqlcmd.exceptions;

public class MissingConnectionException extends Exception{
    public MissingConnectionException() {
        super("Отсутствует подключение к базе данных.");
    }
}
