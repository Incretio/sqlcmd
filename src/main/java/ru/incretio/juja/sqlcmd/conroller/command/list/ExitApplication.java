package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ExitApplication extends Base {

    public ExitApplication(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        String result = "";
        if (model.isConnected()) {
            model.closeConnection();
            result = takeCaption("dbDisconnected").concat(" ");
        }

        result += takeCaption("appClose");
        throw new NeedExitException(result);
    }
}