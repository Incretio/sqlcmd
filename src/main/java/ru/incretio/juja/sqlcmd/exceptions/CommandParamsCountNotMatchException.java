package ru.incretio.juja.sqlcmd.exceptions;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class CommandParamsCountNotMatchException extends CommandException {
    public CommandParamsCountNotMatchException(String currentCommandFormat) {
        super(String.format(
                takeCaption("commandParamsCountNotMatchPattern"),
                currentCommandFormat));
    }
}
