package ru.incretio.juja.sqlcmd.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Command {
    String perform(Connection connection, List<String> params) throws SQLException;
}
