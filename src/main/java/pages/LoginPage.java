package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest{
	
	WebDriver driver;
	
	public LoginPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement inptUserName;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement inptPassword;
	
	@FindBy(xpath = "//button[normalize-space(text()='Login')]")
	WebElement btnlogin;
	
	public void enterUsername (String username) {
		waitForElement(driver,btnlogin);
		setValue(inptUserName,username);
	}
	
	public void enterPassword(String password) {
		setValue(inptPassword,password);
	}
	
	public void clickLogin() {
		click(btnlogin);
	}
}
