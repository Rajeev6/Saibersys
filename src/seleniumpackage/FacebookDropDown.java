package seleniumpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.FindBy;

import junit.framework.Assert;

import org.junit.Test; 
 
@SuppressWarnings("deprecation")
public class FacebookDropDown {
	 
	 
	public static void main(String[] args) throws Exception {
    	 
    	 //System.setProperty("webdriver.chrome.driver","d:\\struts2\\Selenium\\chromedriver.exe");
    	WebDriver  driver = new FirefoxDriver();
    		
          driver.manage().window().maximize();
 
          driver.get("http://www.facebook.com");
     
          WebElement mbl=driver.findElement(By.id("contactpoint_label"));
          WebElement pass=driver.findElement(By.id("u_0_b"));
          
 
         boolean status=mbl.isDisplayed();
 
         System.out.println("Mobile is Displayed >>"+status);
 
         boolean enabled_status=mbl.isEnabled();
 
          System.out.println("Mobile is Enabled >>"+enabled_status);
          
          boolean status1=mbl.isDisplayed();
          
          System.out.println("Password is Displayed >>"+status1);
  
          boolean enabled_status1=mbl.isEnabled();
  
           System.out.println("password is Enabled >>"+enabled_status1);
 
          /*  boolean selected_status=male_radio_button.isSelected();
 
          System.out.println("Male radio button is Selected >>"+selected_status);
 
          male_radio_button.click();
 
        boolean selected_status_new=male_radio_button.isSelected();
 
          System.out.println("Male radio button is Selected >>"+selected_status_new);
          Assert.assertEquals(false, selected_status_new);
 */
     }
 
}