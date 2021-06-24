package org.core.util;

import org.apache.log4j.xml.DOMConfigurator;

public class Logger {

private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
private static Logger instance = null;

    public static synchronized Logger getLogger() {
        if (instance == null) {
            instance = new Logger();
            DOMConfigurator.configure("log4j.xml");
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

    private void endFormat (String testName){

        String formattedName = String.format("----------[**  Test case: '%1$s' **]-----------", testName);
        String delims = "";
        int nChars = formattedName.length();
        for (int i = 0; i < nChars; i++) {
            delims += "*";
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
    }

    public void startTest(String testName){
        startFormat(testName);
    }

    public void endTest(String testName){
        endFormat(testName);
    }

    public void warn(String message) {

        logger.warn(message);

    }

    public void error(String message) {

        logger.error(message);

    }

    public void fatal(String message) {

        logger.fatal(message);

    }

    public void debug(String message) {

        logger.debug(message);

    }
}
