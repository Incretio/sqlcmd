package ru.incretio.sqlcmd.command.perform;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import ru.incretio.sqlcmd.command.Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public class ClearCommandPerform implements Command {
    @Override
    public String perform(Connection connection, List<String> params) throws SQLException {
        String tableName = params.get(0);
        Statement statement = connection.createStatement();
        String result = "";
        try {
            statement.execute("delete from " + tableName + " where id > 0");
            result = "Таблица " + tableName + " очищена.";
        } catch (MySQLSyntaxErrorException e){
            result = e.getMessage();
        }

        return result;
    }
}
