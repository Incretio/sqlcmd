package ru.incretio.juja.sqlcmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.ServiceFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private Service service;
    @Autowired
    private ServiceFactory serviceFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        configureRequest(req);
        new CommandPerformer(service).performGetFunction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        configureRequest(req);
        new CommandPerformer(service).performPostFunction(req, resp);
    }

    private void configureRequest(HttpServletRequest req) {
        updateService(req);
        setMenuToAttribute(req);
    }

    private void updateService(HttpServletRequest req) {
        Service sessionService = (Service) req.getSession().getAttribute("service");
        if (sessionService != null) {
            service = sessionService;
        } else {
            service = serviceFactory.makeService();
        }
    }

    private void setMenuToAttribute(HttpServletRequest req) {
        req.setAttribute("items", service.commandsList());
    }
}
