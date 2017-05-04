package ru.incretio.juja.sqlcmd.data;

import java.sql.Connection;
import java.sql.SQLException;

public interface Connectable {
    Connection getConnection(String userName, String password) throws SQLException;
}
