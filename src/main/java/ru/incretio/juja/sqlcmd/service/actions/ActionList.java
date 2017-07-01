package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.exceptions.UnsupportedActionException;
import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;

public enum ActionList {
    MENU(Menu.class, "/menu"),
    CONNECT(Connect.class, "/connect"),
    CLOSE_CONNECTION(CloseConnection.class, "/closeConnection"),
    SELECT_TABLE(SelectTable.class, "/select"),
    CLEAR_TABLE(ClearTable.class, "/clear"),
    TABLES_LIST(TablesList.class, "/takeTablesList"),
    CREATE_TABLE(CreateTable.class, "/createTable"),
    DELETE_RECORD(DeleteRecord.class, "/delete"),
    DROP_TABLE(DropTable.class, "/dropTable"),
    ERROR_PAGE(ErrorPage.class, "/errorPage"),
    HELP(Help.class, "/help"),
    INSERT_RECORD(InsertRecord.class, "/insert"),
    UPDATE_RECORD(UpdateRecord.class, "/update"),
    DEFAULT_ACTION(DefaultAction.class, "/menu");

    private final Class actionClass;
    private final String url;

    ActionList(Class action, String url) {
        this.actionClass = action;
        this.url = url;
    }

    public static Action getAction(String url, Service service, HttpServletRequest request, HttpServletResponse response) throws UnsupportedActionException {
        Class currentActionClass = getActionClass(url);
        try {
            Constructor constructor =
                    currentActionClass.getDeclaredConstructor(Service.class, HttpServletRequest.class, HttpServletResponse.class);
            return (Action) constructor.newInstance(service, request, response);
        } catch (Exception e) {
            throw new UnsupportedActionException(e);
        }
    }

    private static Class getActionClass(String url) {
        for (ActionList actionList : ActionList.values()) {
            if (actionList.url.equalsIgnoreCase(url)) {
                return actionList.actionClass;
            }
        }

        return DEFAULT_ACTION.actionClass;
    }
}
