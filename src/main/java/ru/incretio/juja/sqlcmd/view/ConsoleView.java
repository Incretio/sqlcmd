package ru.incretio.juja.sqlcmd.view;

import java.io.*;

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
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() {
        String result = "";
        try {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
