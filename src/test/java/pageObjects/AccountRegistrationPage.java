package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	//Constructor
	WebDriver driver;
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Locator
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement Agree;
	
	@FindBy(xpath="//button[normalize-space()='Continue']")
	WebElement Continue;
	
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	//Access Method
	public void setFirtName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void clickAgree() {
		Agree.click();
	}
	
	public void clickContinue() {
		Continue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return(msgConfirmation.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
}
