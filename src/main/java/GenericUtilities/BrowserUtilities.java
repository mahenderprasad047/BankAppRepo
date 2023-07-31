package GenericUtilities;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtilities 
{
	static WebDriver driver;
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static WebDriver startBrowser(String browserName, String url) throws InterruptedException
	{
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			
			ChromeOptions opt = new ChromeOptions();
			//opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			opt.addExtensions(new File("./Extensions/AdBlocker.crx"));
			driver = new ChromeDriver(opt);
			Thread.sleep(4000);
			Set<String> s=driver.getWindowHandles();
			Iterator<String> I1= s.iterator();
			while(I1.hasNext())
			{
				String child_window=I1.next();
				driver.switchTo().window(child_window);
				//System.out.println("Tiles" + driver.switchTo().window(child_window).getTitle());
			}
			
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Wrong choice");
			return null;
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));	//For Browser to load
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	//For every WebElement
		driver.get(url);
		return driver;		
	}
	
	public static void CloseBrowser(WebDriver driver)
	{
		driver.quit();
		System.out.println("Browser closed");
	}
	
	
}
