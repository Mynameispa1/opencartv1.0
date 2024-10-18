package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		logger.info("****Starting TC002_LoginTest******");
		
		try
		{
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage macc = new MyAccountPage(driver);
			boolean status = macc.account();	
			Assert.assertEquals(status, true);
			//Assert.assertTrue(status);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
			
		logger.info("****finished TC002_LoginTest******");
	}
	
	
}
