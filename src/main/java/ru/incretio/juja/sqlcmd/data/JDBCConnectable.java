package ru.incretio.juja.sqlcmd.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by incre on 26.01.2017.
 */
public abstract class JDBCConnectable implements Connectable {

    private final String CONNECTIVITY_TYPE = "jdbc";
    private final String URL_TEMPLATE = "%s:%s://%s/%s";

    private final String dbHost;
    private final String dbName;

    public JDBCConnectable(String dbHost, String dbName) throws ClassNotFoundException {
        this.dbHost = dbHost;
        this.dbName = dbName;
    }

    @Override
    public Connection getConnection(String userName, String password) throws SQLException {
        String url = String.format(URL_TEMPLATE, CONNECTIVITY_TYPE, getJDBCUrl(), dbHost, dbName);
        Properties props = new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", password);
        Connection connection = DriverManager.getConnection(url, props);

        return connection;
    }

    protected abstract String getJDBCUrl();

    protected abstract void loadJDBCDriver(String jdbcDriverName) throws ClassNotFoundException;

}
