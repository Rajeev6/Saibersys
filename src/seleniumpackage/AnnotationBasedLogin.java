package seleniumpackage;

import java.io.File;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.Sheet;
import jxl.Workbook;



public class AnnotationBasedLogin {
	
	WebDriver driver;
	Workbook workbook;
	Sheet sh1;
	int numrow,numcol;
    static String username;
    static String password;
    Object [][] gmailData;

@DataProvider(name="testData")
public Object [][] testFeedData()
{	

	try {
		workbook= Workbook.getWorkbook(new File("//Users//Rajeev//Desktop//Loginxls.xls"));
		sh1= workbook.getSheet(0);
		numrow=  sh1.getRows();
		numcol= sh1.getColumns();	
	}catch (Exception e){
     	   e.printStackTrace();
        }
	Object [][] gmailData=new Object[numrow-1][numcol];
	for(int i=1;i<numrow;i++)
    {
		System.out.println("Hai");
		gmailData[i-1][0]=sh1.getCell(0,i).getContents();
 	    gmailData[i-1][1]=sh1.getCell(1,i).getContents();
    }
	System.out.println("testfeed");
	return gmailData;
	
}

@Test(dataProvider="testData")
public void test(String email, String passwd) throws InterruptedException
{
	System.out.println("tetsonly");
	 driver = new FirefoxDriver();
	 driver.manage().window().maximize();
	 driver.get("http://www.gmail.com");
	 Thread.sleep(2000);
	 driver.findElement(By.id("Email")).clear();
	 driver.findElement(By.id("Email")).sendKeys(email);
	 Thread.sleep(2000);
	 driver.findElement(By.id("next")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.id("Passwd")).clear();
	 driver.findElement(By.id("Passwd")).sendKeys(passwd);
	 driver.findElement(By.id("signIn")).click();
	 Thread.sleep(2000);
	 if(driver.getCurrentUrl().contains("LoginAuth#password"))
	 {
		 
		 
	 }	 
}

@AfterMethod
public void quitTC()
{
	   // close browser after execution
	   driver.quit();
}

}

