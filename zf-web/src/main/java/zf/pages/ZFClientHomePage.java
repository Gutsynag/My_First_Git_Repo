package zf.pages;

import org.openqa.selenium.By;

import framework.ElementManager;

public class ZFClientHomePage extends ElementManager{
	private static By manageClientHeader          =By.xpath("//div[@class='manage-client-header fw']/div[@class='fl title']");
	private static By clientDetailExpandArrow     =By.xpath("(//div[@class='client-table-row-wrapper']/div[contains(@class,'client-table-row')]/div[contains(@class,'row no-gutter')]/div[contains(@class,'width-4')]/div/span[contains(@class,'arrow')])[1]");
	private static By addSubClientButton          =By.xpath("//div[@class='fw table-row-additioanl-details open']/descendant::span[contains(text(),'Add Sub-Client')]/parent::button");
	private static By editClientButton            =By.xpath("//div[@class='fw table-row-additioanl-details open']/descendant::span[contains(text(),'Edit Client')]/parent::button");
	private static By moreDetailsButton			  =By.xpath("//div[@class='fw table-row-additioanl-details open']/descendant::span[contains(text(),'More Details')]/parent::button");
	private static By ClientsTab				  =By.xpath("//a[@title='Clients']");
	private static By clientRowGeneric			  =By.xpath("//div[@class='client-table-row-wrapper']/div[contains(@class,'client-table-row')]");
	
	public String getClientsPageTitle(){
		return elementGetText(manageClientHeader);
	}
	
	public void addClientButtonClick() throws InterruptedException {
		waitElementVisibleClick(addSubClientButton, 20000);
	}
	
	public void editClientButtonClick() {
		elementClick(editClientButton);
	}
	
	public void moreDetailsButtonClick()
	{
		elementClick(moreDetailsButton);
	}
	
	public void clicOnClientTab() throws InterruptedException
	{
		waitElementVisibleClick(ClientsTab, 20000);
	}
	public void clickOnClientDetailsExpandArrow() throws InterruptedException{
		waitElementVisibleClick(clientDetailExpandArrow, 20000);
	}
	public void clickOnAddClientButton() throws InterruptedException{
		waitElementVisibleClick(addSubClientButton, 20000);
	}

	public void clickOnEditClientButton()
	{
		elementClick(editClientButton);
	}
	ZFAddSubClientPage zfaddclient=new ZFAddSubClientPage();
	
	public void callingzfsubclient() throws InterruptedException
	{
		zfaddclient.addSubClientDetails();
		zfaddclient.inputEmailAssignAdministrator();
		zfaddclient.inputPreviewAndCreateClient();
	}
}


	


