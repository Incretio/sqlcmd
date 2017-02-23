package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.data.Connectable;
import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.query.Querable;
import ru.incretio.juja.sqlcmd.query.QueryFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by incre on 22.02.2017.
 */
public class ConnectionConfig {
    private static ConnectionConfig instance = new ConnectionConfig();
    private Connection connection;
    private JDBCConnectionType jdbcConnectionType = JDBCConnectionType.PostgreSQL;
    private Querable querable = QueryFactory.makePostgreSQLQuery();

    private ConnectionConfig() {
    }

    public static ConnectionConfig getInstance() {
        return instance;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public Querable getQuerable(){
        return querable;
    }

    public JDBCConnectionType getJDBCConnectionType() {
        return jdbcConnectionType;
    }
}