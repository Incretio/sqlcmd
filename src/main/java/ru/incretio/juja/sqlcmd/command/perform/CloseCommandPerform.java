package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CloseCommandPerform implements Performable {
    private final static String OUTPUT_TEXT = "Отключились от БД.";

    @Override
    public String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException, MissingConnectionException {
        connectionConfig.testAndGetConnection().close();
        connectionConfig.setConnection(null);

        return OUTPUT_TEXT;
    }

    public static void main(String[] args) {
//        FileInputStream fis = new FileInputStream("ru.incretio.juja.sqlcmd.resources.strings");
//        String text = new PropertyResourceBundle(fis).getString("OUTPUT_TEXT");
//        System.out.println(text);
        ResourceBundle bundle = ResourceBundle.getBundle("ru.incretio.juja.sqlcmd.resources.strings");
        String text = bundle.getString("OUTPUT_TEXT");
        System.out.println(text);
    }
}