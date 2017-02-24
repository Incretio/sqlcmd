package ru.incretio.juja.sqlcmd.exceptions.command;

public class EmptyCommandException extends CommandException {
    public EmptyCommandException() {
        super("Введена пустая команда.");
    }
}
