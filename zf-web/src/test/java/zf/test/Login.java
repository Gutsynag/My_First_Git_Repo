package zf.test;

import org.openqa.selenium.By;

import framework.ElementManager;

public class Login extends ElementManager {

	private static final By SEARCH = By.xpath("//div[@class='zfch-icons-wrapper-bottom']/descendant::span[@class='zfcui-icon zfcui-icon-suche zfcui-icon zfch-overlay-trigger']");
	private static final By SEARCHTEXTBOX=By.xpath("(//input[@placeholder='Enter a search term...'])[4]");
	private static final By BUTTON=By.xpath("(//span[@class='zfcui-icon zfcui-icon-arrow-big-thin-r zfcui-color-white'])[4]");
	private static final By BUTTON1=By.xpath("(//span[@class='zfcui-icon zfcui-icon-arrow-big-thin-r zfcui-color-white'])[4");
	private static final By TECHNOLOGIES = By.xpath("(//span[text()='Technologies'])[1]");
	public void click() {
		elementClick(SEARCH);
		
	}
	
	public void enterText(String text) {
		
		elementSendKeys(SEARCHTEXTBOX, text);
		elementClick(BUTTON);
		
	}
	
	public void enterText1(String text) {
		elementSendKeys(SEARCHTEXTBOX, text);
		elementClick(BUTTON1);
		
	}
	
	public String getZFTitle() {
		String titile = getTitile();
		return titile;
		
	}
	
	public String getTechnologiesText() {
		String Text = elementGetText(TECHNOLOGIES);
		return Text;

	}
}
