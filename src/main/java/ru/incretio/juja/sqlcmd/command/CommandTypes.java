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
    CONNECT("connect", new Command(getConnectCommandCheck(), new Connect(), getConnectCommandNotation())),
    CLOSE("close", new Command(getCloseCommandCheck(), new CloseConnection(), getCloseCommandNotation())),
    // Tables command
    TABLES("tables", new Command(getTablesCommandCheck(), new TablesList(), getTablesCommandNotation())),
    COLUMNS("columns", new Command(getColumnsCommandCheck(), new ColumnsList(), getColumnsCommandNotation())),
    CREATE("create", new Command(getCreateCommandCheck(), new CreateTable(), getCreateCommandNotation())),
    DROP("drop", new Command(getDropCommandCheck(), new DropTable(), getDropCommandNotation())),
    CLEAR("clear", new Command(getClearCommandCheck(), new ClearTable(), getClearCommandNotation())),
    FIND("find", new Command(getFindCommandCheck(), new SelectTable(), getFindCommandNotation())),
    INSERT("insert", new Command(getInsertCommandCheck(), new InsertRow(), getInsertCommandNotation())),
    UPDATE("update", new Command(getUpdateCommandCheck(), new UpdateRow(), getUpdateCommandNotation())),
    DELETE("delete", new Command(getDeleteCommandCheck(), new DeleteRow(), getDeleteCommandNotation())),
    // Exist command
    TABLE_EXIST("table_exist", new Command(getTableExistCommandCheck(), new TableExists(), getTableExistCommandNotation())),
    COLUMN_EXIST("column_exist", new Command(getColumnExistCommandCheck(), new ColumnExists(), getColumnExistCommandNotation())),
    // DB command
    DROPDB("dropdb", new Command(getDropDBCommandCheck(), new DropDB(), getDropDBCommandNotation())),
    CREATEDB("createdb", new Command(getCreateDBCommandCheck(), new CreateDB(), getCreateDBCommandNotation())),
    // System command
    EXECUTE("execute", new Command(getExecuteCommandCheck(), new ExecuteAnyQuery(), getExecuteCommandNotation())),
    HELP("help", new Command(getHelpCommandCheck(), new Help(), getHelpCommandNotation())),
    EXIT("exit", new Command(getExitCommandCheck(), new ExitApp(), getExitCommandNotation()));

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
