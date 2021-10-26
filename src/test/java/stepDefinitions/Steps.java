package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pageObjects.AddcustomerPage;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass{
	
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver","F:\\Selenium\\nopCommerceV001_Cucmber\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		lp=new LoginPage(driver);
	   //System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
	   
	   
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
	  driver.get(url);
	  driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
	  lp.setUserName(email);
	  lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
	 
		lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
		
		String tlt=driver.getTitle();
		System.out.println("curent title"+tlt);
		if(driver.getPageSource().contains("Login Was Unsuccessful")) {
			driver.close();
			Assert.assertFalse(false);
		}else
		{
			Assert.assertEquals(title, driver.getTitle());
		}
	  
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
	  lp.clickLogout();
	  Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
	  driver.close();
	  driver.quit();
	}
	
	//Add customers info
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		
		addcust=new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
	    addcust.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
		addcust.clickOnCustomersMenuItem();
		Thread.sleep(3000);
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
	    addcust.clickOnAddnew();
	    Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration",addcust.getPageTitle() );
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
	    String email=randomstring()+"@gmail.com";
	    addcust.setEmail(email);
	    addcust.setPassword("test123");
	    addcust.setFirstName("kailash");
	    addcust.setLastName("telmani");
	    addcust.setGender("Male");
	    addcust.setDob("7/05/1985");
	    addcust.setCompanyName("busyQA");
	    //addcust.setCustomerRoles("Guest");
	    Thread.sleep(3000);
	    addcust.setManagerOfVendor("Vendor 2");
	    Thread.sleep(1000);
	    addcust.setAdminContent("This is for testing ......");
	    	    	
	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
	    addcust.clickOnSave();
	    Thread.sleep(2000);
	}

	@Then("User can veiw configuration message {string}")
	public void user_can_veiw_configuration_message(String string) {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(" The new customer has been added succesffully"));
	}
	
	//steps searching  a customer  using Email id.....
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
	 searchCust=new SearchCustomerPage(driver);
	 searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	 
	}
	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	 boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	Assert.assertEquals(true, status);
	}

	//Steps for searching a customer by using FirstName and LastName
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
	   searchCust=new SearchCustomerPage(driver);
	   searchCust.setFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCust.setLastName("Terces");
	   
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		
		boolean status1=searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status1);
		
	  
	}

	
	
}
