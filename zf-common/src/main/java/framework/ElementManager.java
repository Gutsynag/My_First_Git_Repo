package framework;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.annotation.Nonnull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class ElementManager  extends ExtentReport{
	private WebDriverWait wait;
	private WebElement element;
	private WebElement elementDestination;
	private Actions actionBuilder;
	private String errormessage;
	JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriverInstance();

	/**
	 * This method is used to interact with a web element by "Clicking on it"
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * elementToBeClickable. If the web element does not exist timeout exception
	 * will be thrown. This method will also log the locator path in the Log4J
	 * log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementClick(By byType) {
		try {
			element = DriverManager.getDriverInstance().findElement(byType);
			TestLogger.elementClickIdentifier(element.getText());
			sleep(1000);
			element.click();
			sleep(2000);
		} catch (Exception exceptionMessage) {
			errormessage = byType.toString() + " not found";
		//	takeFailureScreenShot(byType);
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method is used to interact with a web element by "Clicking on it"
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * elementToBeClickable. If the web element does not exist timeout exception
	 * will be thrown. This method will also log the locator path in the Log4J
	 * log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementCSSClick(By byType) {
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementClickIdentifier(element.getText());
					element.click();
				}
			}
		} catch (Exception exceptionMessage) {
			errormessage = byType.toString() + " not found";
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method is used for pass the value to edit fields using the Selenium
	 * WebDriver findElement(By), WebDriverWait & elementToBeClickable. If the
	 * web element does not exist timeout exception will be thrown. This method
	 * will also log the locator path in the Log4J log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param value
	 *            Is the pass the value to element
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementSendKeys(By byType, String value) {
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			element = DriverManager.getDriverInstance().findElement(byType);
			sleep(1000);	
			element.clear();
			element.sendKeys(value);
			sleep(1000);	
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = "sendkeys is not working for " + byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
		//	takeFailureScreenShot(byType);
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method is used to interact with a web element by "Hovering over it"
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * hoverOverRegistrar. If the web element does not exist a screenshot will
	 * be taken and a timeout exception will be thrown. This method will also
	 * log the locator path in the Log4J log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param scroll
	 *            If set to true the element will scroll into view if set to
	 *            false scrolling will be disabled
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementMouseHover(By byType, Boolean scroll) {
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				if (null != DriverManager.getDriverInstance().findElements(byType)) {
					element = DriverManager.getDriverInstance().findElement(byType);
					if (element.isDisplayed() && element.isEnabled()) {
						if (scroll) {
							((JavascriptExecutor) DriverManager.getDriverInstance())
							.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
						}
						actionBuilder = new Actions(DriverManager.getDriverInstance());
						actionBuilder.moveToElement(element).build();
						actionBuilder.perform();
						TestLogger.elementSelect("Successfully Mouse hover on element");
					}
				}
			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = "Mouseover is not working for " + byType;
			} else {
				errormessage = byType + " not found";
			}

			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method will help you to scroll vertically till end of the web-page
	 * 
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 */

	public void elementScrollVertical() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)
					DriverManager.getDriverInstance();
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception exceptionMessage) {
			errormessage = "Vertical scroll is not working";
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);

		}
	}

	/**
	 * This method will help you to scroll to a particular element in the
	 * web-page
	 * 
	 * @param locatorName
	 *            Is the name of the locator
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 */

	public boolean ScrollTillElementVisible(By byType) {
		boolean webelelementvisible = false;
		try {

			element = DriverManager.getDriverInstance().findElement(byType);
			js.executeScript("arguments[0].scrollIntoView();", element);
			if (element.isDisplayed()) {
				webelelementvisible = true;

				TestLogger.elementIdentifier(element.getText());
			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage =byType.toString();
			} else {
				errormessage = byType + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
		return webelelementvisible;
	}

	/**
	 * This method will help you to scroll to a particular element in the
	 * web-page
	 * 
	 * @param locatorName
	 *            Is the name of the locator
	 * @param xcoordinate
	 *            Is the variable is the number at x-axis, it moves
	 * @param ycordinate
	 *            Is the variable is the number at y-axis, it moves
	 */
	public void ScrollByPixel(int Xcoodinate, int ycoordinate) {

		try {

			for (int i = 0; i <= Xcoodinate; i = i + 50) {
				js.executeScript("scroll(" + i + ",0)");
			}
			for (int j = 0; j <= ycoordinate; j = j + 50) {
				js.executeScript("scroll(0," + j + ")");

			}
		} catch (Exception exceptionMessage) {
			errormessage = "Scroll is not happend to " + Xcoodinate + " and " + ycoordinate;
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}

	}

	/**
	 * This method is used to mandatory to set a 'check box' check or un-check
	 * using the Selenium WebDriver findElement(By) & WebDriverWait. This method
	 * will also log the locator path in the Log4J log file. boolean bChecked
	 * (true, false} provide final stage of check box {checked, Unchecked}
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to verify
	 * @param check
	 *            Is used to check or un-check the check box
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementSetCheckbox(String elementName, By byType, boolean check, 
			String locatorName) {
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				if (element.isSelected()) {
					if (!check) {
						element.click();
						if (elementName.isEmpty()) {
							TestLogger.elementCheckBoxIdentifier(
									"'" + element.getText() + "'" + "checkbox is identified and checkbox un-checked");
						} else {
							TestLogger.elementCheckBoxIdentifier(
									"'" + elementName + "'" + "checkbox is identified and checkbox un-checked");
						}
					}

				} else {

					if (check) {
						element.click();
						if (elementName.isEmpty()) {
							TestLogger.elementCheckBoxIdentifier(
									"'" + element.getText() + "'" + "checkbox is identified and checkbox checked");
						} else {
							TestLogger.elementCheckBoxIdentifier(
									"'" + elementName + "'" + "checkbox is identified and checkbox checked");
						}
					}
				}
			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = locatorName;
			} else {
				errormessage = locatorName + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method is used to interact with a web element by "Capturing text"
	 * using the Selenium WebDriver findElement(By), WebDriverWait & GetText. If
	 * the web element does not exist a screenshot will be taken and a timeout
	 * exception will be thrown. This method will also log the locator path in
	 * the Log4J log file.
	 * 
	 * @param byType
	 *            is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 * @return returns a string of text
	 */
	public String eget(WebElement ele){
		return ele.getText();

	}

	public String elementGetText(By byType) {
		String returnText = null;
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementIdentifier(element.getText());
					returnText = element.getText();
				}
			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
		return returnText;
	}

	/**
	 * This method is used to interact with a web element of type input by
	 * "Capturing the value" using the Selenium WebDriver findElement(By),
	 * WebDriverWait & GetAttribute("value"). If the web element does not exist
	 * a screenshot will be taken and a timeout exception will be thrown. This
	 * method will also log the locator path in the Log4J log file.
	 * 
	 * @param driver
	 *            is the WebDriver object being used for example (Browsers:
	 *            Chrome, FireFox or IE etc...)
	 * @param byType
	 *            is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 * @return returns a string of text equal to the value in the input box
	 */
	public String elementGetValue(By byType) {
		String returnText = null;
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				returnText = element.getAttribute("value");

			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
		return returnText;
	}

	/**
	 * This method is used to interact with a List by providing the optionValue
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * selectByVisibleText. If the web element does not exist a screenshot will
	 * be taken and a timeout exception will be thrown. This method will also
	 * log the locator path in the Log4J log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param optionValue
	 *            Is the string variable being passed that will be entered/typed
	 *            into a field
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementSelect(By byType, String optionValue) {

		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				new Select(element).selectByVisibleText(optionValue);

			} 
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method is used to interact with a List by providing the optionValue
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * selectByVisibleText. If the web element does not exist a screenshot will
	 * be taken and a timeout exception will be thrown. This method will also
	 * log the locator path in the Log4J log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param optionValue
	 *            Is the string variable being passed that will be entered/typed
	 *            into a field
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementCSSSelect(By byType, String optionValue) {

		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeSelected(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				new Select(element).selectByVisibleText(optionValue);
			} 
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method is return boolean value based on the value to availability
	 * and fields using the Selenium WebDriver findElement(By), WebDriverWait &
	 * elementToBeClickable. If the web element does not exist timeout exception
	 * will be thrown. This method will also log the locator path in the Log4J
	 * log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param value
	 *            Is the pass the value to element
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public boolean elementAvailability(By byType) {

		boolean elementIdentifier = false;
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				if (element.isDisplayed()) {
					elementIdentifier = true;
					TestLogger.elementIdentifier(byType.toString());
				} else {
					elementIdentifier = false;
					TestLogger.elementIdentifierFail("" + byType.toString());
				}
			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
		return elementIdentifier;
	}

	/**
	 * This method is used to submit the form data, if the element to be clicked
	 * is within the form. This method will submitted to remote server. It will
	 * wait till the new page gets loaded. It is using the Selenium WebDriver
	 * findElement(By), WebDriverWait & elementToBeClickable. If the web element
	 * does not exist timeout exception will be thrown. This method will also
	 * log the locator path in the Log4J log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementSubmit(By byType) {
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementClickIdentifier(element.getText());
					element.submit();
				}
			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method will help you to return the webElement
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public WebElement elementReturn(By byType) {
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			element = DriverManager.getDriverInstance().findElement(byType);
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
		return element;
	}

	/**
	 * This method is used to find the progress bar. If the web element does not
	 * exist timeout exception will be thrown. This method will also log the
	 * locator path in the Log4J log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementProgressBar(By byType) {

		try {
			boolean isProgressPresent = DriverManager.getDriverInstance().findElements(byType).size() > 0;
			if (isProgressPresent)
				new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime())
				.until(ExpectedConditions.invisibilityOfElementLocated(byType));
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method is used to find the progress bar. If the web element does not
	 * exist timeout exception will be thrown. This method will also log the
	 * locator path in the Log4J log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */
	public void elementDragdrop(By byType1,By byType2) {

		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeClickable(byType1)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType1);
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementIdentifier(byType1.toString());
					if (wait.until(ExpectedConditions.elementToBeClickable(byType2)) != null) {
						elementDestination = DriverManager.getDriverInstance().findElement(byType2);
						if (elementDestination.isDisplayed() && element.isEnabled()) {
							TestLogger.elementIdentifier(byType2.toString());
							Actions builder = new Actions(DriverManager.getDriverInstance());
							Action dragAndDrop = builder.clickAndHold(element).moveToElement(elementDestination).release(elementDestination).build();
							dragAndDrop.perform();
						} else {
							TestLogger.elementIdentifierFail("" + byType1.toString());
						}
					} else {
						TestLogger.elementIdentifierFail("" + byType2.toString());
					}				
				}}
		}catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType1.toString() + " and "+byType2.toString()+ " are not identified successfully";
			} else {
				errormessage = "source or target element not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	/**
	 * This method will help you to catch the exeception element in the web-page
	 * 
	 * @param element
	 *            Is the name of the variable used to store the web element
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param message
	 *            Is the exception received
	 * @param error
	 *            Is the error message which will be displayed in log
	 */

	public void elementCatch(WebElement element, long explicittime, Exception message, String error) {
		Reporter.log("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
				+ message.getMessage(), true);

		if (element != null) {
			if (element.getText().isEmpty()) {
				TestLogger.elementIdentifierFail("ErrorMessage: Web element " + element + " is not working");
				Assert.fail("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
						+ error);
			} else {
				TestLogger.elementIdentifierFail("ErrorMessage: Web element " + element.getText() + " is not working");
				Assert.fail("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
						+ element.getText());
			}

		} else {
			TestLogger.elementIdentifierFail(
					"ErrorMessage: " + error + "\n" + "Exception Message: " + message.getMessage());
			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + error);
		}

	}

	public void linkClick(String Link) {
		try {
			DriverManager.getDriverInstance().findElement(By.linkText(Link));
		}

		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getTitile() {
		return DriverManager.getDriverInstance().getTitle();
	}

	public void refreashPage() {
		DriverManager.getDriverInstance().navigate().refresh();
		sleep(2000);
	}



	public void waituntillelement(By byType) {

		try {
			String text= null;
			for(int i=1;i<=i+1;i++) {

				DriverManager.getDriverInstance().findElement(byType).wait();
				break;
			}


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean waitElementVisibleClick(By byType, long timeoutSeconds) throws InterruptedException {
		boolean visible = false;

		try {

			FluentWait<SearchContext> wait = new FluentWait(DriverManager.getDriverInstance()).withTimeout(timeoutSeconds, TimeUnit.SECONDS).ignoring(NotFoundException.class);
			visible = wait.until(elementVisible(byType));
			sleep(12000);
		} catch (TimeoutException e) {
			visible = false;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			elementClick(byType);
		}
		return visible;

	}
	
	public boolean waitElementToBeVisibleSendValue(By byType, long timeoutSeconds,String value) throws InterruptedException {
		boolean visible = false;

		try {

			FluentWait<SearchContext> wait = new FluentWait(DriverManager.getDriverInstance()).withTimeout(timeoutSeconds, TimeUnit.SECONDS).ignoring(NotFoundException.class);
			visible = wait.until(elementVisible(byType));
			Thread.sleep(12000);
		} catch (TimeoutException e) {
			visible = false;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			elementSendKey(byType, value);
		}
		return visible;

	}

	private static Function<SearchContext, Boolean> elementVisible(final By byType) {
		return new Function<SearchContext, Boolean>() {
			public Boolean apply(@Nonnull SearchContext context) {
				return context != null ? context.findElement(byType).isDisplayed() : false;
			}
		};
	}

	public boolean waitForMessage(By element, int waitDuration) {
		try {

			WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(),200);
			int time = waitDuration;
			long startTime = System.currentTimeMillis();
			while ((System.currentTimeMillis() - startTime) <= time) {
				try {
					if (null != element) {
						wait.until(ExpectedConditions.elementToBeClickable(element));
						wait.until(ExpectedConditions.visibilityOf((WebElement) element));
						wait.ignoring(NoSuchElementException.class);
						wait.ignoring(WebDriverException.class);
						return true;
					}
				} catch (Exception e1) {
					try {
						Thread.sleep(500);
					} catch (Exception e2) {

					}
				}
			}
		} catch (Exception e) {
			System.out.println("Element not found in specified duration ");
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		return false;
	}

	/**
	 * This method is used to interact with a web element by "Clicking on it"
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * elementToBeClickable. If the web element does not exist timeout exception
	 * will be thrown. This method will also log the locator path in the Log4J
	 * log file.
	 * 
	 * @param byType
	 *            Is the name of the variable used to store the locator path of
	 *            the web element to interact with
	 * @param explicittime
	 *            Is the time to wait for individual web element to exists
	 * @param locatorName
	 *            Is the name of the locator
	 */

	public void elementSetRadioButton(By byType) {
		try {
			Thread.sleep(1000);
			element = DriverManager.getDriverInstance().findElement(byType);
			if (element.isSelected()) {
				TestLogger.elementCheckBoxIdentifier(
						"'" + element.getText() + "'" + "radio button is identified  and same in selected state");						
			}
			else {
				element.click();
				TestLogger.elementCheckBoxIdentifier(
						"'" + element.getText() + "'" + "radio button is identified and selected ");
			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}
			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}
	}

	public void elementSendKey(By byType, String value) {
		try {
			wait = new WebDriverWait(DriverManager.getDriverInstance(), EnvironmentManager.getDelayTime());
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = DriverManager.getDriverInstance().findElement(byType);
				Actions builder = new Actions(DriverManager.getDriverInstance());
				Actions seriesOfAction = builder.moveToElement(element).click().sendKeys(element, value);
				seriesOfAction.perform(); 
				Thread.sleep(2000);

			}
		} catch (Exception exceptionMessage) {
			if (element != null) {
				errormessage = "sendkeys is not working for " + byType.toString();
			} else {
				errormessage = byType.toString() + " not found";
			}

			elementCatch(element, EnvironmentManager.getDelayTime(), exceptionMessage, errormessage);
		}

	}
	
	
	public void uploadFile(String filePath) {

		try {
			StringSelection stringSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.delay(250);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(150);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			System.out.println("Exception while trying to upload file "+filePath);
			e.printStackTrace();
			System.out.println(e);
		}
	}
	public String updateXpath(String dropdownList,String dropdownValue ) {
		String typeDropDownlist=dropdownList.replace("{}", dropdownValue);
		return typeDropDownlist;
	}
	
	public void sleep(long sec) {
		try {
			Thread.sleep(sec);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
		
	}
	
	public boolean compareValue(String Expected, String Actual) {
		
		if(Expected.equals(Actual)) {
			return true;
		}else {
			return false;
		}
	}

}
