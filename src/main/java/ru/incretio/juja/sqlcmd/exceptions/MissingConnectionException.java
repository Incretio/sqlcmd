package ru.incretio.juja.sqlcmd.exceptions;

/**
 * Created by incre on 06.03.2017.
 */
public class MissingConnectionException extends Exception{
    public MissingConnectionException() {
        super("Отсутствует подключение к базе данных.");
    }
}
