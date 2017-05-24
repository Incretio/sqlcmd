package ru.incretio.juja.sqlcmd.conroller.command;

import org.junit.*;
import ru.incretio.juja.sqlcmd.conroller.command.list.Connect;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;
import ru.incretio.juja.sqlcmd.model.query.QueryFactory;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.incretio.juja.sqlcmd.TestConstants.*;

public class CommandsTest {
    private static Model model;
    private static View view;

    /*
    @BeforeClass
    public static void setup() throws Exception {
        model =
                new Model(QueryFactory.makeSQLQuery(JDBCConnectionType.PostgreSQL));
        view = null;
        List<String> params = getListWithParams(
                SERVER_NAME,
                MASTER_DB,
                USER_NAME,
                PASSWORD);
        new Connect().perform(model, view, params);
        params = getListWithParams(TEST_DB_NAME);
        try {
            new DropDB().perform( model, view, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new CreateDB().perform( model, view, params);
        params = getListWithParams(
                SERVER_NAME,
                TEST_DB_NAME,
                USER_NAME,
                PASSWORD);
        new Connect().perform( model, view, params);
        params = getListWithParams(
                "table", "id", "name");
        new CreateTable().perform( model, view, params);
        params = getListWithParams(
                "table0", "id", "name");
        new CreateTable().perform( model, view, params);
    }

    @AfterClass
    public static void clearDataAfterTest() {
        List<String> params = getListWithParams(TEST_DB_NAME);
        try {
            new DropDB().perform( model, view, params);
        } catch (Exception e) {
            // do nothing, DB no exist
        }
    }

    @Test
    public void testCreateAndDropCommandPerform() throws Exception {
        String actual;
        List<String> params = getListWithParams(
                "table1",
                "id",
                "name");
        actual = new CreateTable().perform( model, view, params);
        assertEquals("Таблица table1 добавлена.", actual);
        params = getListWithParams(
                "table1",
                "id",
                "name");
        actual = new CreateTable().perform( model, view, params);
        assertEquals("Таблица table1 уже существует.", actual);
        params = getListWithParams("table1");
        new DropTable().perform( model, view, params);
    }

    @Test
    public void testClearCommandPerform() throws Exception {
        String actual;
        List<String> params = getListWithParams("table");

        actual = new ClearPerform().perform( model, view, params);
        assertEquals("Таблица table очищена.", actual);
        params = getListWithParams("elbat");
        try {
            new ClearPerform().perform( model, view, params);
        } catch (MissingTableException e) {
            assertEquals("Таблица elbat отсутствует.", e.getMessage());
        }
    }

    @Test
    public void testDeletePerform() throws Exception {
        String actual;
        List<String> params = getListWithParams("table", "id", "sdafasdf");
        actual = new DeleteRecords().perform( model, view, params);
        assertEquals("Из таблицы table удалена запись.", actual);
        params = getListWithParams("table", "asdfkj", "1");
        try {
            new DeleteRecords().perform( model, view, params);
        } catch (MissingColumnException e) {
            assertEquals("Колонка asdfkj отсутствует.", e.getMessage());
        }
    }

    @Test
    public void testDropPerform() throws Exception {
        String actual;
        List<String> params = getListWithParams("table10", "id");
        new CreateTable().perform( model, view, params);
        params = getListWithParams("table10");
        actual = new DropTable().perform( model, view, params);
        assertEquals("Таблица table10 удалена.", actual);
        params = getListWithParams("table10");
        try {
            new DropTable().perform( model, view, params);
        } catch (MissingTableException e) {
            assertEquals("Таблица table10 отсутствует.", e.getMessage());
        }
    }

    @Test
    public void testClearInsertUpdateAndFindPerform() throws Exception {
        String actual;
        List<String> params = getListWithParams("table");
        new ClearPerform().perform( model, view, params);
        params = getListWithParams("table", "id", "1");
        actual = new InsertRecord().perform( model, view, params);
        assertEquals("В таблицу table добавлена запись.", actual);
        params = getListWithParams("table", "id", "1", "name", "newvalue");
        actual = new UpdateRecords().perform( model, view, params);
        assertEquals("В таблице table обновлена запись.", actual);
        params = getListWithParams("table");
        actual = new SelectTable().perform( model, view, params);
        String expected = "+----+----------+\n" +
                "+ id + name     +\n" +
                "+----+----------+\n" +
                "+ 1  + newvalue +\n" +
                "+----+----------+\n";
        assertEquals(expected.replace("\n", System.lineSeparator()), actual);
    }

    @Test
    public void testTablesPerform() throws MissingConnectionException, SQLException {
        List<String> params = getListWithParams();
        String actual = new TablesList().perform( model, view, params);
        String expected = "table\n" +
                "table0\n";

        assertEquals(expected.replace("\n", System.lineSeparator()), actual);
    }

    private static List<String> getListWithParams(String... params) {
        List<String> result = new ArrayList<>();
        for (String param : params) {
            result.add(param);
        }

        return result;
    }
*/
}