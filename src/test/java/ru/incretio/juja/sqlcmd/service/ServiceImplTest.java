package ru.incretio.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.ResultSetTableFormatter;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest extends CommandTestBase{

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private Model model;

    @Test
    public void select_correctData_test() throws Exception {
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