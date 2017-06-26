package ru.incretio.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.ResultSetTableFormatter;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceImplTest extends CommandTestBase{

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private Model model;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void select_correct_test() throws Exception {
        // given
        ResultSetTableFormatter resultSetTableFormatter = mock(ResultSetTableFormatter.class);
        when(resultSetTableFormatter.getColumnsNames()).thenReturn(COLUMNS_LIST);
        when(resultSetTableFormatter.getData()).thenReturn(DATA);
        when(model.find(TABLE_NAME)).thenReturn(resultSetTableFormatter);

        // when
        List<List<String>> data = service.select(TABLE_NAME);

        // then
        assertEquals(DATA_WITH_COLUMNS, data);
    }

}