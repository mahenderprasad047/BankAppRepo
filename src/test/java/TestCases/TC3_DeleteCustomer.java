package TestCases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.SwitchToUtility;
import GenericUtilities.Common;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.Reporter;
import GenericUtilities.TestDataProviderForTC;
import Pages.DeleteCustomer;
import Pages.EditCustomer;
import Pages.HomePage;
import Pages.LoginPage;

public class TC3_DeleteCustomer extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	void DeleteCstomer() throws InterruptedException
	{
		Reporter.LogEvent(driver, "Info", "Start of TC3_DeleteCustomer execution", "");
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginID"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		Assert.assertTrue(driver.getCurrentUrl().contains("Managerhomepage"),"URL mismatched after login to Application");
		Reporter.LogEvent(driver, "Done", "Step1", "Successfully logged in to application");
		
		HomePage home = new HomePage(driver);
		home.ClickOnDeleteCustomerLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("DeleteCustomerInput"),"URL mismatched after clicking on DeleteCustomer link");
		Reporter.LogEvent(driver, "Done", "Step2", "Successfully navigated to DeleteCustomer page");
		
		lst = TestDataProviderForTC.getDataFromExcel("TC3_DeleteCustomer","TestData1","NewCustomer");
		DeleteCustomer delete = new DeleteCustomer(driver);
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Assert.assertNotNull(delete.DeleteACustomer(map), "Unable to delete the customer with ID - " + map.get("CustomerID"));
			Reporter.LogEvent(driver, "Info", "Step3", "Successfully deleted the customer with ID - " + map.get("CustomerID"));
		}
		
		Reporter.LogEvent(driver, "Pass", "End of TC3_DeleteCustomer execution", "");
		
		
	}
}
