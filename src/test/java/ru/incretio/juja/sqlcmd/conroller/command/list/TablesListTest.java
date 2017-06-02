package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class TablesListTest extends CommandTestBase {

    @Test
    public void tablesList_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Arrays.asList("table1", "table2"));
        TablesList tablesList = new TablesList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        tablesList.perform(model, view, params);

        verify(model).takeTables();
        verify(view).write(Arrays.asList("table1", "table2"));
    }

    @Test
    public void tablesList_dbEmpty_test() throws Exception {
        TablesList tablesList = new TablesList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        tablesList.perform(model, view, params);

        verify(model).takeTables();
        verify(view).write(takeCaption("dbEmpty"));
    }
}