package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;

import java.util.Collections;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExitApplicationTest extends CommandTestBase {

    @Test(expected = NeedExitException.class)
    public void exit_isConnected_test() throws Exception {
        when(model.isConnected()).thenReturn(true);
        ExitApplication exitApplication = new ExitApplication(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();

        try {
            exitApplication.perform(model, view, params);
        } catch (NeedExitException e) {
            verify(model).isConnected();
            verify(model).closeConnection();
            throw e;
        }
    }

    @Test(expected = NeedExitException.class)
    public void exit_isNotConnected_test() throws Exception {
        ExitApplication exitApplication = new ExitApplication(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();

        try {
            exitApplication.perform(model, view, params);
        } catch (NeedExitException e) {
            verify(model).isConnected();
            verify(model, never()).closeConnection();
            throw e;
        }
    }
}