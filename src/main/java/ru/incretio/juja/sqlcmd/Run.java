package ru.incretio.juja.sqlcmd;


import ru.incretio.juja.sqlcmd.commands.*;
import ru.incretio.juja.sqlcmd.utils.CommandParser;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

import javax.activation.UnsupportedDataTypeException;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Run {
    private static View view = new ConsoleView();
    private static Connection connection;


    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        view.writeHeader();

        boolean isWorking = true;
        while (isWorking) {
            Command command = readDataAndGetCommand();
            String outputText = command.perform();
            view.write(outputText);
        }

        view.wtireFoot();
    }

    private static Command readDataAndGetCommand() throws UnsupportedDataTypeException {
        return CommandParser.getParsedCommand(view.readData());
    }

}
