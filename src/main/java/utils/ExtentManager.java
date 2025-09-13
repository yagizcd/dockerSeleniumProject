
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentManager() {}

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/AutomationReport.html");
            sparkReporter.config().setReportName("Test Otomasyon Raporu");
            sparkReporter.config().setDocumentTitle("Test Sonuçları");
            sparkReporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Tester", "Yağız");
        }
        return extent;
    }

    public static void startTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}