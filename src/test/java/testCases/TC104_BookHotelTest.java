package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.BookHotelPage;
import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import pageObjects.SelectHotelPage;
import testBase.BaseClass;

public class TC104_BookHotelTest extends BaseClass {
	
	@Test (priority=1, groups="Functional")
	public void validlogin () 
	{
		LoginPage lp=new LoginPage(driver);
		lp.set_username(prop.getProperty("username"));
		lp.set_password(prop.getProperty("password"));
		lp.click_login();
		
		
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("London");
		shp.click_Search();
		
		
		
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_radioButton1();
		selctHotPage.click_Continue();
		
		
	}
	
    @Test (priority=2, groups="Functional")	
	public void bookhotel () throws InterruptedException {
		try
		{
		
		BookHotelPage bhp=new BookHotelPage(driver);
		bhp.set_FirstName(" ");
		bhp.set_LastName(" ");
		bhp.set_Address(" ");
		bhp.set_CrdtNumber(getRandomNumeric(16));
		bhp.set_CrdtType("Master Card");
		bhp.set_expiryMonth("February");
		bhp.set_expiryYear("2024");
		bhp.set_cvvNumber(getRandomNumeric(3));
		bhp.click_BookNow();
		
		System.out.println(bhp.get_ProcessingConfirmation());
		Thread.sleep(5000);
		
		System.out.println(bhp.get_BookingConfirmation());
		Assert.assertEquals("Booking Confirmation", bhp.get_BookingConfirmation());
		System.out.println("Success, its verified");
		
		LoginPage lp=new LoginPage(driver);
		lp.click_logout();
		lp.click_loginAgain();
		}
   
	   catch(Exception e)
		 {
		   Assert.fail();
		   		  
		 }
	  Thread.sleep(3000);
	  
    
	}
	
}	























