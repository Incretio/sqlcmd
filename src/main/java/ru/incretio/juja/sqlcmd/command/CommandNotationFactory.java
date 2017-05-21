package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.interfaces.Notationable;

import static ru.incretio.juja.sqlcmd.command.Command.*;

abstract class CommandNotationFactory {
    private final static String NOTATION_NOTATION = "\t%s:\n\t\t%s;";

    private static String getFormattedNotation(String commandFormat, String commandDescription) {
        return String.format(NOTATION_NOTATION, commandFormat, commandDescription);
    }

    public static Notationable makeClearCommandNotation() {
        return () -> getFormattedNotation(CLEAR.toString().concat(" tableName"),
                "очистить содержимое указанной таблицы");
    }

    public static Notationable makeCloseCommandNotation() {
        return () -> getFormattedNotation(CLOSE.toString(),
                "закрыть соединение с базой данных");
    }

    public static Notationable makeColumnExistCommandNotation() {
        return () -> getFormattedNotation(COLUMN_EXIST.toString().concat(" tableName columnName"),
                "проверить наличие указанной колонки в указанной таблице");
    }

    public static Notationable makeColumnsCommandNotation() {
        return () -> getFormattedNotation(COLUMNS.toString().concat(" tableName"),
                "показать список столбцов указанной таблицы");
    }

    public static Notationable makeConnectNotation() {
        return () -> getFormattedNotation(CONNECT.toString().concat(" serverName dbName username password"),
                "подключиться к базе данных");
    }

    public static Notationable makeCreateCommandNotation() {
        return () -> getFormattedNotation(CREATE.toString().concat(" tableName column1 [column2] [columnN]"),
                "добавить новую таблицу (имя столбца не может начинаться с цифры)");
    }

    public static Notationable makeCreateDBCommandNotation() {
        return () -> getFormattedNotation(CREATEDB.toString().concat(" dbName"),
                "добавить новую базу данных");
    }

    public static Notationable makeDeleteCommandNotation() {
        return () -> getFormattedNotation(DELETE.toString().concat(" tableName whereColumn whereValue"),
                "удалить записи, удовлетворяющие условию");
    }

    public static Notationable makeDropCommandNotation() {
        return () -> getFormattedNotation(DROP.toString().concat(" tableName"),
                "удалить указанную таблицу");
    }

    public static Notationable makeDropDBCommandNotation() {
        return () -> getFormattedNotation(DROPDB.toString().concat(" dbName"),
                "удалить базу данных");
    }

    public static Notationable makeExecuteCommandNotation() {
        return () -> getFormattedNotation(EXECUTE.toString().concat(" \"textQuery\""),
                "выполнить пользовательский запрос (должен быть указан в двойных ковычках)");
    }

    public static Notationable makeExitCommandNotation() {
        return () -> getFormattedNotation(EXIT.toString(),
                "закрыть соединение и выйти из программы");
    }

    public static Notationable makeFindCommandNotation() {
        return () -> getFormattedNotation(FIND.toString().concat(" tableName"),
                "показать содержимое указанной таблицы");
    }

    public static Notationable makeHelpCommandNotation() {
        return () -> getFormattedNotation(HELP.toString(),
                "показать список команд и их описаниями");
    }

    public static Notationable makeInsertCommandNotation() {
        return () -> getFormattedNotation(INSERT.toString().concat(" tableName column1 value1 [column2 value2] [columnN valueN]"),
                "добавить запись в указанную таблицу");
    }

    public static Notationable makeTableExistCommandNotation() {
        return () -> getFormattedNotation(TABLE_EXIST.toString().concat(" tableName"),
                "проверить наличие указанной таблицы в базе данных");
    }

    public static Notationable makeTablesCommandNotation() {
        return () -> getFormattedNotation(TABLES.toString(),
                "показать список таблиц базы данных");
    }

    public static Notationable makeUpdateCommandNotation() {
        return () -> getFormattedNotation(UPDATE.toString().concat(" tableName whereColumn whereValue setColumn setValue"),
                "обновить записи, удовлетворяющие условию в указанной таблице");
    }
}
