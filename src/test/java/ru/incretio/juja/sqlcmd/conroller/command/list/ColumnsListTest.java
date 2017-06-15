package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by incre on 03.06.2017.
 */
public class ColumnsListTest extends CommandTestBase {

    @Test
    public void columnsList_correct_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(COLUMNS_LIST);
        ColumnsList columnsList = new ColumnsList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        columnsList.perform(model, view, params);

        verify(model).takeTablesList();
        verify(model).takeTableColumns(TABLE_NAME);
        verify(view).write(COLUMNS_LIST);
    }

}