package ru.incretio.juja.sqlcmd.conroller;

import ru.incretio.juja.sqlcmd.conroller.command.Command;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;
import ru.incretio.juja.sqlcmd.conroller.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class Controller {

    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void doMainWork() {
        view.writeHeader();

        boolean isExit = false;
        while (!isExit) {
            try {
                ParsedCommandLine parsedCommandLine = new ParsedCommandLine(view.read());
                performCommand(
                        parsedCommandLine.getCommandName(),
                        parsedCommandLine.getParamsList());
            } catch (Exception e) {
                isExit = (e instanceof NeedExitException);
                processingException(e);
            }
        }

        view.writeFooter();
    }

    private void performCommand(String commandName, List<String> params) throws Exception {
        Command command = Command.takeByName(commandName);
        if (command.checkParams(params)) {
            command.perform(model, view, params);
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



