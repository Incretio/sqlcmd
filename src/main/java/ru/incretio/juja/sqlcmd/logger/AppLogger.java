package ru.incretio.juja.sqlcmd.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private final static String LOG_FILE_NAME = "AppLog.log";
    private final static Logger LOGGER = Logger.getLogger("AppLog");

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME);
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void warning(String msg) {
        LOGGER.warning(msg);
    }

    public static void info(String msg){
        LOGGER.info(msg);
    }
}
