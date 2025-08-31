package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;


public class TC_RF_009
{
	public void validateMessageOnProvidingAlreadyRegisteredEmailID()
	{
		  WebDriver driver= new ChromeDriver();
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	      String str=driver.findElement(By.xpath("//div[@id='content']/p")).getText();
	      SoftAssert softassert = new SoftAssert();
	      softassert.assertEquals(str, "If you already have an account with us, please login at the login page.","Validated but not matched");
	      driver.findElement(By.id("input-firstname")).sendKeys("Praveen");
	      driver.findElement(By.id("input-lastname")).sendKeys("Kumar11");
	      driver.findElement(By.id("input-email")).sendKeys("nadda.kumar@gmail.com");
	      driver.findElement(By.id("input-telephone")).sendKeys("124325");
	      driver.findElement(By.id("input-password")).sendKeys("abcd1234");
	      driver.findElement(By.id("input-confirm")).sendKeys("abcd1234");
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	      String str1=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
	      softassert.assertEquals(str1, "Warning: E-Mail Address is already registered!");
	      softassert.assertAll();     
	      driver.close();
	}

}
