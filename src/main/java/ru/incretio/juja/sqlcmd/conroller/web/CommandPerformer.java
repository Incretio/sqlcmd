package ru.incretio.juja.sqlcmd.conroller.web;

import ru.incretio.juja.sqlcmd.conroller.utils.HelpCommand;
import ru.incretio.juja.sqlcmd.service.Service;
import ru.incretio.juja.sqlcmd.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandPerformer {

    private Service service;

    public CommandPerformer(Service service) {
        this.service = service;
    }

    public void performGetFunction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    public void performPostFunction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void toProcessServiceException(ServiceException e, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        openErrorPage(req, resp, e);
    }

    private void doDropTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = getTableName(req);
        service.dropTable(tableName);
        redirectToMenu(resp);
    }

    private void doClear(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = getTableName(req);
        service.clear(tableName);
        redirectToMenu(resp);
    }

    private void doDeleteRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = getTableName(req);
        String whereColumnName = getWhereColumnName(req);
        String whereColumnValue = getWhereColumnValue(req);
        service.delete(tableName, whereColumnName, whereColumnValue);
        redirectToMenu(resp);
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = getTableName(req);
        String whereColumnName = getWhereColumnName(req);
        String whereColumnValue = getWhereColumnValue(req);
        String setColumnName = getSetColumnName(req);
        String setColumnValue = getSetColumnValue(req);
        service.update(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
        redirectToMenu(resp);
    }

    private void doInsert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = getTableName(req);
        List<String> columns = removeNullAndEmptyValues(req.getParameterValues("columns"));
        List<String> values = removeNullAndEmptyValues(req.getParameterValues("values"));
        service.insert(tableName, columns, values);
        redirectToMenu(resp);
    }

    private void doCreateTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        String tableName = getTableName(req);
        List<String> columns = removeNullAndEmptyValues(req.getParameterValues("columns"));
        columns.removeAll(Collections.singleton(null));
        service.createTable(tableName, columns);
        redirectToMenu(resp);
    }

    private void doConnect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String serverName = getServerName(req);
        String dbName = getDbName(req);
        String userName = getUserName(req);
        String password = getPassword(req);
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
        String tableName = getTableName(req);
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

    private String getSetColumnValue(HttpServletRequest req) {
        return req.getParameter("setColumnValue");
    }

    private String getSetColumnName(HttpServletRequest req) {
        return req.getParameter("setColumnName");
    }

    private String getWhereColumnValue(HttpServletRequest req) {
        return req.getParameter("whereColumnValue");
    }

    private String getWhereColumnName(HttpServletRequest req) {
        return req.getParameter("whereColumnName");
    }

    private String getTableName(HttpServletRequest req) {
        return req.getParameter("tableName");
    }

    private String getPassword(HttpServletRequest req) {
        return req.getParameter("password");
    }

    private String getUserName(HttpServletRequest req) {
        return req.getParameter("userName");
    }

    private String getDbName(HttpServletRequest req) {
        return req.getParameter("dbName");
    }

    private String getServerName(HttpServletRequest req) {
        return req.getParameter("serverName");
    }

}
