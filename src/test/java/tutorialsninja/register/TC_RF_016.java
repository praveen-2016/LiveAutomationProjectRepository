package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class TC_RF_016 
{   
	WebDriver driver=null;
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test
	public void verifySpaceIsNotAllowedInRegisterAccountPafeFields()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://tutorialsninja.com/demo/");
		Actions action=new Actions(driver);
		action.click(driver.findElement(By.linkText("My Account"))).perform();
		action.click(driver.findElement(By.linkText("Register"))).perform();
		action.click(driver.findElement(By.id("input-firstname"))).perform();
	    action
	          .sendKeys(Keys.SPACE)
	          .sendKeys(Keys.TAB)
	          .sendKeys(Keys.SPACE)
	          .sendKeys(Keys.TAB)
	          .sendKeys(Keys.SPACE)
	          .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.SPACE)
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.SPACE)
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.SPACE)
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.ARROW_LEFT)
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.SPACE)
	    	   .sendKeys(Keys.ENTER)
	    	   .build().perform();	
	      
Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), "First Name must be between 1 and 32 characters!");
Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), "Last Name must be between 1 and 32 characters!");
Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), "E-Mail Address does not appear to be valid!");
Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), "Telephone must be between 3 and 32 characters!");
Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), "Password must be between 4 and 20 characters!");

	}

}
