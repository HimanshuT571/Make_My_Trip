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

public class FlightsPage {
	
	@FindBy(xpath = "//li[@data-cy='menu_Flights']//a")
	private WebElement flights  ;
	
	@FindBy(xpath = "//li[@data-cy='oneWayTrip']")
	private WebElement oneWay  ;
	
	@FindBy(xpath = "//input[@placeholder='From']")
	private WebElement boardingCity ;
	
	@FindBy(xpath = "//ul[@role='listbox']//li")
	private List<WebElement> suggestionsFromCities; 
	
	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement destinationCity ;
	
	@FindBy(xpath = "//ul[@role='listbox']//li")
	private List <WebElement> suggestionsToCities; 
	
	@FindBy(css ="input[id='departure']")
	private WebElement departure  ;
	
	@FindBy(css ="input[id='travellers']")
	private WebElement travellers  ;
	
	@FindBy(xpath ="//li[contains(@data-cy,'adults')]")
	private List<WebElement> adults  ;
	
	@FindBy(xpath ="//li[contains(@data-cy,'children')]")
	private List<WebElement> selectChildren  ;
	
	@FindBy(xpath ="//li[contains(@data-cy,'travelClass')]")
	private List<WebElement> travelClasses  ;
	
	@FindBy(xpath ="//button[text()='APPLY']")
	private WebElement applyBtn ;
	
	@FindBy(xpath ="//a[text()='Search']")
	private WebElement search  ;
	
	private WebDriver driver ; 
	private WebDriverWait wait ; 
	
	public  FlightsPage(WebDriver driver) 
	{ 
		
		PageFactory.initElements(driver, this);
		this.driver = driver ; 
		this.wait = new WebDriverWait (this.driver ,Duration.ofSeconds(10));
	}
	
	public boolean selectFlights()
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.flights));
		boolean isSelected = this.flights.getAttribute("class").contains("active");
		if(!isSelected)
		{
			this.flights.click();
		}
		return isSelected;
	}
	
	public boolean selectOneway() 
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.oneWay));
		boolean isSelected = this.flights.getAttribute("class").contains("selected");
		if(!isSelected)
		{
			this.flights.click();
		}
		return isSelected;
	}
	
	public void enterFromCity (String fromCityName)
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.boardingCity));
		this.boardingCity.sendKeys(fromCityName);
	}
	
	public void selectFromCity (int index) 
	{
		if(index >= 0 && index <= suggestionsFromCities.size())
	    {
			WebElement selectedFromCity = this.suggestionsFromCities.get(index);
			this.wait.until(ExpectedConditions.elementToBeClickable(selectedFromCity));
		    selectedFromCity.click();
	    }
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
	    
	}
	
	public void enterToCity (String destinationCity)
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.destinationCity));
		this.destinationCity.sendKeys(destinationCity);;
	}
	
	public void selectDestinationCity (int index) 
	{	
		if(index >= 0 && index <= suggestionsToCities.size())
		{
		WebElement toCity = this.suggestionsToCities.get(index);
	    this.wait.until(ExpectedConditions.elementToBeClickable(toCity));
	    toCity.click();
		}
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
	}
	
	public void selectDate (int index)
	{
		LocalDate date = LocalDate.now(); 
		LocalDate futureDate = date.plusDays(index); 
		String formatDate = futureDate.format(DateTimeFormatter.ofPattern("EE MMM dd yyyy"));
		System.out.println(formatDate);
		WebElement travelDate = this.driver.findElement(By.xpath("//div[@aria-label='"+formatDate+"']")); 
		this.wait.until(ExpectedConditions.elementToBeClickable(travelDate));
		travelDate.click();	
	}
	
	public void selecttraveller ()
	{
		this.wait.until(ExpectedConditions.visibilityOfAllElements(this.travellers));
		travellers.click();	
	}
	
	public void selectAdults (int index)
	{
		if(index >= 0 && index <= adults.size())
		{
			WebElement adult = this.adults.get(index);
			this.wait.until(ExpectedConditions.elementToBeClickable(adult));
			adult.click();
		}
		else
		{
			 throw new IllegalArgumentException("Invalid class index: " + index);
		}
		
	}
	
	public void choosePeople (int index) 
	{
		if(index >= 0 && index <= selectChildren.size())
		{
			WebElement child = this.selectChildren.get(index);
		    this.wait.until(ExpectedConditions.elementToBeClickable(child));
		    child.click();
		}
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
	    
	}
	
	public void chooseTravelClass (int index) 
	{
		if(index >= 0 && index <= travelClasses.size())
		{
			WebElement travelClass = this.travelClasses.get(index);
		    this.wait.until(ExpectedConditions.elementToBeClickable(travelClass));
		    travelClass.click();
		}
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
	    
	}
	
	public void clickOnApplyBtn () 
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.applyBtn));
		this.applyBtn.click();
	}
	
	public void clickOnsearch () 
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.search));
		this.search.click();
	}
	
}

