package tutorialsninja.register;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_002
{   
	WebDriver driver=null;
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
    @Test
	public  void amazonInGetPasscodefromGmail() throws InterruptedException
	{
		
		  driver= new ChromeDriver();
	      driver.manage().window().maximize();
	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	      driver.get("https://www.amazon.in/");
	      
	      List<WebElement> elements = driver.findElements(By.xpath("//button[@type='submit']"));
	      if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
	          elements.get(0).click();
	      }	           
	    	  driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();
		     // driver.findElement(By.linkText("Sign in")).click();
		      driver.findElement(By.id("ap_email_login")).sendKeys("nadda.kumar@gmail.com");
		      Thread.sleep(5000);
		      driver.findElement(By.xpath("//input[@type='submit']")).click();
		      
		      //driver.findElement(By.xpath("//input[@id='continue']")).click();
		      Thread.sleep(5000);
		      try {
		            Runtime.getRuntime().exec("C:\\Users\\nadda\\OneDrive\\Desktop\\auoit\\winpin.exe");
		            Thread.sleep(3000); // Optional: wait for ESC to be sent
		        } catch (IOException | InterruptedException e) {
		            e.printStackTrace();
		        }
		      Thread.sleep(5000);
		      driver.findElement(By.xpath("//a[@id='auth-fpp-link-bottom']")).click();
		      Thread.sleep(5000);
		      driver.findElement(By.xpath("//input[@id='continue']")).click();
		      String emailid="nadda.kumar@gmail.com" ;
		      String apppasscode="xclc bqdk jvvo jcxp";
		      
		      Thread.sleep(10000);
		      
		      String host = "imap.gmail.com";
	            String username = emailid; // replace with your email
	            String appPassword = apppasscode; // replace with your app password

	            Properties props = new Properties();
	            props.put("mail.store.protocol", "imaps");
	            String verificationCode;
	            try 
	            {
	                Session session = Session.getInstance(props);
	                Store store = session.getStore("imaps");
	                store.connect(host, username, appPassword);

	                Folder inbox = store.getFolder("INBOX");
	                inbox.open(Folder.READ_ONLY);

	                Message[] messages = inbox.getMessages();
	                System.out.println("Total messages: " + messages.length);

	                for (int i = messages.length - 1; i >= messages.length - 1; i--)
	                { // read last 2 emails
	                    Message msg = messages[i];
	                    System.out.println("Subject: " + msg.getSubject());
	                    System.out.println("From: " + msg.getFrom()[0].toString());

	                    Object content = msg.getContent();
	                    if (content instanceof String)
	                    {
	                        System.out.println("Bodyif-----------: " + content);
	                    } 
	                    else if (content instanceof Multipart)
	                    {
	                        Multipart multipart = (Multipart) content;
	                        for (int j = 0; j < multipart.getCount(); j++)
	                        {
	                            BodyPart part = multipart.getBodyPart(j);
	                            if (part.isMimeType("text/plain")) 
	                            {
	                            	
	                               // System.out.println("Bodyelseifforif---: " + part.getContent());
	                                
	                                Object contentObj = part.getContent();
	                                String bodyText = contentObj.toString().replaceAll("(?m)^[ \t]*\r?\n", "").replaceAll("(\r?\n){2,}", "\n\n");  // Convert to String
	                                System.out.println("Bodyelseifforif---: " + bodyText.trim());

	                                // Extract 6-digit code using regex
	                                Pattern pattern = Pattern.compile("\\d{6}");
	                                Matcher matcher = pattern.matcher(bodyText);
	                                matcher.find();
                                    verificationCode = matcher.group();
	                                System.out.println("Extracted verification code: " + verificationCode);                            
	                                driver.findElement(By.id("input-box-otp")).sendKeys(verificationCode);
	                                driver.findElement(By.xpath("//span[@id='cvf-submit-otp-button']")).click();
	                            }
	                        }
	                    }
	                    System.out.println("--------------------------------------------------");
	                }

	                inbox.close(false);
	                store.close();

	            } catch (Exception e)
	            {
	                e.printStackTrace();
	            }
             
	
	      //driver.close();
    }

	  

}


