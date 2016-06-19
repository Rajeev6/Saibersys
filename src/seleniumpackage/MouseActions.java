package seleniumpackage;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
 
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
 
public class MouseActions {
 
public static void main(String[] args) throws InterruptedException {
String baseUrl = "http://www.facebook.com";
        WebDriver driver = new FirefoxDriver();
 
        driver.get(baseUrl);           
        
        WebElement text = driver.findElement(By.id("email"));    
        Actions builder = new Actions(driver);
        Action seriesofActions = builder
        		.moveToElement(text)
        		.click().keyDown(text, Keys.SHIFT)
        		.sendKeys(text, "hello")
        		.keyUp(text, Keys.SHIFT)
        		.doubleClick(text)
        		.contextClick()
        		.build();
        		
        seriesofActions.perform();
        Thread.sleep(3000);
        driver.quit();
}
}
