package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;  //UI of the report
    public ExtentReports extent;				//populate common info on the report
    public ExtentTest test;						//Creating test case entries in the report and update status of the test methods
    
    String repName;

    // Initialize ExtentReports
    public void onStart(ITestContext testcontext) {
    	
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	repName = "Test-Report-" + timeStamp + ".html";
    	sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        
        sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Report title
        sparkReporter.config().setReportName("Opencart Functional Test Report"); // Report name
        sparkReporter.config().setTheme(Theme.DARK); // Dark or standard theme
        
        // Initialize the ExtentReports object
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Add some basic system info
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        String os = testcontext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);
        
        String browser = testcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Operating System", browser);
        
        List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
        	extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName()); //Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());
        
        test.log(Status.FAIL,result.getName()+" got failed"); //Update status passed/failed/skipped
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        try {
        String imgPath = new BaseClass().captureScreen(result.getName());
        test.addScreenCaptureFromPath(imgPath);
        }
        catch (Exception e1) {
        	e1.printStackTrace();
        }
    }

    public void onTestSuccess(ITestResult result) {
    	 test = extent.createTest(result.getTestClass().getName());
    	 test.assignCategory(result.getMethod().getGroups());
    	 test.log(Status.PASS, result.getName()+"got successfully executed");
    	     }
    
    public void onTestSkipped(ITestResult result) {
   	 test = extent.createTest(result.getTestClass().getName());
   	 test.assignCategory(result.getMethod().getGroups());
   	 test.log(Status.SKIP, "Test case SKIPPED is: "+result.getName());
   	test.log(Status.INFO, "Test case SKIPPED is: "+result.getThrowable().getMessage());
   }
    
    public void onFinish(ITestContext context) {
    	extent.flush();
    	String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
    	File extentReport = new File(pathOfExtentReport);
    	try {
    		Desktop.getDesktop().browse(extentReport.toURI());
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	/*try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports"+repName);
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("pavan123@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("pavan123@gmail.com");  //sender
			email.setSubject("Test Results");
			email.setMsg("Please find attached report");
			email.addTo("pavankumar.muvva65@gmail.com");  //receiver
			email.attach(url, "extent report", "Please check report...");
			email.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
    			
    	
    }

}
