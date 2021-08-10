package org.core.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static Logger logger = new Logger();
    int counter = 1;
    int retryLimit = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (counter<retryLimit) {
            counter++;
            System.out.println("Retry test case: " + result.getMethod());
            logger.info("Retry test case: " + result.getMethod());
            return true;
        }
        return false;
    }
}
