package Pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightReviewPage {

	@FindBy(xpath = "//span[text()='No, Let Me Choose']")
	private WebElement letMeChooseBtn;
	
	@FindBy(xpath = "//div[@class='seatBlock pointer']")
	private List<WebElement> availableSeats;
	
	@FindBy (xpath = "//span[text()='Skip to cabs']")
	private WebElement skipToCabs ;
	
	@FindBy (xpath ="//span[text()='Skip to add-ons']")
	private WebElement skipToAddOns ; 
	
	@FindBy (xpath = "//button[text()='Continue']")
	private WebElement continueBtn ;
	
	@FindBy (xpath ="//button[text()='Proceed to pay']") 
	private WebElement proceedToPayBtn;

	private WebDriver driver;
	private WebDriverWait waits;

	public FlightReviewPage(WebDriver driver) 
	{

		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.waits = new WebDriverWait(this.driver, Duration.ofSeconds(10));

	}

	public void checkReviewPage() 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.letMeChooseBtn));
		this.letMeChooseBtn.click();

	}
	
	public void selectAvailableseat(int index) 
	{
		if(index >= 0 && index <= this.availableSeats.size())
		{
			WebElement availableSeat = this.availableSeats.get(index);
			this.waits.until(ExpectedConditions.elementToBeClickable(availableSeat));
			availableSeat.click();
		}
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}

	}
	
	public void clickSkipToCabs() 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.skipToCabs));
		this.skipToCabs.click();

	}
	
	public void clickSkipToAddOns() 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.skipToAddOns));
		this.skipToAddOns.click();

	}
	
	public void clickContinueBtn() 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.continueBtn));
		this.continueBtn.click();

	}
	
	public void clickProceedToPay() 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.proceedToPayBtn));
		this.proceedToPayBtn.click();

	}

}


