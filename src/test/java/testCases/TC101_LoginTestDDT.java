package testCases;

import org.testng.annotations.Test;
import org.testng.Assert; 

import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC101_LoginTestDDT extends BaseClass{
	
	@Test (dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= "DataDriven")
	public void verify_loginDDT(String username, String password, String result) throws InterruptedException
	{
		try
		{
		driver.navigate().refresh();
		
		LoginPage lp=new LoginPage(driver);  
		lp.set_username(username);
		lp.set_password(password);
		
		lp.click_login();
		
		
		//Search Hotel Page
		SearchHotelPage shp=new SearchHotelPage(driver);  
		boolean targetpage=shp.ispageExist();
		
		System.out.println("Is page displayed: "+targetpage);
					
		if(result.equalsIgnoreCase("Valid"))
		{
			if (targetpage==true)
			{   
				lp.click_logout();
				lp.click_loginAgain();
				Thread.sleep(3000);
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
				
			}
		}
		if (result.equalsIgnoreCase("Invalid"))
		{
			if (targetpage==true)
			{   
				lp.click_logout();
				lp.click_loginAgain();
				Thread.sleep(3000);
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);	
				
			}
		}			
	 }
	  catch(Exception e)
		 {
		  Assert.fail();
		  
		 }
	  Thread.sleep(3000);
	 

	}


}
