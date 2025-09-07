package tutorialsninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utility.DateProvider;

public class TC_RF_018_1 
{  

    WebDriver driver=null;
    
    @AfterMethod
    public void tearDown()
    {
    	driver.quit();
    }
	@Test(dataProvider="userdata")
	public void verifyBoundaryValuesOfFieldsOnREgisterAccountPage(String fname,String lname,String email,String tel,String pass,String conf) throws IOException, InterruptedException
		{
		
		  driver= new ChromeDriver();
		  DateProvider dp=new DateProvider();
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	      if(email.equals("CALL"))
	      {
	    	  email=dp.dateProvider();
	      }
	      Actions action=new Actions(driver);
	      action.click(driver.findElement(By.id("input-firstname"))).perform();
		    action
		          .sendKeys(fname)	          
		          .sendKeys(Keys.TAB)
		          .sendKeys(lname)	          
		          .sendKeys(Keys.TAB)
		           .sendKeys(email)
		          .sendKeys(Keys.TAB)	    	   
		    	   .sendKeys(tel)
		    	   .sendKeys(Keys.TAB)	    	   
		    	   .sendKeys(pass)
		    	   .sendKeys(Keys.TAB)	    	   
		    	   .sendKeys(conf)
		    	   .sendKeys(Keys.TAB)
		    	   .sendKeys(Keys.ARROW_LEFT)	    	   
		    	   .sendKeys(Keys.TAB)
		    	   .sendKeys(Keys.TAB)
		    	   .sendKeys(Keys.SPACE)	    	   
		    	   .sendKeys(Keys.ENTER)
		    	   .build().perform();	
		         int len=fname.length();
		         Assertions(len);
	      			
		}
	
	@DataProvider(name="userdata")
	public Object[][] dataSupplier()
	{
		Object[][] obj= {{"","",""," "," "," "},
				          { "P","R","A","V","E","E"},
				          { "PR","RR","CALL","VET","EERT","EERT"},
				          { "qwertyuiopasdfghjklzxcvbnm123456","qwertyuiopasdfghjklzxcvbnm123456","CALL","qwertyuiopasdfghjklzxcvbnm123456","EERT1234567890oiuytr","EERT1234567890oiuytr"},
				          { "qwertyuiopasdfghjklzxcvbnm1234566","qwertyuiopasdfghjklzxcvbnm1234566","CALL","qwertyuiopasdfghjklzxcvbnm1234566","EERT1234567890oiuytr4","EERT1234567890oiuytr4"}};
		return obj;
	}
	
	public void Assertions(int size) throws IOException, InterruptedException
	{
		  SoftAssert softAssert=new SoftAssert();
		  switch(size)
		  {
		  case 0:
			 softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), "First Name must be between 1 and 32 characters!");
    	     softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), "Last Name must be between 1 and 32 characters!");
		     softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), "E-Mail Address does not appear to be valid!");
		     softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), "Telephone must be between 3 and 32 characters!");
		     softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), "Password must be between 4 and 20 characters!");
		     softAssert.assertAll(); 
		      break;
		  case 1:
			//Form screenshot as tooltip is not being captured by inspect option
		      WebElement element = driver.findElement(By.xpath("//form[@class='form-horizontal']"));
		      Thread.sleep(3000);
		      //to bring form in focus on screen to take screenshot
		     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		       Thread.sleep(3000);
		       //to make the cursor invisible
		     ((JavascriptExecutor)driver).executeScript(
		    		  "var css = '* { caret-color: transparent !important; }';" +
		    		  "var style = document.createElement('style');" +
		    		  "style.appendChild(document.createTextNode(css));" +
		    		  "document.head.appendChild(style);"
		    		);
			  //To take screenshot and save in folder as actual result
		      File screenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		      DateProvider dp1=new DateProvider();
		      String cleaned=dp1.dateProvider().replace("@gmail.com", "");
		      String filePath=System.getProperty("user.dir")+File.separator+"Evidence"+File.separator+"actualScreenshot"+cleaned+".png";
		      FileHandler.copy(screenshot1, new File(filePath));
		      File f=new File(filePath);
		      String fileName=f.getName();
		      //call to compare ActualImage with expected image
		      imageComparisons(fileName, "expectedScreenshot4.png", cleaned);
			   break; 
		  case 2:
			  String expectedStringone="Your Account Has Been Created!";  
		       String actualstring=driver.findElement(By.id("content")).getText();
		       softAssert.assertTrue(actualstring.contains(expectedStringone));
			  softAssert.assertAll(); 
			   break; 
		  case 32:
			  expectedStringone="Your Account Has Been Created!";  
		      actualstring=driver.findElement(By.id("content")).getText();
		      softAssert.assertTrue(actualstring.contains(expectedStringone));
			  softAssert.assertAll();  
			   break;
		      
		   case 33:
			     softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), "First Name must be between 1 and 32 characters!");
	    	     softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), "Last Name must be between 1 and 32 characters!");
			     //softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), "E-Mail Address does not appear to be valid!");
			     softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), "Telephone must be between 3 and 32 characters!");
			     //softAssert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), "Password must be between 4 and 20 characters!");
			     softAssert.assertAll(); 
			     break; 
		  }
	     
	      
	}
	
	public void imageComparisons(String actualFileImage, String expectedFileImage, String difffilepath) throws IOException
    {
    BufferedImage actualImage = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"Evidence"+File.separator+actualFileImage));
   
    BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"Evidence"+File.separator+expectedFileImage));
    
    ImageDiffer imgdiffer=new ImageDiffer(); 
    ImageDiff imagediff = imgdiffer.makeDiff(expectedImage, actualImage);
 // inside imageComparisons(...)
    BufferedImage diffImg =imagediff.getMarkedImage();
  // .getMarkedImage(expectedImage, actualImage);
    File diffFile = new File(System.getProperty("user.dir")+File.separator+"Evidence"+File.separator+"diff_marked"+difffilepath+".png");
    ImageIO.write(diffImg, "png", diffFile);
    //System.out.println("Marked diff saved at: " + diffFile.getAbsolutePath());
    SoftAssert softAssert=new SoftAssert();
    softAssert.assertFalse(imagediff.hasDiff());
    softAssert.assertAll();
       
    }
}


