package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.logger.AppLogger;
import ru.incretio.juja.sqlcmd.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

class Main {

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
                String output = performCommand(
                        parsedCommandLine.getCommandName(),
                        parsedCommandLine.getParamsList());
                view.write(output);
            } catch (Exception e) {
                isExit = (e instanceof NeedExitException);
                processingException(e);
            }
        }

        view.writeFooter();
    }

    private String performCommand(String commandName, List<String> params) throws Exception {
        Command command = Command.takeByName(commandName);
        if (command.checkParams(params)) {
            return command.perform(connectionConfig, params);
        } else {
            throw new CommandParamsCountNotMatchException(command.getNotation());
        }
    }

    private void processingException(Exception exception) {
        try {
            throw exception;
        } catch (CommandException | MissingAnyDataException | NeedExitException e) {
            view.write(e.getMessage());
        } catch (SQLException e) {
            view.write(String.format(takeCaption("sqlErrorPattern"),
                    e.getMessage().replace("\n", System.lineSeparator())));
        } catch (Exception e) {
            view.write(takeCaption("badAppConfiguration"));
            AppLogger.warning(e.getMessage().concat(": ").concat(Arrays.toString(e.getStackTrace())));
        }
    }


}



