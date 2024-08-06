package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	//Objects
	@FindBy(linkText="Edit your account information")
	private WebElement editUrAccountInfoOption;

public AccountPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}

public boolean getDisplayStatusOfEditUrAccInfoOption() {
	boolean displayStatus= editUrAccountInfoOption.isDisplayed();
	return displayStatus;
}
}