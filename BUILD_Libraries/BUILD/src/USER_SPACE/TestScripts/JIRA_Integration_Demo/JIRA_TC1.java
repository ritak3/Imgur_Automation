package USER_SPACE.TestScripts.JIRA_Integration_Demo; 
 
import java.sql.SQLException;

import org.openqa.selenium.NoSuchElementException; 

import com.sun.jna.platform.win32.Advapi32Util.Account; 

import SOURCE_CODE.SFDC.*; 
import USER_SPACE.TestPrerequisite.*; 
import USER_SPACE.BusinessComponent.*; 
import USER_SPACE.ObjectRepository.*; 




/* 
* 
* @Author: <Name of Test Script Creator> 
* @Description: <Please mention the scope of this test script> 
* @General Guidelines: Every Test Script must begin from Launching 
* URL of login screen and must end with browser closed 
*/ 

public class JIRA_TC1 { 


public static void main(String[] args) throws Exception 
{ 

System.out.println("-----------Begin of TestScript-------------"); 
String TCName = "JIRA_TC1"; 

//Creating framework instance 
SFDCAutomationFW sfdc = new SFDCAutomationFW(TCName); 
DataSetup.sfdc=sfdc; 



try 
{ 

	//Code to update test execution log in JIRA -Zephyr Test.
    DB.Connect(DataSetup.Logininfo);
    if(DB.ReadXLData("Setup", "JIRA_Zephyr_Integration_IsEnabled", "SL_NO", "Setup1").equalsIgnoreCase("Yes"))
    {
    	DB.Connect("TestDataStore\\JIRA_Integration\\JIRA_Integration.xls");
	    System.out.println("JIRA TestCase is:"+DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_TestScriptName", "Selenium_TestScriptName", "TC_CreateLead"));
	    DB.Connect("TestDataStore\\JIRA_Integration\\JIRA_Integration.xls");
    	System.out.println("TC:"+DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_Zephyr_TestExecution_ID", "Selenium_TestScriptName", "TC_CreateLead").substring(0,DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_Zephyr_TestExecution_ID", "Selenium_TestScriptName", "TC_CreateLead").indexOf(".")));
    	
    }

   
} 
catch(SQLException sqlexc)
{
	sqlexc.printStackTrace();
	System.out.println("The TestScript is not present JIRA. Please verify test case mapping (JIRA_Integration.xls) between Selenium and JIRA");
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
