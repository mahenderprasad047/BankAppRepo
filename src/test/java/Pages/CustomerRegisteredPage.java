package Pages;

import org.openqa.selenium.WebDriver;

public class CustomerRegisteredPage 
{

WebDriver driver;
	
	public CustomerRegisteredPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	/*By eleCustomerID = By.xpath("//td[text()='Customer ID']//following::td[1]");
	
	public String GetCustomerIDFromCustomerRegisteredPage()
	{
		return driver.findElement(eleCustomerID).getText();
		
	}*/
	
}
