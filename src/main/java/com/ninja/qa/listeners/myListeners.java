package com.ninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ninja.qa.utiles.ExtentReporter;
import com.ninja.qa.utiles.Utilites;

public class myListeners implements ITestListener {
	
	ExtentReports extentReports;
	ExtentTest extentTest;
	String testName;

	@Override
	public void onStart(ITestContext context) {
		extentReports= ExtentReporter.generateExtentReport();	
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName= result.getName();
		 extentTest = extentReports.createTest(testName);
		extentTest.log(Status.INFO, testName+ "started executing");
			}

	@Override
	public void onTestSuccess(ITestResult result) {
			extentTest.log(Status.PASS, testName+ "got sucessfully executed");
		}

	@Override
	public void onTestFailure(ITestResult result ) {
			WebDriver driver= null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		String desSS= Utilites.captureScreenshot(driver, testName);
		
		extentTest.addScreenCaptureFromPath(desSS);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+  "got failed");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
			extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+  "got skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
		
		String pathOfExtentReport= System.getProperty("user.dir")+"\\test-output\\Extent Report\\extentReport.html";
		File extentReport= new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
