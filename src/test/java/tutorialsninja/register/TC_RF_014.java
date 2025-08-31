package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_014
{   
	@Test
	public void verifyAshtricMandatoryOnRegisterAccountFields()
	{
		 WebDriver driver= new ChromeDriver();
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	      JavascriptExecutor js = (JavascriptExecutor) driver;
	      // Locate the label using CSS selector
	      JSciptRunner(js,driver.findElement(By.cssSelector("label[for='input-firstname']")));
	      JSciptRunner(js,driver.findElement(By.cssSelector("label[for='input-lastname']")));
	      JSciptRunner(js,driver.findElement(By.cssSelector("label[for='input-email']")));
	      JSciptRunner(js,driver.findElement(By.cssSelector("label[for='input-telephone']")));
	      JSciptRunner(js,driver.findElement(By.cssSelector("label[for='input-password']")));
	      JSciptRunner(js,driver.findElement(By.cssSelector("label[for='input-confirm']"))); 
	      
	}
	public void JSciptRunner(JavascriptExecutor js,WebElement webElement)
	{	     
	      String asteriskContent = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",webElement);
	      String color = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",webElement);
	      Assert.assertEquals(asteriskContent.replace("\"","").trim(), "*");
	      Assert.assertEquals(color, "rgb(255, 0, 0)");
	      System.out.println("CSS ::before content: " + asteriskContent.replace("\"","").trim());
	}

}
