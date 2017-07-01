package ru.incretio.juja.sqlcmd.service.actions;

import ru.incretio.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRecord extends Action {

    public UpdateRecord(Service service, HttpServletRequest request, HttpServletResponse response) {
        super("/update", service, request, response);
    }

    @Override
    public void doPost() {
        String tableName = getTableName();
        String whereColumnName = getWhereColumnName();
        String whereColumnValue = getWhereColumnValue();
        String setColumnName = getSetColumnName();
        String setColumnValue = getSetColumnValue();
        try {
            String message = service.update(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
            openMenuPage(message);
        } catch (Exception e) {
            toProcessServiceException(e);
        }
    }

    @Override
    public void doGet() {
        forwardJSP("update");
    }
}
