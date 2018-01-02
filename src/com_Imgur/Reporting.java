package com_Imgur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.testng.Reporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Reporting extends Driver_Main{
    
    private static List<Result> details;
    private static final String resultPlaceholder = "<!-- INSERT_RESULTS -->";
    public static String ReportTemplate =System.getProperty("user.dir");    
    private static final String templatePath = ReportTemplate + "//src//report_template.html";
    public static String reportPath;
    public static Calendar cal = Calendar.getInstance();
    private static String currentDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(cal.getTime());
    //public static File screenshotpath= new File(ReportTemplate +"//Screenshots//Screenshot_"+currentDate + ".png");
    
    
    public static void initialize() {
        details = new ArrayList<Result>();
    }
    
    public static void report(String TestcaseName, String actualValue,String expectedValue, String Screenshot) throws IOException, InterruptedException {
        
        if(actualValue.equals(expectedValue)) {
            
            //TestcaseName = "someT123";
            Result r = new Result("Pass",TestcaseName,"Actual value ‘" + actualValue + "’ matches expected value", null);
            details.add(r);
        } else {
            //createScreenshot();
            Result r = new Result("Fail",TestcaseName, "Actual value ‘" + actualValue + "’ does not match expected value ‘" + expectedValue + "’","<a target=‘_blank’ href="+Screenshot+">Screenshot of the page</a>");
            details.add(r);
        }
    }
    
    public static void showResults() {
        for (int i = 0;i < details.size();i++) {
            System.out.println("Result " + Integer.toString(i) + ": "  + details.get(i).getTestCase() + details.get(i).getResult() + details.get(i).getScreenshot());
        }
    }
    
    public static void writeResults() throws IOException {
   
		
		try {
			String reportIn = new String(Files.readAllBytes(Paths.get(templatePath)));
			for (int i = 0; i < details.size();i++) {
				
				reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr><td>" + Integer.toString(i+1) + "</td><td>" +details.get(i).getResult() + "</td><td>" + 
			details.get(i).getTestCase() + "</td><td>" + details.get(i).getResultText() + "</td><td>" + details.get(i).getScreenshot() + "</td></tr>" + resultPlaceholder);
			}
			
			reportPath = ReportTemplate +"//Reports//report_" + currentDate + ".html";
			Files.write(Paths.get(reportPath),reportIn.getBytes(),StandardOpenOption.CREATE);
			
		} catch (Exception e) {
			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}
        



public static void createScreenshot(WebDriver webdriver,String fileWithPath) throws IOException, InterruptedException
{

   // generate screenshot as a file object
    TakesScreenshot scrShot =((TakesScreenshot)webdriver);

   //Call getScreenshotAs method to create image file

           File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

       //Move image file to new destination

           File DestFile=new File(fileWithPath);

           //Copy file at destination

           FileUtils.copyFile(SrcFile, DestFile);
       
}

}