package org.core.util;

import org.apache.logging.log4j.LogManager;

public class Logger {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);
    private static Logger instance = null;
    public static synchronized Logger getLogger() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    private void startFormat (String testName){
        String formattedName = String.format("----------[**  Test case: '%1$s' **]-----------", testName);
        String delims = "";
        int nChars = formattedName.length();
        for (int i = 0; i < nChars; i++) {
            delims += "-";
        }
        logger.info(delims);
        logger.info(formattedName);
        logger.info(delims);
    }

    private void stepFormat (Integer step,String message){
        logger.info(String.format("------** %1$s - %2$s **-------",step, message));
    }
    public void step (Integer step, String message){
        stepFormat(step,message);
        ReportLogger.step(message);
    }
    public void startTest(String testName){
        startFormat(testName);
        ReportLogger.info(testName);

    }

    public void info(String message){
        logger.info(message);
        ReportLogger.info(message);
    }

    private void endFormat (String testName){
        String formattedName = String.format("----------[** End of Test case: '%1$s' **]-----------", testName);
        String delims = "";
        int nChars = formattedName.length();
        for (int i = 0; i < nChars; i++) {
            delims += "*";
        }
        logger.info(delims);
        logger.info(formattedName);
        logger.info(delims);
    }

    public void endTest(String testName){
        endFormat(testName);
        ReportLogger.info(testName);
    }

    public void warn(String message) {

        logger.warn(message);
        ReportLogger.info(message);

    }

    public void error(String message) {

        logger.error(message);
        ReportLogger.fail(message);

    }

    public void fatal(String message) {

        logger.fatal(message);
        ReportLogger.fail(message);
    }

    public void debug(String message) {

        logger.debug(message);

    }
}
