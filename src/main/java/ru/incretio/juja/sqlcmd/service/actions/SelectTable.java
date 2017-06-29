package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectTable extends Action {

    public SelectTable(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/select", service, request, response);
    }

    @Override
    public void doGet() {
        String tableName = getTableName();
        try {
            request.setAttribute("table", service.select(tableName));
            forwardJSP("select");
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }
}
