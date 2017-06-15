package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.mockito.Mock;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

public class CommandTestBase {
    protected Model model = mock(Model.class);
    protected View view = mock(View.class);

    protected static final Checkable CHECKABLE_MOCK = mock(Checkable.class);
    protected static final Notationable NOTATIONABLE_MOCK = mock(Notationable.class);
    protected List<String> params;

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
    protected final static String HELP = "Список доступных комманд:\n" +
            "\tconnect serverName dbName username password:\n" +
            "\t\tподключиться к базе данных;\n" +
            "\ttables:\n" +
            "\t\tпоказать список таблиц базы данных;\n" +
            "\tcreate tableName column1 [column2] [columnN]:\n" +
            "\t\tдобавить новую таблицу (имя столбца не может начинаться с цифры);\n" +
            "\tdrop tableName:\n" +
            "\t\tудалить указанную таблицу;\n" +
            "\tinsert tableName column1 value1 [column2 value2] [columnN valueN]:\n" +
            "\t\tдобавить запись в указанную таблицу;\n" +
            "\tupdate tableName whereColumn whereValue setColumn setValue:\n" +
            "\t\tобновить записи, удовлетворяющие условию в указанной таблице;\n" +
            "\tselect tableName:\n" +
            "\t\tпоказать содержимое указанной таблицы;\n" +
            "\tdelete tableName whereColumn whereValue:\n" +
            "\t\tудалить записи, удовлетворяющие условию;\n" +
            "\tclear tableName:\n" +
            "\t\tочистить содержимое указанной таблицы;\n" +
            "\tclose:\n" +
            "\t\tзакрыть соединение с базой данных;\n" +
            "\texit:\n" +
            "\t\tзакрыть соединение и выйти из программы;\n" +
            "\thelp:\n" +
            "\t\tпоказать список команд и их описаниями;\n" +
            "\texecute \"textQuery\":\n" +
            "\t\tвыполнить пользовательский запрос (должен быть указан в двойных ковычках);\n";
}
