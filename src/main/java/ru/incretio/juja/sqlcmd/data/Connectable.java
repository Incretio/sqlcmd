package ru.incretio.juja.sqlcmd.data;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by incre on 26.01.2017.
 */
public interface Connectable {
    Connection getConnection(String userName, String password) throws SQLException;
}
