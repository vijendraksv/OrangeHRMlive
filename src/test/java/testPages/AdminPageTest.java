package testPages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BaseTest;
import pages.Utils;
import pages.LoginPage;
import pages.AdminPage;
import pages.HomePage;
import pages.PIMPage;

public class AdminPageTest extends BaseTest  {

	LoginPage loginPage;
	AdminPage adminPage;
	HomePage homePage;
	PIMPage pimPage;
	SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	public void initiate() throws IOException {
		WebDriver driver = WebDriverManager.chromedriver().create();
		loginPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
		homePage = new HomePage(driver);
		pimPage = new PIMPage(driver);
	
		goToUrl(driver, Utils.getURL());
		loginPage.enterUsername(Utils.getUserName());
		loginPage.enterPassword(Utils.getPassword());
		loginPage.clickLogin();
	}

	@Test
	public void checkCurrencyInAdmin() throws IOException {
		homePage.clickonMenu("Admin");
		
		adminPage.clickCategoryDropdown("Job");
		adminPage.selectJobCategory("Pay Grades");
		
		adminPage.clickPayGradeAddButton();
		adminPage.enterPayGradeName("Indian Rupee");
		adminPage.clickSavePayGrade();
		
		adminPage.clickCurrencyAddButton();
		adminPage.clickCurrencyDropdown();
		adminPage.selectCurrencyValue("Indian Rupee");
		adminPage.enterMinSalary("30000");
		adminPage.enterMaxSalary("100000");
		adminPage.clickSaveCurrency();
		wait(5);
		
		int recordsCurr = adminPage.getNoOfRecords();
		if (recordsCurr<1 && adminPage.checkAddedRecords()) {
			Assert.fail();
		}
	}
}
