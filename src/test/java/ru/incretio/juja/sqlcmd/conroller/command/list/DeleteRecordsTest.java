package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class DeleteRecordsTest extends CommandTestBase {

    @Test
    public void deleteRecords_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(Collections.singletonList("whereColumnName"));
        DeleteRecords deleteRecords = new DeleteRecords(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, "whereColumnName", "whereColumnValue");

        deleteRecords.perform(model, view, params);

        verify(model, times(2)).takeTables();
        verify(model).takeTableColumns(TABLE_NAME);
        verify(model).executeDeleteRecords(TABLE_NAME, "whereColumnName", "whereColumnValue");
        verify(view).write(String.format(takeCaption("recordDeletedPattern"), TABLE_NAME));
    }

}