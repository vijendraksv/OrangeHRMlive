package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends BaseTest {

WebDriver driver;
	
	public AdminPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Job']")
	WebElement jobDropdown;
	
	@FindBy(xpath = "//ul [@class='oxd-dropdown-menu']/li/a[text()='Pay Grades']")
	WebElement payGradesOption;
	
	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement btnAdd;
	
	@FindBy(xpath = "//label[text()='Name']/../following-sibling::div//input")
	WebElement inptAddPayGradeName;
	
	@FindBy(xpath = "//*[text()='Add Pay Grade']/..//button[@type='submit']")
	WebElement btnPayGradeSave;
	
	@FindBy(xpath = "//*[text()='Add Currency']/..//button[@type='submit']")
	WebElement btnAddCurrencySave;
	
	@FindBy(xpath = "//div[@class='oxd-select-text-input']")
	WebElement currencyDropDown;
	
	@FindBy(xpath = "//label[text()='Minimum Salary']/../following-sibling::div//input")
	WebElement inptMinSalary;
	
	@FindBy(xpath = "//label[text()='Maximum Salary']/../following-sibling::div//input")
	WebElement inptMaxSalary;
	
	@FindBy(xpath = "//div[@class='oxd-table-card']")
	WebElement currencyRows;
	
	@FindBy(xpath = "//div[@class='oxd-table-body']/div")
	List<WebElement> recordsGroup;
	
	private static final String XPATH_CURRENCY_VALUE = "//div[@class='oxd-select-option']/span[contains(text(),'%s')]";
	private static final String XPATH_CATEGORY_DROPDOWN = "//span[normalize-space()='%s']";
	private static final String XPATH_JOB_DROPDOWN = "//ul[@class='oxd-dropdown-menu']/li/a[text()='%s']";
	
	
	public int getNoOfRecords() {
		int recordsCount = recordsGroup.size();
		return recordsCount;
	}
	
	public void clickCategoryDropdown (String Category) {
		try{
			waitForElement(driver,jobDropdown);
			click(driver.findElement(By.xpath(String.format(XPATH_CATEGORY_DROPDOWN, Category))));
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void selectJobCategory (String Section) {
		try{
			waitForElement(driver, payGradesOption);
			click(driver.findElement(By.xpath(String.format(XPATH_JOB_DROPDOWN, Section))));
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void clickPayGradeAddButton() {
		waitForElement(driver,btnAdd);
		click(btnAdd);
	}
	
	public void enterPayGradeName (String PayGrade) {
		waitForElement(driver,inptAddPayGradeName);
		setValue(inptAddPayGradeName,PayGrade);
	}
	
	public void clickSavePayGrade () {
		click(btnPayGradeSave);
	}
	
	public void clickCurrencyAddButton() {
		waitForElement(driver,btnAdd);
		click(btnAdd);
	}
	
	public void clickCurrencyDropdown() {
		waitForElement(driver,currencyDropDown);
		click(currencyDropDown);
	}
	
	public void selectCurrencyValue (String currencyValue) {
		try{
			click(driver.findElement(By.xpath(String.format(XPATH_CURRENCY_VALUE, currencyValue))));
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void enterMinSalary(String minSalary) {
		setValue(inptMinSalary,minSalary);
	}
	
	public void enterMaxSalary(String maxSalary) {
		setValue(inptMaxSalary,maxSalary);
	}
	
	public void clickSaveCurrency() {
		click(btnAddCurrencySave);
	}
	
	public boolean checkAddedRecords() {
		return isVisible(currencyRows);
	}
}
