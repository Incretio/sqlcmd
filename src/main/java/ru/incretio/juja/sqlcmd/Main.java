package ru.incretio.juja.sqlcmd;


import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.CommandTypes;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.utils.ParsedCommandLine;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;


// connect localhost sqlcmd postgres postgres
// execute create_&_table_&_table123456789(id_&_serial,name_&_text,location_&_text)
public class Main {
    final View view;
    final ConnectionConfig connectionConfig;

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
            } catch (Exception e){
                if (e instanceof NeedExitException){
                    isExit = true;
                }
                processingException(e);
            }
        }

        view.wtireFoot();
    }

    private void processingException(Exception exception) {
        try {
            throw exception;
        } catch (CommandException | MissingConnectionException | MissingTableException e) {
            view.write(e.getMessage());
        } catch (SQLException e) {
            view.write("Ошибка при работе с СУБД: " + e.getMessage());
        } catch (NullPointerException e) {
            view.write("Программа некорректно настроена.");
            e.printStackTrace();
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



