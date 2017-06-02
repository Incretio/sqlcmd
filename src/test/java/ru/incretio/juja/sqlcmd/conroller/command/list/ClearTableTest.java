package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ClearTableTest extends CommandTestBase {

    @Test
    public void clearTablePerform_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Arrays.asList(TABLE_NAME));
        ClearTable clearTable = new ClearTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList(TABLE_NAME);

        clearTable.perform(model, view, params);

        verify(model).takeTables();
        verify(model).executeClearTable(TABLE_NAME);
        verify(view).write(String.format(takeCaption("tableCleared"), TABLE_NAME));
    }

}

