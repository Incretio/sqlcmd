package ru.incretio.juja.sqlcmd.utils.logger;

import org.junit.Test;

import java.io.File;

public class AppLoggerTest {

    @Test
    public void createLoggerFile_correct_test() throws Exception {
        new File(AppLogger.LOG_FILE_NAME).delete();
        AppLogger.warning(new Throwable("testException"));
        assert new File(AppLogger.LOG_FILE_NAME).exists();
    }

}