package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	//Constructor
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}
	
	//Locator
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmailaddress;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement btnLogin;
	
	//Access Method
	public void setEmail(String email) {
		txtEmailaddress.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtpassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
}
