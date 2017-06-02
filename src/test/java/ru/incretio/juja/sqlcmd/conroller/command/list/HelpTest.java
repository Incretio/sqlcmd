package ru.incretio.juja.sqlcmd.conroller.command.list;

import org.junit.Test;
import ru.incretio.juja.sqlcmd.exceptions.MissingConnectionException;

import java.sql.SQLException;
import java.util.Collections;

import static org.mockito.Mockito.verify;

public class HelpTest extends CommandTestBase {

    @Test
    public void help_correct_test() throws MissingConnectionException, SQLException, ClassNotFoundException {
        Help help = new Help(CHECKABLE_MOCK, NOTATIONABLE_MOCK);
        params = Collections.emptyList();

        help.perform(model, view, params);

        verify(view).write(HELP.replaceAll("\n", System.lineSeparator()));
    }
}