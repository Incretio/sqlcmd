package ru.incretio.juja.sqlcmd.view;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.EmptyColumnsNamesTableFormatterException;
import ru.incretio.juja.sqlcmd.exceptions.EmptyDataTableFormatterException;
import ru.incretio.juja.sqlcmd.exceptions.IncorrectDataTableFormatterException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TableFormatterTest {
    private final static List<String> columnsNamesTest =
            Arrays.asList(new String[]{"id", "name", "value column", "column else"});
    private final static List<String> columnsNamesOneColumnTest =
            Arrays.asList(new String[]{"id"});
    private final static List<List<String>> dataTest =
            Arrays.asList(
                    Arrays.asList(new String[]{"1", "name one", "value one", "something else"}),
                    Arrays.asList(new String[]{"2", "name two two", "value two two", "something else"}),
                    Arrays.asList(new String[]{"3", "name three three three", "value three three", "something else"}));
    private final static List<List<String>> dataOneColumnTest =
            Arrays.asList(
                    Arrays.asList(new String[]{"1"}),
                    Arrays.asList(new String[]{"2"}),
                    Arrays.asList(new String[]{"3"}));

    @Test
    public void tableFormatter_correctDataAndManyColumns_test() throws Exception {
        List<List<String>> data = dataTest;
        List<String> columnsNames = columnsNamesTest;
        TableFormatter tableFormatter = new TableFormatter(data, columnsNames);
        String excepted = "+----+------------------------+-------------------+----------------+\n" +
                "+ id + name                   + value column      + column else    +\n" +
                "+----+------------------------+-------------------+----------------+\n" +
                "+ 1  + name one               + value one         + something else +\n" +
                "+ 2  + name two two           + value two two     + something else +\n" +
                "+ 3  + name three three three + value three three + something else +\n" +
                "+----+------------------------+-------------------+----------------+\n";
        String actual = tableFormatter.getFormattedTable();
        assertEquals(excepted.replace("\n", System.lineSeparator()), actual);
    }

    @Test
    public void tableFormatter_correctDataAndOneColumn_test() throws Exception {
        List<List<String>> data = dataOneColumnTest;
        List<String> columnsNames = columnsNamesOneColumnTest;
        TableFormatter tableFormatter = new TableFormatter(data, columnsNames);
        String excepted = "+----+\n" +
                "+ id +\n" +
                "+----+\n" +
                "+ 1  +\n" +
                "+ 2  +\n" +
                "+ 3  +\n" +
                "+----+\n";
        String actual = tableFormatter.getFormattedTable();
        assertEquals(excepted.replace("\n", System.lineSeparator()), actual);
    }

    @Test(expected = IncorrectDataTableFormatterException.class)
    public void tableFormatter_differentNumberOfColumnsAndData_test() throws Exception {
        List<List<String>> data = dataTest;
        List<String> columnsNames = columnsNamesOneColumnTest;
        TableFormatter tableFormatter = new TableFormatter(data, columnsNames);
        tableFormatter.getFormattedTable();
    }

    @Test
    public void formatterTable_emptyData_test() throws Exception {
        List<List<String>> data = new ArrayList<>();
        List<String> columnsNames = columnsNamesTest;
        TableFormatter tableFormatter = new TableFormatter(data, columnsNames);
        tableFormatter.getFormattedTable();
    }

    @Test(expected = EmptyColumnsNamesTableFormatterException.class)
    public void formatterTable_emptyColumnsNames_test() throws Exception {
        List<List<String>> data = dataTest;
        List<String> columnsNames = new ArrayList<>();
        TableFormatter tableFormatter = new TableFormatter(data, columnsNames);
        tableFormatter.getFormattedTable();
    }

    @Test(expected = EmptyDataTableFormatterException.class)
    public void formatterTable_nullData_test() throws Exception {
        List<String> columnsNames = new ArrayList<>();
        TableFormatter tableFormatter = new TableFormatter(null, columnsNames);
        System.out.println(tableFormatter.getFormattedTable());
    }

    @Test(expected = EmptyColumnsNamesTableFormatterException.class)
    public void formatterTable_nullColumnsNames_test() throws Exception {
        List<List<String>> data = new ArrayList<>();
        TableFormatter tableFormatter = new TableFormatter(data, null);
        System.out.println(tableFormatter.getFormattedTable());
    }

    @Test(expected = EmptyDataTableFormatterException.class)
    public void formatterTable_nullDataAndColumnsNames_test() throws Exception {
        TableFormatter tableFormatter = new TableFormatter(null, null);
        tableFormatter.getFormattedTable();
    }
}