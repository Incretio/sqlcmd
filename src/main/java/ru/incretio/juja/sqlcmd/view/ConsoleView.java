package ru.incretio.juja.sqlcmd.view;

import javax.activation.UnsupportedDataTypeException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by incre on 27.01.2017.
 */
public class ConsoleView extends View {
    public final BufferedReader reader;
    public final BufferedWriter writer;

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
