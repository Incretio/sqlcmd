package ru.incretio.juja.sqlcmd.controls;

import javax.activation.UnsupportedDataTypeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by incre on 31.01.2017.
 */
public enum CommandType {
    CONNECT("connect"),
    TABLES("tables"),
    DROP("drop"),
    CLEAR("clear"),
    CREATE("create"),
    FIND("find"),
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete"),
    HELP("help"),
    EXIT("exit");

    public static List<CommandType> getCommandsList() {
        List<CommandType> list = new ArrayList<>();
        Collections.addAll(
                list,
                CONNECT,
                TABLES, DROP, CLEAR, CREATE,
                FIND, INSERT, UPDATE, DELETE,
                HELP, EXIT);
        return list;
    }

    public static CommandType getCommandByName(String commandName) throws UnsupportedDataTypeException {
        for (CommandType commandType : getCommandsList()) {
            if (commandType.getName().equals(commandName)){
                return commandType;
            }
        }
        throw new UnsupportedDataTypeException("Command \"" + commandName + "\" does not supported!");
    }

    private final String name;

    public String getName() {
        return name;
    }

    CommandType(String name) {
        this.name = name;
    }


    public boolean is(String commandName) {
        return this.getName().toLowerCase().equals(commandName.toLowerCase());
    }

}