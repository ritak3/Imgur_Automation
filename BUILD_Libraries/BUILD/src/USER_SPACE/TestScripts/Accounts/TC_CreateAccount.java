package USER_SPACE.TestScripts.Accounts;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;




import SOURCE_CODE.SFDC.*;
import USER_SPACE.ObjectRepository.*;
import USER_SPACE.BusinessComponent.*;
import USER_SPACE.TestPrerequisite.DataSetup;

/*
#******************************************************************************#
# 																			   #
# @Author: [YourName], Cognizant											   #
# @Description: This test script creates an Account directly from Account tab. #
# @Created on: 25/11/2015 											           #
# 											                                   #
#******************************************************************************#

*/

public class TC_CreateAccount {
	
	
	
	public static void main(String[] args) throws Exception
	{
			System.out.println("-----------Begin of TestScript-------------");
			
			
			String TCName = "TC_CreateAccount";
			
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
			
			
			//Choosing Sales Application
			//Invoking Business Component
			BC.SelectApplication("Sales");
			
			
			//Calling in built framework function
			//Clicking on Accounts tab
			sfdc.Tab("Accounts").Click();
			
			//Clicking on New button
			sfdc.Button("New").Click();
			
			//Clicking on Save button without entering mandatory information.
			//This is the use of Object Repository
			AccountScreen.SaveButton().Click();
			
			
			//Verifying Field Level Error message
			AccountScreen.AccountNameField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
			
			//Entering value in test field
			AccountScreen.AccountNameField().Type("TestAccount_"+BC.GetCurrentDateTimeStamp());
			
			//Selecting value from pick list
			AccountScreen.RatingField().SelectPL("Cold");
			AccountScreen.TypeField().SelectPL("Customer - Direct");
			AccountScreen.IndustryField().SelectPL("Banking");
			AccountScreen.OwnershipField().SelectPL("Public");
			AccountScreen.CustomerPriorityField().SelectPL("High");
			
			//Choosing a date value from date lookup
			AccountScreen.SLAExpirationDateField().Click();
			sfdc.SelectFromDateLookup(2);
			
			//Entering Address Information
			AccountScreen.PhoneField().Type("1234567891");
			AccountScreen.BillingStreetField().Type("123 Testing Highway");
			AccountScreen.BillingCityField().Type("Kolkata");
			AccountScreen.BillingStateProvinceField().Type("West Bengal");
			AccountScreen.BillingZipPostalCodeField().Type("7000136");
			AccountScreen.BillingCountryField().Type("India");
			
			//Click on Copy Billing Address link
			sfdc.Link("Copy Billing Address to Shipping Address").Click();
			AccountScreen.SLAField().SelectPL("Gold");
			AccountScreen.UpsellOpportunityField().SelectPL("Maybe");
			AccountScreen.ActiveField().SelectPL("No");
			
			//Clicking on Save button
			//AccountScreen.SaveButton().Click();
			sfdc.GetWebDriver().findElement(By.xpath("//input[@name='save'][2]")).click();
			
			if(AccountScreen.EditButton().WaitForElement(30))
			{
				sfdc.AddToXLLogs("Account record has been created successfully.", "Pass");
				System.out.println("Account record has been created successfully.");				
			}
			else
			{
				sfdc.AddToXLLogs("Unable to save the account record during creating.", "Fail");
				System.out.println("Unable to save the account record during creating.");
			}
		
			
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
