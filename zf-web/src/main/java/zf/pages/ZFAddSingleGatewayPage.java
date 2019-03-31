package zf.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;

public class ZFAddSingleGatewayPage extends ElementManager{
	JsonReader jsonData=new JsonReader();
	ZFGatewayHomePage zfgatewayhomepage=new ZFGatewayHomePage();

	private static  By SINGLEGATEWAY_RB										=By.xpath("(//div[contains(@class,'ui-radiobutton-box ui-widget ui-state-default')])[1]");
	private static  By MULTIPLEGATEWAY_RB									=By.xpath("(//div[contains(@class,'ui-radiobutton-box ui-widget ui-state-default')])[2]");
	private static  By ADDSINGLEGATWAY_GATEWAYNAME_EB						=By.xpath("//input[@name='gatewayName']");
	private static  By ADDSINGLEGATWAY_TYPE_DD								=By.xpath("//p-dropdown[@optionlabel='gatewayType']");
	private static  By ADDSINGLEGATWAY_MODEL_DD								=By.xpath("//p-dropdown[@optionlabel='gatewayModel']");
	private static  By ADDSINGLEGATWAY_MACID_EB								=By.xpath("//input[@name='gatewayMacId']");
	private static  By ADDSINGLEGATWAYSERIALNUMBER_EB						=By.xpath("//input[@name='gatewaysNo']");
	private static  By ADDSINGLEGATWAY_SIMNUMBER_EB							=By.xpath("//input[@name='gatewaysimNo']");
	private static  By ADDSINGLEGATWAY_UPLOADGATEWAYCERTIFICATE				=By.id("file-input");
	private static  By ADDSINGLEGATWAY_NEXT_BT								=By.xpath("//button[@class='zf-button next fr primary ng-star-inserted' and text()='Next']");
	private static  By ADDSINGLEGATWAY_CUSTOMERNAME_ED						=By.xpath("//input[@name='customer']");
	private static  By ADDSINGLEGATWAY_CUSTOMERCOUNTRY_DD					=By.xpath("//p-dropdown[@name='selectCountry']");
	private static  By ADDSINGLEGATWAY_CUSTOMERAREA_DD						=By.xpath("//p-dropdown[@name='selectedCity']");
	private static  By ADDSINGLEGATWAY_BUSINESSUNIT_EB						=By.xpath("//input[@name='businessLine']");
	private static  By ADDSINGLEGATWAY_OPERATOR_EB							=By.xpath("//input[@name='operator']");
	private static  By ADDSINGLEGATWAY_CUSTOMERDETAIL_NEXT_BT				=By.xpath("//button[@class='zf-button next fr primary' and text()='Next']");
	private static  By ADDSINGLEGATWAY_ADDITIONALDETAIL_DESCRIPTION_EB		=By.xpath("//textarea[@name='description']");
	private static  By ADDSINGLEGATWAY_ADDITIONALDETAIL_METADATA_ICON		=By.xpath("//div[@class='add-icon']");
	private static  By ADDSINGLEGATWAY_ADDITIONALDETAIL_METADATA_KEY_EB     =By.xpath("//table[@class='meta-wh meta-data-table']//tr[@class='ng-star-inserted']/td[1]");
	private static  By ADDSINGLEGATWAY_ADDITIONALDETAIL_METADATA_VALUE_EB   =By.xpath("//table[@class='meta-wh meta-data-table']//tr[@class='ng-star-inserted']/td[2]");
	private static  By ADDSINGLEGATWAY_ADDITIONALDETAIL_FINISH_BT			=By.xpath("//button[text()='Finish']");
	private static  By ADDSINGLEGATWAY_TOASTER_DT							=By.xpath("//div[@class='toast-title']");
	private static String TYPEDROPDOWNLIST									="//span[text()='{}']";

	
	
	public void addSingleGateway() {

		try {
			sleep(3000);
			zfgatewayhomepage.clickAddGateway();
			JsonReader.getJsonObject("AddSingleGateway");
			elementSendKeys(ADDSINGLEGATWAY_GATEWAYNAME_EB,jsonData.getJsonData("GatewayName"));
			waitElementVisibleClick(ADDSINGLEGATWAY_TYPE_DD,10000);
			elementClick(By.xpath(dynamicXpath(TYPEDROPDOWNLIST,"GatewayType")));
			elementClick(ADDSINGLEGATWAY_MODEL_DD);
			elementClick(By.xpath(dynamicXpath(TYPEDROPDOWNLIST,"Model")));
			elementSendKeys(ADDSINGLEGATWAY_MACID_EB,jsonData.getJsonData("MACID"));
			elementSendKeys(ADDSINGLEGATWAYSERIALNUMBER_EB,jsonData.getJsonData("SerialNumber"));
			elementSendKeys(ADDSINGLEGATWAY_SIMNUMBER_EB,jsonData.getJsonData("SIMNumber"));
			elementClick(ADDSINGLEGATWAY_UPLOADGATEWAYCERTIFICATE);
			uploadFile(jsonData.getJsonData("UploadCertificate"));
			elementClick(ADDSINGLEGATWAY_NEXT_BT);


			//Enter Client Details
			elementSendKeys(ADDSINGLEGATWAY_CUSTOMERNAME_ED,jsonData.getJsonData("CustomerName"));
			elementClick(ADDSINGLEGATWAY_CUSTOMERCOUNTRY_DD);
			elementClick(By.xpath(dynamicXpath(TYPEDROPDOWNLIST,"CustomerCountry")));
			elementClick(ADDSINGLEGATWAY_CUSTOMERAREA_DD);
			elementClick(By.xpath(dynamicXpath(TYPEDROPDOWNLIST,"CustomerArea")));
			elementSendKeys(ADDSINGLEGATWAY_BUSINESSUNIT_EB,jsonData.getJsonData("BusinessUnit"));
			elementSendKeys(ADDSINGLEGATWAY_OPERATOR_EB,jsonData.getJsonData("Operator"));
			elementClick(ADDSINGLEGATWAY_CUSTOMERDETAIL_NEXT_BT);


			//Additional
			//elementClick(ADDSINGLEGATWAY_ADDITIONALDETAIL_DESCRIPTION_ED);
			elementSendKey(ADDSINGLEGATWAY_ADDITIONALDETAIL_DESCRIPTION_EB,jsonData.getJsonData("Description"));
			elementClick(ADDSINGLEGATWAY_ADDITIONALDETAIL_METADATA_ICON);
			//elementClick(ADDSINGLEGATWAY_ADDITIONALDETAIL_METADATA_KEY_ED);
			elementSendKey(ADDSINGLEGATWAY_ADDITIONALDETAIL_METADATA_KEY_EB,jsonData.getJsonData("MetaDatakey"));
			elementSendKey(ADDSINGLEGATWAY_ADDITIONALDETAIL_METADATA_VALUE_EB,jsonData.getJsonData("MetaDatavalue"));
			elementClick(ADDSINGLEGATWAY_ADDITIONALDETAIL_FINISH_BT);
			verifyToastermessage("ToasterMessageFailed");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}



	public void staticValidation() {
		try {
			sleep(3000);
			zfgatewayhomepage.clickAddGateway();
			elementSetRadioButton(SINGLEGATEWAY_RB);
			elementSetRadioButton(MULTIPLEGATEWAY_RB);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public	String dynamicXpath(String xpath, String Jsonvalue) {
		return updateXpath(xpath,jsonData.getJsonData(Jsonvalue));
	}


	public void verifyToastermessage(String Jsondata) {
		waitForMessage(ADDSINGLEGATWAY_TOASTER_DT,9);
		String toasterText = elementGetText(ADDSINGLEGATWAY_TOASTER_DT);
		compareText(jsonData.getJsonData(Jsondata),toasterText);
	}

}
