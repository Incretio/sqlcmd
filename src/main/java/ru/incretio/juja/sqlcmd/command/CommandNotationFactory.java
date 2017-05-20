package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

import static ru.incretio.juja.sqlcmd.command.CommandTypes.*;

abstract class CommandNotationFactory {
    private final static String PATTERN_NOTATION = "\t%s:\n\t\t%s;";

    private static String getFormattedNotation(String commandFormat, String commandDescription) {
        return String.format(PATTERN_NOTATION, commandFormat, commandDescription);
    }

    public static Notationable getClearCommandNotation() {
        return () -> getFormattedNotation(CLEAR.toString().concat(" tableName"),
                "очистить содержимое указанной таблицы");
    }

    public static Notationable getCloseCommandNotation() {
        return () -> getFormattedNotation(CLOSE.toString(),
                "закрыть соединение с базой данных");
    }

    public static Notationable getColumnExistCommandNotation() {
        return () -> getFormattedNotation(COLUMN_EXIST.toString().concat(" tableName columnName"),
                "проверить наличие указанной колонки в указанной таблице");
    }

    public static Notationable getColumnsCommandNotation() {
        return () -> getFormattedNotation(COLUMNS.toString().concat(" tableName"),
                "показать список столбцов указанной таблицы");
    }

    public static Notationable getConnectCommandNotation() {
        return () -> getFormattedNotation(CONNECT.toString().concat(" serverName dbName username password"),
                "подключиться к базе данных");
    }

    public static Notationable getCreateCommandNotation() {
        return () -> getFormattedNotation(CREATE.toString().concat(" tableName column1 [column2] [columnN]"),
                "добавить новую таблицу (имя столбца не может начинаться с цифры)");
    }

    public static Notationable getCreateDBCommandNotation() {
        return () -> getFormattedNotation(CREATEDB.toString().concat(" dbName"),
                "добавить новую базу данных");
    }

    public static Notationable getDeleteCommandNotation() {
        return () -> getFormattedNotation(DELETE.toString().concat(" tableName whereColumn whereValue"),
                "удалить записи, удовлетворяющие условию");
    }

    public static Notationable getDropCommandNotation() {
        return () -> getFormattedNotation(DROP.toString().concat(" tableName"),
                "удалить указанную таблицу");
    }

    public static Notationable getDropDBCommandNotation() {
        return () -> getFormattedNotation(DROPDB.toString().concat(" dbName"),
                "удалить базу данных");
    }

    public static Notationable getExecuteCommandNotation() {
        return () -> getFormattedNotation(EXECUTE.toString().concat(" 'textQuery'"),
                "выполнить пользовательский запрос (должен быть указан в одинарных ковычках)");
    }

    public static Notationable getExitCommandNotation() {
        return () -> getFormattedNotation(EXIT.toString(),
                "закрыть соединение и выйти из программы");
    }

    public static Notationable getFindCommandNotation() {
        return () -> getFormattedNotation(FIND.toString().concat(" tableName"),
                "показать содержимое указанной таблицы");
    }

    public static Notationable getHelpCommandNotation() {
        return () -> getFormattedNotation(HELP.toString(),
                "показать список команд и их описаниями");
    }

    public static Notationable getInsertCommandNotation() {
        return () -> getFormattedNotation(INSERT.toString().concat(" tableName column1 value1 [column2 value2] [columnN valueN]"),
                "добавить запись в указанную таблицу");
    }

    public static Notationable getTableExistCommandNotation() {
        return () -> getFormattedNotation(TABLE_EXIST.toString().concat(" tableName"),
                "проверить наличие указанной таблицы в базе данных");
    }

    public static Notationable getTablesCommandNotation() {
        return () -> getFormattedNotation(TABLES.toString(),
                "показать список таблиц базы данных");
    }

    public static Notationable getUpdateCommandNotation() {
        return () -> getFormattedNotation(UPDATE.toString().concat(" tableName whereColumn whereValue setColumn setValue"),
                "обновить записи, удовлетворяющие условию в указанной таблице");
    }
}
