package Pom;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import driverSetup.DriverFactory;

public class SelectTrainPage {
	
	@FindBy (xpath = "//label[@for='Available']//p[text()='Available']")
	private WebElement availableBtn ;
	
	@FindBy (xpath = "//div[@data-testid='listing-card']//div[contains(@style,'pointer-events: auto')]")
	private WebElement firstTrain;	
	
	@FindBy (xpath = "//div[@data-testid='listing-card']")
	List<WebElement> trainsListed;
	
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	public SelectTrainPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver,Duration.ofSeconds(10));
	}
	
	public void verifyTrainsPage(String expectedPageTitle)
	{
		this.wait.until(ExpectedConditions.titleContains("Trains"));
		String actualPageTitle = driver.getTitle();
		Assert.assertEquals(actualPageTitle, expectedPageTitle, "Train search page has not changed");
	}
	
	public void clickOnAvailable() throws InterruptedException
	{
		SoftAssert softAssert = new SoftAssert();
		
		List<WebElement> availableFilters =
	            driver.findElements(By.xpath("//ul//li[@class='appendBottom20']"));

		for(WebElement filter : availableFilters)
		{
			if(filter.getText().toLowerCase().contains("available"))
			{
				this.wait.until(ExpectedConditions.elementToBeClickable(this.availableBtn));
				this.availableBtn.click();
			}
		}
				
				boolean isSelected = true;
				Thread.sleep(5000);
				for(WebElement trainN : this.trainsListed)
				{
					String text = trainN.getText();
					if(text.contains("GNWL") 
							|| text.contains("RLWL") 
							|| text.contains("Booking not allowed"))
					{
						 isSelected = false ;
						 break;
					}
				}
				
				softAssert.assertTrue(isSelected, "Available button is not selected");
			
	}
	
	public String[] selectTrain(String availability, String waiting) throws InterruptedException
	{
		
		String trainNameText = null;
		String departureTime = null;
		String arrivalTime = null;
		
		this.wait.until(ExpectedConditions.visibilityOfAllElements(this.trainsListed));
		
		for(WebElement trainN : this.trainsListed)
		{
			try 
			{
				WebElement trainClassCard = trainN.findElement
						(By.xpath(".//div[@class='Cards_cardSection__wZahV']"));
				
				if(trainClassCard.getText().contains(availability)
						|| trainClassCard.getText().contains(waiting))
				{
					
					WebElement selectedTrainName = trainN.findElement
							(By.xpath(".//p[@data-testid='train-name']"));
					
					this.wait.until(ExpectedConditions.visibilityOf(selectedTrainName));
					
					trainNameText = selectedTrainName.getText().trim();
					
					List<WebElement> times = trainN.findElements
							(By.xpath(".//p[@class='appendBottom8']"));
					
					this.wait.until(ExpectedConditions.visibilityOfAllElements(times));
					
					departureTime = times.get(0).getText().trim();
					
					arrivalTime = times.get(2).getText().trim();
					Thread.sleep(5000);
					JavascriptExecutor js = (JavascriptExecutor) this.driver;
					js.executeScript("arguments[0].scrollIntoView(true);", trainClassCard);
					js.executeScript("window.scrollBy(0,-100);");
					Thread.sleep(5000);
					trainClassCard.click();
					
					break;
				}
				
			}
			catch(NoSuchElementException e)
			{
				//skipping of element
			}
			
		}
		String expectedTrainDetails[] = {trainNameText, departureTime, arrivalTime};
		return expectedTrainDetails;
		
	}
	
}
