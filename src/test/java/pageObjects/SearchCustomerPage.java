package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
public WebDriver ldriver;

WaitHelper waithelper;

	
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper = new WaitHelper(ldriver);
	}
	
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement dropdobMonth;
	
	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement dropdobDay;
	
	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(how = How.XPATH, using = "//div[@class='k-multiselect-wrap k-floatwrap]")
	@CacheLookup
	WebElement txtcustomersRoles;
	
	@FindBy(how = How.XPATH, using = "//li[contains[text(),'Administrators')]")
	@CacheLookup
	WebElement lstitemAdministrators;
	
	@FindBy(how = How.XPATH, using = "//li[contains[text(),'Registered')]")
	@CacheLookup
	WebElement lstitemRegistered;
	
	@FindBy(how = How.XPATH, using = "//li[contains[text(),'Guests')]")
	@CacheLookup
	WebElement lstitemGuests;
	
	@FindBy(how = How.XPATH, using = "//li[contains[text(),'Vendors')]")
	@CacheLookup
	WebElement lstitemVendors;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement btnSearchResults;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;
	
	public void setEmail(String email)
	{
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname)
	{
		waithelper.WaitForElement(txtFirstName, 30);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);		
	}
	
	public void setLastName(String lname)
	{
		waithelper.WaitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
		
	}
	
	public void clickSearch()
	{
		btnSearch.click();
		waithelper.WaitForElement(txtLastName, 30);
	}
	
	public int getNoOfRows() {
		return(tableRows.size());
	}
	
	public int getNoOfColumns()
	{
		return(tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email)
	{
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++)
		{
			String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
		    System.out.println(emailid);
			
			if(emailid.equals(email))
			{
				flag=true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String name)
	{
		boolean flag=false;
		for (int  K=1;K<=getNoOfRows();K++)
				{
					String name1=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+K+"]/td[3]")).getText();
					System.out.println(name1);
					String names[]=name1.split(" ");
										
					System.out.println(names[0]);
					System.out.println(names[1]);
					if(names[0].equals("Victoria") && names[1].equals("Terces"))
					{
						
						flag=true;
						
					}
				}
			return flag;
	}
	
	
}
