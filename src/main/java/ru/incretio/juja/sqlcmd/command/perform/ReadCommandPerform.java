package ru.incretio.sqlcmd.command.perform;

import ru.incretio.sqlcmd.command.Command;

import java.sql.Connection;
import java.util.List;

/**
 * Created by ProgDelphi on 03.02.2017.
 */
public class ReadCommandPerform implements Command {

    @Override
    public String perform(Connection connection, List<String> params) {
        return "Запись считана";
    }
}
