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
        configureRequest(req);

        try {
            switch (getActionName(req)) {
                case "/menu":
                    forwardJSP("menu", req, resp);
                    break;
                case "/closeConnection":
                    doCloseConnection(req, resp);
                    break;
                case "/help":
                    doHelp(req, resp);
                    break;
                case "/connect":
                    forwardJSP("connect", req, resp);
                    break;
                case "/select":
                    doSelect(req, resp);
                    break;
                case "/takeTablesList":
                    doTakeTablesList(req, resp);
                    break;
                case "/createTable":
                    forwardJSP("createTable", req, resp);
                    break;
                case "/dropTable":
                    forwardJSP("dropTable", req, resp);
                    break;
                case "/insert":
                    forwardJSP("insert", req, resp);
                    break;
                case "/update":
                    forwardJSP("update", req, resp);
                    break;
                case "/delete":
                    forwardJSP("delete", req, resp);
                    break;
                case "/clear":
                    forwardJSP("clear", req, resp);
                    break;
                case "/errorPage":
                    forwardJSP("errorPage", req, resp);
                    break;
                default:
                    forwardJSP("menu", req, resp);
            }
        } catch (ServiceException e) {
            toProcessServiceException(e, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        configureRequest(req);

        try {
            switch (getActionName(req)) {
                case "/connect":
                    doConnect(req, resp);
                    break;
                case "/createTable":
                    doCreateTable(req, resp);
                    break;
                case "/insert":
                    doInsert(req, resp);
                    break;
                case "/update":
                    doUpdate(req, resp);
                    break;
                case "/delete":
                    doDeleteRecord(req, resp);
                    break;
                case "/clear":
                    doClear(req, resp);
                    break;
                case "/dropTable":
                    doDropTable(req, resp);
                    break;
            }
        } catch (ServiceException e) {
            toProcessServiceException(e, req, resp);
        }
    }

    private void configureRequest(HttpServletRequest req) {
        updateService(req);
        setMenuToAttribute(req);
    }

    private void toProcessServiceException(ServiceException e, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        openErrorPage(req, resp, e);
    }

    private void doDropTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = req.getParameter("tableName");
        service.dropTable(tableName);
        redirectToMenu(resp);
    }

    private void doClear(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = req.getParameter("tableName");
        service.clear(tableName);
        redirectToMenu(resp);
    }

    private void doDeleteRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = req.getParameter("tableName");
        String whereColumnName = req.getParameter("whereColumnName");
        String whereColumnValue = req.getParameter("whereColumnValue");
        service.delete(tableName, whereColumnName, whereColumnValue);
        redirectToMenu(resp);
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = req.getParameter("tableName");
        String whereColumnName = req.getParameter("whereColumnName");
        String whereColumnValue = req.getParameter("whereColumnValue");
        String setColumnName = req.getParameter("setColumnName");
        String setColumnValue = req.getParameter("setColumnValue");
        service.update(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
        redirectToMenu(resp);
    }

    private void doInsert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = req.getParameter("tableName");
        List<String> columns = removeNullAndEmptyValues(req.getParameterValues("columns"));
        List<String> values = removeNullAndEmptyValues(req.getParameterValues("values"));
        service.insert(tableName, columns, values);
        redirectToMenu(resp);
    }

    private void doCreateTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = req.getParameter("tableName");
        List<String> columns = removeNullAndEmptyValues(req.getParameterValues("columns"));
        columns.removeAll(Collections.singleton(null));
        service.createTable(tableName, columns);
        redirectToMenu(resp);
    }

    private void doConnect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String serverName = req.getParameter("serverName");
        String dbName = req.getParameter("dbName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        req.getSession().setAttribute("service", service);
        String message = service.connect(serverName, dbName, userName, password);
        openMenuPage(req, resp, message);
    }

    private void doTakeTablesList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        req.setAttribute("tablesList", service.takeTablesList());
        String pageName = "takeTablesList";
        forwardJSP(pageName, req, resp);
    }

    private void doSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String tableName = req.getParameter("tableName");
        req.setAttribute("table", service.select(tableName));
        forwardJSP("select", req, resp);
    }

    private void doHelp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HelpCommand helpCommand = service.getHelp();
        req.setAttribute("helpHeader", helpCommand.getHeader());
        req.setAttribute("commandsDescriptions", helpCommand.getCommandsDescriptions());
        forwardJSP("help", req, resp);
    }

    private void doCloseConnection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String message = service.closeConnection();
        openMenuPage(req, resp, message);
    }

    private void forwardJSP(String pageName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(pageName + ".jsp").forward(req, resp);
    }


    private void updateService(HttpServletRequest req) {
        Service sessionService = (Service) req.getSession().getAttribute("service");
        if (sessionService != null) {
            service = sessionService;
        } else {
            service = serviceFactory.makeService();
        }
    }

    private void redirectToMenu(HttpServletResponse resp) throws IOException {
        resp.sendRedirect(resp.encodeRedirectURL("menu"));
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

        if (array == null) {
            return result;
        }

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
