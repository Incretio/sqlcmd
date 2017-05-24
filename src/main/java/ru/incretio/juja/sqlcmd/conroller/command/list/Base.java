package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Performable;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.List;

public abstract class Base implements Performable, Checkable, Notationable {
    private final Checkable checkable;
    private final Notationable notationable;

    protected Base(Checkable checkable, Notationable notationable) {
        this.checkable = checkable;
        this.notationable = notationable;
    }

    @Override
    public abstract void perform(Model model, View view, List<String> params) throws Exception;

    @Override
    public boolean checkParams(List<String> params){
        return checkable.checkParams(params);
    }

    @Override
    public String getNotation()
    {
        return notationable.getNotation();
    }
}
