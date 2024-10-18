package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	//construtor
	WebDriver driver;
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//Locator
	@FindBy(xpath="//h2[normalize-space()='My Account']")  //My Account page heading
	WebElement myaccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")  //Added step 6
	WebElement linkLogout;
	
	//Access Method
	public boolean account() {
		try
		{
		return (myaccount.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clicklogout() {
		linkLogout.click();
	}
	
	
	
}
