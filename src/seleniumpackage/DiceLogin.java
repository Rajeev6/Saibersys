package seleniumpackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Sheet;
import jxl.Workbook;



public class DiceLogin {
	
	WebDriver driver;
	Workbook workbook;
	Sheet sh1;
	int numrow,numcol;
    static String username;
    static String password;
    Object [][] gmailData;

public void dataExtract() throws InterruptedException, AWTException
{	

	try {
		workbook= Workbook.getWorkbook(new File("//Users//Rajeev//Desktop//Dice.xls"));
		sh1= workbook.getSheet(0);
		numrow=  sh1.getRows();
		numcol= sh1.getColumns();	
	}catch (Exception e){
     	   e.printStackTrace();
        }
	String [][] diceData=new String[numrow-1][numcol];
	for(int i=1;i<numrow;i++)
    {
		diceData[i-1][0]=sh1.getCell(0,i).getContents();
		diceData[i-1][1]=sh1.getCell(1,i).getContents();
		diceData[i-1][2]=sh1.getCell(2,i).getContents();
		diceData[i-1][3]=sh1.getCell(3,i).getContents();
		diceData[i-1][4]=sh1.getCell(4,i).getContents();
		login(diceData[i-1][0],diceData[i-1][1],diceData[i-1][2],diceData[i-1][3],diceData[i-1][4]);
    }
	//return diceData;
	
}

public void login(String email, String passwd, String firstName, String lastName,String resume) throws InterruptedException, AWTException
{
	 Boolean flag;
	 driver = new FirefoxDriver();
	 driver.manage().window().maximize();
	 driver.get("http://www.dice.com");
	 Thread.sleep(2000); 
	 driver.get("http://www.dice.com");
	 //driver.switchTo().parentFrame();
	 //driver.findElement(By.LinkText("close")).click();
	 driver.findElement(By.id("Login_1")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.id("Email_1")).sendKeys(email);
	 Thread.sleep(1000);
	 driver.findElement(By.id("Email_1")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.id("Password_1")).clear();
	 driver.findElement(By.id("Password_1")).sendKeys(passwd);
	 driver.findElement(By.id("LoginBtn_1")).click();
	 Thread.sleep(1000);
	 if(driver.getCurrentUrl().contains("dashboard#/profiles"))
	 {
		 flag=diceSearch();
		 if(flag==true)
		 {
			 easyApply(firstName,lastName,resume);
		 }
	 }
	 else
		 System.out.println("Invalid Login");
}

public Boolean diceSearch() throws InterruptedException
{
	// driver.findElement(By.cssSelector("input[value='Contracts']")).click();   
	Boolean flag=true;
	driver.findElement(By.id("job")).clear();
	driver.findElement(By.id("job")).sendKeys("Java Developer");
	Thread.sleep(1000);
	String s=driver.getCurrentUrl();
	driver.get(s);
	driver.findElement(By.id("location")).clear();
	driver.findElement(By.linkText("Third Party")).click();
	/*String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	    driver.findElement(By.xpath(".//*[@id='myModal']/div/div/div[1]/button")).click();
	}

	driver.switchTo().window(parentWindowHandler); */
	//driver.findElement(By.xpath(".//*[@id='searchJob']/div/div[3]/input")).click();
	if(driver.findElement(By.xpath(".//*[@id='serp']/div[2]/ul/li[4]")).isDisplayed())
	{
		System.out.println("Displayed");
		driver.findElement(By.xpath(".//*[@id='position1']")).click();
		Thread.sleep(3000);
		return flag;
	}
	else
	{
		System.out.println("Not Displayed");
		flag=false;
		return flag;
	}
		  
}
public void easyApply(String firstName, String lastName, String resume) throws InterruptedException, AWTException
{
	driver.findElement(By.id("easyApplybtn")).click();
	System.out.println("Easy Apply");
	Thread.sleep(1000);
	
	/*Set<String> handles = driver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	String child= iterator.next();
	driver.switchTo().window(child);*/
	driver.switchTo().frame("dice-iframe");
	driver.findElement(By.id("first-name-input")).clear();
	Thread.sleep(2000);
	driver.findElement(By.id("first-name-input")).sendKeys(firstName);
	Thread.sleep(1000);
	driver.findElement(By.id("last-name-input")).clear();
	Thread.sleep(1000);
	driver.findElement(By.id("last-name-input")).sendKeys(lastName);
	Thread.sleep(1000);
	
	driver.findElement(By.cssSelector("input[value='upload']")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("resume-file-input")).click();
	uploadMAC(resume);
	
	/*Select resumeSelect= new Select(driver.findElement(By.id("resume-select")));
	resumeSelect.selectByValue("0");
	//driver.findElement(By.id("resume-select")).click();*/
	//driver.findElement(By.id("submit-btn")).click();
	Thread.sleep(1000);
}
public void uploadMAC(String resume) throws AWTException
{
	String getFile="//Users//Rajeev//Desktop//Rajeev Job/"+resume;
	File file = new File(getFile);
	 
	StringSelection stringSelection= new StringSelection(file.getAbsolutePath());
	 
	//Copy to clipboard 
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	 
	Robot robot = new Robot();
	 
	// Cmd + Tab is needed since it launches a Java app and the browser looses focus
	 
	robot.keyPress(KeyEvent.VK_META);
	 
	robot.keyPress(KeyEvent.VK_TAB);
	 
	robot.keyRelease(KeyEvent.VK_META);
	 
	robot.keyRelease(KeyEvent.VK_TAB);
	 
	robot.delay(500);
	 
	//Open Goto window
	 
	robot.keyPress(KeyEvent.VK_META);
	 
	robot.keyPress(KeyEvent.VK_SHIFT);
	 
	robot.keyPress(KeyEvent.VK_G);
	 
	robot.keyRelease(KeyEvent.VK_META);
	 
	robot.keyRelease(KeyEvent.VK_SHIFT);
	 
	robot.keyRelease(KeyEvent.VK_G);
	 
	//Paste the clipboard value
	 
	robot.keyPress(KeyEvent.VK_META);
	 
	robot.keyPress(KeyEvent.VK_V);
	 
	robot.keyRelease(KeyEvent.VK_META);
	 
	robot.keyRelease(KeyEvent.VK_V);
	 
	//Press Enter key to close the Goto window and Upload window
	 
	robot.keyPress(KeyEvent.VK_ENTER);
	 
	robot.keyRelease(KeyEvent.VK_ENTER);
	 
	robot.delay(500);
	 
	robot.keyPress(KeyEvent.VK_ENTER);
	 
	robot.keyRelease(KeyEvent.VK_ENTER);
	 
}

public void quit()
{
	   // close browser after execution
	   driver.quit();
}

public static void main(String args[]) throws InterruptedException, AWTException 
{
	DiceLogin dl=new DiceLogin();
	dl.dataExtract();
	dl.quit();
}


}

