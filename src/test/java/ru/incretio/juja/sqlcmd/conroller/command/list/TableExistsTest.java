package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TableExistsTest extends CommandTestBase {

    @Test
    public void tableExists_correct_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.singletonList(TABLE_NAME));
        TableExists tableExists = new TableExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        tableExists.perform(model, view, params);

        verify(model).takeTablesList();
    }

    @Test(expected = MissingTableException.class)
    public void tableExists_missingTable_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.emptyList());
        TableExists tableExists = new TableExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        try {
            tableExists.perform(model, view, params);
        } catch (MissingTableException e) {
            verify(model).takeTablesList();
            throw e;
        }
    }
}