package zf.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import framework.DatabaseUtility;
import framework.DriverManager;
import framework.EnvironmentManager;
import framework.ExcelReader;
import framework.ExtentReport;
import framework.JsonReader;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
//import DatabaseUtility;

public class mainclass {

	ExtentReport extentReport=new ExtentReport(); ;

//	@BeforeTest
//	public void beforeTest() {
//		//ExtentReport.configureReport();
//
//	}
//	@BeforeMethod
//	public void beforeMethod(Method testName) {
//		String methodName = testName.getName();
//		extentReport.startTest(methodName);
//	}
//
//	@Test(enabled = false)
//	public void testMethod1() throws ParseException, IOException {
//
//		DriverManager.getDriver(EnvironmentManager.getBrowserName());
//		ExcelReader s=new ExcelReader();
//		Map<String, List<String>> data = s.getData("test6");
//		String string = data.get("test6").get(3);
//		System.out.println(string);
//
//	}

	@Test()
	public void testMethod2() {
		String a=EnvironmentManager.getCertificatePath();
		File c1 = new File(a);
		System.out.println(a);
		c1.getAbsolutePath().toString();
		System.out.println(c1);
//		JsonReader j=new JsonReader();
//		JSONObject jsonObject = JsonReader.getJsonObject("AddSingleGateway");
//	//	String jsonData = j.getJsonData("website");
//		System.out.println("Value for particular key is : "+jsonObject);
//		JSONObject jsonObject1 = JsonReader.getJsonObject("AddMultipleGateway");
//		System.out.println("Value for particular key is : "+jsonObject1);
//		Assert.assertTrue(false);
	}
	
	
//	@Test
//	  public void test1()  {
//		try {  
//		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% INTO Testng %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//		  
//		  //String tableCreation =  "CREATE TABLE members_code3 (NAME varchar(255),ID int)";
//		
//		  String tableCreation =  "CREATE TABLE members_code3 (NAME VARCHAR(255),ID INTEGER)";
//		  
//		  System.out.println("The sql string is : "+ tableCreation);
//		  System.out.println("The connection string from testng test is : "+DatabaseUtility. getConnection("mysql"));
//		  
//		  System.out.println("Table created :"+DatabaseUtility.createTableQuery(DatabaseUtility. getConnection("mysql"),tableCreation));
//		  
//		  String tableDeletion = "DROP TABLE members_code3";
//		  
//		  System.out.println("Table deleted is :"+DatabaseUtility.dropTableQuery(DatabaseUtility.getConnection("mysql"),tableDeletion));
//		}catch(Exception e) {
//			
//			System.out.println("  Exception caught while dealiong with sqlserver ");
//			
//		}
//	  }
//	
//	@AfterMethod
//	public void afterMethod(ITestResult result,Method testName)
//	{
//		extentReport.getResult(result,testName.getName());
//
//	}
//
//	@AfterTest
//	public void afterTest() {
//		ExtentReport.reportClose();
//	}
//	
//	


}
