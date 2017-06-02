package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

/**
 * Created by incre on 03.06.2017.
 */
public class CloseConnectionTest extends CommandTestBase {
    @Test
    public void closePerform_correct_test() throws Exception {
        CloseConnection closeConnection = new CloseConnection(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();

        closeConnection.perform(model, view, params);

        verify(model).closeConnection();
        verify(view).write(takeCaption("connectionClosed"));
    }

}