package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DropTable extends Action {

    public DropTable(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/dropTable", service, request, response);
    }

    @Override
    public void doPost() {
        String tableName = request.getParameter("tableName");
        try {
            String message = service.dropTable(tableName);
            openMenuPage(message);
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    @Override
    public void doGet() {
        forwardJSP("dropTable");
    }
}
