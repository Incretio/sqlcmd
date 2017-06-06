package ru.incretio.juja.sqlcmd.view;

import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public interface View {

    default void writeHeader() {
        write(String.format(takeCaption("headerPattern")));
    }

    default void writeFooter() {
        write(String.format(takeCaption("footerPattern")));
    }

    void write(String text);

    default void write(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : stringList) {
            stringBuilder.append(value)
                    .append(System.lineSeparator());
        }
        write(stringBuilder.toString());
    }

    void writeSelectTable(List<List<String>> data, List<String> columnsNames) throws Exception;

    String read();
}
