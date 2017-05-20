package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.logger.AppLogger;
import ru.incretio.juja.sqlcmd.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.Arrays;

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
                isExit = (e instanceof NeedExitException);
                processingException(e);
            }
        }

        view.writeFooter();
    }

    private void processingException(Exception exception) {
        try {
            throw exception;
        } catch (CommandException | MissingAnyDataException | NeedExitException e) {
            view.write(e.getMessage());
        } catch (SQLException e) {
            view.write(String.format(SQL_ERROR_TEXT, e.getMessage()));
        } catch (Exception e) {
            view.write(BAD_APP_CONFIGURATION);
            AppLogger.warning(e.getMessage().concat(": ").concat(Arrays.toString(e.getStackTrace())));
        }
    }

    private String performCommand(Command command, ParsedCommandLine parsedCommandLine) throws Exception {
        if (command.checkParams(parsedCommandLine.getParamsList())) {
            return command.perform(connectionConfig, parsedCommandLine.getParamsList());
        } else {
            throw new CommandParamsCountNotMatchException(command.getNotationable());
        }
    }


}



