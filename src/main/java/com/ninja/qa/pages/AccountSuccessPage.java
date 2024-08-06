package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;
	//Objects
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement accSucessPageHeading;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retriveAccSuccPageHeading () {
		String accSuccPageHeadingText= accSucessPageHeading.getText();
		return accSuccPageHeadingText;
	}
}
