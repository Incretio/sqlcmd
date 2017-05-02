package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ColumnsCommandPerform implements Performable {
    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception {
        String tableName = params.get(0);
        String result = "";

        try (Statement statement = connectionConfig.testAndGetConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(connectionConfig.getQuerable().getSelectTableColumnsQuery(tableName))) {
            while (resultSet.next()) {
                result += resultSet.getString(1) + "\n";
            }
        }

        if (result.trim().isEmpty()){
            result = "В таблице нет полей.";
        }
        return result;
    }
}
