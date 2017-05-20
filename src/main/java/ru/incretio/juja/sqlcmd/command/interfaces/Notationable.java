package ru.incretio.juja.sqlcmd.command.interfaces;

/**
 * Объект, для которого можно получить описание
 */
@FunctionalInterface
public interface Notationable {
    String getNotation();
}
