package ru.incretio.sqlcmd.command;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public class CommandFactory {
    public static Command makeByCommandName(String commandName) {
        return makeByCommandType(CommandType.valueOf(commandName));
    }

    private static Command makeByCommandType(CommandType commandType) {
        return new CommandImpl(commandType.getCheckable(), commandType.getCommand());
    }
}
