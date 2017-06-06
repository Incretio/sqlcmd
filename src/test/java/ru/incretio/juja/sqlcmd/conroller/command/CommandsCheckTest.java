package ru.incretio.juja.sqlcmd.conroller.command;

import org.junit.Test;
import org.junit.runners.Parameterized;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.incretio.juja.sqlcmd.conroller.command.CommandCheckFactory.*;

public class CommandsCheckTest {
    private final String PARAM_VALUE = "value";
    private final List<String> NULL_PARAM = null;
    private final List<String> EMPTY_PARAM = new ArrayList<>();
    private final List<String> ONE_PARAM = Collections.singletonList(PARAM_VALUE);
    private final List<String> TWO_PARAM = Arrays.asList(PARAM_VALUE, PARAM_VALUE);
    private final List<String> THREE_PARAM = Arrays.asList(PARAM_VALUE, PARAM_VALUE, PARAM_VALUE);
    private final List<String> FOUR_PARAM = Arrays.asList(PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE);
    private final List<String> FIVE_PARAM =
            Arrays.asList(PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE);
    private final List<String> SIX_PARAM =
            Arrays.asList(PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE);
    private final List<String> SEVEN_PARAM =
            Arrays.asList(PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE);
    private final List<String> EIGHT_PARAM =
            Arrays.asList(PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE);
    private final List<String> NINE_PARAM =
            Arrays.asList(PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE, PARAM_VALUE);

    @Test
    public void clearCommandCheck_allVariants_test() {
        Checkable check = makeClearCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void closeCommandCheck_allVariants_test() {
        Checkable check = makeCloseCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(true, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
    }

    @Test
    public void connectCommandCheck_allVariants_test() {
        Checkable check = makeConnectCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
        assertEquals(false, check.checkParams(THREE_PARAM));
        assertEquals(true, check.checkParams(FOUR_PARAM));
        assertEquals(false, check.checkParams(FIVE_PARAM));
    }

    @Test
    public void createCommandCheck_allVariants_test() {
        Checkable check = makeCreateCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(true, check.checkParams(TWO_PARAM));
        assertEquals(true, check.checkParams(THREE_PARAM));
        assertEquals(true, check.checkParams(FOUR_PARAM));
        assertEquals(true, check.checkParams(FIVE_PARAM));
    }

    @Test
    public void createDBCommandCheck_allVariants_test() {
        Checkable check = makeCreateDBCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void deleteCommandCheck_allVariants_test() {
        Checkable check = makeDeleteCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
        assertEquals(true, check.checkParams(THREE_PARAM));
        assertEquals(false, check.checkParams(FOUR_PARAM));
        assertEquals(false, check.checkParams(FIVE_PARAM));
    }

    @Test
    public void dropCommandCheck_allVariants_test() {
        Checkable check = makeDropCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void dropDBCommandCheck_allVariants_test() {
        Checkable check = makeDropDBCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void executeCommandCheck_allVariants_test() {
        Checkable check = makeExecuteCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void exitCommandCheck_allVariants_test() {
        Checkable check = makeExitCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(true, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void findCommandCheck_allVariants_test() {
        Checkable check = makeFindCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void helpCommandCheck_allVariants_test() {
        Checkable check = makeHelpCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(true, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void insertCommandCheck_allVariants_test() {
        Checkable check = makeInsertCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
        assertEquals(true, check.checkParams(THREE_PARAM));
        assertEquals(false, check.checkParams(FOUR_PARAM));
        assertEquals(true, check.checkParams(FIVE_PARAM));
        assertEquals(false, check.checkParams(SIX_PARAM));
        assertEquals(true, check.checkParams(SEVEN_PARAM));
        assertEquals(false, check.checkParams(EIGHT_PARAM));
        assertEquals(true, check.checkParams(NINE_PARAM));
    }

    @Test
    public void tablesCommandCheck_allVariants_test() {
        Checkable check = makeTablesCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(true, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void updateCommandCheck_allVariants_test() {
        Checkable check = makeUpdateCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
        assertEquals(false, check.checkParams(THREE_PARAM));
        assertEquals(false, check.checkParams(FOUR_PARAM));
        assertEquals(true, check.checkParams(FIVE_PARAM));
        assertEquals(false, check.checkParams(SIX_PARAM));
    }

    @Test
    public void columnExistCommandCheck_allVariants_test() {
        Checkable check = makeColumnExistCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(false, check.checkParams(ONE_PARAM));
        assertEquals(true, check.checkParams(TWO_PARAM));
        assertEquals(false, check.checkParams(THREE_PARAM));
    }

    @Test
    public void tableExistCommandCheck_allVariants_test() {
        Checkable check = makeTableExistCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

    @Test
    public void columnsListCommandCheck_allVariants_test() {
        Checkable check = makeColumnsCommandCheck();
        assertEquals(false, check.checkParams(NULL_PARAM));
        assertEquals(false, check.checkParams(EMPTY_PARAM));
        assertEquals(true, check.checkParams(ONE_PARAM));
        assertEquals(false, check.checkParams(TWO_PARAM));
    }

}