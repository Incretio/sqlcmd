package ru.incretio.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.service.exceptions.ServiceException;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CloseConnection extends CommandTestBase {
    private ServiceImpl service;
    private Model model;

    @Before
    public void setup(){
        model = mock(Model.class);
        service = new ServiceImpl(model);
    }

    @Test
    public void closeConnection_correct_test() throws ServiceException, MissingConnectionException, SQLException {
        // given

        // when
        service.closeConnection();

        // then
        verify(model).closeConnection();
    }


}
