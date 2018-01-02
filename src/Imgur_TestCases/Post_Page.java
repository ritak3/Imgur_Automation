package Imgur_TestCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By.ByXPath;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com_Imgur.Browser_Launch;
import com_Imgur.Driver_Main;
import com_Imgur.Excel_Reader;
import com_Imgur.Reporting;

@Test
public class Post_Page extends Driver_Main {
	

	
	public void PostPage() throws IOException, InterruptedException {
		Browser_Launch BL;
		BL = new Browser_Launch();
		BL.BrowserLaunch();
		//driverriver driver = BL.driver;
		String location = BL.location;
		Calendar cal = Calendar.getInstance();
		String currentDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(cal.getTime());
		String screenshotpath= location +"//Screenshots//Screenshot_" + currentDate + ".png";
//		String path = BL.path;
		Excel_Reader obj = BL.obj;
	

		String TC= obj.getCellData("TestCaseNames", "Description", 4);
try
{
		driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 13))).click();
		if(driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 17))).isDisplayed())
		{
			String NexP = driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 17))).getText();
			System.out.println(NexP);
		
			if(NexP=="Next Post")
			{
			Reporting.report(TC, NexP, "Next Post", null);
			Reporter.log(NexP,true);
			}
			else 
			{
				Reporting.createScreenshot(driver, screenshotpath);
				Reporting.report(TC, NexP, "Next Post", screenshotpath);
				Reporter.log(NexP,false);
			}
			
		}
		else
		{
			Reporting.createScreenshot(driver, screenshotpath);
			Reporting.report(TC, "Disabled", "Enabled", screenshotpath);
			
		}
}
catch(Exception e)
{
	driver.close();
	e.printStackTrace();
}
		
	
		Reporting.writeResults();
		driver.close();
		
	}

}
