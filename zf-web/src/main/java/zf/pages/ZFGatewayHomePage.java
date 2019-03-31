package zf.pages;
import org.openqa.selenium.By;

import framework.ElementManager;

public class ZFGatewayHomePage extends ElementManager{

	private static By GATEWAY_ADDGATEWAY_BT    =By.xpath("//button[contains(text(),'Add Gateway')]");
	private static By GATEWAY_ICON             =By.xpath("//a[@title='Gateway']");

	private static By FromDate    =By.xpath("//table/tbody/tr[4]/td[3]/a')]");
	private static By ToDate      =By.xpath("//span/div/div/div[2]/table/tbody/tr[4]/td[7]/a')]");






	public ZFAddSingleGatewayPage clickAddGateway() {

		try {
			waitElementVisibleClick(GATEWAY_ICON,5000);
			//waitElementVisibleClick(GATEWAY_ADDGATEWAY_BT,15000);
			sleep(2500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		return new ZFAddSingleGatewayPage();

	}

}
