package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connect extends Action {

    public Connect(Service service, HttpServletRequest request, HttpServletResponse response) {
        super(service, request, response);
    }

    @Override
    public void doPost() {
        String serverName = getServerName();
        String dbName = getDbName();
        String userName = getUserName();
        String password = getPassword();
        request.getSession().setAttribute("service", service);
        String message;
        try {
            message = service.connect(serverName, dbName, userName, password);
            openMenuPage(message);
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    @Override
    public void doGet() {
        forwardJSP("connect");
    }
}
