package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRecord extends Action {

    public DeleteRecord(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/delete", service, request, response);
    }

    @Override
    public void doPost() {
        String tableName = getTableName();
        String whereColumnName = getWhereColumnName();
        String whereColumnValue = getWhereColumnValue();
        try {
            service.delete(tableName, whereColumnName, whereColumnValue);
            redirectToMenu();
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    @Override
    public void doGet() {
        forwardJSP("delete");
    }
}
