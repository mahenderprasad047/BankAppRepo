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

public class EditCustomer 
{

	WebDriver driver;
	
	public EditCustomer(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By edtCustomerID = By.xpath("//input[@name='cusid']");
	By btnSubmit = By.xpath("//input[@name='AccSubmit']");
	By btnReset = By.xpath("//input[@name='res']");
	
	By edtAddress= By.xpath("//textarea[@name='addr']");
	By edtCity= By.xpath("//input[@name='city']");
	By edtState= By.xpath("//input[@name='state']");
	By edtPIN= By.xpath("//input[@name='pinno']");
	By btnSubmit1= By.xpath("//input[@value='Submit']");
	
	public WebDriver EditACustomer(Map<String,String> map)
	{
		driver.findElement(edtCustomerID).sendKeys(map.get("CustomerID"));
		Common.WaitForFewSeconds(1);
		driver.findElement(btnSubmit).click();
		Common.WaitForFewSeconds(3);
		
		driver.findElement(edtAddress).clear();
		driver.findElement(edtAddress).findElement(edtAddress).sendKeys(map.get("NewAddress"));
		driver.findElement(edtCity).clear();
		driver.findElement(edtCity).sendKeys(map.get("NewCity"));
		driver.findElement(edtState).clear();
		driver.findElement(edtState).sendKeys(map.get("NewState"));
		driver.findElement(edtPIN).clear();
		driver.findElement(edtPIN).sendKeys(map.get("newPIN"));
		Common.WaitForFewSeconds(2);
		driver.findElement(btnSubmit1).click();		
		Common.WaitForFewSeconds(2);
		
		Alert alt;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			alt= wait.until(ExpectedConditions.alertIsPresent());
		}catch(TimeoutException e)
		{
			System.out.println("Timeout - Alert did not appear");
			return null;
		}
		System.out.println("Alert appeared.Alert Text - "+ alt.getText());
		alt.accept();
		Common.WaitForFewSeconds(2);
		
		return driver;
	}
}
