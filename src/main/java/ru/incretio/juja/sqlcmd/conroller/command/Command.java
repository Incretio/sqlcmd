package ru.incretio.juja.sqlcmd.conroller.command;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.conroller.command.list.*;
import ru.incretio.juja.sqlcmd.exceptions.CommandNotFoundException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.incretio.juja.sqlcmd.conroller.command.CommandCheckFactory.*;
import static ru.incretio.juja.sqlcmd.conroller.command.CommandNotationFactory.*;

public enum Command implements Notationable, Checkable, Performable {
    CONNECT(new Connect(makeConnectCheck(), makeConnectNotation())),
    CLOSE(new CloseConnection(makeCloseCommandCheck(), makeCloseCommandNotation())),
    TABLES(new TablesList(makeTablesCommandCheck(), makeTablesCommandNotation())),
    CREATE(new CreateTable(makeCreateCommandCheck(), makeCreateCommandNotation())),
    INSERT(new InsertRecord(makeInsertCommandCheck(), makeInsertCommandNotation())),
    UPDATE(new UpdateRecords(makeUpdateCommandCheck(), makeUpdateCommandNotation())),
    DELETE(new DeleteRecords(makeDeleteCommandCheck(), makeDeleteCommandNotation())),
    FIND(new SelectTable(makeFindCommandCheck(), makeFindCommandNotation())),
    CLEAR(new ClearTable(makeClearCommandCheck(), makeClearCommandNotation())),
    DROP(new DropTable(makeDropCommandCheck(), makeDropCommandNotation())),
    HELP(new Help(makeHelpCommandCheck(), makeHelpCommandNotation())),
    CREATEDB(new CreateDB(makeCreateDBCommandCheck(), makeEmptyNotation())),
    DROPDB(new DropDB(makeDropDBCommandCheck(), makeEmptyNotation())),
    EXIT(new ExitApplication(makeExitCommandCheck(), makeEmptyNotation())),
    TABLE_EXIST(new TableExists(makeTableExistCommandCheck(), makeEmptyNotation())),
    COLUMN_EXIST(new ColumnExists(makeColumnExistCommandCheck(), makeEmptyNotation())),
    COLUMNS(new ColumnsList(makeColumnsCommandCheck(), makeEmptyNotation())),
    EXECUTE(new ExecuteQuery(makeExecuteCommandCheck(), makeEmptyNotation()));

    private final Base base;

    Command(Base base) {
        this.base = base;
    }

    @Override
    public String getNotation() {
        return base.getNotation();
    }

    @Override
    public boolean checkParams(List<String> params) {
        return base.checkParams(params);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        base.perform(model, view, params);
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
                .filter(command -> !command.getNotation().isEmpty())
                .map(Command::getNotation)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
