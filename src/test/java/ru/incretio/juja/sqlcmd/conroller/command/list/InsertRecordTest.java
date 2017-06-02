package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class InsertRecordTest extends CommandTestBase {

    @Test
    public void insertRecords_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(Arrays.asList("column1", "column2"));
        InsertRecord insertRecord = new InsertRecord(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, "column1", "value1", "column2", "value2");

        insertRecord.perform(model, view, params);

        // Для каждой колонки по разу + проверка перед выполнением
        verify(model, times(3)).takeTables();
        verify(model, times(2)).takeTableColumns(TABLE_NAME);
        List<String> columns = Arrays.asList("column1", "column2");
        List<String> values = Arrays.asList("value1", "value2");
        verify(model).executeInsertRecord(TABLE_NAME, columns, values);
        verify(view).write(String.format(takeCaption("recordInsertedPattern"), TABLE_NAME));
    }
}