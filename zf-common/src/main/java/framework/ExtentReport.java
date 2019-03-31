package framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * This class help to create method for generating report
 * 
 */
public class ExtentReport {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static  ExtentTest reporter;

	static CaptureScreenshot captureScreenshot=new CaptureScreenshot();

	/**
	 * This method is used for creating a new folder named Reports for storing the report
	 * 
	 * @return method will return the absolute path
	 */
	private static String loadReportFolder() {

		String path=null;
		File reportsfolder = new File(EnvironmentManager.getReportPath());
		if (reportsfolder.exists()) {
			path=reportsfolder.getAbsolutePath()+EnvironmentManager.getCommonSlash();
		} else {
			reportsfolder.mkdir();
			path=reportsfolder.getAbsolutePath()+EnvironmentManager.getCommonSlash();
		}
		return path;
	}


	/**
	 * This Method is used for generating report with below configuration
	 * 
	 */
	public static void configureReport(){

		try {
			htmlReporter=new ExtentHtmlReporter(loadReportFolder()+getTimeStamp()+EnvironmentManager.getDotHtml());
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle(EnvironmentManager.getReportTitle());
			htmlReporter.config().setReportName(EnvironmentManager.getReportName());
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
			TestLogger.fileInfo(loadReportFolder()+getTimeStamp()+EnvironmentManager.getDotHtml());
		}
		catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
	}



	/**
	 * This method is the starting point of the test.
	 * 
	 */
	public void startTest(String methodName) {

		try {
			reporter=extent.createTest(methodName);
			TestLogger.beforORAfterMetod(methodName);
		}
		catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
	}


	/**
	 * Method will be update the test result in the report
	 * 
	 */
	public void getResult(ITestResult result, String methodName) {

		try {
			if(result.getStatus() == ITestResult.FAILURE) {
				reporter.log(Status.FAIL, " Test case Failed due to below issues:");
				reporter.error(result.getThrowable());


				reporter.addScreenCaptureFromPath(captureScreenshot.captureScreenshot(methodName));

			}else if(result.getStatus() == ITestResult.SUCCESS){
				reporter.log(Status.PASS, methodName+" PASSED");
			}
			else {
				reporter.log(Status.SKIP, "Test Skipped");
				reporter.skip(result.getThrowable());
			}
		} catch (IOException e) {
			TestLogger.errorMessage(e.getMessage());
		}catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
	}


	/**
	 * Method will be used to write all the data in the report
	 * 
	 */
	public static void reportClose(){
		try {
			extent.flush();
		}catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}

	}

	/**
	 * Method will be used to write pass statement
	 * 
	 */
	public static void compareText(String expected, String actual){
		try {
			if(expected.trim().equals(actual.trim())) {
				reporter.pass("Expected value is : "+expected +"      Actual value is : "+actual);
				reporter.addScreenCaptureFromPath(captureScreenshot.captureScreenshot(expected));
			}else {
				Assert.fail("Expected value is : "+expected +"      Actual value is : "+actual);

				reporter.addScreenCaptureFromPath(captureScreenshot.captureScreenshot(actual));

			}
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}


	/**
	 * Method will be used to write log statement
	 * 
	 */
	public static void info(String message){
		try {
			reporter.info(message);
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}

	/**
	 * Method will be used during failure
	 * 
	 */
	public static void testFailed(String message){
		try {
			Assert.fail(message);

			reporter.addScreenCaptureFromPath(captureScreenshot.captureScreenshot(message));
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}



	/**
	 * This method will capture the current time stamp
	 * 
	 * @return Returned the current date
	 */
	public static String getTimeStamp() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(EnvironmentManager.getDateForamt());
		Date dateObject = new Date();
		return simpleDateFormat.format(dateObject);
	}
}

