package tutorialsninja.register;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utility.DateProvider;

public class TC_RF_010
{   
	WebDriver driver=null;
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test
	public void validateRegisterAccountByProvidingInvalidEmail() throws IOException, InterruptedException
	{
		  driver= new ChromeDriver();
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	      String str=driver.findElement(By.xpath("//div[@id='content']/p")).getText();
	      SoftAssert softassert = new SoftAssert();
	      DateProvider dp=new DateProvider();
	      //First Set of invalid date
	      softassert.assertEquals(str, "If you already have an account with us, please login at the login page.","Validated but not matched");
	      driver.findElement(By.id("input-firstname")).sendKeys("Praveen");
	      driver.findElement(By.id("input-lastname")).sendKeys("Kumar11");
	      driver.findElement(By.id("input-email")).sendKeys("nadda.kumar@");
	      driver.findElement(By.id("input-telephone")).sendKeys("124325");
	      driver.findElement(By.id("input-password")).sendKeys("abcd1234");
	      driver.findElement(By.id("input-confirm")).sendKeys("abcd1234");
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
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
	      String cleaned=dp.dateProvider().replace("@gmail.com", "");
	      String filePath=System.getProperty("user.dir")+File.separator+"Evidence"+File.separator+"actualScreenshot"+cleaned+".png";
	      FileHandler.copy(screenshot1, new File(filePath));
	      File f=new File(filePath);
	      String fileName=f.getName();
	      //call to compare ActualImage with expected image
	      imageComparisons(fileName, "expectedScreenshot1.png", cleaned);
	      //Second set of data
	      driver.findElement(By.id("input-email")).clear();
	      driver.findElement(By.id("input-email")).sendKeys("nadda");
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	      Thread.sleep(3000);
	      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);     
	      Thread.sleep(3000);
	      screenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
	      cleaned=dp.dateProvider().replace("@gmail.com", "");
	      filePath=System.getProperty("user.dir")+File.separator+"Evidence"+File.separator+"actualScreenshot"+cleaned+".png";
	      FileHandler.copy(screenshot1, new File(filePath));
	      f=new File(filePath);
	      fileName=f.getName();
	      imageComparisons(fileName, "expectedScreenshot2.png", cleaned);
	      //Third set of data
	      driver.findElement(By.id("input-email")).clear();
	      driver.findElement(By.id("input-email")).sendKeys("kumar123@gmail.");
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	      Thread.sleep(3000);
	      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element); 
	      Thread.sleep(3000);
	      screenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
	      cleaned=dp.dateProvider().replace("@gmail.com", "");
	      filePath=System.getProperty("user.dir")+File.separator+"Evidence"+File.separator+"actualScreenshot"+cleaned+".png";
	      FileHandler.copy(screenshot1, new File(filePath));
	      f=new File(filePath);
	      fileName=f.getName();
	      imageComparisons(fileName, "expectedScreenshot3.png", cleaned);
	      softassert.assertAll();
	      
	      
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
	      System.out.println("Marked diff saved at: " + diffFile.getAbsolutePath());
	      SoftAssert softAssert=new SoftAssert();
	      softAssert.assertFalse(imagediff.hasDiff());
	      softAssert.assertAll();
	         
	      }
	     
	}


