package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

/**
 * Created by incre on 02.05.2017.
 */
public class CommandNotationFactory {
    private static String getFormattedNotation(String commandFormat, String commandDescription) {
        return "\t" + commandFormat + ":\n" +
                "\t\t" + commandDescription + ";";
    }

    public static Notationable getClearCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.CLEAR.toString() + " tableName",
                "очистить содержимое указанной таблицы");
    }

    public static Notationable getCloseCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.CLOSE.toString(),
                "закрыть соединение с базой данных");
    }

    public static Notationable getColumnExistCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.COLUMN_EXIST.toString() + " tableName columnName",
                "проверить наличие указанной колонки в указанной таблице");
    }

    public static Notationable getColumnsCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.COLUMNS.toString() + " tableName",
                "показать список столбцов указанной таблицы");
    }

    public static Notationable getConnectCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.CONNECT.toString() + " serverName dbName username password",
                "подключиться к базе данных");
    }

    public static Notationable getCreateCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.CREATE.toString() + " tableName column1 [column2] [columnN]",
                "добавить новую таблицу (имя столбца не может начинаться с цифры)");
    }

    public static Notationable getCreateDBCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.CREATEDB.toString() + " dbName",
                "добавить новую базу данных");
    }

    public static Notationable getDeleteCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.DELETE.toString() + " tableName whereColumn whereValue",
                "удалить записи, удовлетворяющие условию");
    }

    public static Notationable getDropCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.DROP.toString() + " tableName",
                "удалить указанную таблицу");
    }

    public static Notationable getDropDBCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.DROPDB.toString() + " dbName",
                "удалить базу данных");
    }

    public static Notationable getExecuteCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.EXECUTE.toString() + " 'textQuery'",
                "выполнить пользовательский запрос (должен быть указан в одинарных ковычках)");
    }

    public static Notationable getExitCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.EXIT.toString(),
                "закрыть соединение и выйти из программы");
    }

    public static Notationable getFindCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.FIND.toString() + " tableName",
                "показать содержимое указанной таблицы");
    }

    public static Notationable getHelpCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.HELP.toString(),
                "показать список команд и их описаниями");
    }

    public static Notationable getInsertCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.INSERT.toString() + " tableName column1 value1 [column2 value2] [columnN valueN]",
                "добавить запись в указанную таблицу");
    }

    public static Notationable getTableExistCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.TABLE_EXIST.toString() + " tableName",
                "проверить наличие указанной таблицы в базе данных");
    }

    public static Notationable getTablesCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.TABLES.toString(),
                "показать список таблиц базы данных");
    }

    public static Notationable getUpdateCommandNotation() {
        return () -> getFormattedNotation(CommandTypes.UPDATE.toString() + " tableName whereColumn whereValue setColumn setValue",
                "обновить записи, удовлетворяющие условию в указанной таблице");
    }
}
