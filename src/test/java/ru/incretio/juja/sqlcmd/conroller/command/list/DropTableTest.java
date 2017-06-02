package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

/**
 * Created by incre on 03.06.2017.
 */
public class DropTableTest extends CommandTestBase {

    @Test
    public void dropTable_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        DropTable dropTable = new DropTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        dropTable.perform(model, view, params);

        verify(model).takeTables();
        verify(model).executeDropTable(TABLE_NAME);
        verify(view).write(String.format(takeCaption("tableDeletedPattern"), TABLE_NAME));
    }

}