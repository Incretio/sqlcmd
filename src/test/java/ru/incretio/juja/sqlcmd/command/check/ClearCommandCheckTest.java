package ru.incretio.juja.sqlcmd.command.check;

import org.junit.Before;
import org.junit.Test;
import ru.incretio.juja.sqlcmd.command.CommandCheckFactory;
import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.incretio.juja.sqlcmd.command.CommandCheckFactory.*;

/**
 * Created by incre on 07.03.2017.
 */
public class ClearCommandCheckTest {
    private final String PARAM_VALUE = "value";
    private List<String> params;

    @Test
    public void testClearCommandCheck() {
        Checkable check = getClearCommandCheck();
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
        Checkable check = getCloseCommandCheck();
        assertFalse(check.checkParams(params));
        params = new ArrayList<>();
        assertTrue(check.checkParams(params));
        params.add(PARAM_VALUE);
        assertFalse(check.checkParams(params));
    }

    @Test
    public void testConnectCommandCheck() {
        Checkable check = getConnectCommandCheck();
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
        Checkable check = getCreateCommandCheck();
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
        Checkable check = getCreateDBCommandCheck();
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
        Checkable check = getDeleteCommandCheck();
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
        Checkable check = getDropCommandCheck();
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
        Checkable check = getDropDBCommandCheck();
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
        Checkable check = getExecuteCommandCheck();
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
        Checkable check = getExitCommandCheck();
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
        Checkable check = getFindCommandCheck();
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
        Checkable check = getHelpCommandCheck();
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
        Checkable check = getInsertCommandCheck();
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
        Checkable check = getTablesCommandCheck();
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
        Checkable check = getUpdateCommandCheck();
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