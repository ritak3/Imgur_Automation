package com_Imgur;


import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Result {
	
	private String result;
	private String resultText;
	private String TestCase;
	private String Screenshot;
	public static String ReportTemplate =System.getProperty("user.dir");	
//	private static final String templatePath = ReportTemplate + "//src//report_template.html";
	public static String reportPath;
	public static Calendar cal = Calendar.getInstance();
	public static String currentDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(cal.getTime());
	//public static String location= ReportTemplate +"//Screenshots//Screenshot_" + currentDate + ".png";
	
	public Result(String result,String TestCase, String resultText,String Screenshot) {
		this.result = result;
		this.TestCase = TestCase;
		this.resultText = resultText;
		this.Screenshot = Screenshot;
	}
	


	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public void setTestCase(String TestCase) {
		this.TestCase = TestCase;
	}
	
	public String getTestCase() {
		return this.TestCase;
	}
	
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	
	public String getResultText() {
		return this.resultText;
	}

	public void setScreenshot(String Screenshot) {
		this.Screenshot = Screenshot;
	}
	
	
	public String getScreenshot() {
		
		return this.Screenshot;
	}
}