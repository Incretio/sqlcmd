package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import java.sql.SQLException;
import java.util.List;

public class Help implements Performable {
    private final static String COMMANDS_LIST_TEXT = "Список доступных комманд:\n";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String result = COMMANDS_LIST_TEXT;
        for (String notation : CommandTypes.getNotationsList()) {
            result += notation + "\n";
        }

        return result;
    }
}
