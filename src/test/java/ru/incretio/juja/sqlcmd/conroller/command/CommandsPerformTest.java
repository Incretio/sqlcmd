package ru.incretio.juja.sqlcmd.conroller.command;

import org.junit.*;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.*;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.*;

public class CommandsPerformTest {
    private Model model;
    private View view;
    private static final Checkable CHECKABLE_MOCK = mock(Checkable.class);
    private static final Notationable NOTATIONABLE_MOCK = mock(Notationable.class);
    private List<String> params;

    private final static String TABLE_NAME = "testTableName";
    private final static String COLUMN_ONE_NAME = "testColumnOne";
    private final static String COLUMN_TWO_NAME = "testColumnTwo";
    private final static String COLUMN_THREE_NAME = "testColumnThree";
    private final static List<String> COLUMNS_LIST = Arrays.asList(COLUMN_ONE_NAME, COLUMN_TWO_NAME, COLUMN_THREE_NAME);
    private final static List<String> TABLE_AND_COLUMNS_LIST =
            Arrays.asList(TABLE_NAME, COLUMN_ONE_NAME, COLUMN_TWO_NAME, COLUMN_THREE_NAME);
    private final static String SERVER_NAME = "serverName";
    private final static String DB_NAME = "dbName";
    private final static String USERNAME = "userName";
    private final static String PASSWORD = "password";

    @Before
    public void setup() {
        model = mock(Model.class);
        view = mock(View.class);
        params = null;
    }


    @Test
    public void clearTablePerform_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Arrays.asList(TABLE_NAME));
        ClearTable clearTable = new ClearTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList(TABLE_NAME);

        clearTable.perform(model, view, params);
        
        verify(model).takeTables();
        verify(model).executeClearTable(TABLE_NAME);
        verify(view).write(String.format(takeCaption("tableCleared"), TABLE_NAME));
    }


    @Test
    public void closePerform_correct_test() throws Exception {
        CloseConnection closeConnection = new CloseConnection(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();
        
        closeConnection.perform(model, view, params);

        verify(model).closeConnection();
        verify(view).write(takeCaption("connectionClosed"));
    }

    @Test
    public void columnExists_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME))
                .thenReturn(Collections.singletonList(COLUMN_ONE_NAME));
        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, COLUMN_ONE_NAME);

        columnExists.perform(model, view, params);

        verify(model).takeTables();
        verify(model).takeTableColumns(TABLE_NAME);
    }

    @Test(expected = MissingColumnException.class)
    public void columnExists_missingColumn_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME))
                .thenReturn(Collections.singletonList("column1"));
        ColumnExists columnExists = new ColumnExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, COLUMN_ONE_NAME);

        try {
            columnExists.perform(model, view, params);
        } catch (Exception e) {
            verify(model).takeTables();
            verify(model).takeTableColumns(TABLE_NAME);
            throw e;
        }
    }

    @Test
    public void columnsList_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(COLUMNS_LIST);
        ColumnsList columnsList = new ColumnsList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);

        columnsList.perform(model, view, params);

        verify(model).takeTables();
        verify(model).takeTableColumns(TABLE_NAME);
        verify(view).write(COLUMNS_LIST);
    }

    @Test
    public void connect_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Connect connect = new Connect(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);

        connect.perform(model, view, params);

        verify(model).connect(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);
        verify(view).write(String.format(takeCaption("connectionSuccessPattern"), DB_NAME));
    }

    @Test(expected = ClassNotFoundException.class)
    public void connect_driverLoadingError_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Connect connect = new Connect(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        doThrow(ClassNotFoundException.class).when(model).connect(anyString(), anyString(), anyString(), anyString());
        params = Arrays.asList(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);

        try {
            connect.perform(model, view, params);
        } catch (Exception e){
            verify(model).connect(SERVER_NAME, DB_NAME, USERNAME, PASSWORD);
            throw e;
        }
    }

    @Test
    public void createDB_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        CreateDB createDB = new CreateDB(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.singletonList(DB_NAME);

        createDB.perform(model, view, params);

        verify(model).executeCreateDB(DB_NAME);
        verify(view).write(String.format(takeCaption("dbCreatedPattern"), DB_NAME));
    }

    @Test
    public void createTable_correct_test() throws Exception {
        CreateTable createTable = new CreateTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);

        createTable.perform(model, view, TABLE_AND_COLUMNS_LIST);

        verify(model).takeTables();
        verify(model).executeCreateTable(TABLE_NAME, COLUMNS_LIST);
        verify(view).write(String.format(takeCaption("tableAddedPattern"), TABLE_NAME));
    }

    @Test
    public void createTable_alreadyExists_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        CreateTable createTable = new CreateTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);

        createTable.perform(model, view, TABLE_AND_COLUMNS_LIST);

        verify(model).takeTables();
        verify(model, never()).executeCreateTable(anyString(), anyList());
        verify(view).write(String.format(takeCaption("tableAlreadyExistsPattern"), TABLE_NAME));
    }

    // ToDo: Продолжить от сюда. Добавляем проверку вызова view.write();
    @Test
    public void deleteRecords_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(Collections.singletonList("whereColumnName"));
        DeleteRecords deleteRecords = new DeleteRecords(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, "whereColumnName", "whereColumnValue");
        deleteRecords.perform(model, view, params);

        verify(model).executeDeleteRecords(TABLE_NAME, "whereColumnName", "whereColumnValue");
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
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        DropTable dropTable = new DropTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);
        dropTable.perform(model, view, params);

        verify(model).executeDropTable(TABLE_NAME);
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
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(Arrays.asList("column1", "column2"));
        InsertRecord insertRecord = new InsertRecord(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, "column1", "value1", "column2", "value2");
        insertRecord.perform(model, view, params);

        List<String> columns = Arrays.asList("column1", "column2");
        List<String> values = Arrays.asList("value1", "value2");
        verify(model).executeInsertRecord(TABLE_NAME, columns, values);
    }

    // ToDo: Как тестить этот метод?
    @Test(expected = EmptyDataTableFormatterException.class)
    public void selectTable_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));

        SelectTable selectTable = new SelectTable(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);
        selectTable.perform(model, view, params);

        verify(model).takeTables();
        verify(model).find(any(), TABLE_NAME);
    }

    @Test
    public void tableExists_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));

        TableExists tableExists = new TableExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);
        tableExists.perform(model, view, params);

        verify(model).takeTables();
    }

    @Test(expected = MissingTableException.class)
    public void tableExists_missingTable_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList("table1"));

        TableExists tableExists = new TableExists(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);
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
        params = Arrays.asList(TABLE_NAME);
        tablesList.perform(model, view, params);

        verify(model).takeTables();
    }

    @Test
    public void tablesList_dbEmpty_test() throws Exception {
        TablesList tablesList = new TablesList(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME);
        tablesList.perform(model, view, params);

        verify(model).takeTables();
    }

    @Test
    public void updateRecords_correct_test() throws Exception {
        when(model.takeTables()).thenReturn(Collections.singletonList(TABLE_NAME));
        when(model.takeTableColumns(TABLE_NAME)).thenReturn(Arrays.asList("column1", "column2"));
        UpdateRecords updateRecords = new UpdateRecords(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Arrays.asList(TABLE_NAME, "column1", "value1", "column2", "value2");
        updateRecords.perform(model, view, params);

        verify(model).executeUpdateRecords(TABLE_NAME, "column1", "value1", "column2", "value2");
    }


}