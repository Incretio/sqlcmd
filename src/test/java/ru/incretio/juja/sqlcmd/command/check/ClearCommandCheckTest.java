package ru.incretio.juja.sqlcmd.command.check;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.command.CommandCheckFactory;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static ru.incretio.juja.sqlcmd.command.CommandCheckFactory.*;

public class ClearCommandCheckTest {
    private final String PARAM_VALUE = "value";
    private List<String> params;

    @Test
    public void testClearCommandCheck() {
        Checkable check = new CommandCheckFactory().getClearCommandCheck();
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
        Checkable check = new CommandCheckFactory().getCloseCommandCheck();
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testConnectCommandCheck() {
        Checkable check = new CommandCheckFactory().getConnectCommandCheck();
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
        Checkable check = new CommandCheckFactory().getCreateCommandCheck();
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
        Checkable check = new CommandCheckFactory().getCreateDBCommandCheck();
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
        Checkable check = new CommandCheckFactory().getDeleteCommandCheck();
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
        Checkable check = new CommandCheckFactory().getDropCommandCheck();
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
        Checkable check = new CommandCheckFactory().getDropDBCommandCheck();
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
        Checkable check = new CommandCheckFactory().getExecuteCommandCheck();
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
        Checkable check = new CommandCheckFactory().getExitCommandCheck();
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
        Checkable check = new CommandCheckFactory().getFindCommandCheck();
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
        Checkable check = new CommandCheckFactory().getHelpCommandCheck();
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
        Checkable check = new CommandCheckFactory().getInsertCommandCheck();
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
        Checkable check = new CommandCheckFactory().getTablesCommandCheck();
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
        Checkable check = new CommandCheckFactory().getUpdateCommandCheck();
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
        Checkable check = new CommandCheckFactory().getColumnExistCommandCheck();
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