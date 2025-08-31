package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.DateProvider;

public class TC_RF_012 
{   @Test
	public void registerAccountUsingKeyboardKeys()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://tutorialsninja.com/demo/");
		Actions action=new Actions(driver);
		action.click(driver.findElement(By.linkText("My Account"))).perform();
		action.click(driver.findElement(By.linkText("Register"))).perform();
		String str=driver.findElement(By.xpath("//div[@id='content']/p")).getText();
	    SoftAssert softassert = new SoftAssert();
	    DateProvider dp=new DateProvider();
	    softassert.assertEquals(str, "If you already have an account with us, please login at the login page.","Validated but not matched");
	    action.click(driver.findElement(By.id("input-firstname"))).perform();
	    action
	          .sendKeys("Praveen")	          
	          .sendKeys(Keys.TAB)
	          .sendKeys("Kumar11")	          
	          .sendKeys(Keys.TAB)
	          .sendKeys(dp.dateProvider())
	          .sendKeys(Keys.TAB)	    	   
	    	   .sendKeys("124325")
	    	   .sendKeys(Keys.TAB)	    	   
	    	   .sendKeys("12345")
	    	   .sendKeys(Keys.TAB)	    	   
	    	   .sendKeys("12345")
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.ARROW_LEFT)	    	   
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.TAB)
	    	   .sendKeys(Keys.SPACE)	    	   
	    	   .sendKeys(Keys.ENTER)
	    	   .build().perform();	    	  
	       Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
	       String expectedStringone="Your Account Has Been Created!";  
	       String actualstring=driver.findElement(By.id("content")).getText();
	       softassert.assertTrue(actualstring.contains(expectedStringone));
	       action.click(driver.findElement(By.linkText("Continue"))).perform();
	       softassert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	       action.click(driver.findElement(By.linkText("Logout"))).perform();
	       action.click(driver.findElement(By.linkText("Continue"))).perform();
	       softassert.assertAll();
	       driver.quit();
		
	}

}
