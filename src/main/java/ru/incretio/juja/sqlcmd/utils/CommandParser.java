package ru.incretio.juja.sqlcmd.utils;

import ru.incretio.juja.sqlcmd.controls.CommandImpl;
import ru.incretio.juja.sqlcmd.controls.Command;
import ru.incretio.juja.sqlcmd.controls.CommandType;

import javax.activation.UnsupportedDataTypeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by incre on 29.01.2017.
 */
public class CommandParser {
    public static Command getParsedCommand(List<String> data) throws UnsupportedDataTypeException {
        if (data == null || data.size() == 0) {
            throw new UnsupportedDataTypeException("Empty command.");
        }

        CommandType commandType = CommandType.getCommandByName(data.get(0));
        List<String> commands = new ArrayList<>();
        if (data.size() > 1) {
            commands.addAll(data.subList(1, data.size()));
        }

        return new CommandImpl(commandType, commands);
    }
}
