package ru.incretio.juja.sqlcmd.command;

public class CommandFactory {
    public static Command makeByCommandName(String commandName) {
        return makeByCommandType(CommandType.valueOf(commandName));
    }

    private static Command makeByCommandType(CommandType commandType) {
        return new CommandImpl(commandType.getCheckable(), commandType.getCommand());
    }
}
