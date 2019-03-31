package zf.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;

public class ZFAddMultipleGatewayPage extends ElementManager {
	JsonReader jsonData=new JsonReader();
	ZFGatewayHomePage zfgatewayhomepage=new ZFGatewayHomePage();
	private static  By ADDMULTIPLEGATEWAY_RB                          =By.xpath("//p-radiobutton[@label='MULTIPLE GATEWAY']//div[@class='ui-radiobutton ui-widget']");
	private static  By ADDMULTIPLEGATEWAY_ENROLLMENTGROUP_NEW_RB 	  =By.xpath("//p-radiobutton[@label='New']");
	private static  By ADDMULTIPLEGATEWAY_ENROLLMENTGROUP_EXISTING_RB =By.xpath("//p-radiobutton[@label='Existing']");
	private static  By ADDMULTIPLEGATEWAY_CREATE_ENROLLMENTGROUP_EB   =By.xpath("//input[@class='fw ng-untouched ng-pristine ng-invalid']");
	private static  By ADDMULTIPLEGATEWAY_UPLOADCA_ROOT_CERTIFICATE_F =By.xpath("//div[@class='fr upload-icon sprite'][1]");
	private static  By ADDMULTIPLEGATEWAY_UPLOAD_GATEWAY_DETAILS_F    =By.xpath("//input[@accept='.xls, .xlsx']");
	private static  By ADDMULTIPLEGATEWAY_UPLOAD_GATEWAY_BT           =By.xpath("//button[@class='zf-button upload-btn fr']");
	private static  By ADDMULTIPLEGATEWAY_SUBMIT_BT					  =By.xpath("//button[@class='zf-button next fr primary ng-star-inserted']");
	private static  By ADDMULTIPLEGATWAY_TOASTER_DT					  =By.xpath("//div[@class='toast-title']");
	private static  By ADDMULTIPLEGATEWAY_CAFILENAME				  =By.xpath("//div[@class='cert-name fl']");
	private static  By ADDMULTIPLEGATEWAY_SELECT_ENROLLMENTGROUP_DD   =By.xpath("//span[@class='ui-dropdown-trigger-icon ui-clickable fa fa-chevron-down']");
	private static String ADDMULTIPLEGATEWAY_SELECT_ENROLLGROUP_DL	  ="//span[text()='{}']";

	public void addMultipleGatewayNew() {
		try {
			zfgatewayhomepage.clickAddGateway();
			elementClick(ADDMULTIPLEGATEWAY_RB);
			JsonReader.getJsonObject("AddMultipleGatewayNew");
			elementClick(ADDMULTIPLEGATEWAY_ENROLLMENTGROUP_NEW_RB);
			elementSendKeys(ADDMULTIPLEGATEWAY_CREATE_ENROLLMENTGROUP_EB,jsonData.getJsonData("CreateEnrollmentGroup"));
			elementClick(ADDMULTIPLEGATEWAY_UPLOADCA_ROOT_CERTIFICATE_F);
			uploadFile(jsonData.getJsonData("UploadCA"));
			elementClick(ADDMULTIPLEGATEWAY_UPLOAD_GATEWAY_DETAILS_F);
			uploadFile(jsonData.getJsonData("UploadGatewayDeatils"));
			elementClick(ADDMULTIPLEGATEWAY_UPLOAD_GATEWAY_BT);

			verifyToastermessage("ToasterMessageFailed");
		}catch(Exception e) {

		}
	}

	public void addMultipleGatewayExisting() {

		try {
			JsonReader.getJsonObject("AddMultipleGatewayExisting");
			zfgatewayhomepage.clickAddGateway();
			elementClick(ADDMULTIPLEGATEWAY_RB);
			elementClick(ADDMULTIPLEGATEWAY_ENROLLMENTGROUP_EXISTING_RB);
			elementClick(ADDMULTIPLEGATEWAY_SELECT_ENROLLMENTGROUP_DD);
			elementClick(By.xpath(dynamicXpath(ADDMULTIPLEGATEWAY_SELECT_ENROLLGROUP_DL,"SelectEnrollmentGroup")));
			if(compareValue(elementGetText(ADDMULTIPLEGATEWAY_CAFILENAME),jsonData.getJsonData("CAfilename"))){
				elementClick(ADDMULTIPLEGATEWAY_UPLOAD_GATEWAY_DETAILS_F);
				uploadFile(jsonData.getJsonData("UploadGatewayDetails"));
				elementClick(ADDMULTIPLEGATEWAY_UPLOAD_GATEWAY_BT);
				verifyToastermessage("ToasterMessageSuccess");
				elementClick(ADDMULTIPLEGATEWAY_SUBMIT_BT);
				verifyToastermessage("ToasterMessageSuccess");

			}else {
				//				testFailed("CA file name get from webpage:"+elementGetText(ADDMULTIPLEGATEWAY_CAFILENAME)+"is not same as given"+jsonData.getJsonData("CAfilename"));
			}

		}catch(Exception e) {

		}
	}

	public	String dynamicXpath(String xpath, String Jsonvalue) {
		return updateXpath(xpath,jsonData.getJsonData(Jsonvalue));
	}

	public void verifyToastermessage(String Jsondata) {
		waitForMessage(ADDMULTIPLEGATWAY_TOASTER_DT,9);
		String toasterText = elementGetText(ADDMULTIPLEGATWAY_TOASTER_DT);
		compareText(jsonData.getJsonData(Jsondata),toasterText);
	}

}
