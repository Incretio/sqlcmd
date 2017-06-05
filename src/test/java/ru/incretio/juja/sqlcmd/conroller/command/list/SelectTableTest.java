package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Ignore;
import org.junit.Test;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.ResultSetTableFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class SelectTableTest extends CommandTestBase {

    @Ignore
    @Test
    public void selectTable_correct_test() throws Exception {
        ResultSetTableFormatter resultSetTableFormatter = mock(ResultSetTableFormatter.class);
        List<List<String>> data = new ArrayList<>();
        when(resultSetTableFormatter.getData()).thenReturn(data);
        when(resultSetTableFormatter.getColumnsNames()).thenReturn(COLUMNS_LIST);
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        SelectTable selectTable = new SelectTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK, resultSetTableFormatter);
        params = Arrays.asList(TABLE_NAME);

        selectTable.perform(model, view, params);

        verify(model).takeTables();
        verify(model).find(any(), eq(TABLE_NAME));
        verify(view).writeSelectTable(data, COLUMNS_LIST);
    }

    @Test
    public void selectTable_emptyData_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        SelectTable selectTable = new SelectTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        selectTable.perform(model, view, params);

        verify(model).takeTables();
        verify(model).find(any(), eq(TABLE_NAME));
        verify(view).write(String.format(takeCaption("tableEmptyPattern"), TABLE_NAME));
    }
}