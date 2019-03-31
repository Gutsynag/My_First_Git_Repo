package zf.pages;
import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;

public class ZFAddSubClientPage extends ElementManager {
	JsonReader jsonData=new JsonReader();

	private static By addClientPopupTitleField 				= By.xpath("//div[@role='dialog']/div[contains(@class,'ui-dialog-titlebar')]/span[contains(@class,'ui-dialog-title')]/p-header");
	private static By addClientPopupCloseIcon 				= By.xpath("//div[@role='dialog']/div[contains(@class,'ui-dialog-titlebar')]/descendant::span[@class='pi pi-times']");
	private static By clientDetailsInfoTextArea 			= By.xpath("//div[@role='dialog']/descendant::div[@class='tab-content-wrapper']/descendant::div[@class='horizontal-split']/descendant::label[text()='Information']/following::textarea");
	private static By continueButtonClientDetailsSection    = By.xpath("//div[@class='tab1-content']//span[contains(text(),'Continue')]");
	private static By cancelButtonClientDetailsSection    	= By.xpath("//div[@role='dialog']/descendant::div[@class='tab1-content']/descendant::button[contains(text(),'Cancel')]");
	private static By inviteButton						    = By.xpath("//div[@role='dialog']/descendant::div[@class='tab2-content']/descendant::button[contains(text(),'Invite')]");
	private static By backButtonAssignAdmin 				= By.xpath("//div[@role='dialog']/descendant::div[@class='tab2-content']/descendant::button/descendant::span[contains(text(),'Back')]");
	private static By continueButtonAssignAdmin			    = By.xpath("//div[@class='tab2-content']//span[contains(text(),'Continue')]");
	private static By emailInputAssignAdmin 				= By.xpath("//input[@placeholder='Enter E-mail ID']");
	private static By createButtonPreviewSection 			= By.xpath("//div[@role='dialog']/descendant::div[@class='tab3-content']/descendant::button/descendant::span[contains(text(),'Create')]");
	private static By backButtonPreviewSection 				= By.xpath("//div[@role='dialog']/descendant::div[@class='tab3-content']/descendant::button/descendant::span[contains(text(),'Back')]");
	private static By invalidClientNameErrorField 			= By.xpath("//div[@class='client-name-wrapper']/descendant::span[contains(@class,'error')]");
	private static By invalidEmailErrorField 				= By.xpath("//div[@class='email-wrapper']/descendant::span[contains(@class,'error')]");
	private static By clientNameInputBox 					= By.xpath("(//div[@role='dialog']/descendant::div[@class='tab-content-wrapper']/descendant::div[@class='horizontal-split']/descendant::label[text()='Client Name']/following::input)[1]");
	private static By contactDetailsTitle					= By.xpath("//div[@class='title-wrapper'][contains(text(),'Contact Details')]");
	private static By addClientTitle						= By.xpath("//p-header[contains(text(),'Add Client')]");
	private static By previewClientName						= By.xpath("//p[@class='bold-text']");
	private static By previewClientEmail					= By.xpath("(//div[@class='content-wrapper'])[1]/div[@class='content']/p[2]");
	private static By previewClientTelephone				= By.xpath("(//div[@class='content-wrapper'])[1]/div[@class='content']/p[3]");
	private static By previewClientStreetNumber				= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[1]");
	private static By previewClientstreet					= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[2]");
	private static By previewClientCountry					= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[3]");
	private static By previewClientPostCode					= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[4]");
	private static By createClientCreateToaster				= By.xpath("//div[text()='Unauthorized user']");
	String COMMONXPATH										= "//input[@name='{}']";


	public void addSubClientDetails()
	{
		JsonReader.getJsonObject("ClientDetails");
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"clientName")),jsonData.getJsonData("clientName"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"clientEmail")),jsonData.getJsonData("email"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"clientStreet")),jsonData.getJsonData("street"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"clientStreetNumber")),jsonData.getJsonData("streetNumber"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"telephone")),jsonData.getJsonData("telephone"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"clientPostcode")),jsonData.getJsonData("postCode"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"website")),jsonData.getJsonData("webSite"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"clientCountry")),jsonData.getJsonData("country"));
		elementSendKeys(By.xpath(dynamicXpath(COMMONXPATH,"fax")),jsonData.getJsonData("fax"));
		elementClick(continueButtonClientDetailsSection);
	}

	public void inputEmailAssignAdministrator() throws InterruptedException
	{
		JsonReader.getJsonObject("AssignAdministrator");
		waitElementVisibleClick(emailInputAssignAdmin, 10000);
		waitElementToBeVisibleSendValue(emailInputAssignAdmin, 10000, jsonData.getJsonData("email1"));
		elementClick(inviteButton);
		elementClick(continueButtonAssignAdmin);
	}

	public void inputPreviewAndCreateClient() throws InterruptedException {

		compareText(elementGetText(previewClientName), jsonData.getJsonData("clientName"));
		compareText(elementGetText(previewClientEmail), jsonData.getJsonData("email"));
		compareText(elementGetText(previewClientTelephone), jsonData.getJsonData("telephone"));
		compareText(elementGetText(previewClientStreetNumber), jsonData.getJsonData("streetNumber"));
		compareText(elementGetText(previewClientstreet), jsonData.getJsonData("street"));
		compareText(elementGetText(previewClientCountry), jsonData.getJsonData("country"));
		compareText(elementGetText(previewClientPostCode), jsonData.getJsonData("postCode"));
		waitElementVisibleClick(createButtonPreviewSection,30000);
		verifyToastermessage("ToasterMsg");
	}


	public void verifyToastermessage(String Jsondata) {
		waitForMessage(createClientCreateToaster,9);
		String toasterText = elementGetText(createClientCreateToaster);
		compareText(jsonData.getJsonData(Jsondata),toasterText);
	}

	public void clickOnAddClientCloseIcon() throws InterruptedException{
		elementClick(addClientPopupCloseIcon);
		Thread.sleep(1000);
	}

	public	String dynamicXpath(String xpath, String Jsonvalue) {
		return updateXpath(xpath,Jsonvalue);
	}

}