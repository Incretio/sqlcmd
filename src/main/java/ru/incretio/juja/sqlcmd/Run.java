package ru.incretio.juja.sqlcmd;


import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.CommandFactory;
import ru.incretio.juja.sqlcmd.exceptions.commandexception.CommandException;
import ru.incretio.juja.sqlcmd.exceptions.commandexception.CommandParamsCountNotMatchException;
import ru.incretio.juja.sqlcmd.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

import java.io.*;
import java.sql.SQLException;

public class Run {
    private static final String EXIT_APP_COMMAND = "EXIT";
    private static View view = new ConsoleView();

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        view.writeHeader();

        boolean isWorking = true;
        while (isWorking) {
            try {
                ParsedCommandLine parsedCommandLine = new ParsedCommandLine(view.read());
                if (parsedCommandLine.getCommandName().equals(EXIT_APP_COMMAND)) {
                    isWorking = false;
                }

                Command command = CommandFactory.makeCommand(parsedCommandLine.getCommandName());
                String output = performCommand(command, parsedCommandLine);
                view.write(output);

            } catch (CommandException e) {
                view.write(e.getMessage());
            }

        }

        view.wtireFoot();
    }

    private static String performCommand(Command command, ParsedCommandLine parsedCommandLine) throws CommandParamsCountNotMatchException, SQLException {
        if (command.checkParams(parsedCommandLine.getParamsList())) {
            return command.perform(ConnectionConfig.getInstance(), parsedCommandLine.getParamsList());
        } else {
            throw new CommandParamsCountNotMatchException(command.getNotation());
        }
    }


}



