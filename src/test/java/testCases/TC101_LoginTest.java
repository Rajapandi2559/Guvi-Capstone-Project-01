package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC101_LoginTest extends BaseClass{
	
	@Test (priority=1, groups="Sanity")
	public void Validlogin()
	{
	  try {
	  
	  LoginPage lp=new LoginPage(driver);
	  lp.set_username(prop.getProperty("username"));
	  lp.set_password(prop.getProperty("password"));
	  
	  
	  lp.click_login();		
	  
	  Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel.php"));
	  System.out.println("Login is successful with valid credentials");
	  
	  
	  lp.click_logout();
	  
	  lp.click_loginAgain();
	  }
	  catch (Exception e) {
		  
		  Assert.fail();	  
	  }
	  
	}
	

}
