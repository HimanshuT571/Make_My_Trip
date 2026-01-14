package core;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtility {
	
	public static void captureScreenshot(WebDriver driver, String scenarioName) throws IOException
	{
		LocalDateTime currentDate =  LocalDateTime.now();
		
		String formatedDate = currentDate.format(DateTimeFormatter.ofPattern
				("dd MM yyyy HH_mm_ss"));
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		scenarioName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_");
		String screenshotName = formatedDate + " "+scenarioName;
		
		File dest = new File("test-output/screenshots/"+screenshotName+".png");
		
		FileHandler.copy(src, dest);
	}

}
