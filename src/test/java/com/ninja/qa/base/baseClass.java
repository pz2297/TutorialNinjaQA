package com.ninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninja.qa.utiles.Utilites;

public class baseClass {
	
	WebDriver driver;	
	public Properties prop;
	public Properties dataProp;
	
		public void loadPropertiesFile() throws Exception {
		
	prop= new Properties();
		File proFile= new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\ninja\\qa\\config\\config.properties");
		FileInputStream fis= new FileInputStream(proFile);
		prop.load(fis);
		
		dataProp= new Properties();
		File dataproFile= new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\ninja\\qa\\testdata\\testdata.properties");
		FileInputStream fis2= new FileInputStream(dataproFile);
		dataProp.load(fis2);
		
	}
	
	public WebDriver initializeBrowser (String browserName) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pragati\\eclipse-workspace\\chromedriver.exe");		
				
			if(browserName.equals("chrome")) {
				driver= new ChromeDriver();
			}
			else if (browserName.equals("firefox")) {
				driver= new FirefoxDriver();	
			}
			else if (browserName.equals("edge")){
				driver= new EdgeDriver();
		}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Utilites.implictWait, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Utilites.pageLoad, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
					
			return driver;
		
	}

}
