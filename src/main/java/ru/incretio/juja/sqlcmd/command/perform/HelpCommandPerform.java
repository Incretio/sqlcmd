package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by incre on 17.02.2017.
 */
public class HelpCommandPerform implements Performable {
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String result = "Список доступных комманд:\n";

        for (String notation : CommandTypes.getNotationsList()) {
            result += notation + "\n";

        }

        return result;
    }
}
