package ru.incretio.juja.sqlcmd.conroller.command;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;

import static ru.incretio.juja.sqlcmd.conroller.command.Command.*;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

abstract class CommandNotationFactory {
    private final static String NOTATION_PATTERN = "%s:%n\t%s;";

    private static String getFormattedNotation(String commandFormat, String commandDescription) {
        return String.format(NOTATION_PATTERN, commandFormat, commandDescription);
    }

    public static Notationable makeEmptyNotation(){
        return () -> "";
    }

    public static Notationable makeClearCommandNotation() {
        return () -> getFormattedNotation(CLEAR.toString().concat(" tableName"),
                takeCaption("clearCommandHelp"));
    }

    public static Notationable makeCloseCommandNotation() {
        return () -> getFormattedNotation(CLOSE.toString(),
                takeCaption("closeCommandHelp"));
    }

    public static Notationable makeConnectNotation() {
        return () -> getFormattedNotation(CONNECT.toString().concat(" serverName dbName username password"),
                takeCaption("connectCommandHelp"));
    }

    public static Notationable makeCreateCommandNotation() {
        return () -> getFormattedNotation(CREATE.toString().concat(" tableName column1 [column2] [columnN]"),
                takeCaption("createCommandHelp"));
    }

    public static Notationable makeDeleteCommandNotation() {
        return () -> getFormattedNotation(DELETE.toString().concat(" tableName whereColumn whereValue"),
                takeCaption("deleteCommandHelp"));
    }

    public static Notationable makeDropCommandNotation() {
        return () -> getFormattedNotation(DROP.toString().concat(" tableName"),
                takeCaption("dropCommandHelp"));
    }

    public static Notationable makeExecuteCommandNotation() {
        return () -> getFormattedNotation(EXECUTE.toString().concat(" \"textQuery\""),
                takeCaption("executeCommandHelp"));
    }

    public static Notationable makeExitCommandNotation() {
        return () -> getFormattedNotation(EXIT.toString(),
                takeCaption("exitCommandHelp"));
    }

    public static Notationable makeFindCommandNotation() {
        return () -> getFormattedNotation(FIND.toString().concat(" tableName"),
                takeCaption("findCommandHelp"));
    }

    public static Notationable makeHelpCommandNotation() {
        return () -> getFormattedNotation(HELP.toString(),
                takeCaption("helpCommandHelp"));
    }

    public static Notationable makeInsertCommandNotation() {
        return () -> getFormattedNotation(INSERT.toString().concat(" tableName column1 value1 [column2 value2] [columnN valueN]"),
                takeCaption("insertCommandHelp"));
    }

    public static Notationable makeTablesCommandNotation() {
        return () -> getFormattedNotation(TABLES.toString(),
                takeCaption("tablesCommandHelp"));
    }

    public static Notationable makeUpdateCommandNotation() {
        return () -> getFormattedNotation(UPDATE.toString().concat(" tableName whereColumn whereValue setColumn setValue"),
                takeCaption("updateCommandHelp"));
    }
}
