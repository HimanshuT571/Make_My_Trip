package Pom;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import driverSetup.DriverFactory;

public class TravellerPage {
	
	@FindBy (xpath = "//h3")
	private WebElement trainName;
	
	@FindBy (xpath = "//span[text()='Add Traveller']")
	private WebElement addTravellerBtn;
	
	@FindBy (xpath = "//input[@id='name']")
	private WebElement travellerName;
	
	@FindBy (xpath = "//input[@id='age']")
	private WebElement travellerAge;
	
	@FindBy (xpath = "//span[text()='Select']")
	private WebElement clickTravellerAge;
	
	@FindBy (xpath = "//div[contains(@class,'formField genderField ')]//ul/li")
	private List<WebElement> genders ;
	
	@FindBy (xpath = "//p[@class='appendBottom10']")
	private List<WebElement> times;
	
	@FindBy (xpath = "//div[@class=' column appendRight50']//p/span[1]")
	private WebElement trainNumber;
	
	@FindBy (xpath = "//span[text()='Add Traveller Information']")
	private WebElement travellerPopupText;
	
	@FindBy (xpath = "//span[text()='India']")
	private WebElement nationality;
	
	@FindBy (xpath = "//span[text()='No Berth Preference']")
	private WebElement clickBerth;
	
	@FindBy (xpath = "//button[text()='Add']")
	private WebElement addBtn;
	
	@FindBy (xpath = "//ul[@id='travellersData']//li")
	private WebElement verifyTravellersAdded;
	
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	public TravellerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver,Duration.ofSeconds(5));
	}
	
	public void verifyReviewPage(String expectedTrainDetails[])
	{
		SoftAssert softAssert = new SoftAssert();
		String selectedTrainNumber = this.trainNumber.getText()
				.replace("#","").replace("|", "").trim();
		
		Assert.assertTrue(
			    driver.getCurrentUrl().contains(selectedTrainNumber),
			    "Correct URL has not opened"
			);
		
		this.wait.until(ExpectedConditions.visibilityOf(this.trainName));
		String reviewTrainName = this.trainName.getText();
		
		this.wait.until(ExpectedConditions.visibilityOfAllElements(this.times));
		
		String reviewDepartureTime = this.times.get(0)
				.getText().toUpperCase().trim().replace(", 0", ",");
		
		String reviewArrivalTime = this.times.get(1)
				.getText().toUpperCase().trim().replace(", 0", ",");	
		
		String actualTrainDetails[] = {reviewTrainName, reviewDepartureTime, reviewArrivalTime};
		Assert.assertEquals( actualTrainDetails, expectedTrainDetails, "Train details do not match");
	}
	
	public void clickOnAddTraveller()
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.addTravellerBtn));
		addTravellerBtn.click();
		
		Assert.assertEquals
		(this.travellerPopupText.getText(), "Add Traveller Information", "popup has not opened");
	}
	
	public void enterTravellerName(String nameOfTraveller)
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.travellerName));
		travellerName.sendKeys(nameOfTraveller);
	}
	
	public void enterTravellerAge(String ageOfTraveller)
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.travellerAge));
		travellerAge.sendKeys(ageOfTraveller);
	}
	
	public void selectTravellerGender(int index) throws InterruptedException
	{
		Thread.sleep(3000);
		this.clickTravellerAge.click();
		 this.wait.until(ExpectedConditions.visibilityOfAllElements(genders));
		 if (index < 0 || index >= genders.size()) {
		        throw new IllegalArgumentException("Invalid gender index: " + index);
		    }
		 Thread.sleep(3000);
		WebElement travellerGender = genders.get(index);
		this.wait.until(ExpectedConditions.elementToBeClickable(travellerGender));
		travellerGender.click();
	}
	
	public String getNationalityText()
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.nationality));
		String nationalityText = nationality.getText();
		return nationalityText;
	}
	
	public void selectBerth(String berthPreference)
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.clickBerth));
		this.clickBerth.click();
		WebElement berth = this.wait.until
				(
				ExpectedConditions.elementToBeClickable
					(By.xpath("//span[text()='"+berthPreference+"']")
				)
		);
		berth.click();
	}
	
	public void selectMeal(String mealType)
	{
		try 
		{
			WebElement typeOfMeal = this.wait.until
					  (
						        ExpectedConditions.elementToBeClickable
						        (
						            By.xpath("//span[normalize-space()='" + mealType + "']")
				        		)
					  );
						    typeOfMeal.click();
		}
		catch(NoSuchElementException e)
		{
			//skip  selection
		}
		  
	}
	
	public void clickOnAddBtn() throws InterruptedException
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.addBtn));
		this.addBtn.click();
		boolean isAdded = this.verifyTravellersAdded.getAttribute("class").contains("active");
		Assert.assertTrue(isAdded);
		Thread.sleep(10000);
	}

}
