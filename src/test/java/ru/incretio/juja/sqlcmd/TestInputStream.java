package ru.incretio.juja.sqlcmd;

import java.io.IOException;
import java.io.InputStream;

class TestInputStream extends InputStream {
    private String input = "";

    @Override
    public int read() throws IOException {
        if (input.length() == 0) {
            return -1;
        }


        char result = input.charAt(0);

        input = input.substring(1);

        return result;
    }

    public void add(String text) {
        input += text + System.lineSeparator();
    }

    @Override
    public synchronized void reset() {
        input = "";
    }
}
