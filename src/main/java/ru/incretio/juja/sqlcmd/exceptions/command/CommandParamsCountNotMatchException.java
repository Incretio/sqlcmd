package ru.incretio.juja.sqlcmd.exceptions.command;

public class CommandParamsCountNotMatchException extends CommandException {
    public CommandParamsCountNotMatchException(String correntCommandFormat) {
        super("Параметры команды указаны не корректно. \nФормат команды: \n" + correntCommandFormat);
    }
}
