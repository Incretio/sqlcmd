package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TablesList extends Action {

    public TablesList(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/takeTablesList", service, request, response);
    }

    @Override
    public void doGet() {
        try {
            request.setAttribute("tablesList", service.takeTablesList());
        } catch (Exception e) {
            toProcessServiceException(e);
        }
        forwardJSP("takeTablesList");
    }
}
