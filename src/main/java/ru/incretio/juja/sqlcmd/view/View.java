package ru.incretio.juja.sqlcmd.view;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public abstract class View {

    public void writeHeader() {
        write(String.format(takeCaption("headerPattern")));
    }

    public void writeFooter(){
        write(String.format(takeCaption("footerPattern")));
    }

    public abstract void write(String text);

    public abstract String read();
}
