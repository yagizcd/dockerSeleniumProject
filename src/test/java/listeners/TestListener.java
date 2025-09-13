// src/test/java/listeners/TestListener.java

package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import keywords.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Başarılı!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, "Test Başarısız!");
        ExtentManager.getTest().fail(result.getThrowable());

        try {
            WebDriver driver = DriverFactory.getDriver();

            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            ExtentManager.getTest().fail("Hata anının ekran görüntüsü:",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

        } catch (Exception e) {
            e.printStackTrace();
            ExtentManager.getTest().fail("Ekran görüntüsü alınırken bir hata oluştu: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Test Atlandı!");
    }
}