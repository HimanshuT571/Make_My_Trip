package hooks;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import core.ScreenshotUtility;
import driverSetup.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CoreHooks extends DriverFactory{
	
	private WebDriver driver;
	
	@Before
	public void beforeScenario()
	{
		 setup();
		 driver = DriverFactory.driver;
	}
	
	@After
	public void afterScenario(Scenario scenario) throws IOException
	{
		  System.out.println("Driver in After hook: " + DriverFactory.driver);
		try 
		{
			if(scenario.isFailed())
			{
				System.out.println("Scenario failed: " + scenario.getName());
				ScreenshotUtility.captureScreenshot(DriverFactory.driver, scenario.getName());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		DriverFactory.tearDown();
		System.out.println("Browser closed for scenario: " + scenario.getName());
	}

}
 