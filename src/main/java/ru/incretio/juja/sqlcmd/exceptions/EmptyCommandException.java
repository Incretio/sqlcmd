package ru.incretio.juja.sqlcmd.exceptions;

public class EmptyCommandException extends CommandException {
    public EmptyCommandException() {
        super("Введена пустая команда.");
    }
}
