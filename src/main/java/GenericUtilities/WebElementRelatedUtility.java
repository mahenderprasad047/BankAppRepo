package GenericUtilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementRelatedUtility 
{

	public static void TypeUsingJavaScript(WebDriver driver, WebElement ele,String value)
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+value +"'",ele);
	}
	
	public static void ClickUsingJavaScript(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",ele);
	}
	
	public static void ClickAWebElement(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}catch(Exception e)
		{
			System.out.println("Element is not in clickable state");
		}
		
		try {
			ele.click();
		}catch(Exception e)
		{
			System.out.println("Cannot click the WebElement normally");
			try {
				Actions act = new Actions(driver);
				try {
					act.moveToElement(ele).click().build().perform();
				}catch(Exception e2)
				{
					
				}
			}catch(Exception e1)
			{
				System.out.println("Unable to click using Actions class");
			}
			
			
			
			
			
			
		}
	}
	
	
}
