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
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtilities 
{
	
	ThreadLocal<WebDriver> tl=new ThreadLocal<>();
	
	WebDriver driver;
	
	public WebDriver getDriver()
	{
		return tl.get();
	}
	
	public WebDriver startBrowser(String browserName, String url) throws InterruptedException, MalformedURLException
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
			driver = new ChromeDriver(opt);
			tl.set(driver);
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));	//For Browser to load
			getDriver().manage().deleteAllCookies();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	//For every WebElement
			
			Thread.sleep(4000);
			Set<String> s=driver.getWindowHandles();
			Iterator<String> I1= s.iterator();
			while(I1.hasNext())
			{
				String child_window=I1.next();
				getDriver().switchTo().window(child_window);
				System.out.println("Title - " + driver.switchTo().window(child_window).getTitle());
			}
			getDriver().get(url);
			
			
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions opt = new EdgeOptions();
			//opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			opt.addExtensions(new File("./Extensions/AdBlocker.crx"));
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("edge");
			cap.merge(opt);
			driver = new EdgeDriver(opt);
			tl.set(driver);
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));	//For Browser to load
			getDriver().manage().deleteAllCookies();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	//For every WebElement
			Thread.sleep(4000);
			Set<String> s=driver.getWindowHandles();
			Iterator<String> I1= s.iterator();
			while(I1.hasNext())
			{
				String child_window=I1.next();
				getDriver().switchTo().window(child_window);
				System.out.println("Title - " + driver.switchTo().window(child_window).getTitle());
			}
			getDriver().get(url);
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			FirefoxOptions opt = new FirefoxOptions();
			//opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			//opt.addExtensions(new File("./Extensions/AdBlocker.crx"));
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.merge(opt);
			driver = new FirefoxDriver(opt);
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
		else
		{
			System.out.println("Wrong choice");
			return null;
		}
		
		System.out.println("Application launched");
		return driver;		
	}
	
	
	public void CloseBrowser(WebDriver driver)
	{
		tl.get().quit();
		System.out.println("Browser closed");
	}
	
	
}
