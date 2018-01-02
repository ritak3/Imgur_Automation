package USER_SPACE.TestScripts.Leads;

import SOURCE_CODE.SFDC.DB;
import SOURCE_CODE.SFDC.ExitUponTestScriptStepFails;
import SOURCE_CODE.SFDC.SFDCAutomationFW;
import USER_SPACE.BusinessComponent.BC;
import USER_SPACE.ObjectRepository.AccountScreen;
import USER_SPACE.ObjectRepository.ConvertLeadScreen;
import USER_SPACE.ObjectRepository.LeadScreen;
import USER_SPACE.TestPrerequisite.DataSetup;


/*
#******************************************************************************#
# 																			   #
# @Author: [YourName], Cognizant											   #
# @Description: This test script converts a lead created in TC_CreateLead      #
# @Created on: 25/11/2015 											           #
# 											                                   #
#******************************************************************************#
*/

public class TC_ConvertLead {
	
	
	public static void main(String[] args) throws Exception
	{
			System.out.println("-----------Begin of TestScript-------------");
			
			
			String TCName = "TC_ConvertLead";
			
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
			
			//Selecting Application
			BC.SelectApplication("Sales");
			Thread.sleep(3000L);
			
			//Opening the Lead Record
			DB.Connect(DataSetup.TC_Data_Leads);
			sfdc.OpenSFDCRecordAsperURL(DB.ReadXLData("TC_CreateLead", "SFDCURL", "SN", "SN01"));
			
			//Clicking on Convert button
			ConvertLeadScreen.ConvertButton().Click();
			Thread.sleep(2000L);
			sfdc.GetScreenShotONsuccess();
			//Clicking on Convert button again
			ConvertLeadScreen.ConvertButton().Click();
			AccountScreen.ActiveField().VerifyViewOnlyValueEquals("Yes");
			Thread.sleep(3000L);
			
			
			//AccountScreen.AccountNameField().WaitForElement(30);
			String AccountName = AccountScreen.AccountNameField().GetViewOnlyValue();
			if (AccountName.length()>0)
			{
				AccountName = AccountName.substring(0, AccountName.indexOf('[')).trim();
				sfdc.AddToXLLogs("Lead is converted to account successfully", "Pass");
			}
			
			DB.Connect(DataSetup.TC_Data_Leads);
			DB.UpdateXLCell("TC_CreateLead",AccountName, "AccountName", "SN", "SN01");
			
			
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
