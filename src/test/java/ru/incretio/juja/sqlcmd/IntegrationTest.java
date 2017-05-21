package ru.incretio.juja.sqlcmd;

import org.junit.*;
import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.exceptions.MissingJDBCConnectionTypeException;
import ru.incretio.juja.sqlcmd.query.QueryFactory;

import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class IntegrationTest {
    private final static TestInputStream in = new TestInputStream();
    private final static TestOutputStream out = new TestOutputStream();

    @Before
    public void setupTest() throws MissingJDBCConnectionTypeException {
        System.setIn(in);
        System.setOut(new PrintStream(out));

        in.add(TestConstants.MASTER_CONNECTION_STRING);
        in.add("execute \"" + QueryFactory.makeSQLQuery(JDBCConnectionType.PostgreSQL).takeDropDBQuery(TestConstants.TEST_DB_NAME) + "\"");
        in.add("execute \"" + QueryFactory.makeSQLQuery(JDBCConnectionType.PostgreSQL).takeCreateDBQuery(TestConstants.TEST_DB_NAME) + "\"");
        in.add("exit");
        Run.main(new String[0]);
        in.reset();
        out.reset();
    }

    @AfterClass
    public static void clearDataAfterTest() throws MissingJDBCConnectionTypeException {
        in.add(TestConstants.MASTER_CONNECTION_STRING);
        in.add("execute \"" + QueryFactory.makeSQLQuery(JDBCConnectionType.PostgreSQL).takeDropDBQuery(TestConstants.TEST_DB_NAME) + "\"");
        in.add("exit");
        Run.main(new String[0]);
    }


    @Test
    public void testCreateTablesDropCommand() {
        in.add(TestConstants.TEST_CONNECTION_STRING);

        in.add("tables");
        in.add("create table1 id field1");
        in.add("tables");
        in.add("find table1");
        in.add("create table2 id field1 field2");
        in.add("tables");
        in.add("find table2");
        in.add("create table3 id field1 field2 field3");
        in.add("tables");
        in.add("find table3");
        in.add("drop table1");
        in.add("tables");
        in.add("drop table2");
        in.add("tables");
        in.add("drop table3");
        in.add("tables");
        in.add("exit");

        Run.main(new String[0]);

        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Вы успешно подключились к базе данных " + TestConstants.TEST_DB_NAME + ".\n" +
                "В базе данных нет таблиц.\n" +
                "Таблица table1 добавлена.\n" +
                "table1\n" +
                "\n" +
                "+----+--------+\n" +
                "+ id + field1 +\n" +
                "+----+--------+\n" +
                "+----+--------+\n" +
                "\n" +
                "Таблица table2 добавлена.\n" +
                "table1\n" +
                "table2\n" +
                "\n" +
                "+----+--------+--------+\n" +
                "+ id + field1 + field2 +\n" +
                "+----+--------+--------+\n" +
                "+----+--------+--------+\n" +
                "\n" +
                "Таблица table3 добавлена.\n" +
                "table1\n" +
                "table2\n" +
                "table3\n" +
                "\n" +
                "+----+--------+--------+--------+\n" +
                "+ id + field1 + field2 + field3 +\n" +
                "+----+--------+--------+--------+\n" +
                "+----+--------+--------+--------+\n" +
                "\n" +
                "Таблица table1 удалена.\n" +
                "table2\n" +
                "table3\n" +
                "\n" +
                "Таблица table2 удалена.\n" +
                "table3\n" +
                "\n" +
                "Таблица table3 удалена.\n" +
                "В базе данных нет таблиц.\n" +
                "Отключились от БД. Закрытие программы...\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }

    @Test
    public void testInsertUpdateDeleteClearCommand() {
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("create table3 id field1 field2 field3");
        in.add("find table3");
        in.add("insert table3 id 1 field1 value11 field2 value21 field3 value31");
        in.add("find table3");
        in.add("insert table3 id 2 field1 value12 field2 value22 field3 value32");
        in.add("find table3");
        in.add("insert table3 id 3 field1 value13 field2 value23 field3 value33");
        in.add("find table3");
        in.add("delete table3 id 2");
        in.add("find table3");
        in.add("update table3 id 3 field2 newValue22");
        in.add("find table3");
        in.add("clear table3");
        in.add("find table3");
        in.add("exit");

        Run.main(new String[0]);

        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Вы успешно подключились к базе данных " + TestConstants.TEST_DB_NAME + ".\n" +
                "Таблица table3 добавлена.\n" +
                "+----+--------+--------+--------+\n" +
                "+ id + field1 + field2 + field3 +\n" +
                "+----+--------+--------+--------+\n" +
                "+----+--------+--------+--------+\n" +
                "\n" +
                "В таблицу table3 добавлена запись.\n" +
                "+----+---------+---------+---------+\n" +
                "+ id + field1  + field2  + field3  +\n" +
                "+----+---------+---------+---------+\n" +
                "+ 1  + value11 + value21 + value31 +\n" +
                "+----+---------+---------+---------+\n" +
                "\n" +
                "В таблицу table3 добавлена запись.\n" +
                "+----+---------+---------+---------+\n" +
                "+ id + field1  + field2  + field3  +\n" +
                "+----+---------+---------+---------+\n" +
                "+ 1  + value11 + value21 + value31 +\n" +
                "+ 2  + value12 + value22 + value32 +\n" +
                "+----+---------+---------+---------+\n" +
                "\n" +
                "В таблицу table3 добавлена запись.\n" +
                "+----+---------+---------+---------+\n" +
                "+ id + field1  + field2  + field3  +\n" +
                "+----+---------+---------+---------+\n" +
                "+ 1  + value11 + value21 + value31 +\n" +
                "+ 2  + value12 + value22 + value32 +\n" +
                "+ 3  + value13 + value23 + value33 +\n" +
                "+----+---------+---------+---------+\n" +
                "\n" +
                "Из таблицы table3 удалена запись.\n" +
                "+----+---------+---------+---------+\n" +
                "+ id + field1  + field2  + field3  +\n" +
                "+----+---------+---------+---------+\n" +
                "+ 1  + value11 + value21 + value31 +\n" +
                "+ 3  + value13 + value23 + value33 +\n" +
                "+----+---------+---------+---------+\n" +
                "\n" +
                "В таблице table3 обновлена запись.\n" +
                "+----+---------+------------+---------+\n" +
                "+ id + field1  + field2     + field3  +\n" +
                "+----+---------+------------+---------+\n" +
                "+ 1  + value11 + value21    + value31 +\n" +
                "+ 3  + value13 + newValue22 + value33 +\n" +
                "+----+---------+------------+---------+\n" +
                "\n" +
                "Таблица table3 очищена.\n" +
                "+----+--------+--------+--------+\n" +
                "+ id + field1 + field2 + field3 +\n" +
                "+----+--------+--------+--------+\n" +
                "+----+--------+--------+--------+\n" +
                "\n" +
                "Отключились от БД. Закрытие программы...\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }


    @Test
    public void testOnlyExitCommand() {
        in.add("exit");
        Run.main(new String[0]);
        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Закрытие программы...\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";
        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }

    @Test
    public void testCloseConnectionAndExitCommand() {
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("close");
        in.add("exit");
        Run.main(new String[0]);
        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Вы успешно подключились к базе данных testdb_a5d8e6.\n" +
                "Отключились от БД.\n" +
                "Закрытие программы...\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }

    @Test
    public void testOnlyHelpCommand() {
        in.add("help");
        in.add("exit");
        Run.main(new String[0]);
        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Список доступных комманд:\n" +
                "\tconnect serverName dbName username password:\n" +
                "\t\tподключиться к базе данных;\n" +
                "\tclose:\n" +
                "\t\tзакрыть соединение с базой данных;\n" +
                "\ttables:\n" +
                "\t\tпоказать список таблиц базы данных;\n" +
                "\tcolumns tableName:\n" +
                "\t\tпоказать список столбцов указанной таблицы;\n" +
                "\tcreate tableName column1 [column2] [columnN]:\n" +
                "\t\tдобавить новую таблицу (имя столбца не может начинаться с цифры);\n" +
                "\tdrop tableName:\n" +
                "\t\tудалить указанную таблицу;\n" +
                "\tclear tableName:\n" +
                "\t\tочистить содержимое указанной таблицы;\n" +
                "\tfind tableName:\n" +
                "\t\tпоказать содержимое указанной таблицы;\n" +
                "\tinsert tableName column1 value1 [column2 value2] [columnN valueN]:\n" +
                "\t\tдобавить запись в указанную таблицу;\n" +
                "\tupdate tableName whereColumn whereValue setColumn setValue:\n" +
                "\t\tобновить записи, удовлетворяющие условию в указанной таблице;\n" +
                "\tdelete tableName whereColumn whereValue:\n" +
                "\t\tудалить записи, удовлетворяющие условию;\n" +
                "\ttable_exist tableName:\n" +
                "\t\tпроверить наличие указанной таблицы в базе данных;\n" +
                "\tcolumn_exist tableName columnName:\n" +
                "\t\tпроверить наличие указанной колонки в указанной таблице;\n" +
                "\tdropdb dbName:\n" +
                "\t\tудалить базу данных;\n" +
                "\tcreatedb dbName:\n" +
                "\t\tдобавить новую базу данных;\n" +
                "\texecute \"textQuery\":\n" +
                "\t\tвыполнить пользовательский запрос (должен быть указан в двойных ковычках);\n" +
                "\thelp:\n" +
                "\t\tпоказать список команд и их описаниями;\n" +
                "\texit:\n" +
                "\t\tзакрыть соединение и выйти из программы;\n" +
                "\n" +
                "Закрытие программы...\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }

    // TODO добавить все неиспользуемые команды
    @Test
    public void testAllCommands() {
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("create table1 id field1 field2 field3");
        in.add("tables");
        in.add("insert table1 id 1 field1 field11Value field2 field21Value field3 field31Value");
        in.add("insert table1 id 2 field1 field12Value field2 field22Value field3 field32Value");
        in.add("insert table1 id 3 field1 field13Value field2 field23Value field3 field33Value");
        in.add("find table1");
        in.add("delete table1 id 2");
        in.add("find table1");
        in.add("update table1 id 1 field1 field11ValueNew");
        in.add("find table1");
        in.add("clear table1");
        in.add("find table1");
        in.add("drop table1");
        in.add("tables");
        in.add("help");
        in.add("exit");

        Run.main(new String[0]);

        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Вы успешно подключились к базе данных testdb_a5d8e6.\n" +
                "Таблица table1 добавлена.\n" +
                "table1\n" +
                "\n" +
                "В таблицу table1 добавлена запись.\n" +
                "В таблицу table1 добавлена запись.\n" +
                "В таблицу table1 добавлена запись.\n" +
                "+----+--------------+--------------+--------------+\n" +
                "+ id + field1       + field2       + field3       +\n" +
                "+----+--------------+--------------+--------------+\n" +
                "+ 1  + field11Value + field21Value + field31Value +\n" +
                "+ 2  + field12Value + field22Value + field32Value +\n" +
                "+ 3  + field13Value + field23Value + field33Value +\n" +
                "+----+--------------+--------------+--------------+\n" +
                "\n" +
                "Из таблицы table1 удалена запись.\n" +
                "+----+--------------+--------------+--------------+\n" +
                "+ id + field1       + field2       + field3       +\n" +
                "+----+--------------+--------------+--------------+\n" +
                "+ 1  + field11Value + field21Value + field31Value +\n" +
                "+ 3  + field13Value + field23Value + field33Value +\n" +
                "+----+--------------+--------------+--------------+\n" +
                "\n" +
                "В таблице table1 обновлена запись.\n" +
                "+----+-----------------+--------------+--------------+\n" +
                "+ id + field1          + field2       + field3       +\n" +
                "+----+-----------------+--------------+--------------+\n" +
                "+ 3  + field13Value    + field23Value + field33Value +\n" +
                "+ 1  + field11ValueNew + field21Value + field31Value +\n" +
                "+----+-----------------+--------------+--------------+\n" +
                "\n" +
                "Таблица table1 очищена.\n" +
                "+----+--------+--------+--------+\n" +
                "+ id + field1 + field2 + field3 +\n" +
                "+----+--------+--------+--------+\n" +
                "+----+--------+--------+--------+\n" +
                "\n" +
                "Таблица table1 удалена.\n" +
                "В базе данных нет таблиц.\n" +
                "Список доступных комманд:\n" +
                "\tconnect serverName dbName username password:\n" +
                "\t\tподключиться к базе данных;\n" +
                "\tclose:\n" +
                "\t\tзакрыть соединение с базой данных;\n" +
                "\ttables:\n" +
                "\t\tпоказать список таблиц базы данных;\n" +
                "\tcolumns tableName:\n" +
                "\t\tпоказать список столбцов указанной таблицы;\n" +
                "\tcreate tableName column1 [column2] [columnN]:\n" +
                "\t\tдобавить новую таблицу (имя столбца не может начинаться с цифры);\n" +
                "\tdrop tableName:\n" +
                "\t\tудалить указанную таблицу;\n" +
                "\tclear tableName:\n" +
                "\t\tочистить содержимое указанной таблицы;\n" +
                "\tfind tableName:\n" +
                "\t\tпоказать содержимое указанной таблицы;\n" +
                "\tinsert tableName column1 value1 [column2 value2] [columnN valueN]:\n" +
                "\t\tдобавить запись в указанную таблицу;\n" +
                "\tupdate tableName whereColumn whereValue setColumn setValue:\n" +
                "\t\tобновить записи, удовлетворяющие условию в указанной таблице;\n" +
                "\tdelete tableName whereColumn whereValue:\n" +
                "\t\tудалить записи, удовлетворяющие условию;\n" +
                "\ttable_exist tableName:\n" +
                "\t\tпроверить наличие указанной таблицы в базе данных;\n" +
                "\tcolumn_exist tableName columnName:\n" +
                "\t\tпроверить наличие указанной колонки в указанной таблице;\n" +
                "\tdropdb dbName:\n" +
                "\t\tудалить базу данных;\n" +
                "\tcreatedb dbName:\n" +
                "\t\tдобавить новую базу данных;\n" +
                "\texecute \"textQuery\":\n" +
                "\t\tвыполнить пользовательский запрос (должен быть указан в двойных ковычках);\n" +
                "\thelp:\n" +
                "\t\tпоказать список команд и их описаниями;\n" +
                "\texit:\n" +
                "\t\tзакрыть соединение и выйти из программы;\n" +
                "\n" +
                "Отключились от БД. Закрытие программы...\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }

    @Test
    public void testErrorData() {
        in.add("tables");
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("unsupported_command");
        in.add("");
        in.add("find table");
        in.add("create table1 id name");
        in.add("update table1 idd 1 name newName");
        in.add("execute \"bla\"");
        in.add("create table1 id name");
        in.add("column exist idd");
        in.add("create table2 3id name");
        in.add("exit ha");
        in.add("exit");

        Run.main(new String[0]);

        String expected =
                "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                        "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                        "\n" +
                        "Отсутствует подключение к базе данных.\n" +
                        "Вы успешно подключились к базе данных " + TestConstants.TEST_DB_NAME + ".\n" +
                        "Вы успешно подключились к базе данных " + TestConstants.TEST_DB_NAME + ".\n" +
                        "Команда 'unsupported_command' не найдена.\n" +
                        "Введена пустая команда.\n" +
                        "Таблица table отсутствует.\n" +
                        "Таблица table1 добавлена.\n" +
                        "Колонка idd отсутствует.\n" +
                        "Ошибка при работе с СУБД: ERROR: syntax error at or near \"bla\"\n" +
                        "  Позиция: 1.\n" +
                        "Таблица table1 уже существует.\n" +
                        "Команда 'column' не найдена.\n" +
                        "Параметры команды указаны не корректно. \n" +
                        "Формат команды: \n" +
                        "\tcreate tableName column1 [column2] [columnN]:\n" +
                        "\t\tдобавить новую таблицу (имя столбца не может начинаться с цифры);\n" +
                        "Параметры команды указаны не корректно. \n" +
                        "Формат команды: \n" +
                        "\texit:\n" +
                        "\t\tзакрыть соединение и выйти из программы;\n" +
                        "Отключились от БД. Закрытие программы...\n" +
                        "\n" +
                        "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }

    @Test
    public void testDropDB() {
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("createdb test321db");
        in.add("dropdb test321db");
        in.add("exit");

        Run.main(new String[0]);

        String expected =
                "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                        "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                        "\n" +
                        "Вы успешно подключились к базе данных " + TestConstants.TEST_DB_NAME + ".\n" +
                        "База данных test321db добавлена.\n" +
                        "База данных test321db удалена.\n" +
                        "Отключились от БД. Закрытие программы...\n" +
                        "\n" +
                        "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), out.getData());
    }

}




