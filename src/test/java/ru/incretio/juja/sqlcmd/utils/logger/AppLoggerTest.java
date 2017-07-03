package ru.incretio.juja.sqlcmd.utils.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

import static org.junit.Assert.*;

public class AppLoggerTest {
    private final static String LOG_FILE_NAME = "logFileTest.txt";
    private String currentFileName;
    private AppLogger appLogger;

    @Before
    public void beforeTest() {
        currentFileName = LOG_FILE_NAME + new Random().nextInt();
        appLogger = new AppLogger(currentFileName);
    }

    @After
    public void afterTest() {
        appLogger.delete();
    }

    @Test
    public void createLoggerFile_correct_test() throws Exception {
        assertTrue(new File(currentFileName).exists());
    }

    @Test
    public void writeSomethingInFile_correct_test() throws Exception {
        appLogger.warning(new Throwable("testException"));
        try (FileInputStream fileInputStream = new FileInputStream(currentFileName)) {
            assertTrue(fileInputStream.available() > 0);
        }
    }

}