package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.exceptions.CommandNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ru.incretio.juja.sqlcmd.command.CommandCheckFactory.*;
import static ru.incretio.juja.sqlcmd.command.CommandNotationFactory.*;

public enum CommandTypes {
    CONNECT("connect", new Command(getConnectCommandCheck(), new ConnectCommandPerform(), getConnectCommandNotation())),
    TABLES("tables", new Command(getTablesCommandCheck(), new TablesCommandPerform(), getTablesCommandNotation())),
    CLEAR("clear", new Command(getClearCommandCheck(), new ClearCommandPerform(), getClearCommandNotation())),
    DROP("drop", new Command(getDropCommandCheck(), new DropCommandPerform(), getDropCommandNotation())),
    CREATE("create", new Command(getCreateCommandCheck(), new CreateCommandPerform(), getCreateCommandNotation())),
    FIND("find", new Command(getFindCommandCheck(), new FindCommandPerform(), getFindCommandNotation())),
    INSERT("insert", new Command(getInsertCommandCheck(), new InsertCommandPerform(), getInsertCommandNotation())),
    UPDATE("update", new Command(getUpdateCommandCheck(), new UpdateCommandPerform(), getUpdateCommandNotation())),
    DELETE("delete", new Command(getDeleteCommandCheck(), new DeleteCommandPerform(), getDeleteCommandNotation())),
    EXIT("exit", new Command(getExitCommandCheck(), new ExitCommandPerform(), getExitCommandNotation())),
    CLOSE("close", new Command(getCloseCommandCheck(), new CloseCommandPerform(), getCloseCommandNotation())),
    HELP("help", new Command(getHelpCommandCheck(), new HelpCommandPerform(), getHelpCommandNotation())),
    EXECUTE("execute", new Command(getExecuteCommandCheck(), new ExecuteCommandPerform(), getExecuteCommandNotation())),
    DROPDB("dropdb", new Command(getDropDBCommandCheck(), new DropDBCommandPerform(), getDropDBCommandNotation())),
    CREATEDB("createdb", new Command(getCreateDBCommandCheck(), new CreateDBCommandPerform(), getCreateDBCommandNotation())),
    TABLE_EXIST("table_exist", new Command(getTableExistCommandCheck(), new TableExistCommandPerform(), getTableExistCommandNotation())),
    COLUMNS("columns", new Command(getColumnsCommandCheck(), new ColumnsCommandPerform(), getColumnsCommandNotation())),
    COLUMN_EXIST("column_exist", new Command(getColumnExistCommandCheck(), new ColumnExistCommandPerform(), getColumnExistCommandNotation()));

    private String commandName;
    private Command command;

    CommandTypes(String commandName, Command command) {
        this.commandName = commandName;
        this.command = command;
    }

    public static Command getCommand(String commandName) throws CommandNotFoundException {
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
