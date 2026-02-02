package core;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;

public class ScreenshotUtility {
	
	public static void captureScreenshot(WebDriver driver, String scenarioName) throws IOException
	{
		LocalDateTime currentDate =  LocalDateTime.now();
		
		String formatedDate = currentDate.format(DateTimeFormatter.ofPattern
				("dd MM yyyy HH_mm_ss"));
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		try 
		{
			byte[] screenshotTaken = screenshot.getScreenshotAs(OutputType.BYTES);
			scenarioName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_");
			String screenshotName = formatedDate + " "+scenarioName;
	        Allure.addAttachment(scenarioName + " - Screenshot", "image/png",
	                new ByteArrayInputStream(screenshotTaken), ".png");
	        System.out.println("Screenshot attached to Allure for scenario: " + scenarioName);
		}
		catch(Exception e)
		{
			System.err.println("Screenshot failed: " + e.getMessage());
		}
	}
}
