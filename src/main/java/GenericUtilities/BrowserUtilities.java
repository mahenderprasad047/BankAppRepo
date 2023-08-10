package GenericUtilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtilities 
{
	static WebDriver driver;
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static WebDriver startBrowser(String browserName, String url) throws InterruptedException, MalformedURLException
	{
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			
			ChromeOptions opt = new ChromeOptions();
			//opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			opt.addExtensions(new File("./Extensions/AdBlocker.crx"));
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.merge(opt);
			//driver = new ChromeDriver(opt);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));	//For Browser to load
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	//For every WebElement
			
			Thread.sleep(4000);
			Set<String> s=driver.getWindowHandles();
			Iterator<String> I1= s.iterator();
			while(I1.hasNext())
			{
				String child_window=I1.next();
				driver.switchTo().window(child_window);
				System.out.println("Title - " + driver.switchTo().window(child_window).getTitle());
			}
			driver.get(url);
			
			
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
		
		System.out.println("Application launched");
		return driver;		
	}
	
	public static void CloseBrowser(WebDriver driver)
	{
		driver.quit();
		System.out.println("Browser closed");
	}
	
	
}
