package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class ConnectTest extends CommandTestBase {
    @Test
    public void connect_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Connect connect = new Connect(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);

        connect.perform(model, view, params);

        verify(model).connect(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);
        verify(view).write(String.format(takeCaption("connectionSuccessPattern"), DB_NAME));
    }

    @Test(expected = ClassNotFoundException.class)
    public void connect_driverLoadingError_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Connect connect = new Connect(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        doThrow(ClassNotFoundException.class).when(model).connect(anyString(), anyString(), anyString(), anyString());
        params = Arrays.asList(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);

        try {
            connect.perform(model, view, params);
        } catch (Exception e) {
            verify(model).connect(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);
            throw e;
        }
    }

}