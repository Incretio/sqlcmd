package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class DropDBTest extends CommandTestBase {

    @Test
    public void dropDB_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        DropDB dropDB = new DropDB(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(DB_NAME);

        dropDB.perform(model, view, params);

        verify(model).executeDropDB(DB_NAME);
        verify(view).write(String.format(takeCaption("dbDeletedPattern"), DB_NAME));
    }

}