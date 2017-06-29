package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorPage extends Action {

    public ErrorPage(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/errorPage", service, request, response);
    }

    @Override
    public void doGet() {
        forwardJSP("errorPage");
    }
}
