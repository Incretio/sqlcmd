package ru.incretio.juja.sqlcmd.command.interfaces;

import ru.incretio.juja.sqlcmd.ConnectionConfig;
import java.util.List;

/**
 * Объект, который можно выполнить с конфигом и параметрами
 */
@FunctionalInterface
public interface Performable {
    String perform(ConnectionConfig connectionConfig, List<String> params) throws Exception;
}
