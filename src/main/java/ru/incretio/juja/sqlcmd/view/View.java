package ru.incretio.juja.sqlcmd.view;

import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public abstract class View {

    public void writeHeader() {
        write(String.format(takeCaption("headerPattern")));
    }

    public void writeFooter() {
        write(String.format(takeCaption("footerPattern")));
    }

    public abstract void write(String text);

    public void write(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : stringList) {
            stringBuilder.append(value)
                    .append(System.lineSeparator());
        }
        write(stringBuilder.toString());
    }

    public abstract String read();
}
