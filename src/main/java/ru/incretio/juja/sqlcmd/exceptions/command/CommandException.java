package ru.incretio.juja.sqlcmd.exceptions.command;

public class CommandException extends Exception {
    public CommandException() {
    }

    public CommandException(String s) {
        super(s);
    }
}