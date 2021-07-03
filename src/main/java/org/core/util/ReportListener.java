package org.core.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportListener {

    ExtentReports reports = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("");

    ExtentTest test = reports.createTest("test");

    public void test(){
        reports.attachReporter(spark);
    }
}
