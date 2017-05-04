package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.query.Querable;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionConfig {
    private Connection connection;
    private final Querable querable;

    public ConnectionConfig(Querable querable) {
        this.querable = querable;
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

    public Connection testAndGetConnection() throws MissingConnectionException, SQLException {
        testConnection();
        return connection;
    }

    public Querable getQuerable() {
        return querable;
    }

    public boolean testConnection() throws MissingConnectionException, SQLException {
        if (!isConnected()) {
            throw new MissingConnectionException();
        }
        return true;
    }
}