package ru.incretio.juja.sqlcmd.utils;

import java.io.IOException;
import java.io.OutputStream;

public class CompareTwoString {
    private final OutputStream out;

    public CompareTwoString(OutputStream out) {
        this.out = out;
    }

    private void compare(String expected, String actual) throws IOException {
        out.write(("actualLength = " + actual.length() +
                "; expectedLength = " + expected.length() + "\n").getBytes());
        for (int i = 0; i < actual.length(); i++) {
            if (actual.charAt(i) != expected.charAt(i)) {
                char curExpectedChar = expected.charAt(i);
                char curActualChar = actual.charAt(i);
                out.write(("index = " + i +
                        "; charExcepted = " + curExpectedChar +
                        "; charActual = " + curActualChar +
                        "; valueExpected = " + (int) curExpectedChar +
                        "; valueActual = " + (int) curActualChar + "\n").getBytes());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CompareTwoString compareTwoString = new CompareTwoString(System.out);
        compareTwoString.compare("123455789", "123456789");
    }
}
