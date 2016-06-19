package seleniumpackage;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

 
public class LoginTest 
{
 
       WebDriver driver;
       Workbook wb;
       Sheet sh1;
       int numrow;
       static String username;
       static String password;
       ArrayList list;
 
       @BeforeTest
       public void Setup()
       {
    	   list=new ArrayList();
	       //System.setProperty("webdriver.chrome.driver","d:\\struts2\\Selenium\\chromedriver.exe");
	       driver = new FirefoxDriver();
           driver.manage().window().maximize();
             //driver.get("http://www.facebook.com");
           driver.get("http://www.gmail.com");
       }
 
       @Test(dataProvider="testdata")
       public void TestFireFox()
       {
    	       	   
    	  /* 
          driver.findElement(By.id("Email")).clear();
          
          driver.findElement(By.id("Email")).sendKeys(TestDDT1.username);
          driver.findElement(By.id("Passwd")).clear();
          driver.findElement(By.id("Passwd")).sendKeys(TestDDT1.password);
          */
       }
       @DataProvider(name="testdata")
       public void TestDataFeed()
       {
           try 
           {
 
        	   // load workbook
        	   //wb=Workbook.getWorkbook(new File("location of excel sheet/fbdata.xls"));
        	   wb=Workbook.getWorkbook(new File("//Users//Rajeev//Desktop//Login.xlsx"));
        	   // load sheet in my case I am referring to first sheet only
        	   sh1= wb.getSheet(0);
        	   // get number of rows so that we can run loop based on this
        	   numrow=  sh1.getRows();
           }
           catch (Exception e)
           {
        	   e.printStackTrace();
           }
           // Create 2 D array and pass row and columns
           Object [][] facebookdata=new Object[numrow][sh1.getColumns()];
           // This will run a loop and each iteration  it will fetch new row
           for(int i=0;i<numrow;i++)
           {
        	   // Fetch first row username
        	   facebookdata[i][0]=sh1.getCell(0,i).getContents();
        	   System.out.println(facebookdata[i][0]);
        	   //Fetch first row password
        	   facebookdata[i][1]=sh1.getCell(1,i).getContents();
        	   System.out.println(facebookdata[i][1]);
        	   list.add(facebookdata[1][i]);
           }
           // Return 2d array object so that test script can use the same
           //return facebookdata;
           LoginTest.username=(String)list.get(0);
          
           LoginTest.password=(String)list.get(1);
           
       }
 
       @AfterTest
       public void QuitTC()
       {
 
    	   // close browser after execution
    	   driver.quit();
       }
       public static void main(String args[])
       {
    	   LoginTest td=new LoginTest	();
    	   td.Setup();
    	   
    	   //td.TestFireFox();
    	   td.TestDataFeed();
    	   
       }
 
}

