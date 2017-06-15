package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class CreateTableTest extends CommandTestBase {
    @Test
    public void createTable_correct_test() throws Exception {
        CreateTable createTable = new CreateTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);

        createTable.perform(model, view, TABLE_AND_COLUMNS_LIST);

        verify(model).takeTablesList();
        verify(model).executeCreateTable(TABLE_NAME, COLUMNS_LIST);
        verify(view).write(String.format(takeCaption("tableAddedPattern"), TABLE_NAME));
    }

    @Test
    public void createTable_alreadyExists_test() throws Exception {
        when(model.takeTablesList()).thenReturn(Collections.singletonList(TABLE_NAME));
        CreateTable createTable = new CreateTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);

        createTable.perform(model, view, TABLE_AND_COLUMNS_LIST);

        verify(model).takeTablesList();
        verify(model, never()).executeCreateTable(anyString(), anyList());
        verify(view).write(String.format(takeCaption("tableAlreadyExistsPattern"), TABLE_NAME));
    }

}