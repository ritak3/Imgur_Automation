package USER_SPACE.TestScripts.Opportunity; 
 
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

public class TC_CreateOpp1 { 


public static void main(String[] args) throws Exception 
{ 

System.out.println("-----------Begin of TestScript-------------"); 
String TCName = "TC_CreateOpp1"; 

//Creating framework instance 
SFDCAutomationFW sfdc = new SFDCAutomationFW(TCName); 
DataSetup.sfdc=sfdc; 



try 
{ 
//Making a connection with excel sheet to retrieve login information 
DB.Connect(DataSetup.Logininfo); 
String URL = DB.ReadXLData("LoginInfo", "URL", "Name", "Sourav Mukherjee"); 

//Launching the URL
sfdc.OpenURL(args,URL, DataSetup.Browser); 

//Login to SFDC 
sfdc.LoginToSFDC("Sourav Mukherjee");

//Create Test Case Body 
BC.SelectApplication("Sales");

sfdc.Tab("Opportunities").Click();

//sfdc.Button("New Opportunity").Click();

OpportunityScreen.NewButton().Click();

OpportunityScreen.SaveButton().Click();

OpportunityScreen.OpportunityNameField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
OpportunityScreen.CloseDateField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
OpportunityScreen.StageField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
sfdc.VerifyPageLevelErrorMessage("Review all error messages below to correct your data");

OpportunityScreen.OpportunityNameField().Type("Test_Opp_1");
OpportunityScreen.CloseDateField().Click();
sfdc.SelectFromDateLookup(5);
OpportunityScreen.StageField().SelectPL("Prospecting");
OpportunityScreen.SaveButton().Click();

OpportunityScreen.EditButton().WaitForElement(120);


//Logging Out 
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
