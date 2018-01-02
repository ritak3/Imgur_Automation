package USER_SPACE.TestScripts.Leads; 
 
import org.openqa.selenium.By;
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

public class TC_SideBarSearch { 


public static void main(String[] args) throws Exception 
{ 

System.out.println("-----------Begin of TestScript-------------"); 
String TCName = "TC_SideBarSearch"; 

//Creating framework instance 
SFDCAutomationFW sfdc = new SFDCAutomationFW(TCName); 
DataSetup.sfdc=sfdc; 



try 
{ 
//Making a connection with excel sheet to retrieve login information 
DB.Connect(DataSetup.Logininfo); 
String URL = DB.ReadXLData("LoginInfo","URL","Name","Sourav Mukherjee"); 

//Launching the URL
sfdc.OpenURL(args,URL, DataSetup.Browser); 

//Login to SFDC 
sfdc.LoginToSFDC("Sourav Mukherjee"); 

//Create Test Case Body 

sfdc.SideBarSearch("Parameters", "Fe");








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
