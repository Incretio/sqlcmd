package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.query.Queryable;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionConfig {
    private Connection connection;
    private final Queryable queryable;

    public ConnectionConfig(Queryable queryable) {
        this.queryable = queryable;
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

    public Connection getConnection() throws MissingConnectionException, SQLException {
        if (!isConnected()) {
            throw new MissingConnectionException();
        }
        return connection;
    }

    public Queryable getQueryable() {
        return queryable;
    }

    public void closeConnection() throws MissingConnectionException, SQLException {
        getConnection().close();
        connection = null;
    }

}