package ru.incretio.juja.sqlcmd.data;

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
