package stepDef;

import Pom.Home_Page;
import Pom.SelectTrainPage;
import Pom.Trains_Page;
import Pom.TravellerPage;
import driverSetup.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class TrainsStepDef extends DriverFactory {
	
	private Home_Page homePage;
	
	private Trains_Page trainsPage;
	
	private SelectTrainPage selectTrainPage;
	
	private TravellerPage travellerPage;
	
	private String[] expectedTrainDetails; 
	
	@Given("User click on trains button")
	public void clickTrainsBtn()
	{
		homePage = new Home_Page(driver);
		trainsPage = new Trains_Page(driver);
		selectTrainPage = new SelectTrainPage(driver);
		travellerPage = new TravellerPage(driver);
		homePage.verifyHomePageOpen("https://www.makemytrip.com/");
		homePage.clickOnclosePopup1();
		homePage.clickOnClosePopup2();
		homePage.clickTrainsBtn();
	}
	
	@Given("User click on Book Train Tickets")
	public void clickBookTrainTicketsBtn()
	{
		trainsPage.bookTrainTicketsSelected();
	}

	@When("User enter the {string} in from station tab")
	public void enterBoardingStn(String boardingCityName) throws InterruptedException
	{
		trainsPage.enterBoardingCity(boardingCityName);
	}
	
	@When("User select {string} of city {string} from the suggestions")
	public void selectBoardingStn(String stationName, String city) throws InterruptedException
	{
		trainsPage.selectCityToBoard(stationName);
		trainsPage.verifySelectedBoardingStn(stationName, city);
	}
	
	@When("User enter the {string} in To station tab")
	public void enterArrivalStn(String arrivalCityName) throws InterruptedException
	{
		trainsPage.enterDestinationCity(arrivalCityName);
	}
	
	@When("User select the {string} in the city {string} from the suggestions")
	public void selectArrivalStn(String stationName, String city) throws InterruptedException
	{
		trainsPage.selectCityToTravel(stationName);
		trainsPage.verifySelectedDestStn(stationName, city);
	}
	
	@When("User select the travel date {int} days from current date from calendar")
	public void selectTheTravelDate(int index)
	{
		trainsPage.selectDate(index);
	}
	
	@When("User select {string} from the class dropdown")
	public void selectTheSeatClass(String className)
	{
		trainsPage.selectClass(className);
	}
	
	@When("User click on search button")
	public void clickOnSearchBtn()
	{
		trainsPage.clickSearchBtn();
	}
	
	@When("User click on Available seat option from quick filters")
	public void clickOnAvailableBtn() throws InterruptedException
	{
		selectTrainPage.verifyTrainsPage("MakeMyTrip - Trains");
		selectTrainPage.clickOnAvailable();
	}
	
	@When("User select the train with {string} or {string} seats")
	public void selectTrainSeatFromAvailable(String availability, String waiting) throws InterruptedException
	{
		expectedTrainDetails = selectTrainPage.selectTrain(availability, waiting);
	}
	
	@When("User click on add travellers button")
	public void clickOnAddTravellersBtn()
	{
		travellerPage.verifyReviewPage(expectedTrainDetails);
		travellerPage.clickOnAddTraveller();
	}
	
	@When("User enters {string} traveller name")
	public void userEnterTravellerName(String nameOfTraveller)
	{
		travellerPage.enterTravellerName(nameOfTraveller);
	}
	
	@When("User enters {string} traveller age")
	public void userEnterTravellerAge(String travellerAge)
	{
		travellerPage.enterTravellerAge(travellerAge);
	}
	
	@When("User selects traveller gender")
	public void userSelectTravellerGender() throws InterruptedException
	{
		travellerPage.selectTravellerGender(0);
	}
	
	@When("User selects traveller nationality")
	public void userSelectTravellerNationality()
	{
		travellerPage.getNationalityText();
	}
	
	@When("User selects berth {string} preference")
	public void userSelectBerthPreference(String berthName)
	{
		travellerPage.selectBerth(berthName);
	}
	
	@When("User clicks on Add button")
	public void clickOnAddBtn() throws InterruptedException
	{
		travellerPage.clickOnAddBtn();
	}
	
}
