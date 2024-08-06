package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	//Objects
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@name='newsletter' and @value='1']")
	private WebElement yesNewSletterOption;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement policyCheckboxField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddWarMsg;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarMsg;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;	
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Action/methods
	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneNo) {
		telephoneField.sendKeys(telephoneNo);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}
	
	public void selectYesNewssletterOption() {
		yesNewSletterOption.click();
	}
	
	public void selectPrivacyPolicy() {
		policyCheckboxField.click();
	}
	
	public AccountSuccessPage clickContinueOption() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstName, String lastName, String emailText, String telephoneNo, String password, String confirmPassword){
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneNo);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		yesNewSletterOption.click();
		policyCheckboxField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String retriveDuplicateEmailAddWarning () {
		String duplicateEmailWarningText= duplicateEmailAddWarMsg.getText();
		return duplicateEmailWarningText;
}
	public String retrivePrivacyPolicyWarning () {
		String privacyPolicyWarningText= privacyPolicyWarMsg.getText();
		return privacyPolicyWarningText;
}
	public String retriveFirstNameWarning () {
		String firstNameWarningText= firstNameWarning.getText();
		return firstNameWarningText;
}
	public String retriveLastNameWarning () {
		String lastNameWarningText= lastNameWarning.getText();
		return lastNameWarningText;
}
	
	public String retriveEmailWarning () {
		String emailWarningText= emailWarning.getText();
		return emailWarningText;
}
	public String retriveTelephoneWarning () {
		String telephoneWarningText= telephoneWarning.getText();
		return telephoneWarningText;
}
	public String retrivePasswordWarning () {
		String passwordWarningText= passwordWarning.getText();
		return passwordWarningText;
}
	
	public  boolean displayStatusOfWarningMessages (String expectPrivacyPolicyWar, String expectedFirstNameWar, String expectedLastNameWar, String expectedEmailWar,
			String expectedTelephoneWar, String expectedPassWar) {
		
		//String actualPrivacyPolicyWarningText= privacyPolicyWarMsg.getText() ;
		boolean privacyPolicyStatusWarningStatus= privacyPolicyWarMsg.getText().contains(expectPrivacyPolicyWar);
		
		//String actaulFirstNameWarningText= firstNameWarning.getText();
		Boolean firstNameWarningStatus= firstNameWarning.getText().equals(expectedFirstNameWar);
		
		//String actualLastNameWarningText= lastNameWarning.getText();
		boolean lastNameWarningStatus= lastNameWarning.getText().equals(expectedLastNameWar);
		
		//String actualEmailWarningText= emailWarning.getText();
		boolean emailWarningStatus= emailWarning.getText().equals(expectedEmailWar);
		
		//String actualTelephoneWarningText= telephoneWarning.getText();
		boolean telephoneWarningStatus= telephoneWarning.getText().equals(expectedTelephoneWar);
		
		//String actualPasswordWarningText= passwordWarning.getText();
		boolean passwordWarningStatus= passwordWarning.getText().equals(expectedPassWar);
		
		return privacyPolicyStatusWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
	}
}

