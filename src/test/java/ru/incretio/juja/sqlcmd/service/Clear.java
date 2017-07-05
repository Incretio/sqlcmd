package ru.incretio.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.model.Model;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Clear extends CommandTestBase{

    private ServiceImpl service;
    private Model model;

    @Before
    public void setup(){
        model = mock(Model.class);
        service = new ServiceImpl(model);
    }

    @Test
    public void clear_correct_test() throws Exception {
        // given

        // when
        service.clear(TABLE_NAME);

        // then
        verify(model).executeClearTable(TABLE_NAME);
    }
}
