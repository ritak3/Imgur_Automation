package SOURCE_CODE.SFDC;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MemberOfAllTab {
	
	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String xpath = ".//*[@title='All Tabs']";
	
	MemberOfAllTab(WebDriver WD)
	{
		myWD = WD;
	}
	
	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on All Tab link
	 */
	public boolean Click() throws Exception
	{	
		try
		{
			if (autoFW.WaitForElement(xpath,30))
			{
				myWD.findElement(By.xpath(xpath)).click();
				autoFW.AddToXLLogs("Clicked on All Tab link.", "Pass");
				System.out.println("Clicked on All Tab link.");
				return true;
			}
			else
			{
				System.out.println("Unable to click on All Tab link.");
				autoFW.AddToXLLogs("Unable to click on All Tab link.", "Fail");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to click on All Tab link.");
			autoFW.AddToXLLogs("Unable to click on All Tab link.", "Fail");
			return false;
		}
	}
	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if All Tabs link is displayed in the UI
	 */
	public boolean IsDisplayed() throws Exception
	{
		try
		{
			if (autoFW.WaitForElement(xpath,30))
			{
				autoFW.AddToXLLogs("All Tab link is found.", "Pass");
				System.out.println("All Tab link is found.");
				return true;
					
			}
			else
			{
				autoFW.AddToXLLogs("Unable to find All Tab link.", "Fail");
				System.out.println("Unable to find All Tab link.");
				return false;
			}
							
		}
		catch(Exception e)
		{
			autoFW.AddToXLLogs("Unable to find All Tab link.", "Fail");
			System.out.println("Unable to find All Tab link.");
			return false;
		}
		
	}
}
