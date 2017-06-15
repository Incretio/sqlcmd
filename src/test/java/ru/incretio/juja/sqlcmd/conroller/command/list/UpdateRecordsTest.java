package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateRecordsTest extends CommandTestBase {

    @Test
    public void updateRecords_correct_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(Arrays.asList("column1", "column2"));
        UpdateRecords updateRecords = new UpdateRecords(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, "column1", "value1", "column2", "value2");

        updateRecords.perform(model, view, params);

        // Для каждой колонки по разу + проверка перед выполнением
        verify(model, times(3)).takeTablesList();
        verify(model, times(2)).takeTableColumns(TABLE_NAME);
        verify(model).executeUpdateRecords(TABLE_NAME, "column1", "value1", "column2", "value2");
    }
}