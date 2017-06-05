package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.Command;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class Help extends Base {

    public Help(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws SQLException {
        StringBuilder result = new StringBuilder(takeCaption("commandList").concat(System.lineSeparator()));
        for (String notation : Command.getNotationsList()) {
            result.append(notation.concat(System.lineSeparator()));
        }

        view.write(result.toString());
    }
}
