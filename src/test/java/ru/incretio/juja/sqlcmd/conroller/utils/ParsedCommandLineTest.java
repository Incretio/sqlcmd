package ru.incretio.juja.sqlcmd.conroller.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.incretio.juja.sqlcmd.exceptions.EmptyCommandException;
import static org.junit.Assert.*;

public class ParsedCommandLineTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test (expected = EmptyCommandException.class)
    public void testNullStringParsedCommandLine() throws Exception {
        new ParsedCommandLine(null);
    }

    @Test (expected = EmptyCommandException.class)
    public void testEmptyStringParsedCommandLine() throws Exception {
        new ParsedCommandLine("");
    }


    @Test (expected = EmptyCommandException.class)
    public void testSpacesStringParsedCommandLine() throws Exception {
        new ParsedCommandLine("    ");
    }

    @Test
    public void testNoParamsParsedCommandLine() throws Exception {
        ParsedCommandLine parsedCommandLine = new ParsedCommandLine("command");
        assertEquals(parsedCommandLine.getCommandName(), "command");
        assertEquals(parsedCommandLine.getParamsList().size(), 0);
    }


    @Test
    public void testOneParamParsedCommandLine() throws Exception {
        ParsedCommandLine parsedCommandLine = new ParsedCommandLine("   command    param1   ");
        assertEquals(parsedCommandLine.getCommandName(), "command");
        assertEquals(parsedCommandLine.getParamsList().size(), 1);
        assertEquals(parsedCommandLine.getParamsList().get(0), "param1");
    }

    @Test
    public void testThreeParamsParsedCommandLine() throws Exception {
        ParsedCommandLine parsedCommandLine = new ParsedCommandLine("   command    param1   param2 param3");
        assertEquals(parsedCommandLine.getCommandName(), "command");
        assertEquals(parsedCommandLine.getParamsList().size(), 3);
        assertEquals(parsedCommandLine.getParamsList().get(0), "param1");
        assertEquals(parsedCommandLine.getParamsList().get(1), "param2");
        assertEquals(parsedCommandLine.getParamsList().get(2), "param3");
    }

    @Test
    public void testQueryParsedCommandLine() throws Exception {
        ParsedCommandLine parsedCommandLine = new ParsedCommandLine("   command   \" select * from table where  param1 = 0 and param2 = 'value'\"");
        assertEquals(parsedCommandLine.getCommandName(), "command");
        assertEquals(parsedCommandLine.getParamsList().size(), 1);
        assertEquals(parsedCommandLine.getParamsList().get(0), " select * from table where  param1 = 0 and param2 = 'value'");
    }

    @Test
    public void testEleQueryParsedCommandLine() throws Exception {
        ParsedCommandLine parsedCommandLine = new ParsedCommandLine("   command   \" select * from table where  param1 = 0 and param2 = 'value'\" param2");
        assertEquals(parsedCommandLine.getCommandName(), "command");
        assertEquals(parsedCommandLine.getParamsList().size(), 2);
        assertEquals(parsedCommandLine.getParamsList().get(0), " select * from table where  param1 = 0 and param2 = 'value'");
        assertEquals(parsedCommandLine.getParamsList().get(1), "param2");
    }

}