package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import java.sql.SQLException;
import java.util.List;

public class HelpCommandPerform implements Performable {
    private final String commandsListText = "Список доступных комманд:\n";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String result = commandsListText;

        for (String notation : CommandTypes.getNotationsList()) {
            result += notation + "\n";
        }

        return result;
    }
}
