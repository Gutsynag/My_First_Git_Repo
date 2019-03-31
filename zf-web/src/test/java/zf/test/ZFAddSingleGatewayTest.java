package zf.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.ZFAddSingleGatewayPage;
import zf.pages.MicrosoftLoginPage;

public class ZFAddSingleGatewayTest extends ZFAddSingleGatewayPage{
     
     MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
     @BeforeMethod
     public void beforeMethod(Method testName) {
           startTest(testName.getName());
          DriverManager.getDriver(EnvironmentManager.getBrowserName());
           microsoftlogin.microsoftLogin();
           }
     
      @Test
     public void Tc01addSingleGateway() {
    	
           addSingleGateway();
     }
     
     @AfterMethod
     public void afterMethod(ITestResult result,Method testName)
     {
           getResult(result,testName.getName());
           DriverManager.closeAllBrowser();
     }
}

