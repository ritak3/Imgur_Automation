
package USER_SPACE.TestScripts.Leads;

import org.openqa.selenium.NoSuchElementException;

import com.sun.jna.platform.win32.Advapi32Util.Account;

import SOURCE_CODE.SFDC.*;
import USER_SPACE.ObjectRepository.*;
import USER_SPACE.BusinessComponent.*;
import USER_SPACE.TestPrerequisite.DataSetup;

/*
#****************************************************************#
# 																 #
# @Author: [YourName], Cognizant								 #
# @Description: [Mention the test case scope/description]. 	     #
# @Created on: 25/11/2015 										 #
# 											                     #
#****************************************************************#

*/

public class TestScriptTemplate {
	
	
	
	public static void main(String[] args) throws Exception
	{
			System.out.println("-----------Begin of TestScript-------------");
			
			
			String TCName = "TestScript_Template";
			
			SFDCAutomationFW sfdc = new SFDCAutomationFW(TCName);
			
			DataSetup.sfdc=sfdc;
			
			
		try
		{
			DB.Connect(DataSetup.Logininfo);
			String URL = DB.ReadXLData("LoginInfo","URL","Name","Sourav Mukherjee");
			
			//Launching the URL
			sfdc.OpenURL(args,URL, DataSetup.Browser);
			
			//Login to SFDC
			sfdc.LoginToSFDC("Sourav Mukherjee");			
			
			
			//BC.CreateLead("TC_CreateLead");
			
			
			
			
			
			sfdc.LogOff();
			sfdc.Quit();
						
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
