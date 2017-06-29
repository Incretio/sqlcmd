package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAction extends Action{
    public DefaultAction(Service service, HttpServletRequest req, HttpServletResponse resp) {
        super("/menu", service, req, resp);
    }

    @Override
    public void doGet() {
        forwardJSP("menu");
    }
}
