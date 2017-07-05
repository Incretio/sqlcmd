package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Menu extends Action {
    public Menu(Service service, HttpServletRequest request, HttpServletResponse response) {
        super(service, request, response);
    }

    @Override
    public void doGet() {
        forwardJSP("menu");
    }
}
