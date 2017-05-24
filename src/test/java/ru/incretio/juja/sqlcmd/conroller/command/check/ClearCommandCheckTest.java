package ru.incretio.juja.sqlcmd.conroller.command.check;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static ru.incretio.juja.sqlcmd.conroller.command.CommandCheckFactory.*;

public class ClearCommandCheckTest {
    private final String PARAM_VALUE = "value";
    private List<String> params;

    @Test
    public void testClearCommandCheck() {
        Checkable check = makeClearCommandCheck();
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
        Checkable check = makeCloseCommandCheck();
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testConnectCommandCheck() {
        Checkable check = makeConnectCheck();
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
        Checkable check = makeCreateCommandCheck();
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
        Checkable check = makeCreateDBCommandCheck();
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
        Checkable check = makeDeleteCommandCheck();
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
        Checkable check = makeDropCommandCheck();
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
        Checkable check = makeDropDBCommandCheck();
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
        Checkable check = makeExecuteCommandCheck();
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
        Checkable check = makeExitCommandCheck();
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
        Checkable check = makeFindCommandCheck();
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
        Checkable check = makeHelpCommandCheck();
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
        Checkable check = makeInsertCommandCheck();
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
        Checkable check = makeTablesCommandCheck();
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
        Checkable check = makeUpdateCommandCheck();
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

    @Test
    public void testColumnExistCommandCheck() {
        Checkable check = makeColumnExistCommandCheck();
        params = null;
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

}