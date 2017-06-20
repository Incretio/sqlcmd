package ru.incretio.juja.sqlcmd.conroller.utils;

import ru.incretio.juja.sqlcmd.conroller.command.Command;

import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class HelpCommand {
    private final String header = takeCaption("commandList");

    public String getHeader() {
        return header;
    }

    public List<String> getCommandsDescriptions() {
        return Command.getNotationsList();
    }
}
