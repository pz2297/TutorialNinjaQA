package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	//Objects
	@FindBy(linkText="HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	public boolean displayStatusOfHPProduct() {
		boolean displayStatus= validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retriveNoProductMessageText () {
		String noProductMsgText= noProductMessage.getText();
		return noProductMsgText;
}
}
