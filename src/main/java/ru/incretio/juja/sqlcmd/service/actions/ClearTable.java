package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearTable extends Action {

    public ClearTable(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/clearTable", service, request, response);
    }

    @Override
    public void doPost() {
        String tableName = getTableName();
        try {
            service.clear(tableName);
            redirectToMenu();
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    @Override
    public void doGet() {
        forwardJSP("clear");
    }
}
