package ru.incretio.juja.sqlcmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    private Service service;
    @Autowired
    private ServiceFactory serviceFactory;

    @RequestMapping(value = {"/", "/menu"}, method = RequestMethod.GET)
    public String menu(HttpServletRequest request) {
        return "menu";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help(HttpServletRequest request) {
        configureRequest(request);
        return "help";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clearGet(HttpServletRequest request) {
        configureRequest(request);
        return "clear";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public String clearPost(HttpServletRequest request) {
        String tableName = request.getParameter("tableName");
        try {
            String message = service.clear(tableName);
            return openMenuPage(request, message);
        } catch (Exception e) {
            return toProcessServiceException(request, e);
        }
    }

    private void configureRequest(HttpServletRequest req) {
        updateService(req);
        setMenuToAttribute(req);
    }

    protected void updateService(HttpServletRequest req) {
        Service sessionService = (Service) req.getSession().getAttribute("service");
        if (sessionService != null) {
            service = sessionService;
        } else {
            service = serviceFactory.makeService();
        }
    }

    private void setMenuToAttribute(HttpServletRequest request) {
        request.setAttribute("items", service.commandsList());
    }

    protected String openMenuPage(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
        return "redirect:/menu";
    }

    protected String toProcessServiceException(HttpServletRequest request, Exception e) {
        request.setAttribute("message", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
        return "errorPage";
    }
}
