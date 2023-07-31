package TestCases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.Common;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.Reporter;
import GenericUtilities.TestDataProviderForTC;
import Pages.AddNewCustomer;
import Pages.HomePage;
import Pages.LoginPage;


public class DUmmyTC extends BaseClass
{
	
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void DummyTCtoCheckLogs() throws InterruptedException
	{
		Reporter.LogEvent(driver, "Info", "Start of TC0_CreateCustomer Execution", "");
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		List<String> lstCustID = new ArrayList<String>();
		
		int i;
		String sCustomerID;
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginID"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		Assert.assertTrue(driver.getCurrentUrl().contains("Managerhomepage"),"Login to application failed - URL mismatched");
		Reporter.LogEvent(driver, "Done", "Step1", "successfully logged in to application");
		
		HomePage home = new HomePage(driver);
		home.ClickOnNewCustomerLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("addcustomerpage"),"URL mismatched after clicking on 'New Customer' link");
		Reporter.LogEvent(driver, "Done", "Step2", "Add New Customer page appears");
		
		
		
		Reporter.LogEvent(driver, "Info", "", "End of TC0_CreateCustomer execution");
	}
}
