package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ExecuteQueryTest extends CommandTestBase {

    @Test
    public void executeQuery_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        ExecuteQuery executeQuery = new ExecuteQuery(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("select * from table");

        executeQuery.perform(model, view, params);

        verify(model).execute("select * from table");
        verify(view).write(takeCaption("queryExecuted"));
    }

}