package USER_SPACE.BusinessComponent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import SOURCE_CODE.SFDC.*;
import USER_SPACE.ObjectRepository.AccountScreen;
import USER_SPACE.ObjectRepository.LeadScreen;
import USER_SPACE.TestPrerequisite.DataSetup;



public class BC {
	
	static SFDCAutomationFW sfdc = DataSetup.sfdc;
	
		
	/**
	 * @author 185584
	 * @return Current datetimestamp 
	 * @Description It generates the current date time stamp at the time it is invoked. This may be used when you want to enter any unique data.
	 * @throws Exception
	 */
	public static String GetCurrentDateTimeStamp() throws Exception
	{
        
       	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        return (sdf.format(cal.getTime()));                     
              
    }
	
	
	/**
	 * @author 185584
	 * @param TestCaseName
	 * @return LeadName on successful Creation of Lead record
	 * @StartScreen Salesforce Home page should be displayed
	 * @EndScreen Lead Details page should be displayed
	 * @throws Exception
	 */
	public static String CreateLead(String TestCaseName) throws Exception
	{
		try 
		{
			
		String FirstName,LastName,Company,LeadStatus,Phone,Mobile,Email,LeadSource,Campaign,Industry,Street,City,Zip,Country;
		String LeadName = "";
		
		DB.Connect(DataSetup.SheetForBC);
			
		FirstName = DB.ReadXLData("CreateLead", "FirstName", "TestCaseName", TestCaseName);
		LastName = DB.ReadXLData("CreateLead", "LastName", "TestCaseName", TestCaseName);
		Company = DB.ReadXLData("CreateLead", "Company", "TestCaseName", TestCaseName);
		LeadStatus = DB.ReadXLData("CreateLead", "LeadStatus", "TestCaseName", TestCaseName);
		Phone = DB.ReadXLData("CreateLead", "Phone", "TestCaseName", TestCaseName);
		Mobile = DB.ReadXLData("CreateLead", "Mobile", "TestCaseName", TestCaseName);
		Email = DB.ReadXLData("CreateLead", "Email", "TestCaseName", TestCaseName);
		LeadSource = DB.ReadXLData("CreateLead", "LeadSource", "TestCaseName", TestCaseName);
		//Campaign = DB.ReadXLData("CreateLead", "Campaign", "TestCaseName", TestCaseName);
		Industry = DB.ReadXLData("CreateLead", "Industry", "TestCaseName", TestCaseName);
		Street = DB.ReadXLData("CreateLead", "Street", "TestCaseName", TestCaseName);
		City = DB.ReadXLData("CreateLead", "City", "TestCaseName", TestCaseName);
		Zip = DB.ReadXLData("CreateLead", "Zip", "TestCaseName", TestCaseName);
		Country = DB.ReadXLData("CreateLead", "Country", "TestCaseName", TestCaseName);
		
		String datetime = GetCurrentDateTimeStamp();
		
		if (FirstName.trim().equals(""))
		{
			FirstName = datetime;
		}
		
		if (LastName.trim().equals(""))
		{
			LastName = datetime;
		}
		if (Company.trim().equals(""))
		{
			Company = datetime;
		}
						
		if (LeadStatus.trim().equals(""))
		{
			LeadStatus = "Open - Not Contacted";
		}
		if (Email.trim().equals(""))
		{
			Email = FirstName + "." + LastName + "@" + datetime + ".com";
		}
		if (Industry.trim().equals(""))
		{
			Industry = "--None--";
		}
		if (LeadSource.trim().equals(""))
		{
			LeadSource = "Other";
		}
		
		sfdc.Tab("Leads").Click();
		
		LeadScreen.NewButton().Click();
		
		sfdc.WaitForPageToLoad(15);
		
		LeadScreen.FirstNameField().Type(FirstName);
		LeadScreen.LastNameField().Type(LastName);
		LeadScreen.CompanyField().Type(Company);
		LeadScreen.LeadStatusField().SelectPL(LeadStatus);
		LeadScreen.PhoneField().Type(Phone);
		LeadScreen.MobileField().Type(Mobile);
		LeadScreen.EmailField().Type(Email);
		LeadScreen.LeadSourceField().SelectPL(LeadSource);
		//LeadScreen.CampaignField().Type(Campaign);
		LeadScreen.IndustryField().SelectPL(Industry);
		LeadScreen.StreetField().Type(Street);
		LeadScreen.CityField().Type(City);
		LeadScreen.ZipPostalCodeField().Type(Zip);
		LeadScreen.CountryField().Type(Country);
		
		LeadScreen.SaveButton().Click();
		
		if(LeadScreen.EditButton().WaitForElement(30))
		{
			LeadName = LeadScreen.NameField().GetViewOnlyValue();
			
			sfdc.AddToXLLogs("Lead record is created successfully.", "Pass");
			System.out.println("Lead record is created successfully.");
			DB.Connect(DataSetup.SheetForBC);
			DB.UpdateXLCell("CreateLead", LeadName, "LeadName" , "TestCaseName", TestCaseName);
			return LeadName;
		}
		else
		{
			sfdc.AddToXLLogs("Unable to save the Lead record.", "Fail");
			System.out.println("Unable to save the Lead record.");
			return "";
		}

		
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to create Lead record.");
			sfdc.AddToXLLogs("Unable to create Lead record.", "Fail");
			return "";
		}
	}
	public static String EnterCreateLeadInfo(String TestCaseName) throws Exception
	{
		String LeadName = "";
		try 
		{
			
			return LeadName;
		}catch(Exception e)
		{
			e.printStackTrace();
			return LeadName;
		}
	}
	
	
	public static void SelectApplication(String ApplicationName) throws Exception
	{
		if (sfdc.GetWebDriver().findElement(By.xpath("//*[@id='tsidLabel']")).getText().trim().equals(ApplicationName.trim()))
		{
			sfdc.AddToXLLogs("The application ("+ApplicationName+") is selected successfully.", "Pass");
			System.out.println("The application ("+ApplicationName+") is selected successfully.");
		}
		else
		{
			sfdc.GetWebDriver().findElement(By.xpath("//*[@id='tsid-arrow']")).click();
			Thread.sleep(1000L);
        	if (sfdc.GetWebDriver().findElement(By.xpath("//*[contains(@class,'menuButtonMenuLink') and contains(text(), '"+ApplicationName+"')]")).isDisplayed())
        	{
        		sfdc.GetWebDriver().findElement(By.xpath(".//*[contains(@class,'menuButtonMenuLink') and contains(text(), '"+ApplicationName+"')]")).click();
        		sfdc.AddToXLLogs("The application ("+ApplicationName+") is selected successfully.", "Pass");
    			System.out.println("The application ("+ApplicationName+") is selected successfully.");
    	   	}
        	else
        	{
        		sfdc.AddToXLLogs("Unable to select the application for ("+ApplicationName+"). Please check the related xpaths or application name properly.","Fail");
				System.out.println("Unable to select the application for ("+ApplicationName+"). Please check the related xpaths or application name properly.");
        	}
		
		}

	}
	
	
	
	/**
	 * @author Cogniant
	 * @Description This function Waits till the record is saved successfully. It waits till the Feed option is shown. Also sends the message to the log 
	 * @return Returns true if Feed option is found within 20 sec else returns false
	 * @StartupScreen This component should be invoked after clicking on save button for any SFDC object that has Feed/Details option for the record level display.
	 * @ExitScreen no click event in performed in the UI. So no change of screen 
	 * @throws Exception
	 */
	public static boolean WaitTillTheRecordIsSaved() throws Exception
	{
		if(sfdc.WebDriverWaitForElement("//ul[normalize-space(@class)='optionContainer']/li[1]/a[1]/span[2][contains(normalize-space(text()),'Feed')]", 20)!=null)
		{
			sfdc.AddToXLLogs("Successfully saved the record.", "Pass");
			System.out.println("Successfully saved the record.");
			return true;
		}
		else
		{
			sfdc.AddToXLLogs("Could not find the Feed option after saving the record.", "Fail");
			System.out.println("Could not find the Feed option after saving the record.");
			return false;
		}
	}
	
	public static boolean LoginAsUser(String UserName) throws Exception
	{
		try
		{
			sfdc.LoginToSFDC(DataSetup.SYSTEM_ADMIN_USERNAME);
			sfdc.GlobalSearch("People", UserName);
			Thread.sleep(2000L);
			sfdc.WaitForPageToLoad(15);
			sfdc.WaitForElement("//a[contains(normalize-space(@class),'zen-trigger') and contains(normalize-space(@title),'User Action Menu')]", 20);
			sfdc.GetWebDriver().findElement(By.xpath("//a[contains(normalize-space(@class),'zen-trigger') and contains(normalize-space(@title),'User Action Menu')]")).click();
//			sfdc.GetWebDriver().findElement(By.xpath("//div[normalize-space(@class)='profileHeader']/div[1]/div[1]/a[1]")).click();
			//sfdc.HighLight(sfdc.WebDriverWaitForElement("//div[normalize-space(@class)='profileHeader']/div[1]/div[1]/a[1]/b[@class='zen-selectArrow']", 10));
			//sfdc.WebDriverWaitForElement("//div[normalize-space(@class)='profileHeader']/div[1]/div[1]/a[1]/b[@class='zen-selectArrow']", 10).click();
			Thread.sleep(1000L);
			sfdc.WaitForElement("//span[normalize-space(text())='User Detail']", 20);			
			sfdc.GetWebDriver().findElement(By.xpath("//span[normalize-space(text())='User Detail']")).click();
			Thread.sleep(2000L);
			sfdc.Button("Login").WaitForElement(10);
			
			sfdc.Button("Login").Click();
			sfdc.WaitForPageToLoad(20);
			sfdc.Tab("Home").Click();
			sfdc.WaitForPageToLoad(20);
			sfdc.AddToXLLogs("The user ("+UserName+") has successfully logged into the application.", "Pass");
			System.out.println("The user ("+UserName+") has successfully logged into the application.");
			return true;
		}
		catch(Exception e)
		{
			sfdc.AddToXLLogs("Unable to login as ("+UserName+"). Please check the xpaths in LoginAsUser function business component.","Fail");
			System.out.println("Unable to login as ("+UserName+"). Please check the xpaths in LoginAsUser function business component.");
			e.printStackTrace();
			return false;
			
		}
    }
	
	
	
	
	/**
	 * @author Cognizant
	 * @Description Clicks on Details tab
	 * @throws Exception
	 */
	public static void ClickONDetailsTab() throws Exception
	{
		try 
		{
			sfdc.GetWebDriver().findElement(By.xpath("//div[@class='mainContent'][1]/div[1]/ul[1]/li[2]/a[1]/span[2]")).click();
			sfdc.AddToXLLogs("Clicked on Details tab.", "Pass");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to find the Details tab.");
			sfdc.AddToXLLogs("Unable to find the Details tab.", "Fail");
		}
	}
	
}
