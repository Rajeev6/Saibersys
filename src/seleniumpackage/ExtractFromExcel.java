package seleniumpackage;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExtractFromExcel {

public static void main(String args[]) throws Exception
{


	WebDriver driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.get("http://www.gmail.com");
    
    File file= new File("//Users//Rajeev//Desktop//Login.xlsx");

    FileInputStream fis= new FileInputStream(file);

    XSSFWorkbook workbook= new XSSFWorkbook(fis);

    XSSFSheet sheet1= workbook.getSheetAt(0);
    String email=sheet1.getRow(1).getCell(0).getStringCellValue();
    String passwd=sheet1.getRow(1).getCell(1).getStringCellValue();

    driver.findElement(By.id("Email")).sendKeys(email);
    Thread.sleep(2000);
    driver.findElement(By.id("next")).click();
    //System.out.println(email+" "+passwd);
    Thread.sleep(2000);
    //driver.switchTo().activeElement().sendKeys(passwd);
    //driver.findElement(By.xpath(".//*[@id='Passwd']")).sendKeys(passwd);;
    driver.findElement(By.id("Passwd")).sendKeys(passwd);
    driver.findElement(By.id("signIn")).click();
    
    Thread.sleep(5000);
    driver.close();


	workbook.close();

}

}

