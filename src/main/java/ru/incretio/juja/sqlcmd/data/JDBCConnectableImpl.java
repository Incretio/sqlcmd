package ru.incretio.juja.sqlcmd.data;

/**
 * Created by incre on 26.01.2017.
 */
public class JDBCConnectableImpl extends JDBCConnectable {
    private final String jdbcUrl;

    public JDBCConnectableImpl(
            JDBCConnectionType jdbcConnectionType,
            String dbHost, String dbName) throws ClassNotFoundException {
        super(dbHost, dbName);
        this.jdbcUrl = jdbcConnectionType.getJdbcUrl();
        loadJDBCDriver(jdbcConnectionType.getJdbcDriverName());

    }

    @Override
    protected void loadJDBCDriver(String jdbcDriverName) throws ClassNotFoundException {
        Class.forName(jdbcDriverName);
    }

    @Override
    protected String getJDBCUrl() {
        return jdbcUrl;
    }

}