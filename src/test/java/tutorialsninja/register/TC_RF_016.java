package tutorialsninja.register;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.DateProvider;

public class TC_RF_016 
{    WebDriver driver=null;
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	@Test(dataProvider="inputdata")
	public void verifyComplexityOfPassword(String password)
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
	      driver.findElement(By.id("input-password")).sendKeys(password);
	      driver.findElement(By.id("input-confirm")).sendKeys(password);
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	      String expectedString="Password is not as per the requirements, it should be 8 characterlong and combination of digits, spacial character and alphabets";     
	      softassert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/child::h1")).getText(),expectedString,"Not Matched the string");
	      softassert.assertAll();
	      
	}
	@DataProvider(name="inputdata")
	public Object[][] DataSupplier()
	{
		Object[][] obj={{"abcd"},{"Abcd1234"},{"Ab123£%"},{"12ABCD12"},{"_A345612"}};
		return obj;
	}

}
