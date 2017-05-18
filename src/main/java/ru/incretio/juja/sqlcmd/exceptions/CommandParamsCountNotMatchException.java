package ru.incretio.juja.sqlcmd.exceptions;

public class CommandParamsCountNotMatchException extends CommandException {
    public CommandParamsCountNotMatchException(String currentCommandFormat) {
        super("Параметры команды указаны не корректно. \nФормат команды: \n".concat(currentCommandFormat));
    }
}
