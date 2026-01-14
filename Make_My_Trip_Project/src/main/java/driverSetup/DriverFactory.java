package driverSetup;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.ConfigReader;
import config.EnvironmentConfig;

public class DriverFactory {
	
	public static WebDriver driver;
	
	public static EnvironmentConfig environmentConfig;
	
	public void setup()
	{
		environmentConfig = ConfigReader.getActiveEnvironment();
		
		if(environmentConfig.getBrowserName().equals("chrome"))
		{
			driver = new ChromeDriver();
			
		}
		
		if(environmentConfig.getBrowserName().equals("firefox"))
		{
			driver = new FirefoxDriver();
			
		}
		
		if(environmentConfig.getBrowserName().equals("edge"))
		{
			driver = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(environmentConfig.getBaseUrl());
	}
	
	public static void tearDown()
	{
		if(driver != null)
		{
			driver.quit();
		}
	}

}
