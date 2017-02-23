package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DeleteCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String tableName = params.get(0);
        String whereColumnName = params.get(1);
        String whereColumnValue = params.get(2);

        Statement statement = connectionConfig.getConnection().createStatement();
        String result = "";
        try {
            statement.execute(connectionConfig.getQuerable().getDeleteQuery(tableName, whereColumnName, whereColumnValue));
            result = "Из таблицы " + tableName + " удалена запись.";
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }
}
