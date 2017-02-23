package ru.incretio.juja.sqlcmd.exceptions.commandexception;

/**
 * Created by incre on 23.02.2017.
 */
public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String currentCommandName) {
        super("Команда '" + currentCommandName + "' не найдена.");
    }
}
