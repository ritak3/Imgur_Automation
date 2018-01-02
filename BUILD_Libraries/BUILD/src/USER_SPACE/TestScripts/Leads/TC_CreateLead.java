
package USER_SPACE.TestScripts.Leads;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.sun.jna.platform.win32.Advapi32Util.Account;

import SOURCE_CODE.SFDC.*;
import USER_SPACE.BusinessComponent.*;
import USER_SPACE.ObjectRepository.*;
import USER_SPACE.TestPrerequisite.DataSetup;


/*
#****************************************************************#
# 																 #
# @Author: [YourName], Cognizant								 #
# @Description: This test script creates an Lead from Leads tab. #
# @Created on: 25/11/2015 										 #
# 											                     #
#****************************************************************#

*/


public class TC_CreateLead {
	
	
	
	public static void main(String[] args) throws Exception
	{
			System.out.println("-----------Begin of TestScript-------------");
			
			
			String TCName = "TC_CreateLead";
			
			SFDCAutomationFW sfdc = new SFDCAutomationFW(TCName);
			
			DataSetup.sfdc=sfdc;
			
			
		try
		{
			DB.Connect(DataSetup.Logininfo);
			String URL = DB.ReadXLData("Logininfo","URL","Name","Sourav Mukherjee");
			
			//Launching the URL
			sfdc.OpenURL(args,URL, DataSetup.Browser);
			
			//Login to SFDC
			sfdc.LoginToSFDC("Sourav Mukherjee");		
			
			//Selecting application
			BC.SelectApplication("Sales");
			//BC.CreateLead("TC_CreateLead2");
			//AccountScreen.SEC_AccountDetail().AccountName().VerifyViewOnlyValueEquals("fhh");
			//AccountScreen.AccountNameField().VerifyViewOnlyValueEquals("hjgjh");
			
			//Clicking on Leads Tab
			sfdc.Link("Leads").Click();
			LeadScreen.NewButton().Click();
			
						
			LeadScreen.FirstNameField().Type(sfdc.GetCurrentDateTimeStamp());
			
			//LeadScreen.LastNameField().Type("Mukherjee");
			LeadScreen.SaveButton().Click();
			Thread.sleep(2000L);
			LeadScreen.LastNameField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
			LeadScreen.CompanyField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
			sfdc.GetScreenShotONsuccess();
			LeadScreen.LastNameField().Type(sfdc.GetCurrentDateTimeStamp());
			LeadScreen.CompanyField().Type(sfdc.GetCurrentDateTimeStamp());
						
			LeadScreen.SaveButton().Click();
			if (LeadScreen.EditButton().WaitForElement(30))
			{
				sfdc.AddToXLLogs("Lead is created successfully.", "Pass");
				System.out.println("Lead is created successfully.");
			}
			
			else
			{
				sfdc.AddToXLLogs("Unable to create the Lead record", "Fail");
				System.out.println("Unable to create the Lead record");
			}
			sfdc.GetScreenShotONsuccess();
			
			DB.Connect(DataSetup.TC_Data_Leads);
			DB.UpdateXLCell(TCName,LeadScreen.NameField().GetViewOnlyValue(), "LeadName", "SN", "SN01");
			DB.UpdateXLCell(TCName,sfdc.GetCurrentURL(), "SFDCURL", "SN", "SN01");
			
			
			sfdc.LogOff();
			
			
		}
		catch(ExitUponTestScriptStepFails e)
		{
			e.printStackTrace();
			System.out.println("Exception(ExitUponTestScriptStepFails) in main");
		}
		catch(Exception e)
        {
			e.printStackTrace();
			System.out.println("Exception(Exception) in main");
        }
		finally
		{
		
			sfdc.GenerateXLLog();
			sfdc.Quit();
			System.out.println("-----------End of TestScript-------------");
			
		}
	}
	
}
