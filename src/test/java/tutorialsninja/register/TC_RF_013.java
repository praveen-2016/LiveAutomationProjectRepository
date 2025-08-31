package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_RF_013
{   
	@Test
	public void verifyPlaceholderValuesOnRegisterAccountPage()
	{
		  WebDriver driver= new ChromeDriver();
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	      SoftAssert softassert=new SoftAssert();
	      softassert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"), "First Name");
	      softassert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("placeholder"), "Last Name");
	      softassert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"), "E-Mail");
	      softassert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("placeholder"), "Telephone");
	      softassert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("placeholder"), "Password");
	      softassert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("placeholder"), "Password Confirm");
	      softassert.assertAll();
	      driver.quit();
		
	}

}
