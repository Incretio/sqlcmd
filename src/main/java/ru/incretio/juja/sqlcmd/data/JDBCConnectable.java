package ru.incretio.juja.sqlcmd.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnectable {
    private final String jdbcUrl;

    private final static String CONNECTIVITY_TYPE = "jdbc";
    private final static String URL_TEMPLATE = "%s:%s://%s/%s";
    private final static String USER_PROPERTY_NAME = "user";
    private final static String PASSWORD_PROPERTY_NAME = "password";

    private final String dbHost;
    private final String dbName;

    public JDBCConnectable(JDBCConnectionType jdbcConnectionType, String dbHost, String dbName) throws ClassNotFoundException {
        this.dbHost = dbHost;
        this.dbName = dbName;
        jdbcUrl = jdbcConnectionType.getJdbcUrl();
        loadJDBCDriver(jdbcConnectionType.getJdbcDriverName());
    }

    public Connection getConnection(String userName, String password) throws SQLException {
        String url = String.format(URL_TEMPLATE, CONNECTIVITY_TYPE, jdbcUrl, dbHost, dbName);
        Properties props = getProperties(userName, password);
        return DriverManager.getConnection(url, props);
    }

    private Properties getProperties(String userName, String password){
        Properties props = new Properties();
        props.setProperty(USER_PROPERTY_NAME, userName);
        props.setProperty(PASSWORD_PROPERTY_NAME, password);
        return props;
    }

    private void loadJDBCDriver(String jdbcDriverName) throws ClassNotFoundException{
        Class.forName(jdbcDriverName);
    }

}
