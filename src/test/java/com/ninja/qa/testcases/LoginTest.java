package com.ninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninja.qa.base.baseClass;
import com.ninja.qa.pages.AccountPage;
import com.ninja.qa.pages.HomePage;
import com.ninja.qa.pages.LoginPage;
import com.ninja.qa.utiles.Utilites;

public class LoginTest extends baseClass{
	
	LoginPage loginPage;
	AccountPage accPage;
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws Exception {
		loadPropertiesFile();
		driver= initializeBrowser(prop.getProperty("browser"));
		HomePage homePage= new HomePage(driver);
		loginPage= homePage.naviagateToLogin();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
@Test (priority=1, dataProvider="validCredentials")
public void verifyLoginWithValidCredentials(String email, String pass) {
	accPage= loginPage.Login(email, pass);
		Assert.assertTrue(accPage.getDisplayStatusOfEditUrAccInfoOption(), "Edit ur info option is not displayed");
	}
	
	@DataProvider(name="validCredentials")
	public Object[][] supplyTestData() {
		
		//Object [][] data= {{"abcTest24@gmail.com", "12345"}, {"xyzTest24@gmail.com", "12345"}, {"pqrTest24@gmail.com", "12345"}};
		
		Object[][] data= Utilites.getTestDataFromExcel("Login");
		
		return data;
		
	}
	
	@Test (priority=2)
	public void verifyLoginWithInvalidCredentials() {
		accPage= loginPage.Login(Utilites.generateTimeStamp(), dataProp.getProperty("invalidPass"));
		String expWm=dataProp.getProperty("MismatchEmailPassWarning");
		Assert.assertTrue(loginPage.retriveEmailPassNotMatchingWarMsg().contains(expWm), "expected message is incorrect");
	}
	
	@Test (priority=3)
	public void verifyLoginWithInvalidEmailIdAndValidPasswrd() {
		accPage= loginPage.Login(Utilites.generateTimeStamp(), prop.getProperty("validPass"));
		
		Assert.assertTrue(loginPage.retriveEmailPassNotMatchingWarMsg().contains(dataProp.getProperty("MismatchEmailPassWarning")), "expected message is incorrect");
	
		
	}
	@Test (priority=4)
	public void verifyLoginWithValidEmailIdAndInValidPasswrd() {
		accPage= loginPage.Login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPass"));
			Assert.assertTrue(loginPage.retriveEmailPassNotMatchingWarMsg().contains(dataProp.getProperty("MismatchEmailPassWarning")), "expected message is incorrect");
	
		}
	
	@Test (priority=5)
	public void verifyLoginWithoutProvidingCredentails() {
		//driver.findElement(By.id("input-email")).sendKeys("");
		//driver.findElement(By.id("input-password")).sendKeys("");
		loginPage.clickOnLoginButton();
	Assert.assertTrue(loginPage.retriveEmailPassNotMatchingWarMsg().contains(dataProp.getProperty("MismatchEmailPassWarning")), "expected message is incorrect");
	
		}
	
	
}
