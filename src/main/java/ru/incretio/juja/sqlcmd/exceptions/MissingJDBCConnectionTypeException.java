package ru.incretio.juja.sqlcmd.exceptions;

public class MissingJDBCConnectionTypeException extends MissingAnyDataException {
    public MissingJDBCConnectionTypeException(String s) {
        super(String.format("Тип JDBCConnection: %s, отсутствует.", s));
    }
}
