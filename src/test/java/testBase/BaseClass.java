package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;  //We need static wile doing the extent report
	public org.apache.logging.log4j.Logger logger; //This is log4j step
	public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os" ,"browser" })
	public void setup(String os, String browser) throws InterruptedException, IOException {
		
		//Loading config.properties file
		//Location of properties file
		FileInputStream file = new FileInputStream("D:\\pavan\\opencartv1.0\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());  //log4j step
		
		//if the execution environment is "remote"
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//OS
			switch(os.toLowerCase())
			{
			case "windows" : capabilities.setPlatform(Platform.WIN11); break;
			case "mac"	   : capabilities.setPlatform(Platform.MAC); break;
			case "Linux"   : capabilities.setPlatform(Platform.LINUX);break;
			default		   : System.out.println("Invalid OS"); return;
			}
			
			//Browser
			switch(browser.toLowerCase())
			{
			case "chrome"  : capabilities.setBrowserName("chrome"); break;
			case "edge"	   : capabilities.setBrowserName("edge"); break;
			case "firefox" : capabilities.setBrowserName("firefox");break;
			default		   : System.out.println("Invalid Browser Name"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities );
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(browser.toLowerCase()) {
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge"   : driver = new EdgeDriver();break;
			case "firefox" : driver = new FirefoxDriver();break;
			default : System.out.println("Invalid browser");return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//a[normalize-space()='Register']"));
		 ((ChromiumDriver) driver).executeScript("arguments[0].click();", element);
				
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.close();
	}
	
	//To get Random String
		public String randomString() {
			String generatedstring = RandomStringUtils.randomAlphabetic(5);   //RandomStringUtils is predefined class which is there in Commons Library
			return generatedstring;
		}
		
		//To get Random Number
		public String randomNumber() {
			String generatedNumber = RandomStringUtils.randomNumeric(10);
			return generatedNumber;
		}
		
		//To get Random Random AlphaNumeric value
		public String randomAlpaNumeric() {
			String generatedstring = RandomStringUtils.randomAlphabetic(5);
			String generatedNumber = RandomStringUtils.randomNumeric(5);
			return (generatedstring+"@"+generatedNumber);
			
		}

		//Screen Shot
		public String captureScreen(String tname) {
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" +timeStamp+ ".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			return targetFilePath;
		}

}
