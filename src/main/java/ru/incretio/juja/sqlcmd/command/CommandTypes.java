package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.exceptions.CommandNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.incretio.juja.sqlcmd.command.CommandCheckFactory.*;
import static ru.incretio.juja.sqlcmd.command.CommandNotationFactory.*;

public enum CommandTypes {
    CONNECT("connect", new Command(getConnectCommandCheck(), new Connect(), getConnectCommandNotation())),
    TABLES("tables", new Command(getTablesCommandCheck(), new TablesList(), getTablesCommandNotation())),
    CLEAR("clear", new Command(getClearCommandCheck(), new ClearTable(), getClearCommandNotation())),
    DROP("drop", new Command(getDropCommandCheck(), new DropTable(), getDropCommandNotation())),
    CREATE("create", new Command(getCreateCommandCheck(), new CreateTable(), getCreateCommandNotation())),
    FIND("find", new Command(getFindCommandCheck(), new SelectTable(), getFindCommandNotation())),
    INSERT("insert", new Command(getInsertCommandCheck(), new InsertRow(), getInsertCommandNotation())),
    UPDATE("update", new Command(getUpdateCommandCheck(), new UpdateRow(), getUpdateCommandNotation())),
    DELETE("delete", new Command(getDeleteCommandCheck(), new DeleteRow(), getDeleteCommandNotation())),
    EXIT("exit", new Command(getExitCommandCheck(), new ExitApp(), getExitCommandNotation())),
    CLOSE("close", new Command(getCloseCommandCheck(), new CloseConnection(), getCloseCommandNotation())),
    HELP("help", new Command(getHelpCommandCheck(), new Help(), getHelpCommandNotation())),
    EXECUTE("execute", new Command(getExecuteCommandCheck(), new ExecuteAnyQuery(), getExecuteCommandNotation())),
    DROPDB("dropdb", new Command(getDropDBCommandCheck(), new DropDB(), getDropDBCommandNotation())),
    CREATEDB("createdb", new Command(getCreateDBCommandCheck(), new CreateDB(), getCreateDBCommandNotation())),
    TABLE_EXIST("table_exist", new Command(getTableExistCommandCheck(), new TableExists(), getTableExistCommandNotation())),
    COLUMNS("columns", new Command(getColumnsCommandCheck(), new ColumnsList(), getColumnsCommandNotation())),
    COLUMN_EXIST("column_exist", new Command(getColumnExistCommandCheck(), new ColumnExists(), getColumnExistCommandNotation()));

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
        List<String> result = Stream.of(CommandTypes.values())
                .map(commandType -> commandType.command.getNotation())
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public String toString() {
        return commandName;
    }
}
