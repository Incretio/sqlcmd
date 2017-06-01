package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class Connect extends Base {

    public Connect(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws SQLException, MissingConnectionException, ClassNotFoundException {
        int serverNameInd = 0;
        int dbNameInd = 1;
        int userNameInd = 2;
        int passwordInd = 3;
        String serverName = params.get(serverNameInd);
        String dbName = params.get(dbNameInd);
        String userName = params.get(userNameInd);
        String password = params.get(passwordInd);

        String result;
        try {
            model.connect(serverName, dbName, userName, password);
            result = String.format(takeCaption("connectionSuccessPattern"), dbName);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(takeCaption("driverLoadingErrorText"));
        }

        view.write(result);
    }

}
