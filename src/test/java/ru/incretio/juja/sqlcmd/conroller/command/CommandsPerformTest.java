package ru.incretio.juja.sqlcmd.conroller.command;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.ClearTable;
import ru.incretio.juja.sqlcmd.conroller.command.list.CloseConnection;
import ru.incretio.juja.sqlcmd.conroller.command.list.ColumnExists;
import ru.incretio.juja.sqlcmd.conroller.command.list.Connect;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.exceptions.MissingColumnException;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;
import ru.incretio.juja.sqlcmd.exceptions.MissingTableException;
import ru.incretio.juja.sqlcmd.model.query.QueryFactory;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.incretio.juja.sqlcmd.TestConstants.*;

public class CommandsPerformTest {
    private Model model;
    private View view;
    private static final Checkable CHECKABLE_MOCK = Mockito.mock(Checkable.class);
    private static final Notationable NOTATIONABLE_MOCK = Mockito.mock(Notationable.class);
    private List<String> params;

    @Before
    public void setup() {
        model = Mockito.mock(Model.class);
        view = Mockito.mock(View.class);
    }

    @Test
    public void clearTablePerform_correct_test() throws Exception {
        Mockito.when(model.takeTables()).thenReturn(Arrays.asList("tableName"));

        ClearTable clearTable = new ClearTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList("tableName");
        clearTable.perform(model, view, params);

        Mockito.verify(model).takeTables();
        Mockito.verify(model).executeClearTable("tableName");
    }

    @Test(expected = MissingTableException.class)
    public void clearTablePerform_missingTable_test() throws Exception {
        Mockito.when(model.takeTables()).thenReturn(Arrays.asList("table1"));

        ClearTable clearTable = new ClearTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList("tableName");
        clearTable.perform(model, view, params);
    }

    @Test
    public void closePerform_test() throws Exception {
        CloseConnection closeConnection = new CloseConnection(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();
        closeConnection.perform(model, view, params);

        Mockito.verify(model).closeConnection();
    }

    @Test
    public void columnExists_correct_test() throws Exception {
        Mockito.when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        Mockito.when(model.takeTableColumns("tableNameTest"))
                .thenReturn(Collections.singletonList("columnNameTest"));

        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "columnNameTest");
        columnExists.perform(model, view, params);

        Mockito.verify(model).takeTables();
        Mockito.verify(model).takeTableColumns("tableNameTest");
    }

    @Test(expected = MissingTableException.class)
    public void columnExists_missingTable_test() throws Exception {
        Mockito.when(model.takeTables()).thenReturn(Collections.singletonList("table1"));
        Mockito.when(model.takeTableColumns("tableNameTest"))
                .thenReturn(Collections.singletonList("columnNameTest"));

        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "columnNameTest");
        columnExists.perform(model, view, params);
    }

    @Test(expected = MissingColumnException.class)
    public void columnExists_missingColumn_test() throws Exception {
        Mockito.when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        Mockito.when(model.takeTableColumns("tableNameTest"))
                .thenReturn(Collections.singletonList("column1"));

        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "columnNameTest");
        columnExists.perform(model, view, params);
    }

}