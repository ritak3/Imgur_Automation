package SOURCE_CODE.SFDC;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MemberOfTab {

	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String TabName;
	String xpath;
	
	MemberOfTab(String TN)
	{
		TabName = TN;
		xpath = ".//*[@title='"+ TabName+"' or @title='"+TabName+" Tab - Selected' or @title='"+TabName+" Tab'][1]";
		myWD = SFDCAutomationFW.myWD;
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the Tab Name displayed on SFDC menu 
	 * @Date Aug 7, 2014
	 */
	public boolean Click() throws Exception
	{
		try
		{
			//System.out.println(xpath);
			if (autoFW.WaitForElement(xpath,30))
			{
				System.out.println("Clicked on tab ("+TabName+").");
				myWD.findElement(By.xpath(xpath)).click();
				autoFW.AddToXLLogs("Clicked on tab ("+TabName+").", "Pass");
				return true;
			}
			else
			{
				System.out.println("Unable to click on tab ("+TabName+") when xpath is :"+xpath);
				autoFW.AddToXLLogs("Unable to click on tab ("+TabName+") when xpath is :"+xpath, "Fail");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to click on tab ("+TabName+") when xpath is :"+xpath);
			autoFW.AddToXLLogs("Unable to click on tab ("+TabName+") when xpath is :"+xpath, "Fail");
			return false;
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return true if tab name displayed return false if Tab Name is not displayed 
	 * @throws Exception
	 * @Description Verified if a TabName is displayed
	 * @Date Aug 7, 2014
	 */
	public boolean IsDisplayed() throws Exception
	{
		try
		{
			if (autoFW.WaitForElement(xpath,30))
			{
				System.out.println("The tab ("+TabName+") is displayed.");
				myWD.findElement(By.xpath(xpath)).click();
				autoFW.AddToXLLogs("The tab ("+TabName+") is displayed.", "Pass");
				return true;
		
			}
			else
			{
				System.out.println("Unable to find tab ("+TabName+") when xpath is :"+xpath);
				autoFW.AddToXLLogs("Unable to find tab ("+TabName+") when xpath is :"+xpath, "Fail");
				return false;
			
			}
							
		}
		catch(Exception e)
		{
			System.out.println("Unable to find tab ("+TabName+") when xpath is :"+xpath);
			autoFW.AddToXLLogs("Unable to find tab ("+TabName+") when xpath is :"+xpath, "Fail");
			return false;
		
		}
	}
	
}
