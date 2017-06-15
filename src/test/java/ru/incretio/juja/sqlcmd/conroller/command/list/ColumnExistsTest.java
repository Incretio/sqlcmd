package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ColumnExistsTest extends CommandTestBase {

    @Test
    public void columnExists_correct_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME))
                .thenReturn(Collections.singletonList(COLUMN_ONE_NAME));
        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, COLUMN_ONE_NAME);

        columnExists.perform(model, view, params);

        verify(model).takeTablesList();
        verify(model).takeTableColumns(TABLE_NAME);
    }

    @Test(expected = MissingColumnException.class)
    public void columnExists_missingColumn_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME))
                .thenReturn(Collections.singletonList("column1"));
        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, COLUMN_ONE_NAME);

        try {
            columnExists.perform(model, view, params);
        } catch (Exception e) {
            verify(model).takeTablesList();
            verify(model).takeTableColumns(TABLE_NAME);
            throw e;
        }
    }

}