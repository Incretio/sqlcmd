package ru.incretio.juja.sqlcmd.service;

import ru.incretio.juja.sqlcmd.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

public class CommandTestBase {
    protected Model model = mock(Model.class);

    protected final static String TABLE_NAME = "testTableName";
    protected final static String COLUMN_ONE_NAME = "testColumnOne";
    protected final static String COLUMN_TWO_NAME = "testColumnTwo";
    protected final static String COLUMN_THREE_NAME = "testColumnThree";
    protected final static List<String> COLUMNS_LIST = Arrays.asList(COLUMN_ONE_NAME, COLUMN_TWO_NAME, COLUMN_THREE_NAME);
    protected final static List<String> TABLE_AND_COLUMNS_LIST =
            Arrays.asList(TABLE_NAME, COLUMN_ONE_NAME, COLUMN_TWO_NAME, COLUMN_THREE_NAME);
    protected final static String SERVER_NAME = "serverName";
    protected final static String DB_NAME = "dbName";
    protected final static String USERNAME = "userName";
    protected final static String PASSWORD = "password";
    protected final static List<List<String>> DATA =
            new ArrayList(Arrays.asList(
                    Arrays.asList("1", "name1", "value1"),
                    Arrays.asList("2", "name2", "value2"),
                    Arrays.asList("3", "name3", "value3")));
    protected final static List<List<String>> DATA_WITH_COLUMNS =
            new ArrayList(Arrays.asList(
                    Arrays.asList(COLUMN_ONE_NAME, COLUMN_TWO_NAME, COLUMN_THREE_NAME),
                    Arrays.asList("1", "name1", "value1"),
                    Arrays.asList("2", "name2", "value2"),
                    Arrays.asList("3", "name3", "value3")));
    protected final static List<List<String>> DATA_ONLY_COLUMNS =
            new ArrayList(Arrays.asList(
                    Arrays.asList(COLUMN_ONE_NAME, COLUMN_TWO_NAME, COLUMN_THREE_NAME)));
    protected final static List<String> EMPTY_DATA = Collections.EMPTY_LIST;
    protected final static String HELP = "Список доступных комманд:\n" +
            "connect serverName dbName username password:\n" +
            "\tподключиться к базе данных;\n" +
            "close:\n" +
            "\tзакрыть соединение с базой данных;\n" +
            "tables:\n" +
            "\tпоказать список таблиц базы данных;\n" +
            "create tableName column1 [column2] [columnN]:\n" +
            "\tдобавить новую таблицу (имя столбца не может начинаться с цифры);\n" +
            "insert tableName column1 value1 [column2 value2] [columnN valueN]:\n" +
            "\tдобавить запись в указанную таблицу;\n" +
            "update tableName whereColumn whereValue setColumn setValue:\n" +
            "\tобновить записи, удовлетворяющие условию в указанной таблице;\n" +
            "delete tableName whereColumn whereValue:\n" +
            "\tудалить записи, удовлетворяющие условию;\n" +
            "find tableName:\n" +
            "\tпоказать содержимое указанной таблицы;\n" +
            "clear tableName:\n" +
            "\tочистить содержимое указанной таблицы;\n" +
            "drop tableName:\n" +
            "\tудалить указанную таблицу;\n" +
            "help:\n" +
            "\tпоказать список команд и их описаниями;\n";
}
