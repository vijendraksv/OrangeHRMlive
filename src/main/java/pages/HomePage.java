package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest{

	WebDriver driver;
	
	public HomePage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//span[text()='Admin']")
	WebElement AdminMenu;
	
	private static final String XPATH_MENU_BAR = "//span[text()='%s']";
	
	public void clickonMenu(String menuValue) {
		try{
			waitForElement(driver,AdminMenu);
			click(driver.findElement(By.xpath(String.format(XPATH_MENU_BAR, menuValue))));
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
		
}
