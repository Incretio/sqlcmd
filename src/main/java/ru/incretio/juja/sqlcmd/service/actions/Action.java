package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class Action {
    protected final Service service;
    protected final HttpServletRequest request;
    protected final HttpServletResponse response;

    public Action(Service service, HttpServletRequest request, HttpServletResponse response) {
        this.service = service;
        this.request = request;
        this.response = response;
    }

    public void doPost() {
        // do nothing
    }

    public void doGet() {
        // do nothing
    }


    protected void forwardJSP(String pageName) {
        try {
            request.getRequestDispatcher(pageName + ".jsp").forward(request, response);
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    protected void toProcessServiceException(Exception e) {
        try {
            openErrorPage(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    protected void openErrorPage(Exception e) {
        e.printStackTrace();
        request.setAttribute("message", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
        try {
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (Exception e1) {
            toProcessServiceException(e1);
        }
    }

    protected List<String> removeNullAndEmptyValues(String[] array) {
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

    protected void redirectToMenu() {
        try {
            response.sendRedirect(response.encodeRedirectURL("menu"));
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    protected void openMenuPage(String message) {
        request.setAttribute("message", message);
        try {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    protected String getSetColumnValue() {
        return request.getParameter("setColumnValue");
    }

    protected String getSetColumnName() {
        return request.getParameter("setColumnName");
    }

    protected String getWhereColumnValue() {
        return request.getParameter("whereColumnValue");
    }

    protected String getWhereColumnName() {
        return request.getParameter("whereColumnName");
    }

    protected String getTableName() {
        return request.getParameter("tableName");
    }

    protected String getPassword() {
        return request.getParameter("password");
    }

    protected String getUserName() {
        return request.getParameter("userName");
    }

    protected String getDbName() {
        return request.getParameter("dbName");
    }

    protected String getServerName() {
        return request.getParameter("serverName");
    }

}
