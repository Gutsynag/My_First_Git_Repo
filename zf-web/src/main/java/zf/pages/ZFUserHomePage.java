package zf.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.JsonReader;

public class ZFUserHomePage extends ElementManager{
	JsonReader jsonData=new JsonReader();
	private static  By ADDUSER_ADDUSER_BT                =By.xpath("//button[@class='button create-btn button-secondary']");
	private static  By ADDUSER_NAME_EB             		 =By.xpath("(//input[@type='text'])[2]");
	private static  By ADDUSER_EMAIL_EB    				 =By.xpath("(//input[@type='text'])[4]");
	private static  By ADDUSER_SURNAME_EB   			 =By.xpath("(//input[@type='text'])[3]");
	private static  By ADDUSER_USERROLES_EB 			 =By.xpath("//input[@type='search']");
	private static  By ADDUSER_CANCEL_BT    			 =By.xpath("//span[text()='Cancel']");
	private static  By ADDUSER_SAVE_BT     				 =By.xpath("//span[text()='Save']");
	private static  By ADDUSER_POPUP_DT    				 =By.xpath("//div[@class='confirmation-content ng-scope ng-binding']");
	private static  By ADDUSER_POPUPNO_BT  				 =By.xpath("//button[@class='button button-secondary ng-scope ng-binding']");
	private static  By ADDUSER_POPUPYES_BT 				 =By.xpath("//button[@class='button button-primary ng-scope ng-binding']");
	private static  By ADDUSER_TOASTER_DT  				 =By.xpath("//div[@id='toast-container']/div[@ng-repeat='toaster in toasters']");
	private static  By ADDUSER_FILTER_ST  				 =By.xpath("//span[@class='filter-label ng-binding']");
	private static  By ADDUSER_SEARCH1_EB  				 =By.xpath("//input[@type='text']");
	private static  By ADDUSER_SEARCH_EB  				 =By.xpath("//input[@class='filter-input ng-pristine ng-untouched ng-valid ng-empty']");
	private static  By ADDUSER_SEARCHFIRSTVALUE_DT  	 =By.xpath("(//div[@class='item-content'])[1]");
	private static  By ADDUSER_EDITUSER_BT   			 =By.xpath("//button[@class='button button-primary edit-button']");
	private static  By ADDUSER_DELETEUSER_BT   			 =By.xpath("//button[@class='button button-primary delete-button']");
	private static  By ADDUSER_DELETEUSERPOPYES_BT   	 =By.xpath("//button[@class='button button-primary ng-scope ng-binding']");
	private static  By ADDUSER_DELETEUSERPOPNO_BT   	 =By.xpath("//button[@class='button button-secondary ng-scope ng-binding']");
	private static  By ADDUSER_NORESULTSFOUND_DT   	 	 =By.xpath("//span[@class='ng-binding']");
	private static  By USER_ICON   	 	                 =By.xpath("//a[@title='Users']");



	public void addUser() {
		try {
			
			JsonReader.getJsonObject("Tc01addUser");
			waitElementVisibleClick(USER_ICON,10000);
			waitElementVisibleClick(ADDUSER_ADDUSER_BT,10000);
			waitElementToBeVisibleSendValue(ADDUSER_NAME_EB,10000, jsonData.getJsonData("Name"));
			elementSendKeys(ADDUSER_EMAIL_EB, jsonData.getJsonData("Email"));
			elementSendKeys(ADDUSER_SURNAME_EB, jsonData.getJsonData("SureName"));
			elementSendKeys(ADDUSER_USERROLES_EB, jsonData.getJsonData("Role"));
			elementClick(ADDUSER_CANCEL_BT);
			compareText(jsonData.getJsonData("CancelText"), elementGetText(ADDUSER_POPUP_DT));
			elementClick(ADDUSER_POPUPNO_BT);
			elementClick(ADDUSER_SAVE_BT);

			verifyUserCreated();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void editUser() {
		try {
			JsonReader.getJsonObject("Tc02EditUser");
			waitElementVisibleClick(USER_ICON,10000);
			waitElementVisibleClick(ADDUSER_FILTER_ST,10000);
			elementSendKeys(ADDUSER_SEARCH_EB, jsonData.getJsonData("Name"));
			elementClick(ADDUSER_SEARCHFIRSTVALUE_DT);
			elementClick(ADDUSER_EDITUSER_BT);
			waitElementToBeVisibleSendValue(ADDUSER_NAME_EB,10000, jsonData.getJsonData("EditName"));
			elementSendKeys(ADDUSER_EMAIL_EB, jsonData.getJsonData("EditEmail"));
			elementSendKeys(ADDUSER_SURNAME_EB, jsonData.getJsonData("EditSureName"));
			elementSendKeys(ADDUSER_USERROLES_EB, jsonData.getJsonData("EditRole"));
			elementClick(ADDUSER_CANCEL_BT);
			compareText(jsonData.getJsonData("CancelText"), elementGetText(ADDUSER_POPUP_DT));
			elementClick(ADDUSER_POPUPNO_BT);
			elementClick(ADDUSER_SAVE_BT);
			verifyUserUpdated();

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void deleteUser() {
		try {
			JsonReader.getJsonObject("Tc03DeleteUser");
			waitElementVisibleClick(USER_ICON,10000);
			waitElementVisibleClick(ADDUSER_FILTER_ST,10000);
			elementSendKeys(ADDUSER_SEARCH_EB, jsonData.getJsonData("Name"));
			elementClick(ADDUSER_SEARCHFIRSTVALUE_DT);
			waitElementVisibleClick(ADDUSER_DELETEUSER_BT,10000);
			elementClick(ADDUSER_DELETEUSER_BT);
			elementClick(ADDUSER_DELETEUSERPOPYES_BT);
			refreashPage();
			waitElementVisibleClick(ADDUSER_FILTER_ST,10000);
			elementClick(ADDUSER_FILTER_ST);
			elementSendKeys(ADDUSER_SEARCH1_EB, jsonData.getJsonData("Name"));
			compareText(jsonData.getJsonData("Noresultvalue"), elementGetText(ADDUSER_NORESULTSFOUND_DT));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public void verifyUserUpdated() {
		waitForMessage(ADDUSER_TOASTER_DT,10);
		String Sucessmsg = elementGetText(ADDUSER_TOASTER_DT);
		String[] Sucess= Sucessmsg.split("User");
		compareText(jsonData.getJsonData("ToasterMsgSuccess"), Sucess[0].replaceAll("\\s+",""));
		compareText(jsonData.getJsonData("ToasterMsgSuccesUpdated"), Sucess[1]);
	}

	public void verifyUserCreated() {
		waitForMessage(ADDUSER_TOASTER_DT,10);
		String Sucessmsg = elementGetText(ADDUSER_TOASTER_DT);
		String[] Sucess= Sucessmsg.split("User");
		compareText(jsonData.getJsonData("ToasterMsgSuccess"), Sucess[0].replaceAll("\\s+",""));
		compareText(jsonData.getJsonData("ToasterMsgSuccessCreated"), Sucess[1]);
	}
}
