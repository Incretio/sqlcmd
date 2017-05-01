package ru.incretio.juja.sqlcmd.exceptions;

/**
 * Created by incre on 01.05.2017.
 */
public class MissingTableException extends Exception{
    public MissingTableException(String tableName) {
        super("Таблица " + tableName + " отсутствует.");
    }
}
