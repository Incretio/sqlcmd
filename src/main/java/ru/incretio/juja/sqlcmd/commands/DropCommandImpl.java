package ru.incretio.juja.sqlcmd.commands;

import java.util.List;

public class DropCommandImpl implements Command {

    private List<String> params;

    @Override
    public String perform() {
        if (isCorrectCommand()) {
            return "Таблица удалена...";
        }
        return "Список параметров не совпадает!";
    }

    @Override
    public void setParams(List<String> params) {
        this.params = params;
    }

    @Override
    public boolean isCorrectCommand() {
        return params.size() == 1;
    }
}
