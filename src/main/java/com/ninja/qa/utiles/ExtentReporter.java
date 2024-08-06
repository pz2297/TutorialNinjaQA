package com.ninja.qa.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReports= new ExtentReports();
		File extentReportFile= new File(System.getProperty("user.dir")+"\\test-output\\Extent Report\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Test Automation Result");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReports.attachReporter(sparkReporter);
		
		Properties configProp= new Properties();
		File configProFile= new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\ninja\\qa\\config\\config.properties");
		try {
		FileInputStream fisConfigProp= new FileInputStream(configProFile);
		configProp.load(fisConfigProp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		extentReports.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReports.setSystemInfo("Browser Name",configProp.getProperty("browser"));
		extentReports.setSystemInfo("Email",configProp.getProperty("validEmail"));
		extentReports.setSystemInfo("Password",configProp.getProperty("validPass"));
		extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReports.setSystemInfo("Username", System.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));

		
		return extentReports;
	}

}
