package tutorialsninja.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import utility.DateProvider;

public class TC_RF_015 
{   
	
	WebDriver driver=null;
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test
	public void databaseValidationOfCustomerValidation() throws SQLException
	{
		  driver= new ChromeDriver();
		  DateProvider dp=new DateProvider();
	      driver.manage().window().maximize();
	      driver.get("http://localhost/opencart/");
	      driver.findElement(By.linkText("My Account")).click();
	      driver.findElement(By.linkText("Register")).click();
	     // String str=driver.findElement(By.xpath("//div[@id='content']/p")).getText();
	     // SoftAssert softassert = new SoftAssert();
	      //softassert.assertEquals(str, "If you already have an account with us, please login at the login page.","Validated but not matched");
	      driver.findElement(By.id("input-firstname")).sendKeys("Praveen");
	      driver.findElement(By.id("input-lastname")).sendKeys("Kumar11");
	      driver.findElement(By.id("input-email")).sendKeys(dp.dateProvider());
	      driver.findElement(By.id("input-telephone")).sendKeys("124325");
	      driver.findElement(By.id("input-password")).sendKeys("abcd1234");
	      driver.findElement(By.id("input-confirm")).sendKeys("abcd1234");
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	      //String str1=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
	     // softassert.assertEquals(str1, "Warning: E-Mail Address is already registered!");
	      //softassert.assertAll();  
	      databaseValidationPostCreation();
	      
	}
	
   public void databaseValidationPostCreation() throws SQLException
   {
	   String url="jdbc:mysql://localhost:3308/";
		String database="opencart_db";
		String serverTimezone="?serverTimezone=UTC";
		String dbURL=url+database+serverTimezone;
		String userName="root";
		String password="";
		//Create mysql instance		
		//Class.forName("com.mysql.cj.jdbc.Driver");		
		
		Connection con= DriverManager.getConnection(dbURL,userName,password);
		if(!con.isClosed())
	    {
		System.out.println("Connected to data base--> opencart_db");
		Statement statement=con.createStatement();
		ResultSet resultset= statement.executeQuery("select * from oc_customer");
		while(resultset.next())
		  {
			System.out.println(resultset.getString("firstname")+"----"+resultset.getString("email")+"---"+resultset.getString("password"));
		  }
		System.out.println("-------------------------------------------------------");
		     PreparedStatement preparedStatement = con.prepareStatement("select * from oc_customer where firstname=? and telephone=?");
		     preparedStatement.setString(1, "Praveen");
		     preparedStatement.setInt(2, 124325);
		     ResultSet resultset2 = preparedStatement.executeQuery();
		     while(resultset2.next())
			  {
				System.out.println(resultset2.getString("customer_id")+"----"+resultset2.getString("firstname")+"----"+resultset2.getString("email")+"---"+resultset2.getInt("telephone"));
			   }  
		}
		con.close();
		if(con.isClosed())
		{
			System.out.println("===Connection to database opencart_db is closed----");
		}

   }

}
