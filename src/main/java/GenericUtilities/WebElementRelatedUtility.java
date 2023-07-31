package GenericUtilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	
}
