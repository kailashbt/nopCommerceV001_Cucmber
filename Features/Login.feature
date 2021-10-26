Feature: Login

Scenario:Successful Login with Valid Credentials
	Given User Launch Chrome browser
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on Log out link
	And close browser
	
	Scenario Outline: Login Data Driven 
	Given User Launch Chrome browser
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "<email>" and Password as "<password>"
	And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on Log out link
	And close browser
	
	Examples:
		| email | password |
		| admin@yourstore.com | admin |
		| email1 | admin1 |
		 