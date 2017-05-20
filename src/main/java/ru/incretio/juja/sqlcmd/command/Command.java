package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.exceptions.CommandNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.incretio.juja.sqlcmd.command.CommandCheckFactory.*;
import static ru.incretio.juja.sqlcmd.command.CommandNotationFactory.*;

public enum Command implements Notationable, Checkable, Performable {
    CONNECT(new ConnectPerform(), makeConnectCheck(), makeConnectNotation()),
    CLOSE(new ClosePerform(), makeCloseCommandCheck(), makeCloseCommandNotation()),
    TABLES(new TablesPerform(), makeTablesCommandCheck(), makeTablesCommandNotation()),
    COLUMNS(new ColumnsPerform(),makeColumnsCommandCheck(), makeColumnsCommandNotation()),
    CREATE(new CreatePerform(),makeCreateCommandCheck(), makeCreateCommandNotation()),
    DROP(new DropPerform(),makeDropCommandCheck(), makeDropCommandNotation()),
    CLEAR(new ClearPerform(),makeClearCommandCheck(), makeClearCommandNotation()),
    FIND(new FindPerform(),makeFindCommandCheck(), makeFindCommandNotation()),
    INSERT(new InsertPerform(),makeInsertCommandCheck(), makeInsertCommandNotation()),
    UPDATE(new UpdatePerform(),makeUpdateCommandCheck(), makeUpdateCommandNotation()),
    DELETE(new DeletePerform(),makeDeleteCommandCheck(), makeDeleteCommandNotation()),
    TABLE_EXIST(new TableExistsPerform(),makeTableExistCommandCheck(), makeTableExistCommandNotation()),
    COLUMN_EXIST(new ColumnExistsPerform(),makeColumnExistCommandCheck(), makeColumnExistCommandNotation()),
    DROPDB(new DropDBPerform(),makeDropDBCommandCheck(), makeDropDBCommandNotation()),
    CREATEDB(new CreateDBPerform(),makeCreateDBCommandCheck(), makeCreateDBCommandNotation()),
    EXECUTE(new ExecutePerform(),makeExecuteCommandCheck(), makeExecuteCommandNotation()),
    HELP(new HelpPerform(),makeHelpCommandCheck(), makeHelpCommandNotation()),
    EXIT(new ExitPerform(),makeExitCommandCheck(), makeExitCommandNotation());

    private final Notationable notationable;
    private final Checkable checkable;
    private final Performable performable;

    Command(Performable performable, Checkable checkable, Notationable notationable) {
        this.performable = performable;
        this.notationable = notationable;
        this.checkable = checkable;
    }

    @Override
    public String getNotation() {
        return notationable.getNotation();
    }

    @Override
    public boolean checkParams(List<String> params) {
        return checkable.checkParams(params);
    }

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        return performable.perform(connectionConfig, params);
    }

    public static Command takeByName(String commandName) throws CommandNotFoundException {
        try {
            return Command.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CommandNotFoundException(commandName);
        }
    }

    public static List<String> getNotationsList() {
        return Stream.of(Command.values())
                .map(Command::getNotation)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
