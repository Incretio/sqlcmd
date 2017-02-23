package ru.incretio.juja.sqlcmd.command.interfaces;

import ru.incretio.juja.sqlcmd.ConnectionConfig;

import java.sql.SQLException;
import java.util.List;

public interface Performable {
    String perform(ConnectionConfig connectionConfig, List<String> params) throws SQLException;
}
