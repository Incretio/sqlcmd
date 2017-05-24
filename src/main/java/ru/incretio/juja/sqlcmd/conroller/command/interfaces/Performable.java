package ru.incretio.juja.sqlcmd.conroller.command.interfaces;

import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

/**
 * Объект, который можно выполнить с конфигом и параметрами
 */
@FunctionalInterface
public interface Performable {
    void perform(Model model, View view, List<String> params) throws Exception;
}
