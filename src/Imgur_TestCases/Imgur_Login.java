package Imgur_TestCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com_Imgur.Browser_Launch;
import com_Imgur.Driver_Main;
import com_Imgur.Excel_Reader;
import com_Imgur.Reporting;



@Test
public class Imgur_Login extends Driver_Main 
{
	
	
		//File screenshotpath= new File(ReportTemplate +"//Screenshots//Screenshot_"+currentDate + ".png");
		
		
	public void LoginTest() throws IOException, InterruptedException
	{
		
		Browser_Launch BL;
		BL = new Browser_Launch();
		BL.BrowserLaunch();
		Reporting.initialize();
		//driverriver driver = BL.driver;
		String location = BL.location;
		Calendar cal = Calendar.getInstance();
		String currentDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(cal.getTime());
		String screenshotpath= location +"//Screenshots//Screenshot_" + currentDate + ".png";
//		String path = BL.path;
		Excel_Reader obj = BL.obj;
		String errorText;
		
		String TC= obj.getCellData("TestCaseNames", "Description", 2);
		

			String Signin= driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 2))).getText();
			
			if (Signin!=null)
			{
				System.out.println(Signin);
			Reporting.report(TC,Signin, "sign in", null);
			Reporter.log(Signin,true);
			
			}
			else
			{
				System.out.println(Signin);
				Reporting.report(TC,"Sign in value is null", Signin, screenshotpath);
				Reporter.log(Signin,false);
			}

			String a = obj.getCellData("LoginInfo", "UserName", 2).toString().toLowerCase();
			driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 2))).click();
			driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 3))).sendKeys(obj.getCellData("LoginInfo", "UserName", 2));
			driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 4))).sendKeys(obj.getCellData("LoginInfo", "Password", 2));
			driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 5))).click();
			if(driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 6))).isDisplayed())

			{
				Reporting.createScreenshot(driver, screenshotpath);
				errorText = driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 6))).getText();
				System.out.println(errorText);
				Reporting.report(TC,errorText, a, screenshotpath);
				Reporter.log(Signin,false);

			}
			else if(obj.getCellData("Xpaths", "Path", 9).toString()!=null)
			{
			
				Reporting.report(TC,a, a, null);
//				Reporting.createScreenshot(driver);
				System.out.println(obj.getCellData("LoginInfo", "UserName", 2) + " : User logged in ");
				Reporter.log(obj.getCellData("LoginInfo", "UserName", 2) + " : User logged in ",true);
			}
			else
			{
				Reporting.createScreenshot(driver, screenshotpath);
				Reporting.report(TC,"Sign in", a, screenshotpath);
				Reporter.log(Signin,false);
				System.out.println("User not logged in");
			}
			

//			Mail_Report.main(new String[0]);
//			driver.quit();	


		
		Reporting.writeResults();
		driver.close();
			
			
		}
}
