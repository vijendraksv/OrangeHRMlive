package testPages;

import java.io.IOException;
import java.util.ArrayList;

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

public class PIMPageTest extends BaseTest {

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
		public void verifyUserSearchPartialText() throws IOException {
			homePage.clickonMenu("PIM");
			pimPage.enterEmployeeName("ch");
			wait(5);

			softAssert.assertTrue(pimPage.checkNameList("ch"),"Prediction List does not have entered value");
			pimPage.clickEmployeeInfoSearch();
			
			int records = pimPage.getNoOfRecords();
			if (records<1) {
				Assert.fail();
			}
			
			ArrayList<String> recordsNames = pimPage.getFirstNameAndLastNameList();
			int count = 0;
			for (int i=0; i< records;i++) {
				if (recordsNames.get(i).toLowerCase().contains("ch")) {
					count++;
				}
			}
			Assert.assertEquals(count, records,"All the records dont have string ch in First name or Last name");
			softAssert.assertAll();
		}
}
