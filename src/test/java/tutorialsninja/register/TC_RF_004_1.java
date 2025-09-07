package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_RF_004_1
{   
	
	@Test
	public void validationOfAllMandatoryfieldsWarningMessagesWithAgreedStatement()
	{
		  WebDriver driver= new ChromeDriver();
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	      SoftAssert softAssert=new SoftAssert();
	      String statement="If you already have an account with us, please login at the login page.";
	      softAssert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/p")).getText(), statement);
	      String firstNameWarning="First Name must be between 1 and 32 characters!";
	      softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), firstNameWarning);
	      String lastNameWarning="Last Name must be between 1 and 32 characters!";
	      softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), lastNameWarning);
	      String emailWarning="E-Mail Address does not appear to be valid!";
	      softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), emailWarning);
	      String telephoneWarning="Telephone must be between 3 and 32 characters!";
	      softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), telephoneWarning);
	      String passwordWarning="Password must be between 4 and 20 characters!";
	      softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), passwordWarning);
	      driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	      softAssert.assertAll();
	      driver.close();
	      
	      
	}

}
