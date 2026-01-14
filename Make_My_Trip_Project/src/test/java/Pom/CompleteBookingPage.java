package Pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompleteBookingPage {
	
	@FindBy (xpath = "(//div[@id='INSURANCE']//span[@class='customRadioBtn sizeSm primary ']")
	private List<WebElement> secureTravelBtns ; // scroll into view 
	
	@FindBy (xpath = "//button[text()='+ ADD NEW ADULT']")
	private WebElement addNewAdult ; // scroll into view 
	
	@FindBy (xpath = "//input[@placeholder='First & Middle Name']")
	private WebElement firstName ;
	
	@FindBy (xpath = "//input[@placeholder='Last Name']")
	private WebElement lastName ;
	
	@FindBy (xpath = "//div[@class='adultListWrapper']//label/input")
	private List<WebElement> genders ;
	
	@FindBy (xpath = "//input[@placeholder='Mobile No']")
	private WebElement mobile ;
	
	@FindBy (xpath = "//input[@placeholder='Email']")
	private WebElement email ;
	
	@FindBy (xpath = "//div[@class='emailIds-dropdown']")
	private WebElement emailSelect ;
	
	@FindBy (xpath = "//button[text()='Continue']")
	private WebElement continueBtn;
	
	@FindBy (xpath = "//button[text()='CONFIRM']")
	private WebElement confirmBtn;
	
	private WebDriver driver ;
	private WebDriverWait waits ;
	
	public CompleteBookingPage(WebDriver driver) 
	{
		
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.waits = new WebDriverWait(this.driver, Duration.ofSeconds(10));	
	}
	
	public void clickonsecureBtn (int index) 
	{
		if(index >= 0 && index <= secureTravelBtns.size())
		{
			JavascriptExecutor js = (JavascriptExecutor)this.driver ;
			WebElement secureBtn = this.secureTravelBtns.get(index);
			this.waits.until(ExpectedConditions.elementToBeClickable(secureBtn));
			js.executeScript("arguments[0].scrollIntoView(true);", secureBtn);
			secureBtn.click();
		}
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
	}
	
	public void clickAddNewAdult () 
	{
		JavascriptExecutor js = (JavascriptExecutor)this.driver ;
		js.executeScript("arguments[0].scrollIntoView(true);", this.addNewAdult);	
		this.waits.until(ExpectedConditions.elementToBeClickable(this.addNewAdult));
		this.addNewAdult.click();
	}
	
	public void fillFirstname (String data) 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.firstName));
		this.firstName.sendKeys("shraddha");
	}
	
	public void fillLastname (String data) 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.lastName));
		this.lastName.sendKeys("mane");
	}
	
	public void selectGender (int index)
	{
		if(index >= 0 && index <= this.genders.size())
		{
			WebElement gender = this.genders.get(index);
			this.waits.until(ExpectedConditions.elementToBeClickable(gender));
			gender.click();
		}
		else
		{
			throw new IllegalArgumentException("Invalid class index: " + index);
		}
	}
	
	public void EnterMobile () 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.mobile));
		this.mobile.sendKeys("8766450101");
	}
	
	public void EnterEmail () 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.email));
		this.email.sendKeys("shraddhamane182000@gmail.comm");
		this.emailSelect.click();
	}
	
	public void clickContinueBtn () 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.continueBtn));
		this.continueBtn.click();
	}
	
	public void clickOnconfirm () 
	{
		this.waits.until(ExpectedConditions.elementToBeClickable(this.confirmBtn));
		this.confirmBtn.click();
	}
	
}


