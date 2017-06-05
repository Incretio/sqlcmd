package ru.incretio.juja.sqlcmd.view;

import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ConsoleView extends View {
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public ConsoleView() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Override
    public void write(String text) {
        try {
            writer.write(text.concat(System.lineSeparator()));
            writer.flush();
        } catch (IOException e) {
            AppLogger.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void writeSelectTable(List<List<String>> data, List<String> columnsNames)
            throws Exception {
        TableFormatter tableFormatter = new TableFormatter(data, columnsNames);
        write(tableFormatter.getFormattedTable());
    }

    @Override
    public String read() {
        String result = "";
        try {
            result = reader.readLine();
        } catch (IOException e) {
            AppLogger.warning(Arrays.toString(e.getStackTrace()));
        }

        return result;
    }

}
