package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage extends BaseTest{

WebDriver driver;

public PIMPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[text()='Employee Name']/../following-sibling::div//input")
	WebElement inptEmployeeName;
	
	@FindBy(xpath = "//div[@role='listbox']")
	WebElement predictionListBox;
	
	@FindBy(xpath = "//div[@role='option']/span[normalize-space()]")
	List<WebElement> predictionList;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnSearch;
	
	@FindBy(xpath = "//div[@class='oxd-table-body']/div")
	List<WebElement> recordsGroup;

	@FindBy(xpath = "//div[@class='oxd-table-card']")
	WebElement employeeRows;
	
	
	public int getNoOfRecords() {
		waitForElement(driver, employeeRows);
		int recordsCount = recordsGroup.size();
		return recordsCount;
	}
	
	public void enterEmployeeName (String name) {
		waitForElement(driver, inptEmployeeName);
		setValue(inptEmployeeName,name);
	}
	
	/**
	 * checks if value is present in the predicted list
	 * @param searchValue
	 * @return true if value is present in all predictions
	 */
	public boolean checkNameList(String searchValue) {
		int count = 0;
		int listCount = predictionList.size();
		for (WebElement Element : predictionList) {
			String name = Element.getText();
			if (name.toLowerCase().contains(searchValue)) {
				count++;
			}
		}
		if (count==listCount) {
			return true;
		}else {
			return false;
		}
	}
	
	public void clickEmployeeInfoSearch() {
		click(btnSearch);
	}
	
	/**
	 * gives the full name of all the records filtered by combining first name and last name
	 * @return full names in Arraylist
	 */
	public ArrayList<String> getFirstNameAndLastNameList() {
		
		int recordCount = getNoOfRecords();
		ArrayList <String> nameList = new ArrayList<String>();
		
		for (int i=1; i<=recordCount;i++) {
			WebElement firstName = employeeRows.findElement(By.xpath("(//div/div[@role='cell'][3])["+i+"]"));
			String fName = getText(firstName);
			WebElement lastName = employeeRows.findElement(By.xpath("(//div/div[@role='cell'][4])["+i+"]"));
			String lName = getText(lastName);
			String fullName = fName +" "+ lName;
			System.out.println("full Name of record "+i+" is "+fullName);
			nameList.add(fullName);
		}
		return nameList;
	}
}
