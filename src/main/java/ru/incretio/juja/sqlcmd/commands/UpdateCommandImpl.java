package ru.incretio.juja.sqlcmd.commands;

import java.util.List;

public class UpdateCommandImpl implements Command {

    private List<String> params;

    @Override
    public String perform() {
        if (isCorrectCommand()) {
            return "Запись обновлена...";
        }
        return "Список параметров не совпадает!";
    }

    @Override
    public void setParams(List<String> params) {
        this.params = params;
    }

    @Override
    public boolean isCorrectCommand() {
        return (params.size() > 2) && (params.size() % 2 != 0);
    }
}
