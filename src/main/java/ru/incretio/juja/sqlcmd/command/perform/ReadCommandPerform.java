package ru.incretio.juja.sqlcmd.command.perform;

import ru.incretio.juja.sqlcmd.command.Command;

import java.sql.Connection;
import java.util.List;

public class ReadCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) {
        return "Запись считана";
    }
}
