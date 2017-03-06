package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UpdateCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String tableName = params.get(0);
        String whereColumnName = params.get(1);
        String whereColumnValue = params.get(2);
        String setColumnName = params.get(3);
        String setColumnValue = params.get(4);

        String result = "";
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            try {
                statement.execute(connectionConfig.getQuerable().getUpdateQuery(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue));
                result = "В таблице " + tableName + " обновлена запись.";
            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }
}