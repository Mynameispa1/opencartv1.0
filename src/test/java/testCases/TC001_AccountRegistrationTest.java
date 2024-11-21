package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	
	@Test(groups = {"Regression", "Master"})
	public  void verify_account_registration() {
		
		logger.info("*** Staring TC001_AccountRegistrationTest ****");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();	
		
		String password=randomAlpaNumeric();
		
		AccountRegistrationPage rp = new AccountRegistrationPage(driver);
		logger.info("*** Providing Customer details  ****");
		rp.setFirtName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setPhoneNum(randomAlpaNumeric());
		rp.setPassword(password);
		rp.setConfirmPassword(password);
		rp.clickAgree();
		rp.clickContinue();
		
		logger.info("*** Validation ****");
		String confirmation = rp.getConfirmationMsg();
		if(confirmation.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		
		else {
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confirmation, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Finished****");
	}	
	
}
