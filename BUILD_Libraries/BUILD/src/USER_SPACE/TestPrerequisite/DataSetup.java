package USER_SPACE.TestPrerequisite;

import SOURCE_CODE.SFDC.SFDCAutomationFW;

public class DataSetup {
	
	public static String pathOffirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	public static String pathOfsafari = "C:\\Program Files (x86)\\Safari\\Safari.exe";
	public static String pathOfOperaLauncher = "C:\\Program Files (x86)\\Opera\\launcher.exe";
	public static String pathOfOperaBinary = "C:\\Program Files\\Opera x64\\opera.exe";
	public static String pathOfchromedriver = "D:\\WebDriverFWONSFDC\\OTHERS\\chromedriver.exe";
	public static String pathOfIEDriver = "D:\\WebDriverFWONSFDC\\OTHERS\\IEDriverServer.exe";
	public static String pathOfProjectHomeDirectory = "D:/Project/PDO - Medical device/AUTOMATION/AutomationWorkspace/MedVantage";
	public static String pathOfTestLogTemplate = "D:\\WebDriverFWONSFDC\\DEMO\\src\\SOURCE_CODE\\SFDC";
	
	public static String Browser = "ff";
	
	public static String testuser1 = "Sourav Mukherjee";
	public static String testuser2 = "Sankha Sen";
	
	
	public static String Logininfo = "GlobalData.xls";
	public static String SheetNameofTestUsers = "LoginInfo";
	public static String SheetForBC = "TestDataStore\\BusinessComponent\\BC.xls";
	public static String PathOfTestLogsExcel = "TestLogs.xls";
	public static String SheetNameofTestLogSummary = "Exe_Log";
	public static String SheetNameofSetupInfo = "Setup";
	
	
	public static String TC_Data_Leads = "TestDataStore\\TestScripts\\TC_Data_Leads.xls";
	
	
	
	
	public static SFDCAutomationFW sfdc;
	public static String SheetNameofTestSuite = "EXE_Leads";
	
	
	
	// ***************** Referring to SendEmailAttachingTestLog function ******************
	/*
	public static String Emailid_TO = "";
	public static String Emailid_CC1 = "";
	public static String Emailid_FROM = "";
	public static String Emailid_Username = ""; //no need to mention @gmail.com. Just mention gmail account name 
	public static String Emailid_Passsword = ""; //type password
	public static String Email_Host = "smtp.gmail.com";
	*/
 
	public static String Emailid_TO = "sourav.mukherjee@cognizant.com";
	public static String Emailid_CC1 = "sourav.mukherjee@cognizant.com"; 
	public static String Emailid_FROM = "sourav.mukherjee@cognizant.com";

	public static String Email_Host = "APACSMTP.cts.com";

	 //Setting up Default Values on Object Repository Creation Fields.
    //public static String SFDC_LOGIN_URL = "[Please enter the login URL]";
	public static String SFDC_LOGIN_URL = "https://login.salesforce.com/";
	//public static String SYSTEM_ADMIN_USERNAME = "[System Admin Username]";
    public static String SYSTEM_ADMIN_USERNAME = "Sourav Mukherjee";
    //public static String NAME_OF_SFDC_OBJECT = "[SFDC Object Name]";
    public static String NAME_OF_SFDC_OBJECT = "Opportunity";
    //public static String SFDC_RECORD_URL = "[SFDC record URL to be entered]";
    public static String SFDC_RECORD_URL = "https://ap1.salesforce.com/0069000000flGZj";
    
    
    //Test Data to be used inside the test case template
    public static String Name_OF_testuser_for_URL = "Sourav Mukherjee";
    public static String Name_OF_testuser_for_Login = "Sourav Mukherjee";
    
}
