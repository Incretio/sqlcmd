package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class TablesPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        String result;
        try (Statement statement = connectionConfig.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(connectionConfig.getQueryable().takeSelectTablesQuery())) {
            result = takeTableList(resultSet);
        }

        if (result.trim().isEmpty()) {
            result = takeCaption("dbEmpty");
        }
        return result;
    }

    private String takeTableList(ResultSet resultSet) throws SQLException {
        String result = "";
        while (resultSet.next()) {
            result += resultSet.getString(1).concat(System.lineSeparator());
        }
        return result;
    }
}
