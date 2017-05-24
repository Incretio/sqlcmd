package ru.incretio.juja.sqlcmd.conroller.command.list.utils;

import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.Arrays;

public class PerformHelper {
    protected final Model model;
    protected final View view;

    public PerformHelper(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    private static boolean contains(String source, String value) {
        return Arrays.asList(source.split(System.lineSeparator()))
                .contains(value);
    }
}
