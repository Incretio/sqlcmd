package ru.incretio.juja.sqlcmd.commands;

import javax.activation.UnsupportedDataTypeException;

public class CommandFactory {
    public static Command getCommandByCommandType(CommandType commandType) throws UnsupportedDataTypeException {
        switch (commandType) {
            case CONNECT:
                return new ConnectCommandImpl();
            case TABLES:
                return new TablesCommandImpl();
            case DROP:
                return new DropCommandImpl();
            case CLEAR:
                return new ClearCommandImpl();
            case CREATE:
                return new CreateCommandImpl();
            case FIND:
                return new FindCommandImpl();
            case INSERT:
                return new InsertCommandImpl();
            case UPDATE:
                return new UpdateCommandImpl();
            case DELETE:
                return new DeleteCommandImpl();
            case HELP:
                return new HelpCommandImpl();
            case EXIT:
                return new ExitCommandImpl();
            default:
                throw new UnsupportedDataTypeException("Command \"" + commandType.toString() + "\" does not supported!");
        }
    }

    public static Command getCommandByName(String commandName) throws UnsupportedDataTypeException {
        return getCommandByCommandType(CommandType.getCommandTypeByName(commandName));
    }
}

