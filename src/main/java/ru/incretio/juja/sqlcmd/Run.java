package ru.incretio.juja.sqlcmd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by incre on 26.01.2017.
 */
public class Run {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost/sqlcmd?user=postgres&password=postgres";
        Connection conn = DriverManager.getConnection(url);

    }
}
