package ru.incretio.juja.sqlcmd;

import org.junit.*;
import ru.incretio.juja.sqlcmd.model.query.QueryFactory;

import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class IntegrationTest {
    private final static TestInputStream in = new TestInputStream();
    private final static TestOutputStream out = new TestOutputStream();

    @Before
    public void setupTest() {
        System.setIn(in);
        System.setOut(new PrintStream(out));

        in.add(TestConstants.MASTER_CONNECTION_STRING);
        in.add(String.format("dropdb %s", TestConstants.TEST_DB_NAME));
        in.add(String.format("createdb %s", TestConstants.TEST_DB_NAME));
        in.add("exit");
        Main.main(new String[0]);
        in.reset();
        out.reset();
    }

    @AfterClass
    public static void clearDataAfterTest() {
        in.add(TestConstants.MASTER_CONNECTION_STRING);
        in.add(String.format("dropdb %s", TestConstants.TEST_DB_NAME));
        in.add("exit");
        Main.main(new String[0]);
    }

    @Test
    public void allCommands_correct_test() {
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("create table1 id field1 field2 field3");
        in.add("tables");
        in.add("insert table1 id 1 field1 field11Value field2 field21Value field3 field31Value");
        in.add("insert table1 id 2 field1 field12Value field2 field22Value field3 field32Value");
        in.add("insert table1 id 3 field1 field13Value field2 field23Value field3 field33Value");
        in.add("select table1");
        in.add("delete table1 id 2");
        in.add("select table1");
        in.add("update table1 id 1 field1 field11ValueNew");
        in.add("select table1");
        in.add("clear table1");
        in.add("select table1");
        in.add("drop table1");
        in.add("tables");
        in.add("getHelp");
        in.add("close");
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("exit");

        Main.main(new String[0]);

        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду getHelp.\n" +
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
                "\tgetHelp:\n" +
                "\t\tпоказать список команд и их описаниями;\n" +
                "\texecute \"textQuery\":\n" +
                "\t\tвыполнить пользовательский запрос (должен быть указан в двойных ковычках);\n" +
                "\n" +
                "Отключились от БД.\n" +
                "Вы успешно подключились к базе данных testdb_a5d8e6.\n" +
                "Отключились от БД. Закрытие программы...\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";

        assertEqualsWithLineSeparatorReplace(expected, out.getData());
    }

    @Test
    public void differentCommands_incorrectData_test() {
        in.add("tables");
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add(TestConstants.TEST_CONNECTION_STRING);
        in.add("unsupported_command");
        in.add("");
        in.add("select table");
        in.add("create table1 id name");
        in.add("update table1 idd 1 name newName");
        in.add("execute \"bla\"");
        in.add("create table1 id name");
        in.add("column exist idd");
        in.add("create table2 3id name");
        in.add("exit ha");
        in.add("exit");

        Main.main(new String[0]);

        String expected =
                "Добро пожаловать в учебный проект Incretio \"sqlcmd\"!\n" +
                        "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду getHelp.\n" +
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

        assertEqualsWithLineSeparatorReplace(expected, out.getData());
    }

    private void assertEqualsWithLineSeparatorReplace(String expected, String actual){
        assertEquals(expected.replace("\n", System.lineSeparator()), actual);
    }

}




