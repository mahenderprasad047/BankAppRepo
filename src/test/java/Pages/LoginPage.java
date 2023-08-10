package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By edtUserName = By.xpath("//input[@name='uid']");
	By edtPassword = By.name("password");
	By btnLogin = By.xpath("//input[@name='btnLogin']");
		
	public WebDriver LoginToApplication(String uName, String uPassword)
	{
		driver.findElement(edtUserName).sendKeys(uName);
		driver.findElement(edtPassword).sendKeys(uPassword);
		driver.findElement(btnLogin).click();
		
		return driver;
	}
	
	
	

}
