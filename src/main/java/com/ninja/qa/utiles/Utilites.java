package com.ninja.qa.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Utilites {
	
	public static final int implictWait= 30;
	public static final int pageLoad=30;
	
	public static String generateTimeStamp() {
		Date date= new Date();
		String timestamp= date.toString().replace(" ", "_").replace(":", "_");
		return "abcT86736"+timestamp+"@gmail.com"; 
		
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
		File excelFile= new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\ninja\\qa\\testdata\\ninjaTestdata.xlsx");
		XSSFWorkbook workbook= null;
		try {
		FileInputStream fisExcel= new FileInputStream(excelFile);
		workbook= new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.getStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int row= sheet.getLastRowNum();
		int col= sheet.getRow(0).getLastCellNum();
		
		Object[][] data= new Object[row][col];
		
		for (int i=0; i<row; i++) {
			
			XSSFRow rows= sheet.getRow(i+1);
				for(int j=0;j<col;j++) {
					XSSFCell cell=rows.getCell(j);
					CellType cellType= cell.getCellType();
					
					switch(cellType) {
					case STRING:
						data[i][j]= cell.getStringCellValue();
						break;
					case NUMERIC:
						data[i][j]= Integer.toString((int)cell.getNumericCellValue());
						break;
						
					case BOOLEAN:
					data[i][j]= cell.getBooleanCellValue();
					break;
					

					}
		}
		
		
	}
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName)
	{
	
	File srcSS= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String desSS= System.getProperty("user.dir")+ "\\Screenshot\\"+testName+".png";
	
	try {
		FileHandler.copy(srcSS, new File (desSS));
	} catch (IOException e) {
			e.printStackTrace();
	}
	return desSS;
}
}

