package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import pageObjects.SelectHotelPage;
import testBase.BaseClass;

public class TC103_SelectHotelTest extends BaseClass{

	@BeforeMethod
	public void info_searchHotel() 
	{
		
		LoginPage lp=new LoginPage(driver);
		lp.set_username(prop.getProperty("username"));
		lp.set_password(prop.getProperty("password"));
		lp.click_login();
		
		
		
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("London");
		shp.select_Hotels("Hotel Creek");
		shp.select_roomType("Double");
		shp.select_no_of_rooms(2);
		shp.set_checkInDate("10/12/2025");
		shp.set_checkOutDate("12/12/2025");
		
		shp.click_Search();
		
	}
	
	@AfterMethod
	public void logout() 
	{ 
		LoginPage lp=new LoginPage(driver);
		
		lp.click_logout();
		lp.click_loginAgain();
	}
	
	@Test (priority=1, groups="Regression")
	public void valid_selectHotel() 
	{
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_radionButton();	
		selctHotPage.click_Continue();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("BookHotel.php"));
		System.out.println("Booking page is verified. . ");	
	}
	
	@Test (priority=2, groups="Regression")
	public void validate_searchhotel() 
	{
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_radionButton();
		
		System.out.println("Location of hotel is: " +selctHotPage.get_Location());
		Assert.assertEquals("London", selctHotPage.get_Location());
		System.out.println("Location validated");
		
		Assert.assertEquals("Hotel Creek", selctHotPage.get_hotelName());
		System.out.println("Hotel Name verified");	
				
	}
	
	@Test (priority=3, groups="Regression")
	public void withoutSelect_hotel() 
	{		
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_Continue();		
		
	}
	
	@Test (priority=4, groups="Regression")
	public void priceCalculation() 
	{
		
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		double price_pernight=selctHotPage.get_pricePerNight();
		double totalprice=selctHotPage.get_totalPrice();
		int noOfRooms=selctHotPage.get_noOfRooms();
		int noOfDays=selctHotPage.get_noOfDays();
		double expectedPrice=price_pernight*noOfRooms*noOfDays;
		System.out.println("Total price: "+totalprice);
		System.out.println("No of Rooms: "+noOfRooms);
		System.out.println("No of Days: " + noOfDays);
		Assert.assertEquals(expectedPrice, totalprice);
		System.out.println("Amount Validated");
				
	}


	
}
