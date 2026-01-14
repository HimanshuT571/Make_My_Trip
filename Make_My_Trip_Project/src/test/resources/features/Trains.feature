
Feature: Train seat booking.

Scenario: Verify user can book train ticket.
Given User click on trains button
And User click on Book Train Tickets
When User enter the "Nagpur" in from station tab
And User select "Nagpur Junction" of city "Nagpur" from the suggestions
And User enter the "Mumbai" in To station tab
And User select the "Mumbai - All Stations" in the city "Mumbai" from the suggestions
And User select the travel date 15 days from current date from calendar
And User select "All Class" from the class dropdown
And User click on search button
And User click on Available seat option from quick filters
And User select the train with "Available" or "GNWL" seats
And User click on add travellers button
And User enters "Automation" traveller name
And User enters "25" traveller age
And User selects traveller gender
And User selects traveller nationality
And User selects berth "Upper" preference
And User clicks on Add button
Then User click on pay and book now button