package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class SelectTableTest extends CommandTestBase {

    @Test
    public void selectTable_emptyData_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.singletonList(TABLE_NAME));
        SelectTable selectTable = new SelectTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        selectTable.perform(model, view, params);

        verify(model).takeTablesList();
        verify(model).find(any(), eq(TABLE_NAME));
        verify(view).write(String.format(takeCaption("tableEmptyPattern"), TABLE_NAME));
    }
}