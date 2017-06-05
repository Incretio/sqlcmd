package ru.incretio.juja.sqlcmd.conroller.command.list.utils;

import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

public class PerformHelper {
    protected final Model model;
    protected final View view;

    public PerformHelper(Model model, View view) {
        this.model = model;
        this.view = view;
    }
}
