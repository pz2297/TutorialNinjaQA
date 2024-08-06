package com.ninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.base.baseClass;
import com.ninja.qa.pages.AccountSuccessPage;
import com.ninja.qa.pages.HomePage;
import com.ninja.qa.pages.RegisterPage;
import com.ninja.qa.utiles.Utilites;

public class RegisterTest extends baseClass{
	
	RegisterPage registerPage;
	AccountSuccessPage accSuccPage;
	public WebDriver driver;	

	@BeforeMethod
	public void setUp() throws Exception {
		loadPropertiesFile();
		driver= initializeBrowser(prop.getProperty("browser"));
		HomePage homePage= new HomePage(driver);
		registerPage= homePage.naviagateToRegister();
	}
	
	@AfterMethod
	public void tearDown() {
	driver.quit();
	}
	
	@Test(priority=1)
	public void validRegisteration() throws Exception{
			accSuccPage= registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilites.generateTimeStamp(), 
				dataProp.getProperty("telephone"), prop.getProperty("validPass"), prop.getProperty("validPass"));
	String actualMessage= accSuccPage.retriveAccSuccPageHeading();
	
	Assert.assertEquals(actualMessage, dataProp.getProperty("accSuccessfullyCreatedHeading"), "Account success page is not displayed");
	
	driver.findElement(By.linkText("Continue")).click();
	}
	
	@Test(priority=2)
	public void validRegisterationWithExisitngEmail() {
	
	registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
			prop.getProperty("validEmail"), dataProp.getProperty("telephone"), prop.getProperty("validPass"), prop.getProperty("validPass"));
	String actualWarningM=registerPage.retriveDuplicateEmailAddWarning();
	Assert.assertTrue(actualWarningM.contains(dataProp.getProperty("duplicateEmailWarning")), "expected message is incorrect");
	
	
	}
	@Test(priority=3)
		public void validRegisterationWithoutFields() {
		
		registerPage.clickContinueOption();
		
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), 
				dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailidWarning"), dataProp.getProperty("phoneWarning"), dataProp.getProperty("passWarning")));
		
		/*
			Assert.assertTrue(registerPage.retrivePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")), "Privancy statement is incorrect");
	
		//String actualFirstnameWar = registerPage.retriveFirstNameWarning();
		Assert.assertTrue(registerPage.retriveFirstNameWarning().contains(dataProp.getProperty("firstNameWarning")), "Firstname warning is incorrect");
		
		Assert.assertTrue(registerPage.retriveLastNameWarning().contains(dataProp.getProperty("lastNameWarning")), "Lastname warning is incorrect");
		
		Assert.assertTrue(registerPage.retriveEmailWarning().contains(dataProp.getProperty("emailidWarning")), "Emailid warning is incorrect");
		
		Assert.assertTrue(registerPage.retriveTelephoneWarning().contains(dataProp.getProperty("phoneWarning")), "Phone warning is incorrect");
		
		Assert.assertTrue(registerPage.retrivePasswordWarning().contains(dataProp.getProperty("passWarning")), "password warning is incorrect");
		*/
			
			
		}
	}
