package SOURCE_CODE.SFDC;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author Cognizant
 *
 */
public class MemberOfButton {
	
	String ButtonName;
	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String xpath;
	
	MemberOfButton(String BN)
	{
		ButtonName = BN;
		
						
		xpath = "//input[(@value=' " + ButtonName+
				" ' or normalize-space(@value)='" + ButtonName + 
				"' or @value='  " + ButtonName +
				"  ') and contains(normalize-space(@class),'btn')][1]";
		
		
		myWD = SFDCAutomationFW.myWD;
	}
	
	
	/**
	 * @Author Sourav Mukherjee
	 * @Description Clicks on Salesforce OOB button
	 * @return boolean
	 * @throws Exception
	 */
	public boolean Click() throws Exception
	{
		try
		{
			//Thread.sleep(2000L);
			if(autoFW.WaitForElement(xpath,30))
			{
				
				autoFW.MouseMove(xpath);
				
				if (myWD.toString().contains("InternetExplorerDriver") || myWD.toString().contains("SafariDriver"))
				{
					((JavascriptExecutor) myWD).executeScript("arguments[0].click();", myWD.findElement(By.xpath(xpath)));
					System.out.println("Clicked on the button ("+ButtonName+")");
					autoFW.AddToXLLogs("Clicked on the button ("+ButtonName+")", "Pass");
					//Thread.sleep(2000L);
					return true;
				}
				else 
				{
					myWD.findElement(By.xpath(xpath)).click();
					System.out.println("Clicked on the button ("+ButtonName+")");
					autoFW.AddToXLLogs("Clicked on the button ("+ButtonName+")", "Pass");
					return true;
				}
			}
			else
			{
				System.out.println("Unable to click on the button ("+ButtonName+")");
				autoFW.AddToXLLogs("Unable to click on the button ("+ButtonName+")", "Fail");
				return false;
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println("Unable to click on the button ("+ButtonName+")");
			autoFW.AddToXLLogs("Unable to click on the button ("+ButtonName+")", "Fail");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Checks if the SFDC OOB button is displayed in the UI
	 */
	public boolean IsDisplayed() throws Exception
	{
		try
		{
			if (autoFW.WaitForElement(xpath,30))
			{
				System.out.println("The button ("+ButtonName+") is displayed.");
				autoFW.AddToXLLogs("The button ("+ButtonName+") is displayed.", "Pass");
				return true;
		
			}
			else
			{
				System.out.println("Unable to find button ("+ButtonName+") when xpath is :"+xpath);
				autoFW.AddToXLLogs("Unable to find button ("+ButtonName+") when xpath is :"+xpath, "Fail");
				return false;
			
			}
				
							
		}
		catch(Exception e)
		{
			System.out.println("Unable to find button ("+ButtonName+") when xpath is :"+xpath);
			autoFW.AddToXLLogs("Unable to find button ("+ButtonName+") when xpath is :"+xpath, "Fail");
			e.printStackTrace();
			return false;
	
		}
	}
	
	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the existence of button in the UI and sends the message in the log
	 */
	public boolean VerifyIfDisplayed(String YesORNo) throws Exception
	{
		if (YesORNo.trim().equalsIgnoreCase("Yes"))
		{
		try
		{
				
			if (autoFW.WaitForElement(xpath,30))
			{
				System.out.println("Successfully Verified the existence of button ("+ButtonName+") is the application.");
				autoFW.AddToXLLogs("Successfully Verified the existence of button ("+ButtonName+") is the application.", "Pass");
				return true;
		
			}
			else
			{
				System.out.println("Unable to find button ("+ButtonName+") when xpath is :"+xpath);
				autoFW.AddToXLLogs("Unable to find button ("+ButtonName+") when xpath is :"+xpath, "Fail");
				return false;
			
			}
				
							
		}
		catch(Exception e)
		{
			System.out.println("Unable to find button ("+ButtonName+") when xpath is :"+xpath);
			autoFW.AddToXLLogs("Unable to find button ("+ButtonName+") when xpath is :"+xpath, "Fail");
			e.printStackTrace();
			return false;
	
		}
		}
		else if (YesORNo.trim().equalsIgnoreCase("No"))
		{
			try
			{
					
				if (autoFW.WaitForElement(xpath,30))
				{
					System.out.println("The button ("+ButtonName+") is found in the application when it is not expected.");
					autoFW.AddToXLLogs("The button ("+ButtonName+") is found in the application when it is not expected.", "Fail");
					return false;
			
				}
				else
				{
					System.out.println("Successfully Verified the non-existence of button ("+ButtonName+") in the application.");
					autoFW.AddToXLLogs("Successfully Verified the non-existence of button ("+ButtonName+") in the application.", "Pass");
					return true;
				
				}
					
								
			}
			catch(Exception e)
			{
				System.out.println("Successfully Verified the non-existence of button ("+ButtonName+") in the application.");
				autoFW.AddToXLLogs("Successfully Verified the non-existence of button ("+ButtonName+") in the application.", "Pass");
				e.printStackTrace();
				return true;
		
			}
		}
		else
		{
			System.out.println("The supplied input parameter is not proper.");
			autoFW.AddToXLLogs("The supplied input parameter is not proper","Fail");
			return false;
		}
	}
	/**
	 * @param waitingTimeinSec
	 * @return boolean
	 * @throws Exception
	 * @Description Waits for specified time for the button to display in the UI
	 */
	public boolean WaitForElement(long waitingTimeinSec) throws Exception
	{
		 try {
			
			 if (myWD.toString().contains("InternetExplorerDriver"))
     	 	 {
				 	autoFW.WaitForPageToLoad(15);
     	 			autoFW.WebDriverWaitForElement(xpath,waitingTimeinSec);
     	 			//return true;
     	 	 }
			 //Thread.sleep(2000L);
			 myWD.manage().timeouts().implicitlyWait(waitingTimeinSec,TimeUnit.SECONDS);
			 System.out.println("Xpath inside WaitForElement:"+xpath);
             List<WebElement> myDynamicElement = myWD.findElements(By.xpath(xpath));
             if (myDynamicElement.size() > 0)
             {
            	 autoFW.AddToXLLogs("The button ("+ButtonName+") was available in the application.", "Pass");
            	 System.out.println("The button ("+ButtonName+") was available in the application.");
            	 return true;
             }
             else
             {
            	 autoFW.AddToXLLogs("Could not find the button ("+ButtonName+") in the application.", "Fail");
            	 System.out.println("Could not find the button ("+ButtonName+") in the application.");
            	 return false;
             }
			 
             //System.out.println("The value of dynamic webelement is:"+myDynamicElement.isDisplayed());
             //return myDynamicElement.isDisplayed();
             }
         catch(NoSuchElementException e)
             {
             System.out.println("Could not find the element after waiting for specified time.");
             autoFW.AddToXLLogs("Could not find the button ("+ButtonName+") in the application.", "Fail");
         	 e.printStackTrace();
             return false;
             //return false;
             }
	}
	
}
