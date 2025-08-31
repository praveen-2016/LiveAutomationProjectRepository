package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_007
{   
	@Test
	public void validateDifferentWaysOfNavigatingToRegisterPage()
	{
		WebDriver driver= new ChromeDriver();
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	      Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), "Register Account");
	      driver.navigate().back();
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Login")).click();
	      Assert.assertTrue(driver.findElement(By.xpath("//div[@class='well']")).getText().contains("New Customer"));
	      driver.findElement(By.linkText("Continue")).click();
	      Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), "Register Account");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Login")).click();
	      Assert.assertTrue(driver.findElement(By.xpath("//div[@class='well']")).getText().contains("By creating an account you will be able to shop faster, be up to date on an order's status, and keep track"));
	      driver.findElement(By.linkText("Register")).click();
	      Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), "Register Account");
	      driver.close();
	      
	      
	      
	      
	      
	      
	}

}
