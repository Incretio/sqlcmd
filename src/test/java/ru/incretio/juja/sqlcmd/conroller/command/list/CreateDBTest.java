package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class CreateDBTest extends CommandTestBase {

    @Test
    public void createDB_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        CreateDB createDB = new CreateDB(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList(DB_NAME);

        createDB.perform(model, view, params);

        verify(model).executeCreateDB(DB_NAME);
        verify(view).write(String.format(takeCaption("dbCreatedPattern"), DB_NAME));
    }

}