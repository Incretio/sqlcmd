package ru.incretio.juja.sqlcmd.command.check;

import org.junit.Before;
import org.junit.Test;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by incre on 07.03.2017.
 */
public class ClearCommandCheckTest {
    private final String PARAM_VALUE = "value";
    private List<String> params;

    @Test
    public void testClearCommandCheck() {
        Checkable check = new ClearCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testCloseCommandCheck() {
        Checkable check = new CloseCommandCheck();
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testConnectCommandCheck() {
        Checkable check = new ConnectCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testCreateCommandCheck() {
        Checkable check = new CreateCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
    }

    @Test
    public void testCreateDBCommandCheck() {
        Checkable check = new CreateDBCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testDeleteCommandCheck() {
        Checkable check = new DeleteCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testDropCommandCheck() {
        Checkable check = new DropCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testDropDBCommandCheck() {
        Checkable check = new DropDBCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testExecuteCommandCheck() {
        Checkable check = new ExecuteCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testExitCommandCheck() {
        Checkable check = new ExitCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testFindCommandCheck() {
        Checkable check = new FindCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testHelpCommandCheck() {
        Checkable check = new HelpCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testInsertCommandCheck() {
        Checkable check = new InsertCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
    }

    @Test
    public void testTablesCommandCheck() {
        Checkable check = new TablesCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testUpdateCommandCheck() {
        Checkable check = new UpdateCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));

    }


}