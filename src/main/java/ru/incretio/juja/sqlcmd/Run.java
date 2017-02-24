package ru.incretio.juja.sqlcmd;


import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.CommandFactory;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.exceptions.command.CommandException;
import ru.incretio.juja.sqlcmd.exceptions.command.CommandParamsCountNotMatchException;
import ru.incretio.juja.sqlcmd.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

import java.io.*;
import java.sql.SQLException;

public class Run {
    private static final String EXIT_APP_COMMAND = CommandTypes.EXIT.toString();
    private static View view = new ConsoleView();

    public static void main(String[] args) {

        view.writeHeader();

        boolean isExit = true;
        while (isExit) {
            try {
                ParsedCommandLine parsedCommandLine = new ParsedCommandLine(view.read());

                Command command = CommandFactory.makeCommand(parsedCommandLine.getCommandName());
                String output = performCommand(command, parsedCommandLine);
                view.write(output);

                isExit = parsedCommandLine.getCommandName().equals(EXIT_APP_COMMAND);
            } catch (CommandException e) {
                view.write(e.getMessage());
            } catch (SQLException e) {
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



