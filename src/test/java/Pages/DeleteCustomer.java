package Pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.Common;

public class DeleteCustomer 
{

	WebDriver driver;
	
	public DeleteCustomer(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By edtCustomerID = By.xpath("//input[@name='cusid']");
	By btnSubmit = By.xpath("//input[@name='AccSubmit']");
	By btnReset = By.xpath("//input[@name='res']");
	
	public WebDriver DeleteACustomer(Map<String,String> map)
	{
		Common.WaitForFewSeconds(2);
		driver.findElement(edtCustomerID).sendKeys(map.get("CustomerID"));
		Common.WaitForFewSeconds(1);
		driver.findElement(btnSubmit).click();
		Common.WaitForFewSeconds(2);
		Alert alt;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			alt= wait.until(ExpectedConditions.alertIsPresent());
		}catch(TimeoutException e)
		{
			System.out.println("Timeout - 1st Alert did not appear");
			return null;
		}
		System.out.println("1st Alert appeared.Alert Text - "+ alt.getText());
		alt.accept();
		
		Common.WaitForFewSeconds(2);
		
		try {
			alt= wait.until(ExpectedConditions.alertIsPresent());
		}catch(TimeoutException e)
		{
			System.out.println("Timeout - 2nd Alert did not appear");
			return null;
		}
		System.out.println("2nd Alert appeared.Alert Text - "+ alt.getText());
		alt.accept();
		
		return driver;
	}
}
