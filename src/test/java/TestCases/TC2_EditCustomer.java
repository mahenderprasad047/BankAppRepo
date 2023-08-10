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
import Pages.EditCustomer;
import Pages.HomePage;
import Pages.LoginPage;

public class TC2_EditCustomer extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void EditCustomers() throws InterruptedException
	{
		Reporter.LogEvent(driver, "Info", "Start of TC2_EditCustomer execution", "");
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginID"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		Assert.assertTrue(driver.getCurrentUrl().contains("Managerhomepage"),"URL mismatched after login to Application");
		Reporter.LogEvent(driver, "Done", "Step1", "Successfully logged in to application");
		
		HomePage home = new HomePage(driver);
		home.ClickOnEditCustomerLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("EditCustomer"),"URL mismatched after clicking on EditCustomer link");
		Reporter.LogEvent(driver, "Done", "Step2", "Successfully navigated to EditCustomer page");
		
		lst = TestDataProviderForTC.getDataFromExcel("TC2_EditCustomer","TestData1","NewCustomer");
		EditCustomer edt= new EditCustomer(driver);
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);			
			Assert.assertNotNull(edt.EditACustomer(map), "Unable to Edit the customer with ID - " + map.get("CustomerID"));
			Reporter.LogEvent(driver, "Info", "Step3", "Successfully updated details for customer with ID - " + map.get("CustomerID"));
			Common.WaitForFewSeconds(2);
		}
		
		Reporter.LogEvent(driver, "Pass", "End of TC2_EditCustomer execution", "");
	}
}
