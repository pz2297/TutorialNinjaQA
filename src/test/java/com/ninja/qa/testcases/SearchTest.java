package com.ninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.base.baseClass;
import com.ninja.qa.pages.HomePage;
import com.ninja.qa.pages.SearchPage;

public class SearchTest extends baseClass {
	SearchPage searchPage;
	HomePage homePage;
	
	public WebDriver driver;	
	
	@BeforeMethod
	public void setUp() throws Exception {
		loadPropertiesFile();
	driver= initializeBrowser(prop.getProperty("browser"));
	homePage= new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
	driver.quit();
	}
	
	@Test (priority=1)
		public void searchValidProduct() {
		
		searchPage= homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		//homePage.enterSearch(dataProp.getProperty("validProduct"));
		//driver.findElement(By.xpath("//button[contains(@class, 'btn btn-default btn-lg')]")).click();
		//searchPage= homePage.clickOnSearchButton();
		 //searchPage= new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusOfHPProduct(), "Valid HP product is not diplayed");
				
		}
	@Test (priority=2)
	public void  verifyWithInvalidProduct() {
		
		searchPage= homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		//homePage.enterSearch(dataProp.getProperty("invalidProduct"));
		//searchPage= homePage.clickOnSearchButton();
		//String actualM= searchPage.retriveNoProductMessageText();
		//Assert.assertEquals(searchPage.retriveNoProductMessageText(), dataProp.getProperty("NoProductSearchMsg"), "No product in search result");
		Assert.assertEquals(searchPage.retriveNoProductMessageText(), "abcd", "No product in search result");
		
	}
	
	@Test (priority=3, dependsOnMethods= {"verifyWithInvalidProduct"})
	public void  verifySearchWithoutProductname() {
		//driver.findElement(By.name("search")).sendKeys("");
			searchPage= homePage.clickOnSearchButton();
				//String actualM= searchPage.retriveNoProductMessageText();
		Assert.assertEquals(searchPage.retriveNoProductMessageText(), dataProp.getProperty("NoProductSearchMsg"), "No product in search result");
	}
}
