package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class Main {
    private final static String SQL_ERROR_TEXT = "Ошибка при работе с СУБД: %s.";
    private final static String BAD_APP_CONFIGURATION = "Программа некорректно настроена.";

    private final View view;
    private final ConnectionConfig connectionConfig;

    public Main(View view, ConnectionConfig connectionConfig) {
        this.view = view;
        this.connectionConfig = connectionConfig;
    }

    public void doMainWork() {
        view.writeHeader();

        boolean isExit = false;
        while (!isExit) {
            try {
                ParsedCommandLine parsedCommandLine = new ParsedCommandLine(view.read());

                Command command = CommandTypes.getCommand(parsedCommandLine.getCommandName());
                String output = performCommand(command, parsedCommandLine);
                view.write(output);
            } catch (Exception e) {
                if (e instanceof NeedExitException) {
                    isExit = true;
                }
                processingException(e);
            }
        }

        view.writeFoot();
    }

    private void processingException(Exception exception) {
        try {
            throw exception;
        } catch (CommandException | MissingConnectionException | MissingTableException e) {
            view.write(e.getMessage());
        } catch (SQLException e) {
            view.write(String.format(SQL_ERROR_TEXT, e.getMessage()));
        } catch (NullPointerException e) {
            view.write(BAD_APP_CONFIGURATION);
            view.write(Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            view.write(e.getMessage());
        }
    }

    private String performCommand(Command command, ParsedCommandLine parsedCommandLine) throws Exception {
        if (command.checkParams(parsedCommandLine.getParamsList())) {
            return command.perform(connectionConfig, parsedCommandLine.getParamsList());
        } else {
            throw new CommandParamsCountNotMatchException(command.getNotation());
        }
    }


}



