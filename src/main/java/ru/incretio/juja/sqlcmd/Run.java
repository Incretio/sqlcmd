package ru.incretio.juja.sqlcmd;


import ru.incretio.juja.sqlcmd.controls.*;
import ru.incretio.juja.sqlcmd.utils.CommandParser;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

import javax.activation.UnsupportedDataTypeException;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by incre on 26.01.2017.
 */
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

        view.wtireFood();
    }

    private static Command readDataAndGetCommand() throws UnsupportedDataTypeException {
        return CommandParser.getParsedCommand(view.readData());
    }







/*
    // connect localhost sqlcmd postgres postgres

    public static Connection performConnect(List<String> parsedCommandLine) {
        Connection conn = null;

        // TODO добавить класс, который будет принимать строку и парсить её и через методы доступа можно будет получить данные
        // если ошибка, то при создании в конструкторе она вылетает
        String commandName = "";
        if (parsedCommandLine.size() > 0) {
            commandName = parsedCommandLine.get(0);
        }
        if (Command.AppCommand.CONNECT.is(commandName)) {
            String dbHost = parsedCommandLine.get(1);
            String dbName = parsedCommandLine.get(2);
            String userName = parsedCommandLine.get(3);
            String password = parsedCommandLine.get(4);

            try {
                conn = ConnectCommand.connect(dbHost, dbName, userName, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;
    }
*/

}
