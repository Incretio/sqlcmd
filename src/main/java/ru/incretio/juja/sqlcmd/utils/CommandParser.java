package ru.incretio.juja.sqlcmd.utils;

import ru.incretio.juja.sqlcmd.commands.*;

import javax.activation.UnsupportedDataTypeException;
import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    public static Command getParsedCommand(List<String> data) throws UnsupportedDataTypeException {
        if (data == null || data.size() == 0) {
            throw new UnsupportedDataTypeException("Empty command.");
        }

        CommandType commandType = CommandType.getCommandTypeByName(data.get(0));
        List<String> params = new ArrayList<>();
        if (data.size() > 1) {
            params.addAll(data.subList(1, data.size()));
        }

        Command result = CommandFactory.getCommandByCommandType(commandType);
        result.setParams(params);

        return result;
    }
}
