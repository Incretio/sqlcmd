package ru.incretio.juja.sqlcmd.conroller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.incretio.juja.sqlcmd.conroller.utils.HelpCommand;
import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.ServiceException;
import ru.incretio.juja.sqlcmd.service.ServiceFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        String action = getActionName(req);

        updateService(req);

        setMenuToAttribute(req);

        if (action.startsWith("/menu")) {
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } else if (action.startsWith("/closeConnection")) {
            try {
                String message = service.closeConnection();
                openMenuPage(req, resp, message);
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }
        } else if (action.startsWith("/help")) {
            HelpCommand helpCommand = service.getHelp();
            req.setAttribute("helpHeader", helpCommand.getHeader());
            req.setAttribute("commandsDescriptions", helpCommand.getCommandsDescriptions());
            req.getRequestDispatcher("help.jsp").forward(req, resp);
        } else if (action.startsWith("/connect")) {
            req.getRequestDispatcher("connect.jsp").forward(req, resp);
        } else if (action.startsWith("/select")) {
            String tableName = req.getParameter("tableName");
            try {
                req.setAttribute("table", service.select(tableName));
                req.getRequestDispatcher("select.jsp").forward(req, resp);
            } catch (Exception e) {
                openErrorPage(req, resp, e);
            }
        } else if (action.startsWith("/takeTablesList")) {
            try {
                req.setAttribute("tablesList", service.takeTablesList());
                req.getRequestDispatcher("takeTablesList.jsp").forward(req, resp);
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }
        } else if (action.startsWith("/createTable")) {
            req.getRequestDispatcher("createTable.jsp").forward(req, resp);
        } else if (action.startsWith("/dropTable")) {
            req.getRequestDispatcher("dropTable.jsp").forward(req, resp);
        } else if (action.startsWith("/insert")) {
            req.getRequestDispatcher("insert.jsp").forward(req, resp);
        } else if (action.startsWith("/update")) {
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        } else if (action.startsWith("/delete")) {
            req.getRequestDispatcher("delete.jsp").forward(req, resp);
        } else if (action.startsWith("/clear")) {
            req.getRequestDispatcher("clear.jsp").forward(req, resp);
        } else if (action.startsWith("/errorPage")) {
            req.getRequestDispatcher("errorPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("missingPage.jsp").forward(req, resp);
        }
    }

    private void updateService(HttpServletRequest req) {
        Service sessionService = (Service) req.getSession().getAttribute("service");
        if (sessionService != null) {
            service = sessionService;
        } else {
            service = serviceFactory.makeService();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateService(req);
        String action = getActionName(req);
        setMenuToAttribute(req);

        if (action.startsWith("/connect")) {
            String serverName = req.getParameter("serverName");
            String dbName = req.getParameter("dbName");
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            try {
                req.getSession().setAttribute("service", service);
                String message = service.connect(serverName, dbName, userName, password);
                openMenuPage(req, resp, message);
            } catch (Exception e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/createTable")) {
            String tableName = req.getParameter("tableName");
            List<String> columns = removeNullAndEmptyValues(req.getParameterValues("columns"));
            columns.removeAll(Collections.singleton(null));
            try {
                service.createTable(tableName, columns);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/insert")) {
            String tableName = req.getParameter("tableName");
            List<String> columns = removeNullAndEmptyValues(req.getParameterValues("columns"));
            List<String> values = removeNullAndEmptyValues(req.getParameterValues("values"));
            try {
                service.insert(tableName, columns, values);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/update")) {
            String tableName = req.getParameter("tableName");
            String whereColumnName = req.getParameter("whereColumnName");
            String whereColumnValue = req.getParameter("whereColumnValue");
            String setColumnName = req.getParameter("setColumnName");
            String setColumnValue = req.getParameter("setColumnValue");
            try {
                service.update(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/delete")) {
            String tableName = req.getParameter("tableName");
            String whereColumnName = req.getParameter("whereColumnName");
            String whereColumnValue = req.getParameter("whereColumnValue");
            try {
                service.delete(tableName, whereColumnName, whereColumnValue);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/clear")) {
            String tableName = req.getParameter("tableName");
            try {
                service.clear(tableName);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/dropTable")) {
            String tableName = req.getParameter("tableName");
            try {
                service.dropTable(tableName);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (ServiceException e) {
                openErrorPage(req, resp, e);
            }

        }
    }

    private void openMenuPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }

    private void openErrorPage(HttpServletRequest req, HttpServletResponse resp, Exception e) throws ServletException, IOException {
        e.printStackTrace();
        req.setAttribute("message", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
        req.getRequestDispatcher("errorPage.jsp").forward(req, resp);
    }

    private List<String> removeNullAndEmptyValues(String[] array) {
        List<String> result = new ArrayList<>();
        for (String value : array) {
            if (value != null && !value.isEmpty()) {
                result.add(value);
            }
        }
        return result;
    }

    private String getActionName(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }

    private void setMenuToAttribute(HttpServletRequest req) {
        req.setAttribute("items", service.commandsList());
    }
}
