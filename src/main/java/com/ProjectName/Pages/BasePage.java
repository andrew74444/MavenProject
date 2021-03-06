package com.ProjectName.Pages;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ProjectName.ExtentListeners.ExtentListeners;
import com.ProjectName.utilities.DriverManager;
import com.ProjectName.utilities.JavaScript;
import com.ProjectName.utilities.RobotClass;
import com.aventstack.extentreports.MediaEntityBuilder;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public abstract class BasePage<T> {

	protected WebDriver driver;
	
	private long LOAD_TIMEOUT = 10;
	private int AJAX_ELEMENT_TIMEOUT = 150;
	public int expTime = 15;
	public Logger log = Logger.getLogger(BasePage.class);
	protected JavascriptExecutor exe;
	protected Robot robot;
	protected WebDriverWait wait;	
	
	public BasePage() {
		this.driver = DriverManager.getDriver();
		this.exe = JavaScript.getJavaScriptObject();
		
		this.robot = RobotClass.getRobotClassObject();
		
	}

	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();
			AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
			page = PageFactory.initElements(driver, clazz);
			PageFactory.initElements(ajaxElemFactory, page);
			ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
			waitForPageToLoad(pageLoadCondition);
			 ((BasePage) page).getPageScreenSot();
		} catch (NoSuchElementException e) {
			/*
			 * String error_screenshot = System.getProperty("user.dir") +
			 * "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
			 * this.takeScreenShot(error_screenshot);
			 */ throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}

	protected abstract void getPageScreenSot();
	
	protected abstract ExpectedCondition getPageLoadCondition();
	
	public String screenshotName;
	
	public void aShot() {
		try {
			

				Screenshot fpScreenshot;

				fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
						.takeScreenshot(DriverManager.getDriver());

				Date d = new Date();
				screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
				try {
					ImageIO.write(fpScreenshot.getImage(), "PNG",
							new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
				} catch (IOException e) {

					e.printStackTrace();
				}
				try {
					ExtentListeners.testReport.get().info(
							"<b>" + "<font color=" + "yellow>" + "Screenshot of new Page Navigation" + "</font>" + "</b>",
							MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());

					// ExtentListeners.testReport.get().info("<b>" + "<font color=" + "yellow>" +
					// "Screenshot of new Page Navigation" + "</font>" + "</b>"+"<p>" + "<a
					// target=\"_blank\" href='" + screenshotName
					// + "'><img src='" + screenshotName +"'width='10%'/></a>" + "</p>");

				} catch (Exception e) {

				}

				scrollUpVertically();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		} catch (Exception e) {
					// TODO: handle exception
				}
			}
	
	public void click(WebElement element, String elementName) {

		ExtentListeners.testReport.get().info("Clicking on : " + elementName);
		log.info("Clicking on " + elementName);
		highlightElement(element);
		element.click();
		log.info("Clicked on " + elementName);
		ExtentListeners.testReport.get().info("Clickied on : " + elementName);

	}

	public void type(WebElement element, String elementName, String value) {

		ExtentListeners.testReport.get().info("Entering text " + value + " in : " + elementName);
		log.info("Text entering in " + elementName + " and the value is " + value);
		element.sendKeys(value);
		log.info("Text entered in " + elementName + " and the value is " + value);
		ExtentListeners.testReport.get().info("Entered text " + value + " in : " + elementName);

	}

	public void selectByVisibleText(WebElement element, String value, String elementName) {
		log.info("Selecting the +" + elementName + "+ value as : " + value);
		ExtentListeners.testReport.get().info("Selecting the " + elementName + " value as : " + value);
		Select sel = new Select(element);
		// highlightElement(element);
		sel.selectByVisibleText(value);
	}

	public void selectUsingIndex(WebElement element, int index, String elementName) {
		Select select = new Select(element);
		ExtentListeners.testReport.get().info("Selecting the " + elementName + " index at : " + index);
		log.info("selectUsingIndex and index is: " + index);
		select.selectByIndex(index);
	}

	public void selectUsingByValue(WebElement element, String value, String elementName) {
		Select select = new Select(element);
		ExtentListeners.testReport.get().info("Selecting the " + elementName + " value as : " + value);
		log.info("selectUsingIndex and value is: " + value);
		select.selectByValue(value);
	}

	public Object executeScript(String script, Object... args) {

		return exe.executeScript(script, args);

	}

	/**
	 * This method will click on element
	 * 
	 * @param element
	 */
	public void clickElementByJavaScript(WebElement element) {
		highlightElement(element);
		executeScript("arguments[0].click();", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	
	//https://www.guru99.com/keyboard-mouse-events-files-webdriver.html
	public void moveToElement(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).perform();
	}

	public void highlightElement(WebElement element) {
		exe.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	public void scrollToElement(WebElement element) {
		exe.executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
				element.getLocation().y);
	}

	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("element is clicked: " + element.toString());
	}

	public void TypeByJavaScript(String Value, WebElement element) {
		executeScript("arguments[0].value='" + Value + "';", element);

	}

	/**
	 * Scroll till element view
	 * 
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		log.info("scroll till web " + element);
		executeScript("arguments[0].scrollIntoView()", element);
	}

	/**
	 * Scroll till element view and click
	 * 
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("element is clicked: " + element.toString());

	}

	/**
	 * This method will scroll down vertically
	 */
	public void scrollDownVertically() {
		log.info("scrolling down vertically...");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * This method will scroll up vertically
	 */
	public void scrollUpVertically() {
		log.info("scrolling up vertically...");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}

	/**
	 * This method will scroll till given pixel (e.g=1500)
	 * 
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBY(0," + pixel + ")");
	}

	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBY(0,-" + pixel + ")");
	}

	/**
	 * This method will zoom screen by 100%
	 */
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	/**
	 * This method will zoom screen by 60%
	 */
	public void zoomInBy60Percentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void setAttribute(WebElement element, String value, String attribute) {
		// System.out.println("value is "+value);
		executeScript("arguments[0].setAttribute('" + attribute + "','" + value + "');", element);
	}

	public String getAttributeValue(WebElement element, String name) {
		// System.out.println("value is "+value);
		return element.getAttribute(name);

	}

	public void switchToIframeByIndex(int x) {
		driver.switchTo().frame(x);

	}

	public void switchTOIframeByName(String name) {

		driver.switchTo().frame(name);
		
	}
	
	public void switchTodefaultContent() {
		DriverManager.getDriver().switchTo().defaultContent();
	}
	
	

	public void switchTOIframeByElement(WebElement element) {
		driver.switchTo().frame(element);
	}

	
	
	
	
	// Alerts

		public Alert getAlert() {
			log.info("alert test: " + DriverManager.getDriver().switchTo().alert().getText());
			return DriverManager.getDriver().switchTo().alert();
		}

		public void acceptAlert() {
			getAlert().accept();
			log.info("accept Alert is done...");
		}

		public void dismissAlert() {
			getAlert().dismiss();
			log.info("dismiss Alert is done...");
		}

		public String getAlertText() {
			String text = getAlert().getText();
			log.info("alert text: " + text);
			return text;
		}

		public boolean isAlertPresent() {
			try {
				DriverManager.getDriver().switchTo().alert();
				log.info("alert is present");
				return true;
			} catch (NoAlertPresentException e) {
				log.info(e.getCause());
				return false;
			}
		}

		public void acceptAlertIfPresent() {
			if (isAlertPresent()) {
				acceptAlert();
			} else {
				log.info("Alert is not present..");
			}
		}

		public void dismissAlertIfPresent() {
			if (isAlertPresent()) {
				dismissAlert();
			} else {
				log.info("Alert is not present..");
			}
		}

		public void acceptPrompt(String text) {
			if (isAlertPresent()) {
				Alert alert = getAlert();
				alert.sendKeys(text);
				alert.accept();
				log.info("alert text: " + text);
			}
		}
	
		private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
			wait = new WebDriverWait(driver, LOAD_TIMEOUT);
			wait.until(pageLoadCondition);
		}
	
	
	
	
		protected void waitForElementToPresent(WebElement element) {
			wait = new WebDriverWait(driver, LOAD_TIMEOUT);
			log.info("waiting for :" + element.toString() + " for :" + expTime + " seconds");		
			wait.until(ExpectedConditions.visibilityOf(element));	
			
			
		}

	
	
	
	
	
	
}
