package ru.incretio.juja.sqlcmd.utils.logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    public final static String LOG_FILE_NAME = "AppLog.log";
    private final Logger logger = Logger.getLogger("AppLog");
    private FileHandler fileHandler;

    public AppLogger() {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME);
            try {
                logger.setUseParentHandlers(false);
                logger.addHandler(fileHandler);
                fileHandler.setFormatter(new SimpleFormatter());
                this.fileHandler = fileHandler;
            } catch (Exception e){
                fileHandler.close();
            }
        } catch (IOException e) {
            // do nothing
        }
    }

    public static void warning(String msg) {
        AppLogger appLogger = new AppLogger();
        try {
            appLogger.logger.warning(msg);
        } finally {
            appLogger.fileHandler.close();
        }
    }

    public static void warning(Throwable throwable) {
        warning(
                throwable.getMessage()
                        .concat(": ")
                        .concat(Arrays.toString(throwable.getStackTrace())));
    }

}
