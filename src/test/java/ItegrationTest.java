
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.incretio.juja.sqlcmd.Run;

import java.io.*;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by incre on 27.02.2017.
 */
public class ItegrationTest {
    private TestInputStream in;
    private TestOutputStream out;

    @Before
    public void setup() throws UnsupportedEncodingException {
        in = new TestInputStream();
        System.setIn(in);

        out = new TestOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testOnlyExit() throws IOException {
        in.add("exit");
        Run.main(new String[0]);
        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd!\"\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Программа будет закрыта.\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";
        assertEquals(expected, out.getData());
    }

    @Test
    public void testOnlyHelp() {
        in.add("help");
        in.add("exit");
        Run.main(new String[0]);
        String expected = "Добро пожаловать в учебный проект Incretio \"sqlcmd!\"\n" +
                "Тут вы можете работать с базой данных. Для того, чтобы получить список возможных комманд, используйте комманду help.\n" +
                "\n" +
                "Список доступных комманд:\n" +
                "\tconnect serverName dbName username password:\n" +
                "\t\tподключиться к базе данных;\n" +
                "\ttables:\n" +
                "\t\tпоказать список таблиц базы данных;\n" +
                "\tclear tableName:\n" +
                "\t\tочистить содержимое указанной таблицы;\n" +
                "\tdrop tableName:\n" +
                "\t\tудалить указанную таблицу;\n" +
                "\tcreate tableName column1 [column2] [columnN]:\n" +
                "\t\tдобавить новую таблицу;\n" +
                "\tfind tableName:\n" +
                "\t\tпоказать содержимое указанной таблицы;\n" +
                "\tinsert tableName column1 value1 [column2 value2] [columnN valueN]:\n" +
                "\t\tдобавить запись в указанную таблицу;\n" +
                "\tupdate tableName whereColumn whereValue setColumn setValue:\n" +
                "\t\tобновить записи, удовлетворяющие условию в указанной таблице;\n" +
                "\tdelete tableName whereColumn whereValue:\n" +
                "\t\tудалить записи, удовлетворяющие условию;\n" +
                "\thelp:\n" +
                "\t\tпоказать список команд и их описаниями;\n" +
                "\texit:\n" +
                "\t\tзакрыть соединение и выйти из программы;\n" +
                "Программа будет закрыта.\n" +
                "\n" +
                "Спасибо за использование нашей программы! Мы старались ;)\n";
        assertEquals(expected, out.getData());
    }
}




