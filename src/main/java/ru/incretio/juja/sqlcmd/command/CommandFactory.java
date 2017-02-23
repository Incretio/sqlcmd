package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.check.*;
import ru.incretio.juja.sqlcmd.command.notation.*;
import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.exceptions.commandexception.CommandNotFoundException;

public class CommandFactory {
    public static Command makeCommand(String commandName) throws CommandNotFoundException {
        Command result;
        switch (commandName.toLowerCase()) {
            case "connect":
                result = new Command(new ConnectCommandCheck(), new ConnectCommandPerform(), new ConnectCommandNotation());
                break;
            case "tables":
                result = new Command(new TablesCommandCheck(), new TablesCommandPerform(), new TablesCommandNotation());
                break;
            case "clear":
                result = new Command(new ClearCommandCheck(), new ClearCommandPerform(), new ClearCommandNotation());
                break;
            case "drop":
                result = new Command(new DropCommandCheck(), new DropCommandPerform(), new DropCommandNotation());
                break;
            case "create":
                result = new Command(new CreateCommandCheck(), new CreateCommandPerform(), new CreateCommandNotation());
                break;
            case "find":
                result = new Command(new FindCommandCheck(), new FindCommandPerform(), new FindCommandNotation());
                break;
            case "insert":
                result = new Command(new InsertCommandCheck(), new InsertCommandPerform(), new InsertCommandNotation());
                break;
            case "update":
                result = new Command(new UpdateCommandCheck(), new UpdateCommandPerform(), new UpdateCommandNotation());
                break;
            case "delete":
                result = new Command(new DeleteCommandCheck(), new DeleteCommandPerform(), new DeleteCommandNotation());
                break;
            case "exit":
                result = new Command(new ExitCommandCheck(), new ExitCommandPerform(), new ExitCommandNotation());
                break;
            case "close":
                result = new Command(new CloseCommandCheck(), new CloseCommandPerform(), new CloseCommandNotation());
                break;
            case "help":
                result = new Command(new HelpCommandCheck(), new HelpCommandPerform(), new HelpCommandNotation());
                break;
            default:
                throw new CommandNotFoundException(commandName);
        }
        return result;
    }
}
