package zf.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.DriverManager;
import framework.EnvironmentManager;
import framework.ExcelReader;
import framework.ExtentReport;
import framework.JsonReader;
import net.minidev.json.parser.ParseException;

public class Zf_UIDemo extends ExtentReport {
	public String value ="ZF Automotive";
	JsonReader jsonreader=new JsonReader();
	ExcelReader ex=new ExcelReader();
	Login login=new Login();
	String technologiesText;
	String jsonData;
	Map<String, List<String>> data;

	//Preconditions
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	@Test
	public void testCaseChromeSucess() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName());
		login.click();
		login.enterText(value);
	}
	@Test
	public void testCaseChromeFailure() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName());
		compareText("Homepage ZF Friedrichshafen AG", login.getZFTitle() );
		login.click();
		login.enterText1(value);
	}
	
	@Test
	public void testCaseFireFoxPass() {
		DriverManager.getDriver("FIREFOX");
		login.click();
		login.enterText(value);
	}
	
	@Test
	public void testCaseFireFoxFailure() {
		DriverManager.getDriver("FIREFOX");
		compareText("Homepage ZF Friedrichshafen AG ", login.getZFTitle() );
		login.click();
		login.enterText1(value);
	}
	
	@Test
	public void testCaseIEFailure() {
		DriverManager.getDriver("IE");
		compareText("Search - ZF Friedrichshafen AG", login.getZFTitle() );
	}
	@Test
	public void testCaseReadingExcel() throws ParseException, IOException {
		DriverManager.getDriver(EnvironmentManager.getBrowserName());
		technologiesText = login.getTechnologiesText();
		data = ex.getData("TestcaseExcel");
		value = data.get("TestcaseExcel").get(2);
		compareText(technologiesText, value );
	}
	@Test
	public void testCaseReadingJson() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName());
		JsonReader.getJsonObject("Testcasejson");
		 jsonData = jsonreader.getJsonData("firstName");
		technologiesText =login.getTechnologiesText();
		compareText(technologiesText,jsonData);
	}


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}


}
