package ru.incretio.juja.sqlcmd.conroller.command.interfaces;

import java.util.List;

/**
 * Объект, для которого можно проверить корректность параметров
 */
@FunctionalInterface
public interface Checkable {
    boolean checkParams(List<String> params);
}
