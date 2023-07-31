package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import GenericUtilities.Common;

public class HomePage 
{

	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	/////
	
	By lnkNewCustomer = By.xpath("//a[text()='New Customer']");
	By lnkEditCustomer = By.xpath("//a[text()='Edit Customer']");
	By lnkDeleteCustomer = By.xpath("//a[text()='Delete Customer']");
	
	
	public WebDriver ClickOnNewCustomerLink()
	{
		Common.WaitForFewSeconds(1);
		driver.findElement(lnkNewCustomer).click();
		Common.WaitForFewSeconds(1);		
		return driver;
	}
	
	public WebDriver ClickOnEditCustomerLink()
	{
		driver.findElement(lnkEditCustomer).click();
		
		return driver;
	}
	
	public WebDriver ClickOnDeleteCustomerLink()
	{
		driver.findElement(lnkDeleteCustomer).click();
		
		return driver;
	}
}
