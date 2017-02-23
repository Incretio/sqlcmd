package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.command.notation.*;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by incre on 17.02.2017.
 */
public class HelpCommandPerform implements Performable {
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String result = "Список доступных комманд:\n";
        result += new ConnectCommandNotation().getNotation() + "\n";
        result += new TablesCommandNotation().getNotation() + "\n";
        result += new ClearCommandNotation().getNotation() + "\n";
        result += new DropCommandNotation().getNotation() + "\n";
        result += new CreateCommandNotation().getNotation() + "\n";
        result += new FindCommandNotation().getNotation() + "\n";
        result += new InsertCommandNotation().getNotation() + "\n";
        result += new UpdateCommandNotation().getNotation() + "\n";
        result += new DeleteCommandNotation().getNotation() + "\n";
        result += new HelpCommandNotation().getNotation() + "\n";
        result += new ExitCommandNotation().getNotation();
        return result;
    }
}
