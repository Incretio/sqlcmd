package ru.incretio.juja.sqlcmd.view;

import javax.activation.UnsupportedDataTypeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class View {
    final String HEADER_TEXT = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
            "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n";
    final String FOOTER_TEXT = "\nСпасибо за использование нашей программы! Мы старались ;)";

    public void writeHeader() {
        write(HEADER_TEXT);
    }

    public void writeFoot(){
        write(FOOTER_TEXT);
    }

    public abstract void write(String text);

    public abstract String read();
}
