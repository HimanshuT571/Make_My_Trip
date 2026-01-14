package Pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectFlightPage {

	@FindBy (xpath = "//span[text()='VIEW PRICES']")
	private List<WebElement> flightLists ;
	
	@FindBy (xpath = "//button[text()='BOOK NOW']")
	private List <WebElement> fareOptions;
	
	private WebDriver driver ; 
	private WebDriverWait waits ;
	
	
	public SelectFlightPage (WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.waits = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}
	
	public void clickOnselectflight (int index) 
	{
		if(index >= 0 && index <= flightLists.size())
		{
			WebElement selectedFlight = flightLists.get(index);
			waits.until(ExpectedConditions.elementToBeClickable(selectedFlight));
			selectedFlight.click();
		}
		else 
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
		
	}
	
	public void viewFares (int index) 
	{
		if(index >= 0 && index <= fareOptions.size())
		{
			WebElement bookNow = this.fareOptions.get(index);
			waits.until(ExpectedConditions.elementToBeClickable(bookNow));
			bookNow.click();
		}
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
		
	}

}
