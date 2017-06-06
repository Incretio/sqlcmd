package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
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
        int serverNameIndex = 0;
        int dbNameIndex = 1;
        int userNameIndex = 2;
        int passwordIndex = 3;
        String serverName = params.get(serverNameIndex);
        String dbName = params.get(dbNameIndex);
        String userName = params.get(userNameIndex);
        String password = params.get(passwordIndex);

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
