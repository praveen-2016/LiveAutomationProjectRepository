package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import utility.DateProvider;

public class TC_RF_008 
{
	
	public void validatePasswordMatchConfirmationOnRegisterationPage()
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
	      DateProvider dp=new DateProvider();
	      driver.findElement(By.id("input-email")).sendKeys(dp.dateProvider());
	      driver.findElement(By.id("input-telephone")).sendKeys("124325");
	      driver.findElement(By.id("input-password")).sendKeys("1234");
	      driver.findElement(By.id("input-confirm")).sendKeys("1234567");
	      driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	      softassert.assertTrue(driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText().contains("Password confirmation does not match password!"));
	      softassert.assertAll();
	      driver.close();	

	}

}
