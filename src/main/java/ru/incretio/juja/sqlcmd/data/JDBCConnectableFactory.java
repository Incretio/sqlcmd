package ru.incretio.juja.sqlcmd.data;

public class JDBCConnectableFactory {
    public static Connectable makeJdbcConnection(
            JDBCConnectionType jdbcConnectionType, String dbHost, String dbName) throws ClassNotFoundException {
        Connectable connectable = new JDBCConnectableImpl(jdbcConnectionType, dbHost, dbName);
        return connectable;
    }
}
