package Pom;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import driverSetup.DriverFactory;

public class Home_Page {
	
	@FindBy (xpath = "//img[@alt='minimize']")
	private WebElement closePopup1;
	
	@FindBy (xpath = "//span[@data-cy='closeModal']")
	private WebElement closePopup2;
	
	@FindBy (xpath = "//span[text()='Trains']")
	private WebElement trainsBtn;
	
	@FindBy (xpath = "//a[@href='https://www.makemytrip.com/railways/']//span/span")
	private WebElement verifyTrainsBtn;
	
	
	
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	public Home_Page(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait (this.driver,Duration.ofSeconds(10));
	}
	
	public void verifyHomePageOpen(String url)
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.closePopup1));
		Assert.assertEquals(this.driver.getCurrentUrl(), url, "Home page url does not match");
	}
	
	public void clickOnclosePopup1()
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.closePopup1));
		closePopup1.click();
	}
	
	public void clickOnClosePopup2()
	{
		this.wait.until(ExpectedConditions.visibilityOf(this.closePopup2));
		closePopup2.click();
	}
	
	public void clickTrainsBtn()
	{
		this.wait.until(ExpectedConditions.elementToBeClickable(this.trainsBtn));
		this.trainsBtn.click();
		this.wait.until(ExpectedConditions.attributeContains
				(this.verifyTrainsBtn, "class", "active"));
		
		Assert.assertTrue(this.verifyTrainsBtn.getAttribute("class").contains("active")
				, "Trains button is not clickable");
	}
	
}
