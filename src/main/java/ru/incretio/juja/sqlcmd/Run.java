package ru.incretio.juja.sqlcmd;


import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.CommandFactory;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.exceptions.CommandException;
import ru.incretio.juja.sqlcmd.exceptions.CommandParamsCountNotMatchException;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

import java.io.Console;
import java.sql.SQLException;

public class Run {
    // connect localhost sqlcmd postgres postgres
    // execute create_&_table_&_table123456789(id_&_serial,name_&_text,location_&_text)
    public static void main(String[] args) {
        String EXIT_APP_COMMAND = CommandTypes.EXIT.toString();
        View view = new ConsoleView();

        view.writeHeader();

        boolean isExit = false;
        while (!isExit) {
            try {
                ParsedCommandLine parsedCommandLine = new ParsedCommandLine(view.read());

                Command command = CommandFactory.makeCommand(parsedCommandLine.getCommandName());
                String output = performCommand(command, parsedCommandLine);
                view.write(output);

                isExit = parsedCommandLine.getCommandName().equals(EXIT_APP_COMMAND);
            } catch (CommandException e) {
                view.write(e.getMessage());
            } catch (MissingConnectionException e) {
                view.write(e.getMessage());
            } catch (SQLException e) {
                view.write(e.getMessage());
            } catch (NullPointerException e) {
                view.write("Программа некорректно настроена.");
                e.printStackTrace();
            }

        }

        view.wtireFoot();
    }

    private static String performCommand(Command command, ParsedCommandLine parsedCommandLine) throws CommandParamsCountNotMatchException, SQLException, MissingConnectionException {
        if (command.checkParams(parsedCommandLine.getParamsList())) {
            return command.perform(ConnectionConfig.getInstance(), parsedCommandLine.getParamsList());
        } else {
            throw new CommandParamsCountNotMatchException(command.getNotation());
        }
    }


}



