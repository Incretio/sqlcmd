package ru.incretio.juja.sqlcmd.conroller.command.list.utils;

import org.junit.Test;
import org.mockito.Mockito;
import ru.incretio.juja.sqlcmd.exceptions.EmptyColumnsNamesTableFormatterException;
import ru.incretio.juja.sqlcmd.exceptions.EmptyDataTableFormatterException;
import ru.incretio.juja.sqlcmd.exceptions.IncorrectDataTableFormatterException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ResultSetTableFormatterTest {

    @Test
    public void columnsNames_correct_test() throws Exception {
        ResultSetMetaData resultSetMetaDataMock = takeResultSetMetaDataMock();
        ResultSet resultSetMock = takeResultSet(resultSetMetaDataMock);
        ResultSetTableFormatter resultSetTableFormatter = new ResultSetTableFormatter();
        resultSetTableFormatter.setResultSet(resultSetMock);

        List<String> expectedColumnsNames = Arrays.asList("id", "name", "value");
        List<String> actualColumnsNames = resultSetTableFormatter.getColumnsNames();
        assertEquals(expectedColumnsNames, actualColumnsNames);
    }

    @Test
    public void data_correct_test() throws SQLException, EmptyDataTableFormatterException, IncorrectDataTableFormatterException, EmptyColumnsNamesTableFormatterException {
        ResultSetMetaData resultSetMetaDataMock = takeResultSetMetaDataMock();
        ResultSet resultSetMock = takeResultSet(resultSetMetaDataMock);
        ResultSetTableFormatter resultSetTableFormatter = new ResultSetTableFormatter();
        resultSetTableFormatter.setResultSet(resultSetMock);

        List<List<String>> expectedData = Arrays.asList(
                Arrays.asList("1", "name1", "value1"),
                Arrays.asList("2", "", "value2"),
                Arrays.asList("3", "name3", "value3"));
        List<List<String>> actualData = resultSetTableFormatter.getData();
        assertEquals(expectedData, actualData);
    }

    private ResultSetMetaData takeResultSetMetaDataMock() throws SQLException {
        ResultSetMetaData resultSetMetaDataMock = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(resultSetMetaDataMock.getColumnCount()).thenReturn(3);
        Mockito.when(resultSetMetaDataMock.getColumnLabel(Mockito.anyInt()))
                .thenReturn("id", "name", "value");
        return resultSetMetaDataMock;
    }

    private ResultSet takeResultSet(ResultSetMetaData resultSetMetaData) throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.next())
                .thenReturn(true, true, true, false);
        Mockito.when(resultSetMock.getString(Mockito.anyInt()))
                .thenReturn("1", "name1", "value1")
                .thenReturn("2", "", "value2")
                .thenReturn("3", "name3", "value3");
        Mockito.when(resultSetMock.getMetaData()).thenReturn(resultSetMetaData);
        return resultSetMock;
    }
}