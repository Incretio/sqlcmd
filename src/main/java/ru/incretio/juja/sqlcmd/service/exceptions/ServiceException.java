package ru.incretio.juja.sqlcmd.service.exceptions;

public class ServiceException extends Exception {
    public ServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
