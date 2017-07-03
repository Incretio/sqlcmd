package ru.incretio.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.utils.ResultSetTableFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Select extends CommandTestBase{

    private ServiceImpl service;
    private Model model;

    @Before
    public void setup(){
        model = mock(Model.class);
        service = new ServiceImpl(model);
    }

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

    @Test
    public void select_emptyColumnsNames_test() throws Exception {
        // given
        ResultSetTableFormatter rsTableFormatter = mock(ResultSetTableFormatter.class);
        when(rsTableFormatter.getColumnsNames()).thenReturn(Collections.emptyList());
        when(rsTableFormatter.getData()).thenReturn(DATA);
        when(model.find(TABLE_NAME)).thenReturn(rsTableFormatter);

        // when
        List<List<String>> data = service.select(TABLE_NAME);

        // then
        assertEquals(EMPTY_DATA, data);
    }

    @Test
    public void select_emptyData_test() throws Exception {
        // given
        ResultSetTableFormatter rsTableFormatter = mock(ResultSetTableFormatter.class);
        when(rsTableFormatter.getColumnsNames()).thenReturn(COLUMNS_LIST);
        when(rsTableFormatter.getData()).thenReturn(new ArrayList<>(Collections.emptyList()));
        when(model.find(TABLE_NAME)).thenReturn(rsTableFormatter);

        // when
        List<List<String>> data = service.select(TABLE_NAME);

        // then
        assertEquals(DATA_ONLY_COLUMNS, data);
    }

}