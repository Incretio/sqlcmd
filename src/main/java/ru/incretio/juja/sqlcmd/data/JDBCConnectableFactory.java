package ru.incretio.juja.sqlcmd.data;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by incre on 29.01.2017.
 */
public class JDBCConnectableFactory {
    public static Connectable makeJdbcConnection(
            JDBCConnectionType jdbcConnectionType, String dbHost, String dbName) throws ClassNotFoundException {
        Connectable connectable = new JDBCConnectableImpl(jdbcConnectionType, dbHost, dbName);
        return connectable;
    }
    public static Connectable makePostgreSQLConnection(
            String dbHost, String dbName) throws ClassNotFoundException {
        Connectable connectable = new JDBCConnectableImpl(JDBCConnectionType.PostgreSQL, dbHost, dbName);
        return connectable;
    }
    public static Connectable makeMySQLConnection(
            String dbHost, String dbName) throws ClassNotFoundException {
        Connectable connectable = new JDBCConnectableImpl(JDBCConnectionType.MySQL, dbHost, dbName);
        return connectable;
    }
    public static Connectable makeMSSQLConnection(
            String dbHost, String dbName) throws ClassNotFoundException {
        Connectable connectable = new JDBCConnectableImpl(JDBCConnectionType.MSSQL, dbHost, dbName);
        return connectable;
    }
}
