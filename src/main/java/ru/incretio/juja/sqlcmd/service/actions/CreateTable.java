package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class CreateTable extends Action {

    public CreateTable(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/createTable", service, request, response);
    }

    @Override
    public void doPost() {
        String tableName = getTableName();
        List<String> columns = removeNullAndEmptyValues(request.getParameterValues("columns"));
        columns.removeAll(Collections.singleton(null));
        try {
            service.createTable(tableName, columns);
            redirectToMenu();
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    @Override
    public void doGet() {
        forwardJSP("createTable");
    }
}
