package zf.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;

public class MicrosoftLoginPage extends ElementManager {
	JsonReader jsonReader = new JsonReader();
	private static By SIGNWITHEMAIL = By.xpath("//input[@type='email']");
	private static By NEXTBUTTON = By.id("idSIButton9");
	private static By PSWRDMAIL = By.xpath("//input[@id='passwordInput']");
	private static By SUBMITBUTTON = By.id("submitButton");
	private static By YESPOPUP = By.id("idSIButton9");

	public ZFGatewayHomePage microsoftLogin() {
		try {
			elementSendKeys(SIGNWITHEMAIL, EnvironmentManager.getUserName());
			elementClick(NEXTBUTTON);
			sleep(2000);
			elementSendKeys(PSWRDMAIL, EnvironmentManager.getPassword());
			elementClick(SUBMITBUTTON);
			sleep(2000);
			elementClick(YESPOPUP);
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
		return new ZFGatewayHomePage();
	}
}
