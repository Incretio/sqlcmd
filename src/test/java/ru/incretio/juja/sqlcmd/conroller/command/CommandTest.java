package ru.incretio.juja.sqlcmd.conroller.command;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.CommandNotFoundException;

import static org.junit.Assert.*;

public class CommandTest {

    @Test
    public void takeByName_correct_test() throws Exception {
        Command expected = Command.takeByName(Command.CLEAR.toString());
        Command actual = Command.CLEAR;
        assertEquals(expected, actual);
    }

    @Test(expected = CommandNotFoundException.class)
    public void takeByName_unsupportedCommand_test() throws Exception {
        Command.takeByName("unsupportedCommand");
    }

}