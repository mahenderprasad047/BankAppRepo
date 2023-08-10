package Pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.Common;
import GenericUtilities.GenerateRandomValue;

public class AddNewCustomer 
{
	WebDriver driver;
	
	public AddNewCustomer(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By edtCustomerName = By.xpath("//input[@name='name']");
	By rdgGender_Male = By.xpath("//input[@value='m']");
	By rdgGender_Female= By.xpath("//input[@value='f']");
	By edtDateOfBirth= By.xpath("//input[@name='dob']");
	By edtAddress= By.xpath("//textarea[@name='addr']");
	By edtCity= By.xpath("//input[@name='city']");
	By edtState= By.xpath("//input[@name='state']");
	By edtPIN= By.xpath("//input[@name='pinno']");
	By edtMobileNumber= By.xpath("//input[@name='telephoneno']");
	By edtEmail= By.xpath("//input[@name='emailid']");
	By edtPassword= By.xpath("//input[@name='password']");
	By btnSubmit= By.xpath("//input[@value='Submit']");
	By btnReset= By.xpath("//input[@value='Reset']");
	
	By eleCustomerID = By.xpath("//td[text()='Customer ID']//following::td[1]");
	
	
	
	
	public WebDriver AddANewCustomer(Map<String,String> map)
	{
		String randomName = GenerateRandomValue.GenerateRandomString(7);
		String randomDigit = GenerateRandomValue.GenerateRandomNumber(5);
		
		
		Common.WaitForFewSeconds(1);
		driver.findElement(btnReset).click();
		Common.WaitForFewSeconds(1);
		
		driver.findElement(edtCustomerName).sendKeys( randomName+" " + map.get("CustomerName"));
		if(map.get("Gender").contains("Male"))
			driver.findElement(rdgGender_Male).click();
		else
			driver.findElement(rdgGender_Female).click();
			
		//driver.findElement(edtDateOfBirth).sendKeys(map.get("DateOfBirth"));
		String[] sTemp = map.get("DateOfBirth").split("-");
		driver.findElement(edtDateOfBirth).sendKeys(sTemp[0]);
		//driver.findElement(edtDateOfBirth).sendKeys(Keys.TAB);
		driver.findElement(edtDateOfBirth).sendKeys(sTemp[1]);
		//driver.findElement(edtDateOfBirth).sendKeys(Keys.TAB);
		driver.findElement(edtDateOfBirth).sendKeys(sTemp[2]);
		
		
		
		driver.findElement(edtAddress).sendKeys(map.get("Address"));
		driver.findElement(edtCity).sendKeys(map.get("City"));
		driver.findElement(edtState).sendKeys(map.get("State"));
		driver.findElement(edtPIN).sendKeys(randomDigit+map.get("PIN"));
		driver.findElement(edtMobileNumber).sendKeys(randomDigit+ map.get("PhoneNumber"));
		driver.findElement(edtEmail).sendKeys(randomName + map.get("Email"));
		driver.findElement(edtPassword).sendKeys(randomName + map.get("Password"));
		Common.WaitForFewSeconds(2);
		driver.findElement(btnSubmit).click();
		
		return driver;
	}
	
	public String GetCustomerIDFromCustomerRegisteredPage()
	{
		String sCustomerID = null;
		Common.WaitForFewSeconds(2);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
		}catch(TimeoutException e)
		{
			System.out.println("Alert did not appear");
		}
		
		sCustomerID = driver.findElement(eleCustomerID).getText();	
		return sCustomerID;
		
	}
}
