package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_018 {

		@Test		
		public void verifyRegisterAccountFieldsHeightWidthAlignmentEtc()
		
		{
			  WebDriver driver= new ChromeDriver();
		      driver.manage().window().maximize();
		      driver.get("https://tutorialsninja.com/demo/");
		      driver.findElement(By.linkText("My Account")).click();
		      driver.findElement(By.linkText("Register")).click();
		      // Locate the label using CSS selector
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-firstname']")).getCssValue("height"),"34px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-firstname']")).getCssValue("width"),"701.25px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-lastname']")).getCssValue("height"),"34px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-lastname']")).getCssValue("width"),"701.25px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-email']")).getCssValue("height"),"34px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-email']")).getCssValue("width"),"701.25px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-telephone']")).getCssValue("height"),"34px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-telephone']")).getCssValue("width"),"701.25px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-password']")).getCssValue("height"),"34px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-password']")).getCssValue("width"),"701.25px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-confirm']")).getCssValue("height"),"34px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[id='input-confirm']")).getCssValue("width"),"701.25px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[type='submit']")).getCssValue("height"),"33.7375px");
		      Assert.assertEquals(driver.findElement(By.cssSelector("input[type='submit']")).getCssValue("width"),"76.5125px");
		      

		      
		}
		 
		}
		
	


