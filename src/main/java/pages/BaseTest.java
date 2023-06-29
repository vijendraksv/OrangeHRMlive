package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseTest {
	protected WebDriver driver;
	
	/**
	 * this method will wait in seconds
	 * @param seconds
	 */
	public static void wait(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method will maximize the window
	 * @param driver
	 */
	public	static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * this method will navigate to the given URL
	 * @param driver
	 * @param URL
	 */
	public static void goToUrl(WebDriver driver, String url) {
		driver.get(url);
		maximizeWindow(driver);
	}
	
	/**
	 * This method Click on the element
	 * @param element
	 */
	protected void click(WebElement element) {
		try {
			if (element != null)
				element.click();
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will enter the value given into input box
	 * @param element
	 * @param value
	 */
	public void setValue(WebElement element, String value) {
		try {
			if (element != null)
				element.sendKeys(value);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will get the text of the element
	 * @param element
	 * @param value
	 */
	public String getText(WebElement element) {
		String text = "";
		try {
			if (element != null)
				text = element.getText();
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	 /**
	 * This method will check if element is displayed
	 * @param element
	 */
	public boolean isVisible(WebElement element) {
		boolean isVisible = false;
		try {
			if (element != null) {
				isVisible = element.isDisplayed();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
		return isVisible;
	}
	
	/**
	 * This method will return no of elements
	 * @param element
	 */
	public int getElementCount(List<WebElement> element) {
		return element.size();
	}
	
	/**
	 * waits for webelement to be displayed
	 * @param element
	 * @param maxinum time to wait
	 */
	public void waitForElement(WebDriver driver, WebElement element) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(NoSuchElementException e){
			e.printStackTrace();
		}
	}
}
