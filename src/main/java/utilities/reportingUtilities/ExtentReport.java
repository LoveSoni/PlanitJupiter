package utilities.reportingUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.ProjectPath;

import java.io.IOException;
import java.util.Objects;

public class ExtentReport {
    private static ExtentReports extentReports;

    public static void init() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(ProjectPath.EXTENT_REPORT_PATH);
            try {
                extentSparkReporter.loadXMLConfig(ProjectPath.EXTENT_CONFIG_XML_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            extentReports.attachReporter(extentSparkReporter);
        }
    }

    public static void flush() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
    }

    public static void createTest(String message) {
        ExtentTest extentTest = extentReports.createTest(message);
        ExtentManager.setExtentTest(extentTest);
    }

}
