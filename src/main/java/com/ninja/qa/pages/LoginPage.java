package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	//Objects
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement emailPassNoMatchingWarningMsg;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterEmailAddress(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterPassword(String pwdText) {
		passwordField.sendKeys(pwdText);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage Login(String emailText, String pwdText ) {
		emailField.sendKeys(emailText);
		passwordField.sendKeys(pwdText);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retriveEmailPassNotMatchingWarMsg () {
		String warText= emailPassNoMatchingWarningMsg.getText();
		return warText;
	}
}
