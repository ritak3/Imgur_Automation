//TC-73:Desktop - HP: The context in "Most Viral" is kept after clicking on a post thumbnail

package Imgur_TestCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
//import org.openqa.selenium.driverriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com_Imgur.Browser_Launch;
import com_Imgur.Driver_Main;
import com_Imgur.Excel_Reader;
import com_Imgur.Reporting;





@Test
public class HomePage_Gallery extends Driver_Main{

	
	public void Homepage() throws IOException, InterruptedException{
		Browser_Launch BL;
		BL = new Browser_Launch();
		BL.BrowserLaunch();
		//driverriver driver = BL.driver;
		String location = BL.location;
		//String path = BL.path;
		Excel_Reader obj = BL.obj;
		//String errorText;
		Calendar cal = Calendar.getInstance();
		String currentDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(cal.getTime());
		String screenshotpath= location +"//Screenshots//Screenshot_" + currentDate + ".png";
		String TC= obj.getCellData("TestCaseNames", "Description", 3);
		

			Actions builder = new Actions(driver);
			WebElement element = driver.findElement(By.xpath(obj.getCellData("Xpaths", "Path", 13)));
			 builder.moveToElement(element).build().perform();
			 String galleryHeader = driver.findElement(By.xpath(obj.getCellData("Xpaths", "Path", 16))).getText();
			String galleryText= driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 15))).getText();
			driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 13))).click();
			System.out.println(galleryText);
			String postTexth1 = driver.findElement(ByXPath.xpath(obj.getCellData("Xpaths", "Path", 14))).getText();
			driver.navigate().back();
			String galleryHeaderB = driver.findElement(By.xpath(obj.getCellData("Xpaths", "Path", 16))).getText();
			
if(galleryText==postTexth1)
{
	Reporting.report(TC,galleryHeaderB, galleryHeader,null);
	System.out.println(galleryHeaderB);
	Reporter.log("testcases passed");
}
else
{
	Reporting.createScreenshot(driver, screenshotpath);
	Reporting.report(TC,galleryHeaderB, galleryHeader,screenshotpath);
	System.out.println(galleryHeaderB);
	Reporter.log("testcases fail");
}
			if(galleryHeader.equals(galleryHeaderB))
			{
				Reporting.report(TC,galleryHeaderB, galleryHeader,null);
				System.out.println(galleryHeaderB);
				Reporter.log("testcases passed");
				
			}
			else
			{
				Reporting.createScreenshot(driver,screenshotpath);
				Reporting.report(TC,galleryHeaderB, galleryHeader, screenshotpath);
				System.out.println(galleryHeaderB + " doesn't match with earlier headline "+ galleryHeader);
			}
			
			Reporting.writeResults();
			driver.close();
//			driver.quit();
			
			//Mail_Report.main(new String[0]);
			
			//Mail_Report.main(new String[0]);
	
			
			}
		
		

	}


