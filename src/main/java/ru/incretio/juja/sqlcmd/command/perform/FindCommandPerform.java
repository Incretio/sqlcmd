package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommandPerform implements Performable {

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException {
        String tableName = params.get(0);

        String result = "";
        try (Statement statement = connectionConfig.getConnection().createStatement()) {
            try {
                ResultSet resultSet = statement.executeQuery(connectionConfig.getQuerable().getSelectQuery(tableName));
                ResultSetMetaData metaData = resultSet.getMetaData();

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    result += String.format("+-%" + (metaData.getColumnDisplaySize(i)) + "s", "").replace(" ", "-");
                }
                result += "+\n";
                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    result += String.format("+ %s%" + (metaData.getColumnDisplaySize(i) - metaData.getColumnLabel(i).length()) + "s", metaData.getColumnLabel(i), "");
                }
                result += "+\n";
                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    result += String.format("+-%" + (metaData.getColumnDisplaySize(i)) + "s", "").replace(" ", "-");
                }
                result += "+\n";

                while (resultSet.next()) {
                    for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                        int columnSize = metaData.getColumnDisplaySize(i);
                        int valueLength = (resultSet.getString(i) == null) ? 0 : resultSet.getString(i).length();
                        String value = (resultSet.getString(i) == null) ? "" : resultSet.getString(i);
                        result += String.format("+ %s%" + (columnSize - valueLength) + "s", value, "");
                    }
                    result += "+\n";
                }

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    result += String.format("+-%" + (metaData.getColumnDisplaySize(i)) + "s", "").replace(" ", "-");
                }
                result += "+\n";


                result = (result.trim().isEmpty()) ? "В таблице " + tableName + " отсутствуют данные." : result;
            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList(new String[]{"123", "stiven", "pupkin"}));
        list.add(Arrays.asList(new String[]{"456", "stiv", "pupk"}));
        list.add(Arrays.asList(new String[]{"789", "sti", "pup"}));
        System.out.print("+");
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(String.format(" %s%" + (10 - list.get(i).get(j).length()) + "s", list.get(i).get(j), "+"));

            }
        }
    }

}
