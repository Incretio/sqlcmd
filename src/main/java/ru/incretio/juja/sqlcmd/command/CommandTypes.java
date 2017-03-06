package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.check.*;
import ru.incretio.juja.sqlcmd.command.notation.*;
import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.exceptions.CommandNotFoundException;

import java.util.ArrayList;
import java.util.List;

public enum CommandTypes {
    CONNECT("connect", new Command(new ConnectCommandCheck(), new ConnectCommandPerform(), new ConnectCommandNotation())),
    TABLES("tables", new Command(new TablesCommandCheck(), new TablesCommandPerform(), new TablesCommandNotation())),
    CLEAR("clear", new Command(new ClearCommandCheck(), new ClearCommandPerform(), new ClearCommandNotation())),
    DROP("drop", new Command(new DropCommandCheck(), new DropCommandPerform(), new DropCommandNotation())),
    CREATE("create", new Command(new CreateCommandCheck(), new CreateCommandPerform(), new CreateCommandNotation())),
    FIND("find", new Command(new FindCommandCheck(), new FindCommandPerform(), new FindCommandNotation())),
    INSERT("insert", new Command(new InsertCommandCheck(), new InsertCommandPerform(), new InsertCommandNotation())),
    UPDATE("update", new Command(new UpdateCommandCheck(), new UpdateCommandPerform(), new UpdateCommandNotation())),
    DELETE("delete", new Command(new DeleteCommandCheck(), new DeleteCommandPerform(), new DeleteCommandNotation())),
    EXIT("exit", new Command(new ExitCommandCheck(), new ExitCommandPerform(), new ExitCommandNotation())),
    CLOSE("close", new Command(new CloseCommandCheck(), new CloseCommandPerform(), new CloseCommandNotation())),
    HELP("help", new Command(new HelpCommandCheck(), new HelpCommandPerform(), new HelpCommandNotation())),
    EXECUTE("execute", new Command(new ExecuteCommandCheck(), new ExecuteCommandPerform(), new ExecuteCommandNotation())),
    DROPDB("dropdb", new Command(new DropDBCommandCheck(), new DropDBCommandPerform(), new DropDBCommandNotation())),
    CREATEDB("createdb", new Command(new CreateDBCommandCheck(), new CreateDBCommandPerform(), new CreateDBCommandNotation()));

    private String commandName;
    private Command command;

    CommandTypes(String commandName, Command command) {
        this.commandName = commandName;
        this.command = command;
    }

    static Command getCommand(String commandName) throws CommandNotFoundException {
        for (CommandTypes commandType : CommandTypes.values()) {
            if (commandType.commandName.equals(commandName)) {
                return commandType.command;
            }
        }
        throw new CommandNotFoundException(commandName);
    }

    public static List<String> getNotationsList() {
        List<String> result = new ArrayList<>();

        for (CommandTypes commandType : CommandTypes.values()) {
            if (commandType.command == null) {
                continue;
            }

            String notation = commandType.command.getNotation();
            if (notation != null) {
                result.add(notation);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return commandName;
    }
}
