package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TablesCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String result = "";
        try (Statement statement = connectionConfig.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(connectionConfig.getQuerable().getSelectTablesQuery())) {
            while (resultSet.next()) {
                result += resultSet.getString(1) + "\n";
            }

        }
        return result;
    }
}
