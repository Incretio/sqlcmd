package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.exceptions.CommandNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.incretio.juja.sqlcmd.command.CommandCheckFactory.*;
import static ru.incretio.juja.sqlcmd.command.CommandNotationFactory.*;

public enum CommandTypes {
    // Connection command
    CONNECT("connect", new Command(new CommandCheckFactory().getConnectCommandCheck(), new ConnectCommandPerform(), getConnectCommandNotation())),
    CLOSE("close", new Command(new CommandCheckFactory().getCloseCommandCheck(), new CloseCommandPerform(), getCloseCommandNotation())),
    // Tables command
    TABLES("tables", new Command(new CommandCheckFactory().getTablesCommandCheck(), new TablesCommandPerform(), getTablesCommandNotation())),
    COLUMNS("columns", new Command(new CommandCheckFactory().getColumnsCommandCheck(), new ColumnsCommandPerform(), getColumnsCommandNotation())),
    CREATE("create", new Command(new CommandCheckFactory().getCreateCommandCheck(), new CreateCommandPerform(), getCreateCommandNotation())),
    DROP("drop", new Command(new CommandCheckFactory().getDropCommandCheck(), new DropCommandPerform(), getDropCommandNotation())),
    CLEAR("clear", new Command(new CommandCheckFactory().getClearCommandCheck(), new ClearCommandPerform(), getClearCommandNotation())),
    FIND("find", new Command(new CommandCheckFactory().getFindCommandCheck(), new FindCommandPerform(), getFindCommandNotation())),
    INSERT("insert", new Command(new CommandCheckFactory().getInsertCommandCheck(), new InsertCommandPerform(), getInsertCommandNotation())),
    UPDATE("update", new Command(new CommandCheckFactory().getUpdateCommandCheck(), new UpdateCommandPerform(), getUpdateCommandNotation())),
    DELETE("delete", new Command(new CommandCheckFactory().getDeleteCommandCheck(), new DeleteCommandPerform(), getDeleteCommandNotation())),
    // Exist command
    TABLE_EXIST("table_exist", new Command(new CommandCheckFactory().getTableExistCommandCheck(), new TableExistsCommandPerform(), getTableExistCommandNotation())),
    COLUMN_EXIST("column_exist", new Command(new CommandCheckFactory().getColumnExistCommandCheck(), new ColumnExistsCommandPerform(), getColumnExistCommandNotation())),
    // DB command
    DROPDB("dropdb", new Command(new CommandCheckFactory().getDropDBCommandCheck(), new DropDBCommandPerform(), getDropDBCommandNotation())),
    CREATEDB("createdb", new Command(new CommandCheckFactory().getCreateDBCommandCheck(), new CreateDBCommandPerform(), getCreateDBCommandNotation())),
    // System command
    EXECUTE("execute", new Command(new CommandCheckFactory().getExecuteCommandCheck(), new ExecuteCommandPerform(), getExecuteCommandNotation())),
    HELP("help", new Command(new CommandCheckFactory().getHelpCommandCheck(), new HelpCommandPerform(), getHelpCommandNotation())),
    EXIT("exit", new Command(new CommandCheckFactory().getExitCommandCheck(), new ExitCommandPerform(), getExitCommandNotation()));

    private final String commandName;
    private final Command command;

    CommandTypes(String commandName, Command command) {
        this.commandName = commandName;
        this.command = command;
    }

    public static Command getCommand(String commandName) throws CommandNotFoundException {
        try {
            return CommandTypes.valueOf(commandName.toUpperCase()).command;
        } catch (IllegalArgumentException e) {
            throw new CommandNotFoundException(commandName);
        }
    }

    public static List<String> getNotationsList() {
        return Stream.of(CommandTypes.values())
                .map(commandType -> commandType.command.getNotation())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return commandName;
    }
}
