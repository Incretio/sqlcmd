package ru.incretio.juja.sqlcmd.conroller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.incretio.juja.sqlcmd.conroller.utils.HelpCommand;
import ru.incretio.juja.sqlcmd.exceptions.CommandException;
import ru.incretio.juja.sqlcmd.exceptions.MissingAnyDataException;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;
import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.ServiceFactory;
import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

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

        Service sessionService = (Service) req.getSession().getAttribute("service");
        if (sessionService != null) {
            service = sessionService;
        } else {
            service = serviceFactory.makeService();
        }

        req.setAttribute("items", service.commandsList());

        if (action.startsWith("/menu")) {
            req.setAttribute("items", service.commandsList());
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } else if (action.startsWith("/closeConnection")) {
            try {
                service.closeConnection();
            } catch (MissingConnectionException | SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(resp.encodeRedirectURL("menu"));
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("select.jsp").forward(req, resp);
        } else if (action.startsWith("/takeTablesList")) {
            try {
                req.setAttribute("tablesList", service.takeTablesList());
            } catch (MissingConnectionException | SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("takeTablesList.jsp").forward(req, resp);
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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getActionName(req);

        if (action.startsWith("/connect")) {
            String serverName = req.getParameter("serverName");
            String dbName = req.getParameter("dbName");
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            try {
                req.getSession().setAttribute("service", service);
                service.connect(serverName, dbName, userName, password);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
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
            } catch (MissingConnectionException | SQLException e) {
                openErrorPage(req, resp, e);
            }
        } else if (action.startsWith("/insert")) {
            String tableName = req.getParameter("tableName");
            List<String> columns = removeNullAndEmptyValues(req.getParameterValues("columns"));
            List<String> values = removeNullAndEmptyValues(req.getParameterValues("values"));
            try {
                service.insert(tableName, columns, values);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (MissingConnectionException | SQLException e) {
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
            } catch (MissingConnectionException | SQLException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/delete")) {
            String tableName = req.getParameter("tableName");
            String whereColumnName = req.getParameter("whereColumnName");
            String whereColumnValue = req.getParameter("whereColumnValue");
            try {
                service.delete(tableName, whereColumnName, whereColumnValue);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (MissingConnectionException | SQLException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/clear")) {
            String tableName = req.getParameter("tableName");
            try {
                service.clear(tableName);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (MissingConnectionException | SQLException e) {
                openErrorPage(req, resp, e);
            }

        } else if (action.startsWith("/dropTable")) {
            String tableName = req.getParameter("tableName");
            try {
                service.dropTable(tableName);
                resp.sendRedirect(resp.encodeRedirectURL("menu"));
            } catch (MissingConnectionException | SQLException e) {
                openErrorPage(req, resp, e);
            }

        }
    }

    private void openErrorPage(HttpServletRequest req, HttpServletResponse resp, Exception e) throws ServletException, IOException {
        String exceptionDescription = getExceptionDescription(e);
        req.setAttribute("message", exceptionDescription);
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

    private String getExceptionDescription(Exception exception) {
        String result;
        try {
            exception.printStackTrace();
            throw exception;
        } catch (CommandException | MissingAnyDataException | NeedExitException e) {
            result = e.getMessage();
        } catch (SQLException e) {
            result = String.format(takeCaption("sqlErrorPattern"),
                    e.getMessage().replace("\n", System.lineSeparator()));
        } catch (Exception e) {
            result = takeCaption("badAppConfiguration");
            AppLogger.warning(e.getMessage().concat(": ").concat(Arrays.toString(e.getStackTrace())));
        }
        return result;
    }

    private String getActionName(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
