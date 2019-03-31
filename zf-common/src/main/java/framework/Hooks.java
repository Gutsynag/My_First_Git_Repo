package framework;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Hooks {
	ExtentReport extentReport=new ExtentReport();
	@BeforeTest
	public void beforeTest() {
		ExtentReport.configureReport();
		}
	
		
	@AfterTest
	public void afterTest()
	{
		ExtentReport.reportClose();

}}
