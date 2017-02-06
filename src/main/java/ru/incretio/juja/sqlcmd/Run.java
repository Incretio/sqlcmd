package ru.incretio.juja.sqlcmd;


import ru.incretio.juja.sqlcmd.command.Command;
import ru.incretio.juja.sqlcmd.command.CommandFactory;
import ru.incretio.juja.sqlcmd.data.JDBCConnectableFactory;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

import javax.activation.UnsupportedDataTypeException;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Run {
    private static View view = new ConsoleView();
    private static Connection connection;


    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        connection = JDBCConnectableFactory.makePostgreSQLConnection("localhost", "sqlcmd").getConnection("postgres", "postgres");

        view.writeHeader();

        boolean isWorking = true;
        while (isWorking) {
            ParsedLine parsedLine = new ParsedLine(view.read());

            Command command = CommandFactory.makeByCommandName(parsedLine.getCommandName());
            String outputResult = command.perform(connection, parsedLine.getParamsList());

            view.write(outputResult);
        }

        view.wtireFoot();
    }


}

class ParsedLine {
    private final List<String> list;

    public ParsedLine(String line) throws UnsupportedDataTypeException {
        this.list = parse(line);
    }

    public String getCommandName() {
        return (list == null || list.size() == 0) ? "" : list.get(0).toUpperCase();
    }

    public List<String> getParamsList() {
        if (list == null || list.size() < 2) {
            return Collections.emptyList();
        }

        return list.subList(1, list.size());
    }

    public List<String> parse(String line) throws UnsupportedDataTypeException {
        if (line == null || line.length() == 0) {
            throw new UnsupportedDataTypeException("Empty data.");
        }

        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(line.split(" ")));

        return result;
    }


}
