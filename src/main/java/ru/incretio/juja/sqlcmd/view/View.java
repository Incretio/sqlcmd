package ru.incretio.juja.sqlcmd.view;

public abstract class View {
    private final static String HEADER_TEXT = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
            "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n";
    private final static String FOOTER_TEXT = "\nСпасибо за использование нашей программы! Мы старались ;)";

    public void writeHeader() {
        write(HEADER_TEXT);
    }

    public void writeFooter(){
        write(FOOTER_TEXT);
    }

    public abstract void write(String text);

    public abstract String read();
}
