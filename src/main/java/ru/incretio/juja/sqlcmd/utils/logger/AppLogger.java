package ru.incretio.juja.sqlcmd.utils.logger;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component
public class AppLogger {
    public String logFileName = "AppLog.log";
    private final Logger logger = Logger.getLogger("AppLog");
    private FileHandler fileHandler;

    public AppLogger() {
        // default log file name
        initLogger();
    }

    public AppLogger(String logFileName) {
        this.logFileName = logFileName;
        initLogger();
    }

    public void delete() {
        fileHandler.close();
        new File(logFileName).delete();
    }

    private void initLogger() {
        try {
            FileHandler fileHandler = new FileHandler(logFileName);
            try {
                logger.setUseParentHandlers(false);
                logger.addHandler(fileHandler);
                fileHandler.setFormatter(new SimpleFormatter());
                this.fileHandler = fileHandler;
            } catch (Exception e) {
                fileHandler.close();
            }
        } catch (IOException e) {
            // do nothing
        }
    }

    public void warning(String msg) {
        logger.warning(msg);
    }

    public void warning(Throwable throwable) {
        logger.warning(
                throwable.getMessage()
                        .concat(": ")
                        .concat(Arrays.toString(throwable.getStackTrace())));
    }

}
