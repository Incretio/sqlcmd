package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import java.sql.SQLException;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class HelpPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String result = takeCaption("commandList").concat(System.lineSeparator());
        for (String notation : Command.getNotationsList()) {
            result += notation.concat(System.lineSeparator());
        }

        return result;
    }
}
