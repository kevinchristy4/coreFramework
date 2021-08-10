package org.core.util;

public final class ReportLogger {

    private ReportLogger(){}
    public static void step(String message){ ReportListener.getReporter().pass(message); }
    public static void info(String message){ ReportListener.getReporter().info(message); }
    public static void fail(String message){
        ReportListener.getReporter().fail(message);
    }
}
