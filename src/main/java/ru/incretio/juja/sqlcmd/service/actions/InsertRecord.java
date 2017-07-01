package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InsertRecord extends Action {

    public InsertRecord(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/insert", service, request, response);
    }

    @Override
    public void doPost() {
        String tableName = request.getParameter("tableName");
        List<String> columns = removeNullAndEmptyValues(request.getParameterValues("columns"));
        List<String> values = removeNullAndEmptyValues(request.getParameterValues("values"));
        try {
            String message = service.insert(tableName, columns, values);
            openMenuPage(message);
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    @Override
    public void doGet() {
        forwardJSP("insert");
    }
}
