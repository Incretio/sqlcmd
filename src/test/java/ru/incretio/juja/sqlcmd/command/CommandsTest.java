package ru.incretio.juja.sqlcmd.command;

import org.junit.*;
import ru.incretio.juja.sqlcmd.ConnectionConfig;
import ru.incretio.juja.sqlcmd.command.perform.*;
import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingJDBCConnectionTypeException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;
import ru.incretio.juja.sqlcmd.query.QueryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.incretio.juja.sqlcmd.TestConstants.*;

public class CommandsTest {
    private static ConnectionConfig connectionConfig;

    @BeforeClass
    public static void setup() throws Exception {
        connectionConfig =
                new ConnectionConfig(QueryFactory.makeSQLQuery(JDBCConnectionType.PostgreSQL));
        List<String> params = getListWithParams(
                SERVER_NAME,
                MASTER_DB,
                USER_NAME,
                PASSWORD);
        new ConnectPerform().perform(connectionConfig, params);
        params = getListWithParams(TEST_DB_NAME);
        try {
            new DropDBPerform().perform(connectionConfig, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new CreateDBPerform().perform(connectionConfig, params);
        params = getListWithParams(
                SERVER_NAME,
                TEST_DB_NAME,
                USER_NAME,
                PASSWORD);
        new ConnectPerform().perform(connectionConfig, params);
        params = getListWithParams(
                "table", "id", "name");
        new CreatePerform().perform(connectionConfig, params);
        params = getListWithParams(
                "table0", "id", "name");
        new CreatePerform().perform(connectionConfig, params);
    }

    @AfterClass
    public static void clearDataAfterTest() {
        List<String> params = getListWithParams(TEST_DB_NAME);
        try {
            new DropDBPerform().perform(connectionConfig, params);
        } catch (Exception e) {
            // do nothing, DB no exist
        }
    }

    @Test
    public void testCreateAndDropCommandPerform() throws MissingConnectionException, SQLException, MissingTableException {
        String actual;
        List<String> params = getListWithParams(
                "table1",
                "id",
                "name");
        actual = new CreatePerform().perform(connectionConfig, params);
        assertEquals("Таблица table1 добавлена.", actual);
        params = getListWithParams(
                "table1",
                "id",
                "name");
        actual = new CreatePerform().perform(connectionConfig, params);
        assertEquals("Таблица table1 уже существует.", actual);
        params = getListWithParams("table1");
        new DropPerform().perform(connectionConfig, params);
    }

    @Test
    public void testClearCommandPerform() throws Exception {
        String actual;
        List<String> params = getListWithParams("table");

        actual = new ClearPerform().perform(connectionConfig, params);
        assertEquals("Таблица table очищена.", actual);
        params = getListWithParams("elbat");
        try {
            new ClearPerform().perform(connectionConfig, params);
        } catch (MissingTableException e) {
            assertEquals("Таблица elbat отсутствует.", e.getMessage());
        }
    }

    @Test
    public void testDeletePerform() throws Exception {
        String actual;
        List<String> params = getListWithParams("table", "id", "sdafasdf");
        actual = new DeletePerform().perform(connectionConfig, params);
        assertEquals("Из таблицы table удалена запись.", actual);
        params = getListWithParams("table", "asdfkj", "1");
        try {
            new DeletePerform().perform(connectionConfig, params);
        } catch (MissingColumnException e) {
            assertEquals("Колонка asdfkj отсутствует.", e.getMessage());
        }
    }

    @Test
    public void testDropPerform() throws MissingConnectionException, SQLException, MissingTableException {
        String actual;
        List<String> params = getListWithParams("table10", "id");
        new CreatePerform().perform(connectionConfig, params);
        params = getListWithParams("table10");
        actual = new DropPerform().perform(connectionConfig, params);
        assertEquals("Таблица table10 удалена.", actual);
        params = getListWithParams("table10");
        try {
            new DropPerform().perform(connectionConfig, params);
        } catch (MissingTableException e) {
            assertEquals("Таблица table10 отсутствует.", e.getMessage());
        }
    }

    @Test
    public void testClearInsertUpdateAndFindPerform() throws Exception {
        String actual;
        List<String> params = getListWithParams("table");
        new ClearPerform().perform(connectionConfig, params);
        params = getListWithParams("table", "id", "1");
        actual = new InsertPerform().perform(connectionConfig, params);
        assertEquals("В таблицу table добавлена запись.", actual);
        params = getListWithParams("table", "id", "1", "name", "newvalue");
        actual = new UpdatePerform().perform(connectionConfig, params);
        assertEquals("В таблице table обновлена запись.", actual);
        params = getListWithParams("table");
        actual = new FindPerform().perform(connectionConfig, params);
        assertEquals(
                "+----+----------+\n" +
                        "+ id + name     +\n" +
                        "+----+----------+\n" +
                        "+ 1  + newvalue +\n" +
                        "+----+----------+\n",
                actual);
    }

    @Test
    public void testTablesPerform() throws MissingConnectionException, SQLException {
        String actual;
        List<String> params = getListWithParams();
        actual = new TablesPerform().perform(connectionConfig, params);
        assertEquals(
                "table\n" +
                        "table0\n",
                actual);
    }

    private static List<String> getListWithParams(String... params) {
        List<String> result = new ArrayList<>();
        for (String param : params) {
            result.add(param);
        }

        return result;
    }

}