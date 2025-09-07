package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.DateProvider;

public class TC_RF_003
{  
	WebDriver driver=null;
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test
	public void verifyRegisteringAccountWithMandatoryFieldsAndSoftAssert() throws InterruptedException
	{
		
      driver= new ChromeDriver();
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
      driver.findElement(By.id("input-confirm")).sendKeys("1234");
      driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
      driver.findElement(By.name("agree")).click();
      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
      String expectedStringone="Your Account Has Been Created!";  
      String expectedStringtwo="Congratulations! Your new account has been successfully created!"; 
      String expectedStringthree="You can now take advantage of member privileges to enhance your online shopping experience with us.";  
      String expectedStringfour="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";  
      String expectedStringfive="A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";  
      String actualstring=driver.findElement(By.id("content")).getText();
      softassert.assertTrue(actualstring.contains(expectedStringone));
      softassert.assertTrue(actualstring.contains(expectedStringtwo));
      softassert.assertTrue(actualstring.contains(expectedStringthree));
      softassert.assertTrue(actualstring.contains(expectedStringfour));
      softassert.assertTrue(actualstring.contains(expectedStringfive));
      driver.findElement(By.linkText("Continue")).click();
      softassert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
      softassert.assertAll();
      
     }
	
}
