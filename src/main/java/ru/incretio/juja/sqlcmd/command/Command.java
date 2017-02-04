package ru.incretio.sqlcmd.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public interface Command {
    String perform(Connection connection, List<String> params) throws SQLException;
}
