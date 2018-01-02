package SOURCE_CODE.SFDC;


import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.Platform;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;






import USER_SPACE.TestPrerequisite.DataSetup;




/**
 * @author Cognizant
 *
 */
/**
 * @author Cognizant
 *
 */
public class SFDCAutomationFW {
	
	public static WebDriver myWD;
	public static JavascriptExecutor Javascript;
	public static Capabilities caps;
	
	public static ArrayList al_xl_steps,al_xl_details,al_xl_results,al_xl_screenshot;
	
	public static String start_time_tc,end_time_tc,browser_ver,reasonforfailure;
	public static long start_time,end_time,seconds,hours,minutes,difference;
	
	public static ArrayList<Integer> al_rowno,al_colno;
	public static ArrayList<String> al_ssfilename;
	public static Integer xl_tc_logs_steps_indicator;
	public static String xl_tc_name=null,FolderName,TCName,xpath;
	
	public static ArrayList al_fieldname,al_sectionname,al_relatedlistname;
	public static ArrayList al_tabnames,al_tabnames_var;
	public static ArrayList al_fieldname_fn, al_sectionname_fn,al_relatedlistname_fn;
	public static SFDCAutomationFW sfdc;
	public static String contentofscreenfile,screenname;
	public static Set< String > uniquebuttons;
	
	public static int xTCRows, xTCCols;
	public static int xlRows, xlCols;
	public static String[][] xlData;
	public static int counterofthreefailsteps;
	
	
	List<WebElement> allposblefieldelements;
	MemberOfAllTab moalltabc;
	MemberOfTab mot;
	MemberOfButton mob;
	MemberOfField mof;
	
	  
    
	public SFDCAutomationFW(String TestCaseName) throws Exception
	{
		TCName  = TestCaseName;
		counterofthreefailsteps = 0;
		
		
		//Overwriting setup data of DataSetup.java as per data supplied in GUI Prerequisite 
		DB.Connect(DataSetup.Logininfo);
		DataSetup.Browser = DB.ReadXLData("Setup", "Browser", "SL_NO", "Setup1");
		DataSetup.pathOffirefox = DB.ReadXLData("Setup", "Path_firefox_exe", "SL_NO", "Setup1");
		DataSetup.pathOfchromedriver = DB.ReadXLData("Setup","Path_chromedriver_exe", "SL_NO", "Setup1");
		DataSetup.pathOfIEDriver = DB.ReadXLData("Setup","Path_IEDriver_exe", "SL_NO", "Setup1");
		DataSetup.pathOfProjectHomeDirectory = DB.ReadXLData("Setup","Path_Home_Directory", "SL_NO", "Setup1");
		DataSetup.pathOfTestLogTemplate = DB.ReadXLData("Setup","Path_TestLog_Template", "SL_NO", "Setup1");
		 	
		System.out.println("TestCase Name is:"+TestCaseName);
		_CreateDirectory();
		
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        start_time_tc = sdf.format(cal.getTime());                     
        
        start_time = System.currentTimeMillis();
        
        DB.InsertXLRowInSummaryTestLogs(DB.Connect(DataSetup.Logininfo), DataSetup.SheetNameofTestLogSummary, TCName,"","",start_time_tc,"","","","");
        // DB.UpdateXLCell("Exe_Log", start_time_tc, ColumnNameTobeUpdated, KeyColumnName, KeyColumnValue);
        
		al_xl_steps = new ArrayList();
		xl_tc_name = TestCaseName;
		al_xl_details = new ArrayList();
		al_xl_results = new ArrayList();
		al_xl_screenshot = new ArrayList();
		xl_tc_logs_steps_indicator = 1;
		
		al_rowno = new ArrayList();
		al_colno = new ArrayList();
		al_ssfilename = new ArrayList();
		
		
		
	}
	private boolean _isSupportedPlatform() throws Exception
	{
	    Platform current = Platform.getCurrent();
	    return Platform.MAC.is(current) || Platform.WINDOWS.is(current);
	}
	
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 1.Name of the browser while running from Batch file, 2. URL to access the application, Name of the browser(chrome,ff,ie,safari,opera) to run individual test case level 
	 * @return boolean
	 * @throws Exception
	 * @Description Launches the URL of the AUT in specified browser.
	 * @Date Aug 7, 2014
	 */
	public boolean OpenURL(String[] args,String URL,String Browser) throws Exception
	{
		try 
		{
			System.out.println("The value of args is:"+args);
			//System.out.println("Testing.......");
			if ((args != null) &&(args.length>0))
			{
				Browser = args[0];
				
			}
			
			
		if (Browser.equalsIgnoreCase("chrome"))
		{
			_killProcess("chromedriver.exe");
			_killProcess("chrome.exe");
			
			System.setProperty("webdriver.chrome.driver",DataSetup.pathOfchromedriver);
			myWD = new ChromeDriver();
			//myWD = new HtmlUnitDriver(true);
			Javascript = ((JavascriptExecutor) myWD);
			AddToXLLogs("Launching URL in ("+Browser+") Browser.", "Pass");
			
			/*
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyRelease(KeyEvent.VK_ESCAPE);
			*/
						
		}
		else if (Browser.equalsIgnoreCase("ff"))
		{
			
			_killProcess("firefox.exe");
			System.out.println("URL: "+URL);
			System.out.println("Browser: "+Browser);
			System.setProperty("webdriver.firefox.bin", DataSetup.pathOffirefox);
			
			myWD = new FirefoxDriver();
			
			//myWD = new HtmlUnitDriver(true);
			Javascript = ((JavascriptExecutor) myWD);
			AddToXLLogs("Launching URL in ("+Browser+") Browser.", "Pass");
		}
		else if(Browser.equalsIgnoreCase("Opera"))
		{
			System.setProperty("os.name","windows");
			DesiredCapabilities capabilities = DesiredCapabilities.opera();
			capabilities.setCapability("opera.logging.level", Level.INFO);
			//capabilities.setCapability("opera.logging.file", "null");
			capabilities.setCapability("opera.binary",DataSetup.pathOfOperaBinary);
			//capabilities.setCapability("opera.host", "127.0.0.1");
			//capabilities.setCapability("opera.launcher",DataSetup.pathOfOperaLauncher);
			//capabilities.setCapability("opera.autostart",true);
			//myWD = new OperaDriver(capabilities);
			//capabilities.setCapability("","");
			//capabilities.setCapability("","");
			
			
			/*
			
			//DesiredCapabilities capabilities = DesiredCapabilities.opera(); 
			DesiredCapabilities capabilities = DesiredCapabilities.opera();
			//capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setCapability("opera.binary", DataSetup.pathOfOperaBinary);
			//capabilities.setCapability("opera.launcher", DataSetup.pathOfOperaLauncher);
			
			capabilities.setCapability("opera.logging.level", "CONFIG");
			
			myWD = new OperaDriver(capabilities);
			
			//capabilities.s.setProperty("opera.binary", DataSetup.pathOfOpera);
			//capabilities.setCapability("opera.log.level", "CONFIG");
			
			//System.setProperty("webdriver.opera.binary", DataSetup.pathOfOpera);
			*/
			
			//capabilities.setPlatform(Platform.ANY);
			//System.setProperty("os.name","windows");
			//myWD = new OperaDriver(capabilities);
			//System.setProperty("os.name","windows");
			//myWD = new OperaDriver();
			Javascript = ((JavascriptExecutor) myWD);
			AddToXLLogs("Launching URL in ("+Browser+") Browser.", "Pass");
		}
		else if (Browser.equalsIgnoreCase("safari"))
		{
			//_killProcess("x.exe");
			//System.setProperty("webdriver.safari.driver", DataSetup.pathOfsafari);
			//System.setProperty("webdriver.safari.driver", DataSetup.pathOfsafari);
			
			//myWD = new SafariDriver();
			
			_killProcess("Safari.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.safari(); 
			System.setProperty("os.name","windows");
			myWD = new SafariDriver(capabilities);
			
			
			//capabilities.setPlatform(Platform.MAC);
			//Thread.sleep(2000L);
			//myWD = new SafariDriver();
			
			
			//Thread.sleep(3000L);
		//	capabilities.setBrowserName("Safari");
			//
			//Thread.sleep(1000L);
			//capabilities.setBrowserName("Safari");
			//capabilities.setJavascriptEnabled(true);
			
			//
			//myWD = new SafariDriver();
			//Thread.sleep(1000L);
			//capabilities.safari();
			//System.setProperty("webdriver.safari.driver", DataSetup.pathOfsafari);
			
			//Thread.sleep(3000L);
			/*
			if (isSupportedPlatform())
			{	
				//System.setProperty("webdriver.safari.driver", DataSetup.pathOfsafari);
				myWD = new SafariDriver();
			}*/
			//myWD = new HtmlUnitDriver(true);
			Javascript = ((JavascriptExecutor) myWD);
			AddToXLLogs("Launching URL in ("+Browser+") Browser.", "Pass");
		}
		else if (Browser.equalsIgnoreCase("ie"))
		{
			//System.setProperty("webdriver.ie.driver", DataSetup.pathOfIEDriver);
			
			_killProcess("IEDriverServer.exe");
			_killProcess("iexplore.exe");
			
			File file = new File(DataSetup.pathOfIEDriver);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			//myWD = new InternetExplorerDriver();
				
			myWD = new InternetExplorerDriver();
			Javascript = ((JavascriptExecutor) myWD);
			AddToXLLogs("Launching URL in ("+Browser+") Browser.", "Pass");
		}
		System.out.println("Driver is:"+myWD);
		System.out.println("URL:"+URL);
		
		myWD.navigate().to(URL);
		
		myWD.manage().window().maximize();
		
		//caps = ((RemoteWebDriver) myWD).getCapabilities();
		if ((myWD.toString().contains("ChromeDriver")) ||(myWD.toString().contains("FirefoxDriver")) || (myWD.toString().contains("InternetExplorerDriver")))
		{
			caps = ((RemoteWebDriver) myWD).getCapabilities();
			browser_ver = "Platform: "+caps.getPlatform()+",Browser Name:"+caps.getBrowserName()+",Version:"+caps.getVersion();
			DB.Connect(DataSetup.Logininfo);
			DB.UpdateXLCell(DataSetup.SheetNameofTestLogSummary,browser_ver, "Browser", "StartofExecution", start_time_tc);
		}
		return true;
		}catch(Exception e) 
		{
			e.printStackTrace();
			AddToXLLogs("Unable to launch the URL","Fail");
			return false;
		}
		
	}
	
	
	
	/**
	 * @author Sourav Mukherjee
	 * @Param NickNameofUser of SFDC
	 * @return boolean
	 * @throws Exception
	 * @Description Logs in to SFDC as a specified nickname of the user. It reads the user name and password from the "Logininfo" sheet of GlobalData.xls file
	 * @Date Aug 7, 2014
	 */
	public boolean LoginToSFDC(String NickNameofUser) throws Exception
	{
		try
		{
		
		WaitForPageToLoad(15);	
		Thread.sleep(1000L);
		//myWD.get(DataSetup.URLofAUT);
		
		Thread.sleep(2000L);
		DB.Connect(DataSetup.Logininfo);
		String URL = DB.ReadXLData("LoginInfo", "URL", "Name", NickNameofUser);
		String UserName = DB.ReadXLData("LoginInfo", "Username", "Name", NickNameofUser);
		String Password = DB.ReadXLData("LoginInfo", "Password", "Name", NickNameofUser);
		//String xpath_UserName_SSO = "//input[normalize-space(@name)='username' and normalize-space(@type)='text'][1]";
		//String xpath_Password_SSO = "//input[normalize-space(@name)='password' and normalize-space(@type)='password'][1]";
		String xpath_UserName = "//input[normalize-space(@type)='email' and normalize-space(@id)='username'][1]";
		String xpath_Password = "//input[normalize-space(@type)='password' and normalize-space(@id)='password'][1]";
		
		String xpath_LoginButton = "//input[contains(@class,'button') and @type='submit'][1]";
		//String xpath_LoginButton_SSO = "//input[normalize-space(@type)='image' and contains(normalize-space(@src),'sign')][1]"; 
		/*
		if(myWD.toString().contains("ChromeDriver"))
		{
			myWD.findElement(By.xpath("/")).sendKeys(Keys.ESCAPE);
			
		}
		*/
		
		WaitForPageToLoad(15); 
		
		//myWD.get(URL);
		//Thread.sleep(5000L);
		
			myWD.findElement(By.xpath(xpath_UserName)).clear();
			myWD.findElement(By.xpath(xpath_UserName)).sendKeys(UserName);
	        
			myWD.findElement(By.xpath(xpath_Password)).clear();
			myWD.findElement(By.xpath(xpath_Password)).sendKeys(Password);
		
			     
			//myWD.findElement(By.xpath("//*[text()='Log in to Salesforce'][1]")).click();
			//myWD.findElement(By.xpath("//input[normalize-space(@type)='image' and contains(normalize-space(@src),'signIn')]")).click();
			myWD.findElement(By.xpath(xpath_LoginButton)).click();
				
		
		
		//WaitForElement("//a[contains(normalize-space(text()),'Home')]", 30);
		WebDriverWaitForElement("//a[contains(normalize-space(text()),'Home')]", 30);
		this.Tab("Home").Click();
		this.WaitForPageToLoad(15);
        if (myWD.findElement(By.xpath("//a[contains(normalize-space(text()),'Home')]")).isDisplayed())
        {
        	System.out.println("The user ("+NickNameofUser+") is able to login successfully.");
        	AddToXLLogs("The user ("+NickNameofUser+") is able to login successfully.","Pass");
        	
        	//Adding Profile name to the Exe_Logs sheet in GlobalData.xls
        	DB.Connect(DataSetup.Logininfo);
        	DB.UpdateXLCellWithApend(DataSetup.SheetNameofTestLogSummary, DB.ReadXLData(DataSetup.SheetNameofTestUsers, "Profiles", "Name", NickNameofUser), "Profiles", "StartofExecution", start_time_tc);
        	//Thread.sleep(15000L); 
        	return true;
        }
        else
        {
        	AddToXLLogs("The user ("+NickNameofUser+") is unable to login.","Fail");
        	System.out.println("The user ("+NickNameofUser+") is unable to login.");
        	return false;
        }
		}catch(Exception e)
		{
			AddToXLLogs("The user ("+NickNameofUser+") is unable to login.","Fail");
        	System.out.println("The user ("+NickNameofUser+") is unable to login.");
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	/**
	 * @author Cognizant
	 * @param UserName by which you want to login
	 * @return true if Login is Successful else return false
	 * @Description login to Salesforce with single sign on feature. 
	 * @throws Exception
	 */
	public boolean LoginAsUser(String UserName) throws Exception
	{
		try
		{
				
			
			LoginToSFDC(DataSetup.SYSTEM_ADMIN_USERNAME);
			GlobalSearch("People", UserName);
			Thread.sleep(2000L);
			WaitForPageToLoad(15);
			WaitForElement("//a[contains(normalize-space(@class),'zen-trigger') and contains(normalize-space(@title),'User Action Menu')]", 20);
			GetWebDriver().findElement(By.xpath("//a[contains(normalize-space(@class),'zen-trigger') and contains(normalize-space(@title),'User Action Menu')]")).click();
//			sfdc.GetWebDriver().findElement(By.xpath("//div[normalize-space(@class)='profileHeader']/div[1]/div[1]/a[1]")).click();
			//sfdc.HighLight(sfdc.WebDriverWaitForElement("//div[normalize-space(@class)='profileHeader']/div[1]/div[1]/a[1]/b[@class='zen-selectArrow']", 10));
			//sfdc.WebDriverWaitForElement("//div[normalize-space(@class)='profileHeader']/div[1]/div[1]/a[1]/b[@class='zen-selectArrow']", 10).click();
			Thread.sleep(1000L);
			WaitForElement("//span[normalize-space(text())='User Detail']", 20);			
			GetWebDriver().findElement(By.xpath("//span[normalize-space(text())='User Detail']")).click();
			Thread.sleep(2000L);
			Button("Login").WaitForElement(10);
			
			Button("Login").Click();
			WaitForPageToLoad(20);
			AddToXLLogs("The user ("+UserName+") has successfully logged into the application.", "Pass");
			System.out.println("The user ("+UserName+") has successfully logged into the application.");
			
			return true;
		}
		catch(Exception e)
		{
			AddToXLLogs("Unable to login as ("+UserName+"). Please check the xpaths in LoginAsUser function business component.","Fail");
			System.out.println("Unable to login as ("+UserName+"). Please check the xpaths in LoginAsUser function business component.");
			e.printStackTrace();
			return false;
			
		}
    }
	
	/**
	 * @author Sourav Mukherjee
	 * @Param Name of 1. SFDC Object like Accounts,Leads,Contacts 2. SearchText
	 * @return boolean
	 * @throws Exception
	 * @Description Searches any sfdc record from SFDC GlobalSearch option
	 * @Date Aug 7, 2014
	 */
	public boolean GlobalSearch(String Object,String SearchItem) throws Exception
	{
		WaitForPageToLoad(15);
		xpath = "//input[contains(@title,'Search') or contains(@id,'Search') or contains(@name,'Search')]";
		try
		{
			myWD.findElement(By.xpath(xpath)).clear();
			myWD.findElement(By.xpath(xpath)).sendKeys(SearchItem.trim());
			//Thread.sleep(1000L);
			//myWD.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
			myWD.findElement(By.xpath("//input[normalize-space(@id)='phSearchButton' and normalize-space(@type)='button' and normalize-space(@value)='Search'][1]")).click();
			WaitForPageToLoad(15);
			Thread.sleep(1000L);
			xpath = "//*[(contains(normalize-space(text()),'"+ Object + "')) and (local-name()='h3' or (local-name()='span' and contains(@class,'searchFirstCell')))]//ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::a[contains(normalize-space(text()),'"+SearchItem+"')]";
			WebDriverWaitForElement(xpath, 20).click();
			//myWD.findElement(By.xpath(xpath)).click();
			System.out.println("Successfully searched "+Object+" record ("+SearchItem+")");
			AddToXLLogs("Successfully searched "+Object+" record ("+SearchItem+")", "Pass");
			return true;
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			xpath = "//div[@class='messageText' and contains(normalize-space(text()),'No matches found')]";
			try
			{
			if (myWD.findElement(By.xpath(xpath)).isDisplayed())
			{
				System.out.println("Search could not find any result for "+Object+" record ("+SearchItem+"). The search result message displayed as (No matches found)");
				AddToXLLogs("Search could not find any result for "+Object+" record ("+SearchItem+"). The search result message displayed as (No matches found)", "Fail");
				return false;
			}
			else
			{
				System.out.println("Unable to search the "+Object+" record ("+SearchItem+")");
				AddToXLLogs("Unable to search the "+Object+" record ("+SearchItem+")", "Fail");
				return false;
			}
			}
			catch(Exception e1)
			{
				System.out.println("Unable to search the "+Object+" record ("+SearchItem+") when xpath is:"+xpath);
				AddToXLLogs("Unable to search the "+Object+" record ("+SearchItem+") when xpath is:"+xpath, "Fail");
				e1.printStackTrace();
				return false;
			}
				
		}
	}
	
	
	/**
	 * @author Cognizant
	 * @param Object
	 * @param SearchItem
	 * @return true if search result is found else return false
	 * @Description Search any salesforce record from Sidebar search
	 * @throws Exception
	 */
	public boolean SideBarSearch(String Object,String SearchItem) throws Exception
    {
           WaitForPageToLoad(15);
           xpath = "//select[contains(@title,'Search')][1]";
           try
           {
                  
                  Select s = new Select(myWD.findElement(By.xpath(xpath)));
                  s.selectByVisibleText(Object);
                  myWD.findElement(By.xpath("//input[contains(@class,'search') and @type='text'][1]")).clear();
                  myWD.findElement(By.xpath("//input[contains(@class,'search') and @type='text'][1]")).sendKeys(SearchItem);
                  Thread.sleep(1000L);
                  myWD.findElement(By.xpath("//input[contains(@class,'search') and @type='text'][1]")).sendKeys(Keys.ENTER);
                  Thread.sleep(3000L);
                  //WaitForPageToLoad(30);
                  xpath = "//*[(contains(normalize-space(text()),'"+ Object + "')) and (local-name()='h3' or (local-name()='span' and contains(@class,'searchFirstCell')))]//ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::a[contains(normalize-space(text()),'"+SearchItem+"')]";
                  WebDriverWaitForElement(xpath, 20).click();
                  System.out.println("Successfully searched "+Object+" record ("+SearchItem+")");
                  AddToXLLogs("Successfully searched "+Object+" record ("+SearchItem+")", "Pass");
                  return true;
           }
           catch(Exception e)
           {
                  AddToXLLogs("Unable to search the item ("+SearchItem+") against object ("+Object+") from Sidebar Search.", "Fail");
                  System.out.println("Unable to search the item ("+SearchItem+") against object ("+Object+") from Sidebar Search.");
                  return false; 
           }
    }
	
	
	/**
	 * @author 185584
	 * @Description When search results could be more than one with exact match
	 * @param ObjectName
	 * @param SearchItem
	 * @param TargetColumn
	 * @param KeyColumn
	 * @param KeyColumnValue
	 * @return true if search result is found, else return flase
	 * @throws Exception
	 */
	public boolean SideBarSearch(String ObjectName,String SearchItem, String TargetColumn,String KeyColumn,String KeyColumnValue) throws Exception
 	{
 				
 		try
 		{
 			 xpath = "//select[contains(@title,'Search')][1]";
 			 Select s = new Select(myWD.findElement(By.xpath(xpath)));
             s.selectByVisibleText(ObjectName);
             myWD.findElement(By.xpath("//input[contains(@class,'search') and @type='text'][1]")).clear();
             myWD.findElement(By.xpath("//input[contains(@class,'search') and @type='text'][1]")).sendKeys(SearchItem);
             Thread.sleep(1000L);
             myWD.findElement(By.xpath("//input[contains(@class,'search') and @type='text'][1]")).sendKeys(Keys.ENTER);
             Thread.sleep(3000L);
             //WaitForPageToLoad(30);
             xpath = "//*[(contains(normalize-space(text()),'"+ ObjectName + "')) and (local-name()='h3' or (local-name()='span' and contains(@class,'searchFirstCell')))]//ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::a[contains(normalize-space(text()),'"+SearchItem+"')]";
             Thread.sleep(2000L);        
             
 		String xpath_KeyColumnPosition ="",xpath_TargetColumnPosition="";
 		
 		//System.out.println("TagName is: "+myWD.findElement(By.xpath("//*[normalize-space(text())='"+ObjectName+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName());
 		//WebElement test = myWD.findElement(By.xpath("//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]"));
 		//System.out.println(test.getText());
 		 		
 		//System.out.println("TagName is: "+myWD.findElement(By.xpath("//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]")).getTagName());
 		
 		
 		if (myWD.findElement(By.xpath("//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
 		{
 			xpath = "//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]";
 			xpath_TargetColumnPosition = xpath + "//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]/ancestor-or-self::div[@class='pbHeader'][1]/following-sibling::div[@class='pbBody'][1]/descendant-or-self::tr[@class='headerRow'][1]/descendant-or-self::*[normalize-space(text())='"+TargetColumn+"']/ancestor-or-self::th[1]/preceding-sibling::th";
 			xpath_KeyColumnPosition = xpath +"//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]/ancestor-or-self::div[@class='pbHeader'][1]/following-sibling::div[@class='pbBody'][1]/descendant-or-self::tr[@class='headerRow'][1]/descendant-or-self::*[normalize-space(text())='"+KeyColumn+"']/ancestor-or-self::th[1]/preceding-sibling::th";
 		    
 		}
 		else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+ObjectName+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
 		{
 			//need to update for Go To List page if there is any
 			
 		}	

 		//System.out.println("xpath is: "+xpath);	
 		//System.out.println("xpath_KeyColumnPosition is: "+xpath_KeyColumnPosition);
 		//System.out.println("xpath_TargetColumnPosition is: "+xpath_TargetColumnPosition);
 		
 		
 		Integer KeyColumnPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size()+1; 
 		
 		//System.out.println("KeyColumnPosition:"+KeyColumnPosition);
 		
 		
 	
 		Integer CountOfSearchItems =  myWD.findElements(By.xpath("//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]/ancestor-or-self::div[@class='pbHeader'][1]/following-sibling::div[@class='pbBody'][1]/descendant-or-self::tr")).size() - 1;  
 		//System.out.println("Count Of Search Result Items:"+CountOfRLItems);
     	Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
 	    System.out.println("TargetColumnPosition:"+TargetColumnPosition);
 	    
 	    System.out.println("KeyColumnPosition"+KeyColumnPosition +" CountOfRLItems:"+CountOfSearchItems +" TargetColumnPosition:"+TargetColumnPosition);
 	    Integer RowPositionOfKeyColumnValue;
 	    
 	    for(int i=2;i<=CountOfSearchItems;i++)
 	    {
 	    	if (myWD.findElement(By.xpath("//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]/ancestor-or-self::div[@class='pbHeader'][1]/following-sibling::div[@class='pbBody'][1]/descendant-or-self::tr["+i+"]/descendant-or-self::*[local-name()='th' or local-name()='td']["+KeyColumnPosition+"]")).getText().trim().equals(KeyColumnValue))
 	    	{
 	    	
 	    		myWD.findElement(By.xpath("//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]/ancestor-or-self::div[@class='pbHeader'][1]/following-sibling::div[@class='pbBody'][1]/descendant-or-self::tr["+i+"]/descendant-or-self::*[local-name()='th' or local-name()='td']["+TargetColumnPosition+"]")).click();
 	    		
 	    		System.out.println("Successfully clicked on ("+TargetColumn+") for related list item ("+KeyColumnValue+") in the ("+ObjectName+")related list");
 	    		AddToXLLogs("Successfully clicked on ("+myWD.findElement(By.xpath("//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]//span[contains(normalize-space(text()),'"+ObjectName+"')][1]/ancestor-or-self::*[local-name()='h3' or local-name()='h1'][1]/ancestor-or-self::div[@class='pbHeader'][1]/following-sibling::div[@class='pbBody'][1]/descendant-or-self::tr["+i+"]/descendant-or-self::*[local-name()='th' or local-name()='td']["+TargetColumnPosition+"]")).getText().trim()+") for related list item ("+KeyColumnValue+") in the ("+ObjectName+")related list", "Pass");
 	    		return true;
 	    		//RowPositionOfKeyColumnValue = 
 	    	}
 	    	System.out.println("No Match");
 	    }
 	    
 	   /* 
 	    Integer KeyColumnValueRowPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1;
 		System.out.println("KeyColumnPosition:"+KeyColumnPosition);
 		System.out.println("CountOfRLItems"+CountOfRLItems);
 		*/
 		
 		System.out.println("Unable to click on the element in the ("+ObjectName+") related list.");
 		AddToXLLogs("Unable to click on the element in the ("+ObjectName+") related list.", "Fail");
 		return false;
 		}
 		catch(Exception e)
 		{
 			System.out.println("Unable to click on the element in the ("+ObjectName+") related list when xpath is:"+xpath);
 			AddToXLLogs("Unable to click on the element in the ("+ObjectName+") related list when xpath is:"+xpath, "Fail");
 			e.printStackTrace();
 			return false;
 		}
 	}
	
	/**
	 * @author Cognizant
	 * @Description Clicking on Details tab against the record. 
	 * @throws Exception
	 */
	public void ClickONDetailsTab() throws Exception
	{
		try
		{
			Thread.sleep(1000L);
			//WaitForElement("//ul[normalize-space(@class)='optionContainer'][1]/li[2]/a[1]", 30);
			GetWebDriver().findElement(By.xpath("//ul[normalize-space(@class)='optionContainer'][1]/li[2]/a[1]/span[2]")).click();
			AddToXLLogs("Clicked on Details tab against the record.", "Pass");
			System.out.println("Clicked on Details tab against the record.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to find the Details tab for clicking.", "Fail");
			System.out.println("Unable to find the Details tab for clicking.");
			
		}
	}
	public MemberOfAllTab AllTabs()
	{
		
		return new MemberOfAllTab(myWD);
	}
	
	public MemberOfTab Tab(String TabName)
	{
		
		return new MemberOfTab(TabName);
	}
	
	public MemberOfButton Button(String ButtonName)
	{
		return new MemberOfButton(ButtonName);
	}
	
	
	
	public MemberOfField Field(String FieldName)
	{
		return new MemberOfField(FieldName);
	}
	
	public MemberOfSection Section(String sectionname)
	{
		return new MemberOfSection(sectionname);
	}
	
	public MemberOfView View()
	{
		return new MemberOfView();
	}
	
	public MemberOfListView ListView(String ColumnName)
	{
		return new MemberOfListView(ColumnName);
	}
	public MemberOfLink Link(String LinkName)
	{
		return new MemberOfLink(LinkName);
	}
	public MemberOfRelatedList RelatedList(String RelatedListName)
	{
		return new MemberOfRelatedList(RelatedListName);
	}
	public MemberOfRL _RL(String RelatedListName,String ColumnName,Integer RowIndex)
	{
		return new MemberOfRL(RelatedListName,ColumnName,RowIndex);
	}
	public MemberOfRL _RL(String RelatedListName,String ColumnName)
	{
		return new MemberOfRL(RelatedListName,ColumnName);
	}
	public MemberOfSEC _SEC(String SectionName,String FieldName)
	{
		return new MemberOfSEC(SectionName,FieldName);
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 1. Enter the Log text message to be sent in the excel logs 2. Pass/Fail
	 * @return void
	 * @throws Exception
	 * @Description Adds the message to excel logs along with Pass/Fail message
	 * @Date Aug 7, 2014
	 */
	public static void AddToXLLogs(String Message,String PassORFail) throws Exception
	{
		
		try
		{
		al_xl_details.add(Message);
		al_xl_results.add(PassORFail);
		
		if (PassORFail.equalsIgnoreCase("Fail"))
		{
			counterofthreefailsteps = counterofthreefailsteps + 1;
			System.out.println("Total Step Failed: "+counterofthreefailsteps);
			al_xl_screenshot.add(_GetScreenShot());
						
		}
		else
		{
			al_xl_screenshot.add("NA");
			System.out.println("Total Step Failed: "+counterofthreefailsteps);
		}
		
		
		if (counterofthreefailsteps >= 3) 
		{
			System.out.println("Total Step Failed: "+counterofthreefailsteps);
			ExitUponTestScriptStepFails exp = new ExitUponTestScriptStepFails("More than 3 steps fail");
			throw exp;
		}
		}catch(Exception e)
		{
			al_xl_screenshot.add("Window not present");
			//counterofthreefailsteps = counterofthreefailsteps + 1;
			
			System.out.println("Unable to capture the screen shot.");
			e.printStackTrace();
			
			if (counterofthreefailsteps >= 3) 
			{
				System.out.println("Total Step Failed: "+counterofthreefailsteps);
				ExitUponTestScriptStepFails exp = new ExitUponTestScriptStepFails("More than 3 steps fail");
				
				throw exp;
			}
		}
	}
	
	/**
	 * @author 185584
	 * @Description Captures the screen shot of browser opened by WebDriver in any point in time and also attached the same in the excel log file. 
	 * @return
	 * @throws Exception
	 */
	public static boolean GetScreenShotONsuccess() throws Exception 
	{
		
		try
		{
		al_xl_details.add("Screenshot captured successfully");
		al_xl_results.add("Pass");
		
		al_xl_screenshot.add(_GetScreenShot());
		return true;
		}
		catch(Exception e)
		{
			al_xl_screenshot.add("Window not present");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return WebDriver
	 * @throws Exception
	 * @Description Returns the WebDriver instance
	 * @Date Aug 7, 2014
	 */
	public static WebDriver GetWebDriver() throws Exception
	{
		return myWD;
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return Name of the screen shot file name on Success, returns blank value on failure
	 * @throws Exception
	 * @Description Captures screen shot of the WebDriver window
	 * @Date Aug 7, 2014
	 */
	public static String _GetScreenShot() throws Exception 
	{
		try 
		{
		File screenshot = ((TakesScreenshot)myWD).getScreenshotAs(OutputType.FILE);
		if(screenshot.exists())
		{
			String screenshotfilename = TCName+"_"+GetCurrentDateTimeStamp()+".png";
			FileUtils.copyFile(screenshot, new File("TestLogs\\"+FolderName+"\\"+screenshotfilename));
			
			return screenshotfilename;	
		}
		else
		{
			return "Window Closed";
		}
		}catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception:Unable to capture the screen shot");
			return "Window Closed";
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @DisplayMode Edit page
	 * @return Entire text of the Error message displayed on the edit page
	 * @throws Exception
	 * @Description Reads the entire text of the error message displayed on the edit page and returns the same on success and returns blank value on failure
	 * @Date Aug 7, 2014
	 */
	public static String GetPageLevelErrorMessage() throws Exception 
	{
		String errorMessage = "";
		try
		{
			errorMessage = myWD.findElement(By.xpath("//div[normalize-space(@class)='pbError' and normalize-space(@id)='errorDiv_ep']")).getText().trim();
			AddToXLLogs("The page level error message displayed as ("+errorMessage+")", "Pass");
			System.out.println("The page level error message displayed as ("+errorMessage+")");
			return errorMessage;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Could not find any page level error message", "Fail");
			System.out.println("Could not find any page level error message");
			return errorMessage;
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param Expected partial string of the page level error message to be verified
	 * @return boolean
	 * @DisplayMode Edit page 
	 * @throws Exception
	 * @Description Verifies if the text message/error message displayed on the page level error message contains the partial text specified in the parameter 
	 * @Date Aug 7, 2014
	 */
	public static boolean VerifyPageLevelErrorMessage(String ErrorMessage) throws Exception 
	{
		//xpath = "//div[normalize-space(@class)='pbError' and normalize-space(@id)='errorDiv_ep']";
		xpath = "//div[(normalize-space(@class)='pbError' and normalize-space(@id)='errorDiv_ep') or (normalize-space(@class)='messageText')]";
		try
		{
			WebDriverWaitForElement(xpath,15);
			if (myWD.findElement(By.xpath(xpath)).getText().trim().contains(ErrorMessage.trim()))
			{
				AddToXLLogs("Successfully verified the page level error message ("+ErrorMessage+")", "Pass");
				System.out.println("Successfully verified the page level error message ("+ErrorMessage+")");
				return true;
			}
			else
			{
				AddToXLLogs("Unable to verify the page level error message when actual message is ("+myWD.findElement(By.xpath("//div[normalize-space(@class)='pbError' and normalize-space(@id)='errorDiv_ep']")).getText().trim()+") and expected message is ("+ErrorMessage+")", "Fail");
				System.out.println("Unable to verify the page level error message when actual message is ("+myWD.findElement(By.xpath("//div[normalize-space(@class)='pbError' and normalize-space(@id)='errorDiv_ep']")).getText().trim()+") and expected message is ("+ErrorMessage+")");
				return false;
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to verify the page level error message when actual message is ("+myWD.findElement(By.xpath("//div[normalize-space(@class)='pbError' and normalize-space(@id)='errorDiv_ep']")).getText().trim()+") and expected message is ("+ErrorMessage+")", "Fail");
			System.out.println("Unable to verify the page level error message when actual message is ("+myWD.findElement(By.xpath("//div[normalize-space(@class)='pbError' and normalize-space(@id)='errorDiv_ep']")).getText().trim()+") and expected message is ("+ErrorMessage+")");
			return false;
		}
	}
	
	
	/**
	 * @author Sourav Mukherjee
	 * @Param path of the excel log file
	 * @return void
	 * @throws Exception
	 * @Description Reads the excel log file that gets generated dynamically and stores the row number, column number and screen shot file name to three array list.
	 * @Date Aug 7, 2014
	 */
	public static void _xlRead(String sPath) throws Exception
	{
		try 
		{
		File myxl = new File(sPath);
		FileInputStream myStream = new FileInputStream(myxl);
		
		HSSFWorkbook myWB = new HSSFWorkbook(myStream);
		HSSFSheet mySheet = myWB.getSheetAt(0);	// Referring to 1st sheet
		xlRows = mySheet.getLastRowNum() + 1;
		xlCols = mySheet.getRow(0).getLastCellNum();
		//System.out.println("TC Rows are: " + xlRows);
		//System.out.println("TC Cols are: "+xlCols);
		//System.out.println("TC Cols are " + xTCCols);
		xlData = new String[xlRows][xlCols];
		String value = "";
	    for (int i = 3; i < xlRows; i++) 
	    {
	           HSSFRow row = mySheet.getRow(i);
	           HSSFCell cell = row.getCell(4); 
	           value = cell.getStringCellValue();
	           HSSFCell cell1 = row.getCell(5);
	          // System.out.println("["+i+",3]"+value);
	           if ((cell.getStringCellValue().equalsIgnoreCase("Fail")) || (cell.getStringCellValue().equalsIgnoreCase("Pass") && cell1.getStringCellValue().trim().length() > 2) )
	           {
	        	   
	        	   al_rowno.add(i);
	        	   al_colno.add(5);
	        	   al_ssfilename.add(cell1.getStringCellValue());
	        	   
	           }   
	                      
	    }
	    
	    mySheet=null;
	    myWB=null;
	    myStream=null;
	    myxl=null;
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String _cellToString(HSSFCell cell) {
    	// This function will convert an object of type excel cell to a string value
    		try {
			int type = cell.getCellType();
    		Object result;
    		switch (type) {
    			case HSSFCell.CELL_TYPE_NUMERIC: //0
    				result = cell.getNumericCellValue();
    				break;
    			case HSSFCell.CELL_TYPE_STRING: //1
    				result = cell.getStringCellValue();
    				break;
    			case HSSFCell.CELL_TYPE_FORMULA: //2
    				throw new RuntimeException("We can't evaluate formulas in Java");
    			case HSSFCell.CELL_TYPE_BLANK: //3
    				result = "-";
    				break;
    			case HSSFCell.CELL_TYPE_BOOLEAN: //4
    				result = cell.getBooleanCellValue();
    				break;
    			case HSSFCell.CELL_TYPE_ERROR: //5
    				throw new RuntimeException ("This cell has an error");
    			default:
    				throw new RuntimeException("We don't support this cell type: " + type);
          }
          return result.toString();
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    			return "";
    		}
    		
        }
	
	/**
	 * @author Sourav Mukherjee
	 * @Param PathOfTestLogFile(Excel)
	 * @return void
	 * @throws Exception
	 * @Description Attaches the screen shot to the excel log against the respective failed message 
	 * @Date Aug 7, 2014
	 */
	public static void _AttachScreenShotToXL(String PathOfTestLogFile) throws Exception
	{
		
		_xlRead(PathOfTestLogFile);
		
		
		FileInputStream file = new FileInputStream(new File(PathOfTestLogFile));

        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        try{
        
        //Cell cell = null;
        //HSSFHyperlink file_link=new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
        //HSSFHyperlink file_link=new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
        
        Cell cel[] = new Cell[al_rowno.size()];
        HSSFHyperlink f_link[] = new HSSFHyperlink[al_rowno.size()];
        
        //List<Cell> cel = new ArrayList<Cell>();
        //List<HSSFHyperlink> f_link = new ArrayList<HSSFHyperlink>();
        
        
        //System.out.println("ArraySize is:"+al_rowno.size());
        //Update the value of cell
        int rownum = 1;
        
        for(int i=0;i<al_rowno.size();i++)
		{
        	//System.out.println("i="+i);
        	    	
        	f_link[i] = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
        	f_link[i].setTextMark("'ScreenShots'!A"+rownum);
        	cel[i] =  sheet.getRow(al_rowno.get(i)).getCell(al_colno.get(i));
        	cel[i].setCellValue(al_ssfilename.get(i));
			cel[i].setHyperlink(f_link[i]);
			rownum = rownum + 40;
        	/*
        	file_link.setAddress("file:///"+DataSetup.pathOfProjectHomeDirectory+"//TestLogs//"+FolderName+"//"+al_ssfilename.get(i));
			 //Row row = my_sheet.getRow(al_rowno.get(i));
			 cell = sheet.getRow(al_rowno.get(i)).getCell(al_colno.get(i));
			 cell.setCellValue(al_ssfilename.get(i));
			 cell.setHyperlink(file_link);
			 */
		}
        
       
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			file.close();
	        FileOutputStream outFile =new FileOutputStream(new File(PathOfTestLogFile));
		    workbook.write(outFile);
		    outFile.close();
		}
        
		/*
		HSSFWorkbook my_workbook = new HSSFWorkbook();
        HSSFSheet my_sheet = my_workbook.getSheet("Results");
        HSSFHyperlink file_link=new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
        
		for(int i=0;i<al_rowno.size();i++)
		{
			 file_link.setAddress("file:///"+DataSetup.pathOfProjectHomeDirectory+"//TestLogs//"+FolderName+"//"+al_ssfilename.get(i));
			 Row row = my_sheet.getRow(al_rowno.get(i));
			 Cell cell = row.getCell(al_colno.get(i));
			 cell.setHyperlink(file_link);
		}
		FileOutputStream out = new FileOutputStream(new File(PathOfTestLogFile));
        my_workbook.write(out);
        out.close();
	*/
	}
	
	/*
	 * The below function Inserts all the PNG files(screen shots) in a particular 
	 * test case logs in the ScreenShots sheet. This function has been developed 
	 * in such a way so that it can work on .xls as well as .xlsx log files
	 */
	
	/**
	 * @author Sourav Mukherjee
	 * @Param Excel Log file name with absolute path 
	 * @return void
	 * @throws Exception
	 * @Description Inserts the failed screen shots to the sheet 'ScreenShot' of the excel log file
	 * @Date Aug 7, 2014
	 */
	public static void _InsertImageToSpreadSheet(String filepath) throws Exception
	{
		InputStream File = new FileInputStream(filepath);
		/* Create a Workbook and Worksheet */
		Workbook my_workbook = WorkbookFactory.create(File);
		//System.out.println("_InsertImageToSpreadSheet function Invoked");
		try
		{
		
		// Workbook my_workbook = new Workbook(File);
        Sheet my_sheet = my_workbook.getSheet("ScreenShots");   
                
        
        int rownum = 1;
        InputStream my_banner_image[] = new InputStream[al_ssfilename.size()];
        int my_picture_id[] = new int[al_ssfilename.size()];
        Drawing drawing[] = new Drawing[al_ssfilename.size()];
        ClientAnchor my_anchor[] = new ClientAnchor[al_ssfilename.size()];
        Picture  my_picture[] = new Picture[al_ssfilename.size()];
        
        for(int i=0;i<al_ssfilename.size();i++)
        {
        if (!al_ssfilename.get(i).equalsIgnoreCase("Window not present"))
        {
        /* Read input PNG / JPG Image into FileInputStream Object*/
        //System.out.println(DataSetup.pathOfProjectHomeDirectory+"//TestLogs//"+FolderName+"//"+al_ssfilename.get(i));
        my_banner_image[i] = new FileInputStream(DataSetup.pathOfProjectHomeDirectory+"//TestLogs//"+FolderName+"//"+al_ssfilename.get(i));
        /* Convert picture to be added into a byte array */
        byte[] bytes = IOUtils.toByteArray(my_banner_image[i]);
        /* Add Picture to Workbook, Specify picture type as PNG and Get an Index */
        my_picture_id[i] = my_workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        /* Close the InputStream. We are ready to attach the image to workbook now */
        my_banner_image[i].close();                
        /* Create the drawing container */
        drawing[i] = my_sheet.createDrawingPatriarch();
        
        //Returns an object that handles instantiating concrete classes
        CreationHelper helper = my_workbook.getCreationHelper();
        /* Create an anchor point */
        my_anchor[i] = helper.createClientAnchor();
        /* Define top left corner, and we can resize picture suitable from there */
        my_anchor[i].setCol1(1);
        my_anchor[i].setRow1(rownum);           
        /* Invoke createPicture and pass the anchor point and ID */
        my_picture[i] = drawing[i].createPicture(my_anchor[i], my_picture_id[i]);
        /* Call resize method, which resizes the image */
        my_picture[i].resize(); 
        rownum = rownum + 40;
        
        }
        
        }
        /* Write changes to the workbook */
        FileOutputStream out = new FileOutputStream(new File(filepath));
        my_workbook.write(out);
        out.close();
                
        
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Generates the Excel log file for each of the test scripts
	 * @Date Aug 7, 2014
	 */
	public static void GenerateXLLog() throws Exception
	{
		int i=0;
		int countoflogmessage = al_xl_results.size();
		
		//Calculating end time for each test case execution
		Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        end_time_tc = sdf1.format(cal1.getTime());      
		
        end_time = System.currentTimeMillis();
        
        difference = end_time-start_time;
		
		//System.out.println(difference);	
		
		seconds = difference / 1000;
		minutes = seconds / 60;
		seconds = seconds % 60;
		hours = minutes / 60;
		minutes = minutes % 60;
		String duration = hours + "h:" + minutes + "m:" + seconds+"s";
        
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss.SSS");
        String filename = sdf.format(cal.getTime());                     
        String TCOverallStatus = "Pass";
       
        //Calculating if all the steps are passed or not
        for(int c=0;c<countoflogmessage;c++)
		{
        	if (al_xl_results.get(c).equals("Fail"))
        	{
        		TCOverallStatus = "Fail";
        		c = countoflogmessage;
        	}
			//System.out.println("Step-"+(c+1)+"|"+"TC-"+xl_tc_name+"|"+"Details-"+al_xl_details.get(c)+"|"+"Result-"+al_xl_results.get(c)+"|");
		}
        
		FileUtils.copyFile(new File(DataSetup.pathOfTestLogTemplate+"\\TestLog_Template.xls"), new File("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls"));
		Thread.sleep(3000L);
		Connection connectivity = DB.Connect("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls");
		reasonforfailure = "";
		try{
		for(int c=0;c<countoflogmessage;c++)
		{
			
			if(al_xl_results.get(c).toString().equalsIgnoreCase("Fail"))
			{
				reasonforfailure = reasonforfailure + "Step-"+(c+1)+":"+al_xl_details.get(c).toString() + "\n";
			}
			//System.out.println("reasonforfailure:"+reasonforfailure);
			PreparedStatement ps = connectivity.prepareStatement("insert into [Results$] values(?,?,?,?,?,?)");
			ps.setString(1,"");
			ps.setString(2,"Step-"+(c+1));
			ps.setString(3, xl_tc_name);
			ps.setString(4, al_xl_details.get(c).toString());
			ps.setString(5, al_xl_results.get(c).toString());
			ps.setString(6, al_xl_screenshot.get(c).toString());
				
			ps.executeUpdate();
			ps.close();
				
				//db.InsertXLRowInTestLogs(connectivity,"Results", "Step-"+(c+1), xl_tc_name, al_xl_details.get(c).toString(),al_xl_results.get(c).toString());
				//db.InsertXLRowInTestLogs("Results", "Step-"+(c+1), xl_tc_name , al_xl_details.get(c), al_xl_results.get(c));
			
				//System.out.println("Step-"+(c+1)+"|"+"TC-"+xl_tc_name+"|"+"Details-"+al_xl_details.get(c)+"|"+"Result-"+al_xl_results.get(c)+"|");
		}
		}catch(Exception e)
		{
			
			connectivity.close();	
			
		}
		connectivity.close();
		
		//Seting the Pass Fail color in the test logs
		try 
		{
		InputStream inp = new FileInputStream("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls");  
	     
	    Workbook wb = WorkbookFactory.create(inp);
	    
	    
	    CellStyle my_style_pass = wb.createCellStyle();
	    CellStyle my_style_fail = wb.createCellStyle();
	    CellStyle my_style_border = wb.createCellStyle();
	    
	    //CellStyle my_style_odd_row = wb.createCellStyle();
	    //CellStyle my_style_even_row = wb.createCellStyle();
	    

	    //Setting up cell color for Status column when the value is Pass
	    //and also adding borders to that cell under Status Column
	    my_style_pass.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
	    my_style_pass.setFillForegroundColor(new HSSFColor.LIME().getIndex());
	    my_style_pass.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        my_style_pass.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        my_style_pass.setBorderTop(HSSFCellStyle.BORDER_THIN);
        my_style_pass.setTopBorderColor(IndexedColors.BLACK.getIndex());
        my_style_pass.setBorderRight(HSSFCellStyle.BORDER_THIN);
        my_style_pass.setRightBorderColor(IndexedColors.BLACK.getIndex());
        my_style_pass.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        my_style_pass.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        
        //my_style_border.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
        //my_style_border.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
       
               

	    //Setting up cell color for Status column when the value is Fail
	    //and also adding borders to that cell under Status Column
        my_style_fail.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
        my_style_fail.setFillForegroundColor(new HSSFColor.CORAL().getIndex());
        my_style_fail.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        my_style_fail.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        my_style_fail.setBorderTop(HSSFCellStyle.BORDER_THIN);
        my_style_fail.setTopBorderColor(IndexedColors.BLACK.getIndex());
        my_style_fail.setBorderRight(HSSFCellStyle.BORDER_THIN);
        my_style_fail.setRightBorderColor(IndexedColors.BLACK.getIndex());
        my_style_fail.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        my_style_fail.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                
        
        //Setting up border for all the Cells other than column Status and ScreenPrints
        my_style_border.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        my_style_border.setBorderTop(HSSFCellStyle.BORDER_THIN);
        my_style_border.setBorderRight(HSSFCellStyle.BORDER_THIN);
        my_style_border.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        
              
        
	    Sheet sheet = wb.getSheet("Results");  
	    
	    for (int myrow = 3; myrow < countoflogmessage+3;myrow++) 
	    {
	    	Row row = sheet.getRow(myrow);  
	    	Cell cell = row.getCell(4);  
	    	
	    	//Setting up Cell Border for all the cells containing logs
	    	Cell cell1 = row.getCell(1);  
	    	Cell cell2 = row.getCell(2);
	    	Cell cell3 = row.getCell(3);
	    	Cell cell5 = row.getCell(5);
	    	
	    	
	    	my_style_border.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
	    	my_style_border.setFillForegroundColor(new HSSFColor.LIGHT_TURQUOISE().getIndex());
	    		
	    	    	    	
	    	cell1.setCellStyle(my_style_border);
	    	cell2.setCellStyle(my_style_border);
	    	cell3.setCellStyle(my_style_border);
	    	//System.out.println("cell5.getStringCellValue()"+cell5.getStringCellValue());
	    	if (cell5.getStringCellValue() !=null)
	    	{
	    	cell5.setCellStyle(my_style_border);
	    	}
	    	if ( cell.getStringCellValue() != null && cell.getStringCellValue().equals("Pass"))
	    	{	    	
		       	cell.setCellStyle(my_style_pass);
		    }
	    	if ( cell.getStringCellValue() != null && cell.getStringCellValue().equals("Fail"))
		    {
	    	  	cell.setCellStyle(my_style_fail);
		    }
    		  
	    	
	    }
	    
	    	    
	    // Write the output to a file  
	    FileOutputStream fileOut = new FileOutputStream("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls");
	    wb.write(fileOut);  
	    fileOut.close(); 
	    Thread.sleep(1000L);
	    
	    _AttachScreenShotToXL("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls");
	    //Thread.sleep(1000L);
	    //Updating the overall summary logs in GlobalSheet
	    //DB.Connect(DataSetup.Logininfo);
	    //System.out.println(TCOverallStatus);
	   // System.out.println(start_time_tc);
	   // System.out.println(DataSetup.SheetNameofTestLogSummary);
	    
	    DB.Connect(DataSetup.Logininfo);
	    DB.UpdateXLCell(DataSetup.SheetNameofTestLogSummary, TCOverallStatus, "Status", "StartofExecution", start_time_tc);
	    DB.Connect(DataSetup.Logininfo);
	    DB.UpdateXLCell(DataSetup.SheetNameofTestLogSummary, end_time_tc, "EndofExecution", "StartofExecution", start_time_tc);
	    DB.Connect(DataSetup.Logininfo);
	    DB.UpdateXLCell(DataSetup.SheetNameofTestLogSummary, duration, "Duration", "StartofExecution", start_time_tc);
	    //DB.UpdateXLCellWithLargeString(DataSetup.SheetNameofTestLogSummary, reasonforfailure, "Description", "StartofExecution", start_time_tc);
	   
	   // DB.UpdateXLCellWithLargeString(DataSetup.SheetNameofTestLogSummary, reasonforfailure, "Description", "StartofExecution", start_time_tc);
	    //System.out.println("Fail Message:"+reasonforfailure);
	    DB.Connect(DataSetup.Logininfo);
	    DB.UpdateXLCell(DataSetup.SheetNameofTestLogSummary, reasonforfailure, "Description", "StartofExecution", start_time_tc);
	
	    //myWD.quit();
	    
	    _InsertImageToSpreadSheet("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls");
	    
	  //Code to update test execution log in JIRA -Zephyr Test.
	    try{
	    DB.Connect(DataSetup.Logininfo);
	    if(DB.ReadXLData("Setup", "JIRA_Zephyr_Integration_IsEnabled", "SL_NO", "Setup1").equalsIgnoreCase("Yes"))
	    {
	    	DB.Connect("TestDataStore\\JIRA_Integration\\JIRA_Integration.xls");
		    System.out.println("JIRA TestCase is:"+DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_TestScriptName", "Selenium_TestScriptName", TCName));
		    String Execution_ID = "";
		    if(TCOverallStatus.equalsIgnoreCase("Pass"))
		    {
		    	DB.Connect("TestDataStore\\JIRA_Integration\\JIRA_Integration.xls");
		    	Execution_ID = DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_Zephyr_TestExecution_ID", "Selenium_TestScriptName", TCName).substring(0,DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_Zephyr_TestExecution_ID", "Selenium_TestScriptName", TCName).indexOf("."));
		    	java.util.Date date= new java.util.Date();
		    	JIRA_INTEGRATION.Update_Test_Execution_Status(Execution_ID, JIRA_INTEGRATION.Status.WIP, "Executed by automation on "+new Timestamp(date.getTime()));
		    	Thread.sleep(1000L);
		    	JIRA_INTEGRATION.Update_Test_Execution_Status(Execution_ID, JIRA_INTEGRATION.Status.PASS, "Executed by automation on "+new Timestamp(date.getTime()));
		    	System.out.println("Test Execution result = Pass is updated successfully in JIRA");
		    }
		    else if (TCOverallStatus.equalsIgnoreCase("Fail"))
		    {
		    	DB.Connect("TestDataStore\\JIRA_Integration\\JIRA_Integration.xls");
		    	Execution_ID = DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_Zephyr_TestExecution_ID", "Selenium_TestScriptName", TCName).substring(0,DB.ReadXLData("Selenium_JIRA_TestScriptMapping", "JIRA_Zephyr_TestExecution_ID", "Selenium_TestScriptName", TCName).indexOf("."));
		    	java.util.Date date= new java.util.Date();
		    	JIRA_INTEGRATION.Update_Test_Execution_Status(Execution_ID, JIRA_INTEGRATION.Status.WIP, "Executed by automation on "+new Timestamp(date.getTime()));
		    	Thread.sleep(1000L);
		    	JIRA_INTEGRATION.Update_Test_Execution_Status(Execution_ID, JIRA_INTEGRATION.Status.FAIL, "Executed by automation on "+new Timestamp(date.getTime()));
		    	System.out.println("Test Execution result = Fail is updated successfully in JIRA");
		    }
		    
		    JIRA_INTEGRATION.Add_Attachment_To_JIRA_TestExecution(new File("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls"), Execution_ID);
	    }
	    }catch(Exception e1)
	    {
	    	e1.printStackTrace();
	    	System.out.println("Unable to update the Test Execution result is JIRA");
	    }
	    
	    
	    //code to send automated email
	    DB.Connect(DataSetup.Logininfo);
	    if(DB.ReadXLData("Setup", "Email_IsEnabled", "SL_NO", "Setup1").trim().equalsIgnoreCase("Yes"))
	    {
	    	_SendEmailAttachingTestLog("TestLogs\\"+FolderName+"\\"+TCName+"_"+filename+"_"+TCOverallStatus+".xls",TCName+"_"+filename+"_"+TCOverallStatus);
	    }
	    
		}
		catch(SQLException sqlexc)
		{
			sqlexc.printStackTrace();
			System.out.println("The TestScript("+TCName+") is not present JIRA. Please verify test case mapping (JIRA_Integration.xls) between Selenium and JIRA");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
	}
	
	//private static final String TASKLIST = "tasklist";
	//private static final String KILL = "taskkill  /F /IM ";

	/**
	 * @author Sourav Mukherjee
	 * @Param Name of the process 
	 * @return boolean
	 * @throws Exception
	 * @Description returns true of the supplied process/service is running else false 
	 * @Date Aug 7, 2014
	 */
	boolean _isProcessRunging(String serviceName) throws Exception
	{

	Process p = Runtime.getRuntime().exec(TASKLIST);
	BufferedReader reader = new BufferedReader(new InputStreamReader(
	p.getInputStream()));
	String line;
	System.out.println(""+reader.readLine());
	while ((line = reader.readLine()) != null) {
		//System.out.println(line);
	if (line.contains(serviceName)) {
		return true;
	}
	}

	return false;
	}

	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Creates the Repository for all the SFDC TabNames from All Tabs
	 * @Date Aug 7, 2014
	 */
	public static void _CreateTestScriptFile(String ModuleName,String TestScriptName) throws Exception
	{
		
		String LOC = "";
		
		
		/*************** Creating content of the testcase.java file ******************/

		LOC = "package USER_SPACE.TestScripts."+ModuleName+"; \n \n"
				+ "import org.openqa.selenium.NoSuchElementException; \n"
				+ "import com.sun.jna.platform.win32.Advapi32Util.Account; \n"
				+ "import SOURCE_CODE.SFDC.*; \n"
				+ "import USER_SPACE.TestPrerequisite.*; \n"
				+ "import USER_SPACE.BusinessComponent.*; \n"
				+ "import USER_SPACE.ObjectRepository.*; \n"
				+ "\n\n\n\n"
				+ "/* \n"
				+ "* \n"
				+ "* @Author: <Name of Test Script Creator> \n"
				+ "* @Description: <Please mention the scope of this test script> \n"
				+ "* @General Guidelines: Every Test Script must begin from Launching \n"
				+ "* URL of login screen and must end with browser closed \n"
				+ "*/ \n\n"
				+ "public class "+ TestScriptName +" { \n\n\n"
				+ "public static void main(String[] args) throws Exception \n"
				+ "{ \n\n"
				+ "System.out.println(\"-----------Begin of TestScript-------------\"); \n"
				+ "String TCName = \""+TestScriptName+"\"; \n\n"
				+ "//Creating framework instance \n"
				+ "SFDCAutomationFW sfdc = new SFDCAutomationFW(TCName); \n"
				+ "DataSetup.sfdc=sfdc; \n\n\n\n"
				+ "try \n"
				+ "{ \n"
				+ "//Making a connection with excel sheet to retrieve login information \n"
				+ "DB.Connect(DataSetup.Logininfo); \n"
				+ "String URL = DB.ReadXLData(\"LoginInfo\",\"URL\",\"Name\",\""+DataSetup.Name_OF_testuser_for_URL+"\"); \n\n"
				+ "//Launching the URL\n"
				+ "sfdc.OpenURL(args,URL, DataSetup.Browser); \n\n"
				+ "//Login to SFDC \n"
				+ "sfdc.LoginToSFDC(\""+DataSetup.Name_OF_testuser_for_Login+"\"); \n\n"
				+ "//Create Test Case Body \n\n\n\n\n\n\n\n\n\n\n"
				+ "//Logging Out \n"
				+ "sfdc.LogOff(); \n"
				+ "sfdc.Quit();	\n\n\n"
				+ "} \n"
				+ "catch(ExitUponTestScriptStepFails e) \n"
				+ "{ \n"
				+ "e.printStackTrace(); \n"
				+ "System.out.println(\"Exception(ExitUponTestScriptStepFails) in main\"); \n"
				+ "} \n"
				+ "catch(Exception e) \n"
				+ "{ \n"
				+ "e.printStackTrace(); \n"
				+ "System.out.println(\"Exception(Exception) in main\"); \n"
				+ "} \n"
				+ "finally \n"
				+ "{ \n"
				+ "sfdc.GenerateXLLog(); \n"
				+ "sfdc.Quit(); \n"
				+ "System.out.println(\"-----------End of TestScript-------------\"); \n"
				+ "} \n"
				+ "} \n"
				+ "} \n";
		
		_CreateTestScriptFileFromTemplate(ModuleName, TestScriptName, LOC);
}
	
	public static void _CreateTestScriptFileFromTemplate(String ModuleName,String TestScriptName,String LineOfCode) throws Exception
	{
		try
		{
			DB.Connect(DataSetup.Logininfo);
			File file1 = new File(DB.ReadXLData("Setup", "Path_Home_Directory", "SL_NO", "Setup1")+"/src/USER_SPACE/TestScripts/"+ModuleName+"\\");
			file1.mkdirs();
			file1 = null;	
			
			
			
			
			
		File file = new File("src\\USER_SPACE\\TestScripts\\"+ModuleName+"\\" + TestScriptName
				+ ".java");
		file.createNewFile();
		BufferedWriter wr = new BufferedWriter(new FileWriter(file));
		wr.write(LineOfCode);
		wr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param serviceName
	 * @return void
	 * @throws Exception
	 * @Description Kills the running process 
	 * @Date Aug 7, 2014
	 */
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

	public boolean _isProcessRunning(String serviceName) throws Exception {

	 Process p = Runtime.getRuntime().exec(TASKLIST);
	 BufferedReader reader = new BufferedReader(new InputStreamReader(
	   p.getInputStream()));
	 String line;
	 while ((line = reader.readLine()) != null) {

	  System.out.println(line);
	  if (line.contains(serviceName)) {
	   return true;
	  }
	 }

	 return false;

	}

	public static void _killProcess(String serviceName) throws Exception {

	  Runtime.getRuntime().exec(KILL + serviceName);

	 }
	/**
	 * @author Cognizant
	 * @Description Select the parent when there is only one window opened.This is very useful when you opened multiple window like popup and upon selection of the value in the lookup the popup gets closed. And now you want to switch back to the parent window. 
	 * @return true when successfully selected the parent window else return false.
	 * @throws Exception
	 */
	public static boolean SelectParentWindow() throws Exception
	{
		
		try
		{
			Set<String> s = myWD.getWindowHandles(); 
	        Iterator<String> ite = s.iterator(); 
	        while(ite.hasNext()) 
	        { 
	            String popup = ite.next(); 
	            System.out.println("popup"+popup);
	            myWD.switchTo().window(popup); 
                System.out.println("Window Title is: "+myWD.getTitle());
                if(myWD.getTitle().length()>0)
                	return true;
                else
                	return false;
	            
	        }
			
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			sfdc.AddToXLLogs("Unable to Select the parent window.", "Fail");
			System.out.println("Exception in SelectParentWindow function.");
			return false;
		}
	}

	/**
	 * @author Cognizant
	 * @Description Selects popup window. This is best in use when there are just two open window including main window. If you have more than two window then this function will not work. Please use SelectWindow function in such scenario
	 * @return Boolean
	 * @throws Exception
	 */
	public static boolean SelectPopupWindow() throws Exception
	{		
		try
		{
			int c=0;
			String mainwindow = myWD.getWindowHandle();   
			System.out.println("mainwindow:"+mainwindow);
			
			Set<String> s = myWD.getWindowHandles(); 
	        Iterator<String> ite = s.iterator(); 
	        while(ite.hasNext()) 
	        { 
	            String popup = ite.next(); 
	            System.out.println("inside SelectWindow:"+popup);
	            if(!popup.equalsIgnoreCase(mainwindow))
	            { 
	                myWD.switchTo().window(popup); 
	                System.out.println("Window Title is: "+myWD.getTitle());
	                System.out.println("window id:"+myWD.getWindowHandle());
	                if(myWD.getWindowHandle().trim().equalsIgnoreCase(popup.trim()))
	                {
	                	return true;
	                	
	                }
	                else
	                {
	                	c = c + 1;
	                }
	             }
	        }
	        if (c>0)
	        {
	        	sfdc.AddToXLLogs("Unable to find the popup window", "Fail");
	        	System.out.println("Unable to find the popup window");
	        	return false;
	        }
	        else
	        {
	        	sfdc.AddToXLLogs("Successfully selected the popup window","Pass");
	        	System.out.println("Successfully selected the popup window");
	        	return true;
	        	
	        }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception:SelectPopupWindow");
			sfdc.AddToXLLogs("Unable to select the popup window", "Fail");
			return false;
		}
	}
	
	
	/**
	 * @author Sourav Mukherjee
	 * @Param UniqueStringinWindowTitle
	 * @return boolean
	 * @throws Exception
	 * @Description Selects the window opened by Selenium Webdriver.
	 * @Date Aug 7, 2014
	 */
	public static boolean SelectWindow(String UniqueStringinWindowTitle) throws Exception
	{
		
		try
		{
			int c=0;
			String mainwindow = myWD.getWindowHandle();   
			System.out.println("mainwindow:"+mainwindow);
			
			Set<String> s = myWD.getWindowHandles(); 
	        Iterator<String> ite = s.iterator(); 
	        while(ite.hasNext()) 
	        { 
	            String popup = ite.next(); 
	            System.out.println("inside SelectWindow:"+popup);
	            if(!popup.equalsIgnoreCase(mainwindow))
	            { 
	                myWD.switchTo().window(popup); 
	                System.out.println("Window Title is: "+myWD.getTitle());
	                if(myWD.getWindowHandle().contains(popup))
	                {
	                	//mainwindow = null;
	                	//s=null;
	                	return true;
	                	
	                }
	                else
	                {
	                	c = c + 1;
	                }
	             }
	        }
	        if (c>0)
	        {
	        	sfdc.AddToXLLogs("Unable to find the window containing title ("+UniqueStringinWindowTitle+")", "Fail");
	        	System.out.println("Unable to find the window containing title ("+UniqueStringinWindowTitle+")");
	        	return false;
	        }
	        else
	        {
	        	sfdc.AddToXLLogs("Successfully selected the window containing title ("+UniqueStringinWindowTitle+")", "Pass");
	        	System.out.println("Successfully selected the window containing title ("+UniqueStringinWindowTitle+")");
	        	return true;
	        	
	        }
		/*	
		Set <String> handles = myWD.getWindowHandles();
		Iterator<String> it = handles.iterator();
		//iterate through your windows
		while (it.hasNext())
		{
			//String parent = it.next();
			String win = it.next();
			System.out.println("win:"+win);
			myWD.switchTo().window(win);
			if (myWD.getTitle().contains(UniqueStringinWindowTitle))
			{
				System.out.println("The title of the Selected window is:"+myWD.getTitle());
				return true;
			}
						
		}
		System.out.println("Could not find the window with title containing "+UniqueStringinWindowTitle);
		return false;
		*/
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception in SelectWindow Function while trying to select the window having title containing  "+UniqueStringinWindowTitle);
			return false;
		}
	}
	
	
	/*
	 * @Author: Sourav
	 * 
	 * Whenever you call this function CloseWindow(), You must call SelectWindow() 
	 * function after this function call to continue working on the next window, 
	 * otherwise the focus to the next workable window will be lost. 
	 * 
	 */
	/**
	 * @author Sourav Mukherjee
	 * @Param UniqueStringinWindowTitle
	 * @return boolean
	 * @throws Exception
	 * @Description Whenever you call this function CloseWindow(), You must call SelectWindow() function after this function call to continue working on the next window, otherwise the focus to the next workable window will be lost.  
	 * @Date Aug 7, 2014
	 */
	public static boolean CloseWindow(String UniqueStringinWindowTitle) throws Exception
	{
		try
		{
			
			String AlreadySelectedWindow = myWD.getWindowHandle();   
			System.out.println("AlreadySelectedWindow:"+AlreadySelectedWindow);
			
			Set<String> s = myWD.getWindowHandles(); 
	        Iterator<String> ite = s.iterator(); 
	        while(ite.hasNext()) 
	        { 
	            String popup = ite.next(); 
	            System.out.println("inside closewindow:"+popup);
	            if(popup.equalsIgnoreCase(AlreadySelectedWindow))
	            { 
	                myWD.switchTo().window(popup).close(); 
	                return true;
	            }
	            
	        }
		System.out.println("Could not find the window with title containing "+UniqueStringinWindowTitle+" while trying to close.");
		return false;
		}catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception in CloseWindow Function while trying to close the window having title containing  "+UniqueStringinWindowTitle);
			return false;
		}
	}
	
	public static void SwitchToChildTab() throws Exception
	{
		try
		{
			new Actions(myWD).sendKeys(myWD.findElement(By.tagName("html")), Keys.CONTROL).sendKeys(myWD.findElement(By.tagName("html")),Keys.NUMPAD2).build().perform();
			System.out.println("Swithing to child tab is successful");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Swithing of Tab is unsuccessful");
		}
	}
	
	public static void SwitchToParentTab() throws Exception
	{
		try
		{
			new Actions(myWD).sendKeys(myWD.findElement(By.tagName("html")), Keys.CONTROL).sendKeys(myWD.findElement(By.tagName("html")),Keys.NUMPAD1).build().perform();
			System.out.println("Swithing to Parent tab is successful");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Swithing to Parent Tab is unsuccessful");
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param Partial text of the Title Of The Frame
	 * @return void
	 * @throws Exception
	 * @Description Selects the Frame by partial text of the title in a window opened by WebDriver
	 * @Date Aug 7, 2014
	 */
	public static void SelectFrame(String TitleOfTheFrame) throws Exception
	{
		try 
		{
			myWD.switchTo().frame(myWD.findElement(By.xpath("//frame[contains((@title),'"+TitleOfTheFrame+"')]")));
			
			System.out.println("Successfully selected the Frame having title ("+TitleOfTheFrame+")");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Unable to select the Frame with title ("+TitleOfTheFrame+")");
		}
	}
	
	/**
	 * @author Cognizant
	 * @param TitleOfTheiFrame
	 * @Description Selects the iFrame by partial text of the title in a window opened by WebDriver
	 * @throws Exception
	 */
	public static void SelectiFrame(String TitleOfTheiFrame) throws Exception
	{
		try 
		{
			myWD.switchTo().frame(myWD.findElement(By.xpath("//iframe[contains((@title),'"+TitleOfTheiFrame+"')]")));
			
			System.out.println("Successfully selected the Frame having title ("+TitleOfTheiFrame+")");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Unable to select the Frame with title ("+TitleOfTheiFrame+")");
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param FrameIndex to be selected. FrameIndex starts from zero.
	 * @return void
	 * @throws Exception
	 * @Description Selects the Frame by Index in a window opened by WebDriver
	 * @Date Aug 7, 2014
	 */
	public static void SelectFrameByIndex(int FrameIndexStartsWithZero) throws Exception
	{
		try 
		{
			myWD.switchTo().frame(FrameIndexStartsWithZero);
			
			System.out.println("Successfully selected the Frame by Index ("+FrameIndexStartsWithZero+")");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Unable to select the Frame by inder ("+FrameIndexStartsWithZero+")");
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Closes the browser opened by Selenium Webdriver
	 * @Date Aug 7, 2014
	 */
	public static void Quit() throws Exception
	{
		try {
		
		myWD.close();
		
		if(myWD.toString().contains("ChromeDriver"))
		{
			_killProcess("chromedriver.exe");
			_killProcess("chrome.exe");
		}
		else if (myWD.toString().contains("FirefoxDriver"))
		{
			_killProcess("firefox.exe");
		}
		else if (myWD.toString().contains("InternetExplorerDriver"))
		{
			_killProcess("IEDriverServer.exe");
			_killProcess("iexplore.exe");

		}

		
		System.out.println("The browser is closed.");
		//AddToXLLogs("The browser is closed", "Pass");
		}
		
		catch(Exception e)
		{
			//e.printStackTrace();
			//AddToXLLogs("Unable to close the browser.","Fail");
		}
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param xpath of the element
	 * @return void
	 * @throws Exception
	 * @Description Moved the mouse pointer to coordinate X+500,Y+500 where X and Y are the coordinates of the element.
	 * @Date Aug 7, 2014
	 */
	public static void MouseMove(String xpathoftheelement) throws Exception  
	{
		/*
		Actions action = new Actions(myWD);
		WebElement we = myWD.findElement(By.xpath("//*[contains(normalize-space(text()),'Home')]"));
		action.moveToElement(we).build().perform();
		*/
		
		Point coordinates = myWD.findElement(By.xpath(xpathoftheelement)).getLocation();
		Robot robot = new Robot();
		robot.mouseMove(coordinates.getX()+500,coordinates.getY()+400);
		
		
	}
	
	/**
	 * @author Cognizant
	 * @param RowIndex starts from 1
	 * @StartScreen The Queue details page showing added user list should be displayed before invoking this function
	 * @EndScreen User Details page is shown.
	 * @throws Exception if there is no user present/associated in requested rowindex inside the Queue
	 * @Description Clicks on the user name present inside the Queue as per requested row index
	 */
	public static void ClickONQueueMember(int RowIndex) throws Exception  
	{
		try
		{
			myWD.findElement(By.xpath("//th[normalize-space(text())='Name'][1]/ancestor-or-self::tr[1]/following-sibling::tr["+RowIndex+"]/th[1]/a[1]")).click();
			AddToXLLogs("Clicked on the user link ("+myWD.findElement(By.xpath("//th[normalize-space(text())='Name'][1]/ancestor-or-self::tr[1]/following-sibling::tr["+RowIndex+"]/th[1]/a[1]")).getText()+") being a Queue member.", "Pass");
			System.out.println("Clicked on the user link ("+myWD.findElement(By.xpath("//th[normalize-space(text())='Name'][1]/ancestor-or-self::tr[1]/following-sibling::tr["+RowIndex+"]/th[1]/a[1]")).getText()+") being a Queue member.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to click on the user link present in row ("+RowIndex+") inside the Queue","Pass");
			System.out.println("Unable to click on the user link present in row ("+RowIndex+") inside the Queue");
			
		}
	}
	
	/**
	 * @author Cognizant
	 * @param RowIndex starts from 1
	 * @return the name of the user present inside Queue 
	 * @StartScreen The Queue details page showing added user list should be displayed before invoking this function
	 * @EndScreen Same as startscreen, no navigation happens by the automation
	 * @throws Exception if there is no user present/associated in requested rowindex inside the Queue
	 * @Description Copies the user name present inside the Queue as per requested row index
	 */
	public static String GetQueueMember(int RowIndex) throws Exception  
	{
		String QMember = "";
		try
		{
			QMember = myWD.findElement(By.xpath("//th[normalize-space(text())='Name'][1]/ancestor-or-self::tr[1]/following-sibling::tr["+RowIndex+"]/th[1]/a[1]")).getText().trim();
			AddToXLLogs("Copied the user name present in row ("+RowIndex+") inside the Queue.","Pass");
			System.out.println("Clicked on the user link ("+myWD.findElement(By.xpath("//th[normalize-space(text())='Name'][1]/ancestor-or-self::tr[1]/following-sibling::tr["+RowIndex+"]/th[1]/a[1]")).getText()+") being a Queue member.");
			return QMember;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to copy the username present inside Queue","Pass");
			System.out.println("Unable to copy the username present inside Queue");
			return QMember;
		}
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Logs out from sales force 
	 * @Date Aug 7, 2014
	 */
	public static void LogOff() throws Exception
	{
		try 
		{
		String xp = "//span[@id='userNavLabel' and normalize-space(@class)='menuButtonLabel']";
		WaitForPageToLoad(15);
		WaitForElement(xp,15);
		myWD.findElement(By.xpath(xp)).click();
		
		myWD.findElement(By.xpath("//a[contains(@href,'logout.jsp')][1]")).click();
		

		/*
		if (myWD.toString().contains("InternetExplorerDriver"))
		{
			Thread.sleep(1000L);
			//Javascript.executeScript("arguments[0].click();", myWD.findElement(By.xpath("//a[normalize-space(@title)='Logout' and (normalize-space(@role)='menuitem') and contains(normalize-space(text()),'Logout')]")));
		}
		*/
		//myWD.findElement(By.xpath("//a[normalize-space(@title)='Logout' and (normalize-space(@role)='menuitem') and contains(normalize-space(text()),'Logout')]")).click();
		//myWD.findElement(By.xpath("//ul[normalize-space(@class)='zen-options' and normalize-space(@role)='menu'][1]/descendant::li[last()]/descendant::a[1]")).click();
		
		WaitForPageToLoad(30);
		AddToXLLogs("Logging out from salesforce", "Pass");
		System.out.println("Logging out from salesforce");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to Logout from Salesforce.");
			AddToXLLogs("Unable to Logout from Salesforce.", "Fail");
			
		}
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Creates the TestLogs directory along with todays date so that TestLog files can be stored 
	 * @Date Aug 7, 2014
	 */
	public static void _CreateDirectory()
	{
		
		 Calendar cal = Calendar.getInstance();
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
         FolderName = sdf.format(cal.getTime());                     
         
		File file = new File("TestLogs\\"+FolderName+"\\");
		file.mkdirs();
		file = null;
		
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Clicks on Yes button on the Alert opened by WebDriver
	 * @Date Aug 7, 2014
	 */
	public static void AlertClickYes() throws Exception
	{
		try 
		{
			Alert alert = myWD.switchTo().alert();
			alert.accept();	
			AddToXLLogs("Clicked on Yes button in the alert.", "Pass");
			System.out.println("Clicked on Yes button in the alert.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to click on Yes button in the Alert", "Fail");
			System.out.println("Unable to click on Yes button in the Alert");
		}
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Clicks on No button on the Alert opened by WebDriver
	 * @Date Aug 7, 2014
	 */
	public static void AlertClickNo() throws Exception
	{
		try 
		{
			Alert alert = myWD.switchTo().alert();
			alert.dismiss();
			
			alert.accept();
			alert.dismiss();
			alert.sendKeys("abcd");
			alert.getText();
			AddToXLLogs("Clicked on No button in the alert.", "Pass");
			System.out.println("Clicked on No button in the alert.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to click on No button in the Alert", "Fail");
			System.out.println("Unable to click on No button in the Alert");
		}
		
	}
	

	/**
	 * @author Sourav Mukherjee
	 * @Param NA
	 * @return void
	 * @throws Exception
	 * @Description Press TAB Key using Robot Class
	 * @Date Jan, 2015
	 */
	public static void PressTABKeyOnWindowAlert() throws Exception
	{
		try 
		{
			Robot robot = new Robot(); 
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			AddToXLLogs("Pressed Tab Key on Window Alert.", "Pass");
			System.out.println("Pressed Tab Key on Window Alert.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to press Tab key on Window Alert", "Fail");
			System.out.println("Unable to press Tab key on Window Alert");
		}
		
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param NA
	 * @return void
	 * @throws Exception
	 * @Description Press ENTER Key using Robot Class
	 * @Date Jan, 2015
	 */
	public static void PressENTERKeyOnWindowAlert() throws Exception
	{
		try 
		{
			Robot robot = new Robot(); 
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			AddToXLLogs("Pressed ENTER Key on Window Alert.", "Pass");
			System.out.println("Pressed ENTER Key on Window Alert.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			AddToXLLogs("Unable to press ENTER key on Window Alert", "Fail");
			System.out.println("Unable to press ENTER key on Window Alert");
		}
		
	}
	
	
	/**
	 * @author Sourav Mukherjee
	 * @Param message to be verified 
	 * @return String
	 * @throws Exception
	 * @Description Verifies the text message displayed in the standard alert message
	 * @Date Jan, 2015
	 */
	public static String VerifyAlertMessage(String message) throws Exception
    {
           try 
           {
                  Alert alert = myWD.switchTo().alert();
                  String retrievedMessage = alert.getText().trim();
                  if(retrievedMessage.contains(message.trim()))
                  {
                	  AddToXLLogs("Successfully verified the message in the alert", "Pass");
                	  System.out.println("Successfully verified the message in the alert.");
                	  return "Pass";
                  }
                  AddToXLLogs("Unable to retrieve the message from the alert", "Fail");
                  System.out.println("Unable to retrieve the message from the alert");
                  return "Fail";
           }
           catch(Exception e)
           {
                  e.printStackTrace();
                  AddToXLLogs("Unable to retrieve the message from the alert", "Fail");
                  System.out.println("Unable to retrieve the message from the alert");
                  return "Fail";
           }
           
    }

	/**
     * @author Sourav Mukherjee
     * @Param RelatesLisName 
     * @DisplayMode View Details page showing related list
     * @return Entire "Pass" if Go to list hyperlink is clicked successfully and returns blank if the hyperlink is absent.
     * @throws Exception
     * @Description Clicks on the Go to list hyperlink which is present under a particular related list.
     * @Date Jan 13, 2015
     */
     public static String ClickOnGoToListLink(String RelatedListName) throws Exception 
     {
            
            try
            {
                   myWD.findElement(By.xpath("//h3[normalize-space(text())='"+RelatedListName+"']/ancestor-or-self::div[normalize-space(@class)='pbHeader']/following-sibling::div[normalize-space(@class)='pbBody'][1]/div[normalize-space(@class)='pShowMore'][1]/a[contains(normalize-space(text()),'Go to list')]")).click();
                   System.out.println("Successfully clicked on Go to List hyperlink.");
                   return "Pass";
            }
            catch(Exception e)
            {
                   e.printStackTrace();
                   System.out.println("Unable to find the (Go to List) hyperlink.");
                   return "";
                   
            }
     }

	/**
	 * @author Sourav Mukherjee
	 * @Param WebElement
	 * @return void
	 * @throws Exception
	 * @Description Clicks on the WebElement by using JavaScript
	 * @Date Aug 7, 2014
	 */
	public static void ClickWebElementByJS(WebElement weElement) 
	{

        // Scroll the browser to the element's Y position
        ((JavascriptExecutor) myWD).executeScript("window.scrollTo(0,"
                + weElement.getLocation().y + ")");
        // Click the element
        int iAttempts = 0;
        while (iAttempts < 5) {
            try {
                weElement.click();
                break;
            } catch (Exception e) {
            }
            iAttempts++;
        }

    }
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 1. xpath of the element. 2. Waiting Time in Second
	 * @return boolean
	 * @throws Exception
	 * @Description Waits for the specified time until the webelemnt is available to WebDriver
	 * @Date Aug 7, 2014
	 */
	public static boolean WaitForElement(String xpath, long waitingTimeinsec) throws Exception
    {
         try {
        	 	//Uncomment WaitForPageLoad() function call when executing the scripts in IE
        	 	//Otherwise comment out
        	 	
        	 	//WaitForPageToLoad(30);
        	 	/*if (myWD.toString().contains("InternetExplorerDriver"))
        	 	{
        	 		if (WebDriverWaitForElement(xpath,waitingTimeinsec)!=null)	
        	 		{
        	 			return true;
        	 		}
        	 		else
        	 		{
        	 			return false;
        	 		}
        	 	}
        	 	else
        	 	{
        		        		
        	 	myWD.manage().timeouts().implicitlyWait(waitingTimeinsec,TimeUnit.SECONDS);
        		List<WebElement> myDynamicElement = myWD.findElements(By.xpath(xpath));
        		if (myDynamicElement.size() > 0)
        		{
        			//System.out.println("Inside WaitForElement(Success):"+myDynamicElement.size());
        			return true;
        		}
        		else
        		{
        			return false;
        		} 
        	 	}*/
        		
        	myWD.manage().timeouts().implicitlyWait(waitingTimeinsec,TimeUnit.SECONDS);
     		List<WebElement> myDynamicElement = myWD.findElements(By.xpath(xpath));
     		if (myDynamicElement.size() > 0)
     		{
     			System.out.println("Success: WaitForElement->Number of Element present is: "+myDynamicElement.size());
     			return true;
     		}
     		else
     		{
     			System.out.println("Unsuccess: WaitForElement->Number of Element present is: "+myDynamicElement.size());
     			return false;
     		} 
        }
         catch(NoSuchElementException e)
         {
        	 e.printStackTrace();
             System.out.println("Exception inside WaitForElement:"+xpath);
             return false;
         }
     }
	/**
	 * @author Sourav Mukherjee
	 * @Param 1. xpath of the element, 2. Time specified in second
	 * @return WebElement
	 * @throws Exception
	 * @Description Waits for the specified time until the webelemnt is available to WebDriver. This uses WebDriverWait class 
	 * @Date Aug 7, 2014
	 */
	public static WebElement WebDriverWaitForElement(String xpath, long waitingTimeinsec) throws Exception
    {
		WebElement element=null;
        try {
        	 	WebDriverWait wait = new WebDriverWait(myWD, waitingTimeinsec);
        	 	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        	 	return element;
           	 }
         	catch(NoSuchElementException e)
         	{
         		e.printStackTrace();
         		System.out.println("Could not find the element even after waiting explicitly for ("+waitingTimeinsec+")sec");
         		AddToXLLogs("Could not find the element even after waiting explicitly for ("+waitingTimeinsec+")sec", "Fail");
         		return element;
        	 
         	}
     }
	/**
	 * @author Sourav Mukherjee
	 * @Param WebElement
	 * @return void
	 * @throws Exception
	 * @Description The expected webelemnt is highlights in green color
	 * @Date Aug 7, 2014
	 */
	public static void HighLight(WebElement webe)
	{
		Javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", webe, "color: green; border: 2px solid green;");
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param timeOutInSeconds
	 * @return void
	 * @throws Exception
	 * @Description Waits for the page to reach ready state 
	 * @Date Aug 7, 2014
	 */
	public static void WaitForPageToLoad(int timeOutInSeconds) throws Exception
	{ 
		
		String command = "return document.readyState"; 

		try
		{
		for (int i=0; i<timeOutInSeconds; i++)
		{ 
			try
			{
				Thread.sleep(1000L);
			}
			catch (InterruptedException e)
			{
				System.out.println("Unable to load the webpage");				
				
			} 
			
			if (Javascript.executeScript(command).toString().equals("complete"))
			{ 
				//System.out.println("Inside WaitForPageToLoad(Success)");
				break; 
			} 
			
			
		} 
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Creates the Repository for all the SFDC TabNames from All Tabs
	 * @Date Aug 7, 2014
	 */
	public static void _CreateAUTRepository_TabNames() throws Exception
	{
		al_tabnames = new ArrayList();
		al_tabnames_var = new ArrayList();
		contentofscreenfile = "";
		
		contentofscreenfile = "package USER_SPACE.ObjectRepository;  \n"
				+ "\n \n \n"
				+ "public class TabNames { \n \n \n";
				
		try{
			
		Integer countofrows= GetWebDriver().findElements(By.xpath("//table[contains(@class,'detailList tabs')]/tbody[1]/tr")).size();
		System.out.println("countofrows:"+countofrows);
		List<WebElement> Tabs = GetWebDriver().findElements(By.xpath("//table[contains(@class,'detailList tabs')]/tbody[1]/descendant::a[text()]"));		
		for(WebElement eachTab: Tabs)
		{
			//System.out.println("The Tab Name is:"+eachTab.getText().trim());
			
			String s = eachTab.getText().trim().replace("+", "").replace("&", "")
					.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
					.replace("-", "").replace(" ", "").replace("*", "")
					.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
					.replace("%", "").replace(")", "").replace(",", "").replace("#", "").replace("!", "")
					.replace("$", "").replace(".", "").replace("!", "");
			
			al_tabnames_var.add(s);
			al_tabnames.add(eachTab.getText().trim());
			//s="";
			
			//contentofscreenfile = contentofscreenfile + "} \n";
			
			//
			
		}
		
		for(int p=0;p<al_tabnames.size();p++)
		{
			System.out.println(al_tabnames_var.get(p) + ":" +al_tabnames.get(p));
			contentofscreenfile = contentofscreenfile + "public static String "+al_tabnames_var.get(p)+" = \""+al_tabnames.get(p)+"\"; \n";
		}
		contentofscreenfile = contentofscreenfile + "} \n";
		screenname = "TabNames";
		_CreateScreenFile();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to create the TabNames repository");
			
		}
		
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param ObjectName. The .java file will get generated with this name.
	 * @return void
	 * @throws Exception
	 * @Description Creates the repository file for an object. This function identifies all the field labels, section, fields under each sections, related lists and its columns, SFDC buttons. The view details page must be displayed to the respective SFDC record.  
	 * @Date Aug 7, 2014
	 */	
	public static void _CreateAUTRepository(String ObjectName) throws Exception
	{
		al_fieldname = new ArrayList();
		al_sectionname = new ArrayList();
		al_relatedlistname = new ArrayList();
		uniquebuttons = new HashSet< String >();
		al_fieldname_fn = new ArrayList();
		al_sectionname_fn = new ArrayList();
		al_relatedlistname_fn = new ArrayList();
		
		contentofscreenfile = "";
		screenname = ObjectName + "Screen";
		
		/*************** Generic Part of the Content of Screen File ******************/

		contentofscreenfile = "package USER_SPACE.ObjectRepository; \n"
				+ "import SOURCE_CODE.SFDC.*; \n"
				+ "import USER_SPACE.TestPrerequisite.*; \n"
				+ "\n \n \n"
				+ "public class "+screenname+" { \n"
				+ "static SFDCAutomationFW sfdc = DataSetup.sfdc; \n"
				+ "static String RList = \"\"; \n"
				+ "static String SecName = \"\"; \n \n \n \n";
		
		try
		{
		
		// ******************************************** Begin of Field Names *********************************************************
		
		Integer countoffieldslabel = 0;
		List<WebElement> ml = GetWebDriver().findElements(By.xpath("//*[contains(@class,'label') and (local-name()='td' or local-name()='th')]"));
		String s;
		for(WebElement we: ml) 
		{
			if(we.getText().trim().length()>0)
			{
				countoffieldslabel++;
				//System.out.println("Field Name: "+we.getText().trim());
				HighLight(we);
				al_fieldname.add(we.getText().trim());
				s = we.getText().trim().replace("+", "").replace("&", "")
						.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
						.replace("-", "").replace(" ", "").replace("*", "")
						.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
						.replace("%", "").replace(")", "").replace(",", "").replace("#", "").replace("!", "")
						.replace("$", "").replace(".", "").replace("\\", "");
				if (Character.isDigit(s.charAt(0)))
				{
					s = s.replaceFirst(s.substring(0, 1),"_");
				}
				
				al_fieldname_fn.add(s);
			}
			
		}
		//System.out.println("Total Number of Fields is:"+countoffieldslabel);
		// ******************************************** End of Field Names *********************************************************
		
		// ********************************* Begin of Section and Related List ******************************************
		List<WebElement> sec = GetWebDriver().findElements(By.xpath("//*[(contains(@class,'mainTitle') and local-name()='h2') or (local-name()='h3')]"));
		for(WebElement we: sec) 
		{
			if((we.getAttribute("id").trim().length()==0) && (we.getText().trim().length()!=0))
			{
				HighLight(we);
				al_sectionname.add(we.getText().trim());
				//countoffieldslabel++;
				//System.out.println("Section Name: "+we.getText().trim());
				//System.out.println("ID:"+we.getAttribute("id").trim().length());
				///System.out.println("Actual Section:"+sfdc.GetWebDriver().findElement(By.xpath("(//*[contains(text(),'"+we.getText().trim()+"') and (local-name()='h2' or local-name()='h3')])/ancestor::div[1][contains(@class,'pbSubheader') or contains(@class,'pbHeader')]")).getText());
			}
			else if ((we.getAttribute("id").trim().length()>0) && (we.getText().trim().length()!=0))
			{
				HighLight(we);
				al_relatedlistname.add(we.getText().trim());
			}
			else
			{
				System.out.println("Please investigate this element as this is not in the defined criteria of Section/Related List.");
			}
			
		}
		
		
		
		//		System.out.println(sfdc.GetWebDriver().findElement(By.xpath("(//*[normalize-space(text())='Account Header'])/ancestor::div[1]")).getAttribute("class"));
		// ********************************* End of Section and Related List ******************************************
		
		
		contentofscreenfile = contentofscreenfile 
		
				+  "// ************************ Functions for Fields ************************************** \n \n ";
		
		
				
		for(int j=0;j<al_fieldname.size();j++)
		{
			System.out.println("Field Name: "+al_fieldname.get(j));
			System.out.println("Field Name for function: "+al_fieldname_fn.get(j));
			
			contentofscreenfile = contentofscreenfile + "public static MemberOfField "+al_fieldname_fn.get(j)+"Field() throws Exception{ \n";
				
			contentofscreenfile = contentofscreenfile + "	return sfdc.Field(\""+al_fieldname.get(j)+"\"); \n";
			contentofscreenfile = contentofscreenfile + "} \n";
			
		}
		contentofscreenfile = contentofscreenfile + "\n \n";
		contentofscreenfile = contentofscreenfile + "// ************************* Functions & Static Classes for Sections ***************************** // \n \n";
		System.out.println("Total Count of Fields:"+al_fieldname.size());
		
		for(int j=0;j<al_sectionname.size();j++)
		{
			System.out.println("Section Name: "+al_sectionname.get(j));
			s = al_sectionname.get(j).toString().replace("+", "").replace("&", "")
					.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
					.replace("-", "").replace(" ", "").replace("*", "")
					.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
					.replace("%", "").replace(")", "").replace(",", "").replace("#", "").replace("!", "")
					.replace("$", "").replace(".", "").replace("\\", "");
			
			contentofscreenfile = contentofscreenfile + "public static Fields_"+s+" SEC_"+s+"() throws Exception{ \n";
			contentofscreenfile = contentofscreenfile + "return new Fields_"+s+"(\""+al_sectionname.get(j).toString()+"\");\n";
			contentofscreenfile = contentofscreenfile + "}\n";
			contentofscreenfile = contentofscreenfile + "public static class Fields_"+s+"{\n";
			contentofscreenfile = contentofscreenfile + "Fields_"+s+"(String SN)\n";
			contentofscreenfile = contentofscreenfile + "{\n";
			contentofscreenfile = contentofscreenfile + "SecName = SN;\n";
			contentofscreenfile = contentofscreenfile + "}\n";
			
			_ReadAllFieldsUnderASection(al_sectionname.get(j).toString());
			
			contentofscreenfile = contentofscreenfile + "}\n";
		
		}
		System.out.println("Total Count of Section Name:"+al_sectionname.size());
		
		contentofscreenfile = contentofscreenfile + "\n \n \n // **************** Functions & Static Classes for Related List ******************** \n \n";
		
		for(int j=0;j<al_relatedlistname.size();j++)
		{
			System.out.println("Related List Name: "+al_relatedlistname.get(j));
			s = al_relatedlistname.get(j).toString().replace("+", "").replace("&", "")
					.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
					.replace("-", "").replace(" ", "").replace("*", "")
					.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
					.replace("%", "").replace(")", "").replace(",", "").replace("#", "").replace("!", "")
					.replace("$", "").replace(".", "").replace("\\", "");
			contentofscreenfile = contentofscreenfile + "public static Columns_"+s+" RL_"+s+"() throws Exception{ \n";
				
			contentofscreenfile = contentofscreenfile + "return new Columns_"+s+"(\""+al_relatedlistname.get(j).toString()+"\"); \n";
			contentofscreenfile = contentofscreenfile + "} \n";
			contentofscreenfile = contentofscreenfile + "public static class Columns_"+s+"{ \n";
			contentofscreenfile = contentofscreenfile + "Columns_"+s+"(String RL) \n";
			contentofscreenfile = contentofscreenfile + "{ \n";
			contentofscreenfile = contentofscreenfile + "RList = RL; \n";
			contentofscreenfile = contentofscreenfile + "} \n";
				
				
			_ReadAllColumnsUnderARelatedList(al_relatedlistname.get(j).toString());
			
			contentofscreenfile = contentofscreenfile + "}\n";
			
		}
		System.out.println("Total Count of Related List Name:"+al_relatedlistname.size());
		_ReadAllButtons();
		contentofscreenfile = contentofscreenfile + "}\n";
		_CreateScreenFile();
		
		
		
		}
catch(Exception e)
{
	System.out.println("Inside catch in CreateAUTRepository");
	e.printStackTrace();
}
}

	public static void _ReadAllFieldsUnderASection(String SectionName) throws Exception
	{
		//Getting all fields under a section
		String s;
		if (sfdc.GetWebDriver().findElement(By.xpath("(//*[normalize-space(text())='"+SectionName+"'])/ancestor::div[1]")).getAttribute("class").contains("pbHeader"))
		{
			List<WebElement> sec_fields = sfdc.GetWebDriver().findElements(By.xpath("(//*[normalize-space(text())='"+SectionName+"'])/ancestor::div[1]/following-sibling::div[1]/descendant::div[contains(@class,'pbSubsection')][1]/descendant::*[contains(@class,'label') and (local-name()='td' or local-name()='th')]"));
			for(WebElement we: sec_fields) 
			{
				if(we.getText().trim().length()>0)
				{
					sfdc.HighLight(we);
					System.out.println("Field Name under Section: "+we.getText().trim());
					s = we.getText().trim().replace("+", "").replace("&", "")
							.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
							.replace("-", "").replace(" ", "").replace("*", "").replace("!", "")
							.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
							.replace("%", "").replace(")", "").replace(",", "").replace("#", "").replace("!", "")
							.replace("$", "").replace(".", "").replace("\\", "");
					if (Character.isDigit(s.charAt(0)))
					{
						s = s.replaceFirst(s.substring(0, 1),"_");
					}	
					contentofscreenfile = contentofscreenfile + "public static MemberOfSEC "+s+"() throws Exception \n";
					contentofscreenfile = contentofscreenfile + "{ \n";
					contentofscreenfile = contentofscreenfile + "return sfdc._SEC(SecName,\""+we.getText().trim()+"\");\n";
					contentofscreenfile = contentofscreenfile + "}\n";
					
				}
				
			}
		}
		else if (sfdc.GetWebDriver().findElement(By.xpath("(//*[normalize-space(text())='"+SectionName+"'])/ancestor::div[1]")).getAttribute("class").contains("pbSubheader"))
		{
			List<WebElement> sec_fields = sfdc.GetWebDriver().findElements(By.xpath("(//*[normalize-space(text())='"+SectionName+"'])/ancestor::div[1]/following-sibling::div[1]/descendant::*[contains(@class,'label') and (local-name()='td' or local-name()='th')]"));
			for(WebElement we: sec_fields) 
			{
				if(we.getText().trim().length()>0)
				{
					sfdc.HighLight(we);
					System.out.println("Field Name under Section: "+we.getText().trim());
					s = we.getText().trim().replace("+", "").replace("&", "")
							.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
							.replace("-", "").replace(" ", "").replace("*", "").replace("!", "")
							.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
							.replace("%", "").replace(")", "").replace(",", "").replace("#", "")
							.replace("$", "").replace(".", "").replace("\\", "");
					if (Character.isDigit(s.charAt(0)))
					{
						s = s.replaceFirst(s.substring(0, 1),"_");
					}
					contentofscreenfile = contentofscreenfile + "public static MemberOfSEC "+s+"() throws Exception \n";
					contentofscreenfile = contentofscreenfile + "{ \n";
					contentofscreenfile = contentofscreenfile + "return sfdc._SEC(SecName,\""+we.getText().trim()+"\");\n";
					contentofscreenfile = contentofscreenfile + "}\n";
					
				}
				
			}
		}
		else
		{
			System.out.println("Unable to read the fields under the section "+SectionName);
		}
	}
	
	public static void _ReadAllColumnsUnderARelatedList(String RelatedListName) throws Exception
	{
		String s;
		//Getting all columns under a related list
			List<WebElement> RL_Cols = sfdc.GetWebDriver().findElements(By.xpath("(//*[normalize-space(text())='"+RelatedListName+"'])/ancestor::div[1]/following-sibling::div[1]/descendant::tr[contains(@class,'headerRow')][1]/descendant::th[text()]"));
			for(WebElement we: RL_Cols) 
			{
				if((we.getText().trim().length()>0) && !(we.getText().trim().equalsIgnoreCase("No records to display")))
				{
					sfdc.HighLight(we);
					System.out.println("Columns under the RL: "+we.getText().trim());
					s = we.getText().trim().replace("+", "").replace("&", "")
							.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
							.replace("-", "").replace(" ", "").replace("*", "").replace("!", "")
							.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
							.replace("%", "").replace(")", "").replace(",", "").replace("#", "")
							.replace("$", "").replace(".", "").replace("\\", "");
					if (Character.isDigit(s.charAt(0)))
					{
						s = s.replaceFirst(s.substring(0, 1),"_");
					}
					contentofscreenfile = contentofscreenfile + "public static MemberOfRL "+s+"(Integer RowIndex) throws Exception \n";
					contentofscreenfile = contentofscreenfile + "{ \n";
						
					contentofscreenfile = contentofscreenfile +	"return sfdc._RL(RList,\""+we.getText().trim()+"\",RowIndex); \n";
					contentofscreenfile = contentofscreenfile + "}\n";
					
					//******************** Below statements Newly Added *************************
					
					contentofscreenfile = contentofscreenfile + "public static MemberOfRL "+s+"() throws Exception \n";
					contentofscreenfile = contentofscreenfile + "{ \n";
						
					contentofscreenfile = contentofscreenfile +	"return sfdc._RL(RList,\""+we.getText().trim()+"\"); \n";
					contentofscreenfile = contentofscreenfile + "}\n";
				}
				
			}
		
	}
	public static void _ReadAllButtons() throws Exception
	{
		//Getting all button in a page
			String s;
			List<WebElement> all_buttons = sfdc.GetWebDriver().findElements(By.xpath("//input[contains(@class,'btn')]"));
			
			contentofscreenfile = contentofscreenfile + "\n \n \n";
			
			for(WebElement we: all_buttons) 
			{
				if(we.getAttribute("value").trim().length()>0)
				{
					sfdc.HighLight(we);
					//System.out.println("Button Name: "+we.getAttribute("value").trim());
					uniquebuttons.add(we.getAttribute("value").trim());
				}
				
			}
			for (String h : uniquebuttons) {
			    System.out.println("Button Name: "+h);
				s = h.trim().replace("+", "").replace("&", "")
						.replace("?", "").replace(":", "").replace("]", "").replace("[", "")
						.replace("-", "").replace(" ", "").replace("*", "").replace("!", "")
						.replace("/", "").replace("'", "").replace("(", "").replace(">", "").replace("<", "")
						.replace("%", "").replace(")", "").replace(",", "").replace("#", "")
						.replace("$", "").replace(".", "").replace("\\", "");
				
				contentofscreenfile = contentofscreenfile + "public static MemberOfButton "+s+"Button() throws Exception{ \n";
				contentofscreenfile = contentofscreenfile +  "return sfdc.Button(\""+h.trim()+"\"); \n";
				contentofscreenfile = contentofscreenfile + "} \n";
		
			}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return void
	 * @throws Exception
	 * @Description Creates the ScreenFiles.java in the repository
	 * @Date Aug 7, 2014
	 */
	public static void _CreateScreenFile() throws Exception
	{
		try
		{
		File file = new File("src\\USER_SPACE\\ObjectRepository\\" + screenname
				+ ".java");
		file.createNewFile();
		BufferedWriter wr = new BufferedWriter(new FileWriter(file));
		wr.write(contentofscreenfile);
		wr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return On Success it returns the entire URL of the WebDriver browser window, on Failure it returns blank value
	 * @throws Exception
	 * @Description Copies the Current URL of the WebDriver browser window, on Failure it returns blank value
	 * @Date Aug 7, 2014
	 */
	public static String GetCurrentURL() throws Exception
	{
		WaitForPageToLoad(30);
		try 
		{
			
			return myWD.getCurrentUrl();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param URL
	 * @return void
	 * @throws Exception
	 * @Description Opens page in already opened browser as per supplied URL
	 * @Date Aug 7, 2014
	 */
	public static void OpenSFDCRecordAsperURL(String URL) throws Exception
	{
		WaitForPageToLoad(15);
		try 
		{
			myWD.get(URL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return The datevalue in String type as per format ddMMyyHHmmss 
	 * @throws Exception
	 * @Description 
	 * @Date Aug 7, 2014
	 */
	public static String GetCurrentDateTimeStamp() throws Exception
	{
        
       	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        return (sdf.format(cal.getTime()));                     
              
    }
	
	
	/**
	* @author Cognizant
	 * @param Year
	 * @param Month
	 * @param Date
	 * @return true of the date is selected successfully else return false
	 * @Description You should click on the date lookup field to enable the OOB date lookup, then only you can call this function to select any specific date by year,month and date
	 * @throws Exception
	 */
	public static boolean SelectFromDateLookup(String Year,String Month,String Date) throws Exception
	{
		try
		{
			//myWD.findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::td[contains(normalize-space(@class),'selectedDate')][1]")).click();
			
			//Selecting the Year as supplied
			WebElement getsingleWebelement = myWD.findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::select[@title='Year'][1]"));
			Select s = new Select(getsingleWebelement);
			s.selectByVisibleText(Year);
			
			System.out.println("Month is:"+Month);
			//Selecting the Month as supplied
			getsingleWebelement = myWD.findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::select[@title='Month'][1]"));
			s = new Select(getsingleWebelement);
			s.selectByVisibleText(Month);
			
			
			//Selecting the Date as supplied
			if(Date.substring(0, 1).equals("0"))
			{
				Date = Date.replace("0", "");
			}
			myWD.findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::table[normalize-space(@class)='calDays'][1]/descendant-or-self::td[normalize-space(text())='"+Date+"'][1]")).click();
			
			
			System.out.println("Supplied date value ("+Date+" "+Month+" "+Year+") is selected from date lookup.");
			AddToXLLogs("Supplied date value ("+Date+" "+Month+" "+Year+") is selected from date lookup.", "Pass");
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println();
			AddToXLLogs("Unable to select the supplied date from SFDC Date lookup.", "Fail");
			return false;
		}
		
	}
	
	/**
	
	 */
	/**
	 * @author Cognizant
	 * @Description This function selects a date from Salesforce date lookup as per supplied parameter.
	 * @param nextdaycount starts from 0,1,2 i.e. today,tomorrow,day after tomorrow so on.
	 * @return
	 * @throws Exception
	 */
	public static boolean SelectFromDateLookup(int nextdaycount) throws Exception
	{
		try
		{
			//selecting today's date
			if(nextdaycount == 0)
			{
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
				String D = sdf.format(cal.getTime()).toString().substring(0, sdf.format(cal.getTime()).toString().indexOf(" ")).trim();
				List<String> DMY = new ArrayList();
				for (String val: sdf.format(cal.getTime()).toString().split(" ", 3))
				{
					DMY.add(val);
			         
			    }
				for(int i=0;i<DMY.size();i++)
				{
					System.out.println(DMY.get(i));
				}
				
				sfdc.SelectFromDateLookup(DMY.get(2),DMY.get(1),DMY.get(0));
								
				
			}
			//Selecting today + nextdaycount 
			if (nextdaycount > 0)
			{
				String DAY="",mon="";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
				String D = sdf.format(cal.getTime()).toString().substring(0, sdf.format(cal.getTime()).toString().indexOf(" ")).trim();
				List<String> DMY = new ArrayList();
				for (String val: sdf.format(cal.getTime()).toString().split(" ", 3))
				{
					DMY.add(val);
			         
			    }
				String month = DMY.get(1);
				int totaldaycount = Integer.valueOf(DMY.get(0)).intValue() + nextdaycount;
				System.out.println("totaldaycount");
				//Calculating 31 day month and the related day
				if (DMY.get(1).contains("Jan") ||DMY.get(1).contains("Mar")||DMY.get(1).contains("May")||DMY.get(1).contains("Jul")||DMY.get(1).contains("Aug")||DMY.get(1).contains("Oct")||DMY.get(1).contains("Dec"))
				{
					if(totaldaycount>31)
					{
						Calendar currentMonth = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
						currentMonth.add(Calendar.MONTH, 1);
						mon = dateFormat.format(currentMonth.getTime());
						int day = totaldaycount - 31;
						DAY = Integer.toString(day);
						
					}
					else
					{
						Calendar currentMonth = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
						mon = dateFormat.format(currentMonth.getTime());
						int day = totaldaycount;
						DAY = Integer.toString(day);
						
					}
				}
				//Calculating 30 day month and the related day
				if (DMY.get(1).contains("Apr") ||DMY.get(1).contains("Jun")||DMY.get(1).contains("Sep")||DMY.get(1).contains("Nov")) 
				{
					if(totaldaycount>30) 
					{
						Calendar currentMonth = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
						currentMonth.add(Calendar.MONTH, 1);
						mon = dateFormat.format(currentMonth.getTime());
						int day = totaldaycount - 30;
						DAY = Integer.toString(day);
						
					}
					else
					{
						Calendar currentMonth = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
						//currentMonth.add(Calendar.MONTH, 1);
						mon = dateFormat.format(currentMonth.getTime());
						int day = totaldaycount;
						DAY = Integer.toString(day);
					}
				}
				//Calculating 28 day month and the related day
				if (DMY.get(1).contains("Feb")  && (totaldaycount>28) ) 
				{
					Calendar currentMonth = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
					currentMonth.add(Calendar.MONTH, 1);
			        mon = dateFormat.format(currentMonth.getTime());
			        int day = totaldaycount - 28;
			        DAY = Integer.toString(day);
			        
				}
				//Calculating 28 day month and the related day
				if (DMY.get(1).contains("Feb")  && (totaldaycount<28) ) 
				{
					Calendar currentMonth = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
					currentMonth.add(Calendar.MONTH, 1);
			        mon = dateFormat.format(currentMonth.getTime());
			        int day = totaldaycount;
			        DAY = Integer.toString(day);
			        
				}
				System.out.println("Date Value Selected was: "+DMY.get(2)+" "+mon+" "+DAY);
				sfdc.SelectFromDateLookup(DMY.get(2),mon,DAY);
			}
			return true;
			
		}
		catch(Exception e)
		{
			
			return false;
		}
	}
	
	
	/**
	 * @param filepath
	 * @param filename
	 * @Description Sends an email to certain set of users with the attachment of test log excel file
	 * @throws Exception
	 */
	public static void _SendEmailAttachingTestLog(String filepath,String filename) throws Exception
	{
		
		  //String to = DataSetup.Emailid_TO;
	      //String cc1 = DataSetup.Emailid_CC1;

	      // Sender's email ID needs to be mentioned
	     //String from = DataSetup.Emailid_FROM;

	    //  final String username = "";//change accordingly
	      //final String password = "";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      //String host = "smtp.gmail.com";
	      /*
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", DataSetup.Email_Host);
	      props.put("mail.smtp.port", "587"); 
	     */ 
	     // props.put("mail.smtp.port", "25");
	      
	      
	      //Below Two sections of code can be used alretnatively based on whether your organization/email account requires authentication or not. 
	      /* ***************************************************** */
	          
	      
	      // Get the default Session object.
	      //Session session = Session.getDefaultInstance(properties);
	      
	      /*
	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(DataSetup.Emailid_Username, DataSetup.Emailid_Passsword);
	            }
	         });

	      */
	      
	      /* ***************************************************** */
	      try {
	    	  
	    	  Properties properties = System.getProperties();
		      // Assuming you are sending email from localhost
		      //String host = "APACSMTP.cts.com";
     

		      // Setup mail server
		      properties.setProperty("mail.smtp.host", DataSetup.Email_Host);
		      

		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(DataSetup.Emailid_FROM));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(DataSetup.Emailid_TO));
	         message.setRecipients(Message.RecipientType.CC,
	                 InternetAddress.parse(DataSetup.Emailid_CC1));
	         
	         // Set Subject: header field
	         message.setSubject("Automation Test Logs: "+filename);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("Hi Team, PFA, the detail level test logs generated via automation script. Thanks, QA Team");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	        
	         DataSource source = new FileDataSource(filepath);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename+".xls");
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent email successfully....");
	      }catch (MessagingException e) {
	    	  e.printStackTrace();
	    	  AddToXLLogs("Unable to send the email.", "Pass");
	    	  System.out.println("Unable to send the email.");
	      }
	}
}
