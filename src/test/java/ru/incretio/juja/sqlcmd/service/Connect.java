package ru.incretio.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import ru.incretio.juja.sqlcmd.model.Model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Connect extends CommandTestBase{

    private ServiceImpl service;
    private Model model;

    @Before
    public void setup(){
        model = mock(Model.class);
        service = new ServiceImpl(model);
    }

    @Test
    public void connect_correct_test() throws Exception {
        // given

        // when
        service.connect(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);

        // then
        verify(model).connect(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);
    }
}
