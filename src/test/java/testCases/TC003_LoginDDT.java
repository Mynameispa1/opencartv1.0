package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "DataDriven")
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("***** Started TC003_LoginDDT Test*****");
		
		try 
		{
			//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage accountpage= new MyAccountPage(driver);
		boolean targetpage = accountpage.account();	
		
		/*Data is Valid   - login success  -test pass -logout
		 * 				  -	login failed -test fail
		 * 
		 * Data is Invalid - login Success	-test fail -logout
		 * 				   -login failed	-test pass
		 */
		
		if (exp.equalsIgnoreCase("Valid")) {
			
			if(targetpage==true) {
				
				accountpage.clicklogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetpage==true) {
				accountpage.clicklogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
	catch(Exception e) {
		Assert.fail();
	}
		Thread.sleep(3000);
		logger.info("***** Finished TC003_LoginDDT Test*****");
	}
}

