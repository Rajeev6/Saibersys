package seleniumpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Workbook;

public class GmailLogin {
	
public static void main(String args[]) throws Exception
{
    File file= new File("//Users//Rajeev//Desktop//Login.xlsx");
    FileInputStream fis= new FileInputStream(file);
    XSSFSheet sheet;
    XSSFWorkbook workbook= new XSSFWorkbook(fis);
    int noOfSheets=workbook.getNumberOfSheets();
    int flag=0;
    CellStyle styleInvalid = workbook.createCellStyle();
    styleInvalid.setFillForegroundColor(IndexedColors.RED.getIndex());
    styleInvalid.setFillPattern(CellStyle.SOLID_FOREGROUND);
    CellStyle styleValid = workbook.createCellStyle();
    styleValid.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    styleValid.setFillPattern(CellStyle.SOLID_FOREGROUND);
    while(noOfSheets!=0)
    {
    	sheet= workbook.getSheetAt(flag);
    	sheet= login(sheet,styleValid,styleInvalid);
    	noOfSheets--; flag++;
    }
    FileOutputStream fos= new FileOutputStream(file);
	workbook.write(fos);
	workbook.close();
}

public static XSSFSheet login(XSSFSheet xlSheet,CellStyle styleValid,CellStyle styleInvalid ) throws InterruptedException, FileNotFoundException
{
	WebDriver driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.get("http://www.gmail.com");
    String email,passwd;
    int rows=xlSheet.getLastRowNum();
    
    
	for(int i=1;i<xlSheet.getLastRowNum()+1;i++)
	{
		 email=xlSheet.getRow(i).getCell(0).getStringCellValue();
		 passwd=xlSheet.getRow(i).getCell(1).getStringCellValue();
		 driver.findElement(By.id("Email")).clear();
		 driver.findElement(By.id("Email")).sendKeys(email);
		 Thread.sleep(2000);
		 driver.findElement(By.id("next")).click();
		 Thread.sleep(2000);
		 if(driver.findElement(By.className("error-msg")).getText().contains("Sorry, Google doesn't "))
		 {
			 driver.get("http://www.gmail.com");
			 driver.findElement(By.linkText("Sign in with a different account")).click();
			 Thread.sleep(2000);
		 	 driver.findElement(By.linkText("Add account")).click();
		 	 Thread.sleep(2000);
			 xlSheet.getRow(i).createCell(2).setCellValue("Invalid EMAIL ID");
			 xlSheet.getRow(i).getCell(2).setCellStyle(styleInvalid);
		 }
		 else
		 {
			 	driver.findElement(By.id("Passwd")).clear();
			 	driver.findElement(By.id("Passwd")).sendKeys(passwd);
			 	driver.findElement(By.id("signIn")).click();
			 	Thread.sleep(2000);
			 	driver.getCurrentUrl();
			 	//System.out.println("Hello Passwd "+driver.getCurrentUrl());
		 		if(driver.getCurrentUrl().contains("LoginAuth#password"))
		 		{
		 			 driver.findElement(By.linkText("Sign in with a different account")).click();
					 Thread.sleep(2000);
				 	 driver.findElement(By.linkText("Add account")).click();
				 	 Thread.sleep(2000);
				 	 xlSheet.getRow(i).createCell(2).setCellValue("Invalid Password");
				 	 xlSheet.getRow(i).getCell(2).setCellStyle(styleInvalid);
		 		}
		 		else
				{
			 		driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")).click();
			 		driver.findElement(By.id("gb_71")).click();
			 		Thread.sleep(2000);
			 		if(i==1)
			 			driver.findElement(By.linkText("Sign in with a different account")).click();
			 		Thread.sleep(2000);
			 		driver.findElement(By.linkText("Add account")).click();
			 		Thread.sleep(2000);
			 		xlSheet.getRow(i).createCell(2).setCellValue("Valid");
			 		xlSheet.getRow(i).getCell(2).setCellStyle(styleValid);
				} 		
		 }		
	}
	driver.close();
	return xlSheet;
}
}

