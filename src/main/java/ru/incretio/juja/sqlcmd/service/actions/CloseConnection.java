package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CloseConnection extends Action {

    public CloseConnection(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/closeConnection", service, request, response);
    }

    @Override
    public void doGet() {
        try {
            String message = service.closeConnection();
            openMenuPage(message);
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }
}
