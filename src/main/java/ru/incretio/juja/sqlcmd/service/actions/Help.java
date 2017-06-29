package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Help extends Action {

    public Help(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/help", service, request, response);
    }

    @Override
    public void doGet() {
        String helpText = "This is enjoy project.";
        request.setAttribute("helpText", helpText);
        try {
            forwardJSP("help");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
