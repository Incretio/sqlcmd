package ru.incretio.juja.sqlcmd.conroller.command;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.*;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.model.query.QueryFactory;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ru.incretio.juja.sqlcmd.TestConstants.*;

public class CommandsPerformTest {
    private Model model;
    private View view;
    private static final Checkable CHECKABLE_MOCK = mock(Checkable.class);
    private static final Notationable NOTATIONABLE_MOCK = mock(Notationable.class);
    private List<String> params;

    @Before
    public void setup() {
        model = mock(Model.class);
        view = mock(View.class);
        params = null;
    }


    // ToDo: добавить в каждый тест проверку значения View

    @Test
    public void clearTablePerform_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Arrays.asList("tableName"));

        ClearTable clearTable = new ClearTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList("tableName");
        clearTable.perform(model, view, params);

        verify(model).takeTables();
        verify(model).executeClearTable("tableName");
    }

    @Test
    public void closePerform_correct_test() throws Exception {
        CloseConnection closeConnection = new CloseConnection(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();
        closeConnection.perform(model, view, params);

        verify(model).closeConnection();
    }

    @Test
    public void columnExists_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        when(model.takeTableColumns("tableNameTest"))
                .thenReturn(Collections.singletonList("columnNameTest"));

        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "columnNameTest");
        columnExists.perform(model, view, params);

        verify(model).takeTables();
        verify(model).takeTableColumns("tableNameTest");
    }

    @Test(expected = MissingColumnException.class)
    public void columnExists_missingColumn_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        when(model.takeTableColumns("tableNameTest"))
                .thenReturn(Collections.singletonList("column1"));

        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "columnNameTest");
        try {
            columnExists.perform(model, view, params);
        }catch (MissingColumnException e){
            verify(model).takeTables();
            verify(model).takeTableColumns("tableNameTest");
            throw e;
        }
    }

    @Test
    public void columnsList_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));

        ColumnsList columnsList = new ColumnsList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest");
        columnsList.perform(model, view, params);

        verify(model).takeTableColumns("tableNameTest");
    }

    @Test
    public void connect_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Connect connect = new Connect(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("serverName", "dbName", "userName", "password");
        connect.perform(model, view, params);

        verify(model).connect("serverName", "dbName", "userName", "password");
    }

    @Test(expected = ClassNotFoundException.class)
    public void connect_driverLoadingError_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Connect connect = new Connect(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        doThrow(ClassNotFoundException.class).when(model).connect(anyString(), anyString(), anyString(), anyString());
        params = Arrays.asList("serverName", "dbName", "userName", "password");
        connect.perform(model, view, params);
    }

    @Test
    public void createDB_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        CreateDB createDB = new CreateDB(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList("dbNameTest");
        createDB.perform(model, view, params);

        verify(model).executeCreateDB("dbNameTest");
    }

    @Test
    public void createTable_correct_test() throws Exception {
        CreateTable createTable = new CreateTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "column1", "column2", "column3");
        createTable.perform(model, view, params);

        List<String> columns = Arrays.asList("column1", "column2", "column3");
        verify(model).executeCreateTable("tableNameTest", columns);
    }

    @Test
    public void createTable_alreadyExists_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));

        CreateTable createTable = new CreateTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "column1", "column2", "column3");
        createTable.perform(model, view, params);

        List<String> columns = Arrays.asList("column1", "column2", "column3");
        verify(model, never()).executeCreateTable(anyString(), anyList());
    }

    @Test
    public void deleteRecords_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        when(model.takeTableColumns("tableNameTest")).thenReturn(Collections.singletonList("whereColumnName"));
        DeleteRecords deleteRecords = new DeleteRecords(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "whereColumnName", "whereColumnValue");
        deleteRecords.perform(model, view, params);

        verify(model).executeDeleteRecords("tableNameTest", "whereColumnName", "whereColumnValue");
    }

    @Test
    public void dropDB_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        DropDB dropDB = new DropDB(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("dbNameTest");
        dropDB.perform(model, view, params);

        verify(model).executeDropDB("dbNameTest");
    }

    @Test
    public void dropTable_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        DropTable dropTable = new DropTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest");
        dropTable.perform(model, view, params);

        verify(model).executeDropTable("tableNameTest");
    }

    @Test
    public void executeQuery_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        ExecuteQuery executeQuery = new ExecuteQuery(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("query text");
        executeQuery.perform(model, view, params);

        verify(model).execute("query text");
    }

    @Test(expected = NeedExitException.class)
    public void exit_isConnected_test() throws Exception {
        when(model.isConnected()).thenReturn(true);
        ExitApplication exitApplication = new ExitApplication(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();
        try {
            exitApplication.perform(model, view, params);
        } catch (NeedExitException e) {
            verify(model).isConnected();
            verify(model).closeConnection();
            throw e;
        }

        assert false;
    }

    @Test(expected = NeedExitException.class)
    public void exit_isNotConnected_test() throws Exception {
        ExitApplication exitApplication = new ExitApplication(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();
        try {
            exitApplication.perform(model, view, params);
        } catch (NeedExitException e) {
            verify(model).isConnected();
            verify(model, never()).closeConnection();
            throw e;
        }

        assert false;
    }

    @Test
    public void help_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Help help = new Help(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();
        help.perform(model, view, params);

        verify(view).write(anyString());
    }

    @Test
    public void insertRecords_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        when(model.takeTableColumns("tableNameTest")).thenReturn(Arrays.asList("column1", "column2"));
        InsertRecord insertRecord = new InsertRecord(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "column1", "value1", "column2", "value2");
        insertRecord.perform(model, view, params);

        List<String> columns = Arrays.asList("column1", "column2");
        List<String> values = Arrays.asList("value1", "value2");
        verify(model).executeInsertRecord("tableNameTest", columns, values);
    }

    // ToDo: Как тестить этот метод?
    @Test(expected = EmptyDataTableFormatterException.class)
    public void selectTable_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));

        SelectTable selectTable = new SelectTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest");
        selectTable.perform(model, view, params);

        verify(model).takeTables();
        verify(model).find(any(), "tableNameTest");
    }

    @Test
    public void tableExists_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));

        TableExists tableExists = new TableExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest");
        tableExists.perform(model, view, params);

        verify(model).takeTables();
    }

    @Test(expected = MissingTableException.class)
    public void tableExists_missingTable_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("table1"));

        TableExists tableExists = new TableExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest");
        try {
            tableExists.perform(model, view, params);
        } catch (MissingTableException e) {
            verify(model).takeTables();
            throw e;
        }
    }

    @Test
    public void tablesList_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Arrays.asList("table1", "table2"));
        TablesList tablesList = new TablesList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest");
        tablesList.perform(model, view, params);

        verify(model).takeTables();
    }

    @Test
    public void tablesList_dbEmpty_test() throws Exception {
        TablesList tablesList = new TablesList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest");
        tablesList.perform(model, view, params);

        verify(model).takeTables();
    }

    @Test
    public void updateRecords_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("tableNameTest"));
        when(model.takeTableColumns("tableNameTest")).thenReturn(Arrays.asList("column1", "column2"));
        UpdateRecords updateRecords = new UpdateRecords(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList("tableNameTest", "column1", "value1", "column2", "value2");
        updateRecords.perform(model, view, params);

        verify(model).executeUpdateRecords("tableNameTest", "column1", "value1", "column2", "value2");
    }


}