package com_Imgur;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;


public class Browser_Launch extends Driver_Main {

	//public static driverriver driver;
	 public String location =System.getProperty("user.dir");
	public String path = location +"//DataSheet.xlsx";
	public Excel_Reader obj = new Excel_Reader(path);
	public String Dweb = obj.getCellData("LoginInfo", "Browser", 2);
	public String a = obj.getCellData("LoginInfo","UserName", 2);
	

	public void BrowserLaunch(){
	


	
	if(Dweb.equalsIgnoreCase("chrome"))
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("-incognito");
		options.addArguments("start-maximized");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();    
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver=new ChromeDriver();
	 
	}
	else if(Dweb.equals("Safari"))
	{
		driver=new SafariDriver();
	}
	else if(Dweb.equals("firefox"))
	{
		System.setProperty("driverriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
		driver=new FirefoxDriver();
	}
	else if(Dweb.equals("Internet Explorer"))
	{
		driver= new InternetExplorerDriver();
	}
	else
	{ 
		System.out.println("No Such browser found");
	}


try{
	
	String url = obj.getCellData("LoginInfo", "URL", 2);
	
	
	driver.navigate().to(url);
	driver.manage().window().maximize();
	
	


}
catch(Exception e)
{
	//driver.quit();
	e.printStackTrace();
}

	
	
}


}
