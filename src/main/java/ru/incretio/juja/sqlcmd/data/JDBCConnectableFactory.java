package ru.incretio.juja.sqlcmd.data;

public class JDBCConnectableFactory {
    public static Connectable makeJdbcConnection(
            JDBCConnectionType jdbcConnectionType, String dbHost, String dbName) throws ClassNotFoundException {
        return new JDBCConnectableImpl(jdbcConnectionType, dbHost, dbName);
    }
}
