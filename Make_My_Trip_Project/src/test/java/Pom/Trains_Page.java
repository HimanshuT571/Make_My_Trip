package Pom;

import java.time.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import driverSetup.DriverFactory;

public class Trains_Page {
	
	@FindBy (xpath = "//span[@data-cy='bookTrainTickets']")
	private WebElement bookTrainTickets;
	
	@FindBy (xpath = "//input[@placeholder='From']")
	private WebElement enterBoardingCityName;
	
	@FindBy (xpath = "//input[@id='fromCity']")
	private WebElement boardingCityName;
	
	@FindBy (xpath = "//label[@for='fromCity']//p")
	private WebElement boardingStnName;
	
	@FindBy (xpath = "//input[@title='To']")
	private WebElement destCity;
	
	@FindBy (xpath = "")
	private List<WebElement> destinationStnNamesList;

	@FindBy (xpath = "//label[@for='toCity']//p")
	private WebElement destinationStnName;
	
	@FindBy (id = "toCity")
	private WebElement destinationCityName;
	
	@FindBy (xpath = "//span[@aria-label='Next Month']")
	private WebElement nextMonthBtn;
	
	@FindBy (xpath = "//label[@for='travelDate']//p/span")
	private WebElement dayOnUi;
	
	@FindBy (xpath = "//label[@for='travelDate']//p/span[2]")
	private WebElement shortMonthOnUi;
	
	@FindBy (xpath = "//label[@for='travelDate']//span[@class='shortYear']")
	private WebElement shortYearOnUi;
	
	@FindBy (xpath = "//p[@data-cy='departureDay']")
	private WebElement travelDayOnUi;
	
	@FindBy (xpath = "//ul[@class='travelForPopup']/li")
	private List<WebElement> classes;  
	
	@FindBy (xpath = "//div[@data-cy='classValue']//p")
	private WebElement classShortNameUi;
	
	@FindBy (xpath = "//p[@data-cy='classText']")
	private WebElement classFullNameUi;
	
	@FindBy (xpath = "//a[text()='Search']")
	private WebElement searchBtn;
	
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	private WebElement travelDate;
	
	
	public Trains_Page(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.wait = new WebDriverWait (this.driver,Duration.ofSeconds(10));
		
	}
	
	//To check the BookTrainTickets button is selected
	public void bookTrainTicketsSelected()
	{
		this.wait.until(ExpectedConditions.attributeContains(
				this.bookTrainTickets, "class", "active"));
		boolean isActive = this.bookTrainTickets.getAttribute("class").contains("active");
		Assert.assertTrue(isActive, "Book Trains Ticket is not selected ");
	}
	
	// Input of boarding city
	public void enterBoardingCity(String boardingCityName) throws InterruptedException
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.boardingCityName));
		this.boardingCityName.click();
		this.wait.until(ExpectedConditions.elementToBeClickable(this.enterBoardingCityName));
		Thread.sleep(3000);
		this.enterBoardingCityName.sendKeys(boardingCityName);
	}
	
	//Select the boarding city from suggestions
	public void selectCityToBoard(String boardingStn) throws InterruptedException
	{
		Thread.sleep(3000);
		List<WebElement> boardingCityNames = driver.findElements
				(By.xpath("//ul[@role='listbox']//li"));
		
		this.wait.until(ExpectedConditions.visibilityOfAllElements
				(boardingCityNames));
		
		for (int i = 0; i < boardingCityNames.size(); i++) 
		{
			
		    String text = boardingCityNames.get(i).getText();
		    System.out.println(text);

		    if (text.toLowerCase().trim().contains(boardingStn.toLowerCase().trim())) 
		    {
		    	WebElement boardingStation = boardingCityNames.get(i);
		        boardingStation.click();
		        break;
		    }
		}
	}
	
	public void verifySelectedBoardingStn(String stationName, String city)
	{
		this.wait.until(ExpectedConditions.attributeContains(
				this.boardingCityName, "value", city));
		
		String boardingCityOnUi = this.boardingCityName.getAttribute("value");
		
		Assert.assertTrue(boardingCityOnUi.contains(city));
	
		this.wait.until(ExpectedConditions.textToBePresentInElement
				(this.boardingStnName, stationName));
		
		String boardingStnOnUi = this.boardingStnName.getText();
		
		Assert.assertTrue(boardingStnOnUi.contains
				(stationName), "Boarding station not matched");	
	}
	
	//Input of destination city
	
	public void enterDestinationCity(String destinationCityName) throws InterruptedException
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.destCity));
		Thread.sleep(3000);
		this.destCity.sendKeys(destinationCityName);
	}
	
	public void selectCityToTravel(String destStn) throws InterruptedException
	{
		Thread.sleep(3000);
		List<WebElement> destinationCityNames = driver.findElements
				(By.xpath("//ul[@role='listbox']//li"));
		
		this.wait.until(ExpectedConditions.visibilityOfAllElements
				(destinationCityNames));
		
		for (int i = 0; i < destinationCityNames.size(); i++) 
		{
			
		    String text = destinationCityNames.get(i).getText();
		    System.out.println(text);

		    if (text.toLowerCase().trim().contains(destStn.toLowerCase().trim())) 
		    {
		    	WebElement destinationStation = destinationCityNames.get(i);
		    	destinationStation.click();
		        break;
		    }
		}
		
		
	}
	
	public void verifySelectedDestStn(String destStn,String city)
	{
		this.wait.until(ExpectedConditions.attributeContains
				(this.destinationCityName, "value", city));
		
		String destinationCityOnUi = this.destinationCityName.getAttribute("value");
		
		Assert.assertTrue(destinationCityOnUi.contains(city));
		
		this.wait.until(ExpectedConditions.textToBePresentInElement
				(this.destinationStnName, destStn));
		
		String destinationStnOnUi = this.destinationStnName.getText();
		
		Assert.assertTrue(destinationStnOnUi.contains
				(destStn), "Destination station not matched");
	}
	
	
	
	// Selection of the travel date
	public void selectDate(int days)
	{
		//Verify calendar is opened
		this.wait.until(ExpectedConditions.attributeContains(
				this.nextMonthBtn, "class", "next"));
		
		Assert.assertTrue(this.nextMonthBtn.getAttribute("class").contains("next"), 
				"Calendar not opened");
		
		LocalDate futureDate = LocalDate.now().plusDays(days); 
		
		String formatDate = futureDate.format(DateTimeFormatter.ofPattern
				("EEE MMM dd yyyy"));
		
		By dateLocator = By.xpath("//div[@aria-label='"+formatDate+"']");
		
		this.travelDate = this.wait.until(
				ExpectedConditions.elementToBeClickable(dateLocator));	
		
		this.travelDate.click();
		this.wait.until(ExpectedConditions.textToBePresentInElement(travelDayOnUi,
				formatDate.substring(0, 3)));
		
		String expectedDate = futureDate.format(DateTimeFormatter.ofPattern
				("EEE MMM d yy"));
		
		String dateOnUi = this.travelDayOnUi.getText().trim().substring(0, 3)+" "	
						 +this.shortMonthOnUi.getText()+" "
						 +this.dayOnUi.getText()+" "
						 +this.shortYearOnUi.getText();
		Assert.assertEquals(dateOnUi, expectedDate,"Selected date not matched on UI");
	}
	
	//Select the class of seat to book
	
	public void selectClass(String className)
	{
		boolean status = false;
		
		for(WebElement selectClass : classes)
		{
			String classFullName = selectClass.getText();
			String classShortForm = selectClass.getAttribute("data-cy");
			
			if(classFullName.equalsIgnoreCase(className))
			{
				this.wait.until(ExpectedConditions.elementToBeClickable(selectClass));
				
				selectClass.click();
				
				this.wait.until(ExpectedConditions.textToBePresentInElement(
						this.classFullNameUi, classFullName));
				
				this.wait.until(ExpectedConditions.textToBePresentInElement(
						this.classShortNameUi, classShortForm));
				
				if(classFullName.equals(this.classFullNameUi.getText().trim()) && 
						classShortForm.equals(this.classShortNameUi.getText().trim()))
				{
					status = true;
				}	
				break;
			}
		}
		Assert.assertTrue(status,"Train class is not selected or not shown on UI");
		
	}
	
	// to search the train for the inputs provides
	public void clickSearchBtn()
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		this.searchBtn.click();
	}

}

