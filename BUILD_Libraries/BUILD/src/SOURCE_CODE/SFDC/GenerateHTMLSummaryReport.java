package SOURCE_CODE.SFDC;

import SOURCE_CODE.SFDC.*;
import USER_SPACE.TestPrerequisite.*;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

public class GenerateHTMLSummaryReport {
	
	public static ArrayList al_testcase_name,al_reasonforfailure,al_status,al_startofexecution,al_endofexecution,al_duration,al_browser,al_profiles,tsr_slnumber,tsr_screenshot;
	public static Integer Total_Number_of_Test_Scripts_Executed,Total_Number_of_Test_Scripts_Passed,Total_Number_of_Test_Scripts_Failed;
	public static String Start_Time,End_Time,Overall_Execution_Time,Test_Environment,Functional_Area;
	public static String FolderName;
	
	GenerateHTMLSummaryReport() throws Exception
	{	
		
		try
		{
			al_testcase_name = new ArrayList();
			al_reasonforfailure = new ArrayList();
			al_status = new ArrayList();
			al_startofexecution = new ArrayList();
			al_endofexecution = new ArrayList(); 
			al_duration = new ArrayList();
			al_browser = new ArrayList();
			al_profiles = new ArrayList();
			tsr_slnumber = new ArrayList();
			tsr_screenshot = new ArrayList();
			
			Total_Number_of_Test_Scripts_Executed = 0;
			Total_Number_of_Test_Scripts_Passed = 0;
			Total_Number_of_Test_Scripts_Failed = 0;
			
			Connection conn = DB.Connect(DataSetup.Logininfo);
			Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "Select * from ["+DataSetup.SheetNameofTestLogSummary+"$]" );
            //System.out.println("sample:"+rs);
            ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println("samplersd:"+rsmd);
            int numberOfColumns = rsmd.getColumnCount();

           // System.out.println("numberOfColumns:"+numberOfColumns);

            int countofrowinexelogexcel = 0;
                while (rs.next()) {
                		
                		countofrowinexelogexcel = countofrowinexelogexcel + 1;
                		tsr_slnumber.add(countofrowinexelogexcel);
                		
                         //System.out.println("sample1:"+rs);
                         for (int i = 1; i <= numberOfColumns; i++) 
                         {
                              
                        	 	
                              String columnValue = rs.getString(i);
                              if (i==1)
                              {
                            	  al_testcase_name.add(columnValue);
                              }
                              else if (i==2)
                              {
                            	  if (columnValue == null)
                            	  {
                            		  columnValue = "N/A";
                            	  }
                            	  al_reasonforfailure.add(columnValue);
                              }
                              else if (i==3)
                              {
                            	  al_status.add(columnValue);
                            	  if (columnValue.trim().equalsIgnoreCase("Fail"))
                            	  {
                            		  Total_Number_of_Test_Scripts_Failed = Total_Number_of_Test_Scripts_Failed + 1;
                            		  tsr_screenshot.add("");
                            	  }
                            	  else
                            	  {
                            		  Total_Number_of_Test_Scripts_Passed = Total_Number_of_Test_Scripts_Passed + 1;
                            		  tsr_screenshot.add("");
                            	  }
                            	  
                              }
                              else if (i==4)
                              {
                            	  al_startofexecution.add(columnValue);
                              }
                              else if (i==5)
                              {
                            	  al_endofexecution.add(columnValue);
                              }
                              else if (i==6)
                              {
                            	  al_duration.add(columnValue);
                              }
                              else if (i==7)
                              {
                            	  al_browser.add(columnValue);
                              }
                              else if (i==8)
                              {
                            	  al_profiles.add(columnValue);
                              }
                              
                              System.out.print(columnValue);
                         
                         }
                         //System.out.println("");
                    }

                    st.close();
                    conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "HTML Report Generated with error! Please remove duplicate test case name from EXE_Log sheet");
			
		}
		/*
		for(int a=0;a<al_startofexecution.size();a++)
		{
			//System.out.println("Inside Arraylist"+tsr_slnumber.get(a).toString()+al_testcase_name.get(a).toString());
		}
		*/
		DB.Connect(DataSetup.Logininfo);
		DB.UpdateXLCell(DataSetup.SheetNameofSetupInfo, al_startofexecution.get(0).toString(), "Overall_Start_Time", "SL_NO", "Setup1");
		DB.Connect(DataSetup.Logininfo);
		DB.UpdateXLCell(DataSetup.SheetNameofSetupInfo, al_endofexecution.get(al_endofexecution.size()-1).toString(), "Overall_End_Time", "SL_NO", "Setup1");
		DB.Connect(DataSetup.Logininfo);
		Start_Time = DB.ReadXLData(DataSetup.SheetNameofSetupInfo, "Overall_Start_Time", "SL_NO", "Setup1");
		DB.Connect(DataSetup.Logininfo);
		End_Time = DB.ReadXLData(DataSetup.SheetNameofSetupInfo, "Overall_End_Time", "SL_NO", "Setup1");
		
		
		//System.out.println("StartTimeIS:"+Start_Time.trim().substring(Start_Time.trim().indexOf(" ")+1));
		//System.out.println("EndTimeIS:"+End_Time.trim().substring(End_Time.trim().indexOf(" ")+1));
		
		////////////
		String STRT_T = Start_Time;
		String END_T = End_Time;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
 
		try {
			d1 = format.parse(STRT_T);
			d2 = format.parse(END_T);
 
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
 
			//System.out.print(diffDays + " days, ");
			//System.out.print(diffHours + " hours, ");
			//System.out.print(diffMinutes + " minutes, ");
			//System.out.print(diffSeconds + " seconds.");
			String d = diffDays+" days, "+diffHours+" hours, "+diffMinutes+" minutes, "+diffSeconds+" seconds";
			
			// Modified calculation of Overall Duration in the Setup Sheet.
			//This will overwrite the earlier calculation
			//System.out.println("");
			int total_h = 0,total_m = 0,total_s = 0;
			for(int i=0;i<al_duration.size();i++)
			{
				
				//System.out.println(al_duration.get(i));
				
				String hh = al_duration.get(i).toString().substring(0, al_duration.get(i).toString().indexOf('h'));
				int h = Integer.parseInt(hh);
				
				if(i==0)
				{
					total_h = h;
				}
			    total_h = total_h + h;
				//System.out.println(h);
				
				
				String mm = al_duration.get(i).toString().substring(al_duration.get(i).toString().indexOf(':')+1, al_duration.get(i).toString().indexOf('m'));
				int m = Integer.parseInt(mm);
				
				if(i==0)
				{
					total_m = m;
				}
			    total_m = total_m + m;
				//System.out.println(m);
				
				
				String ss = al_duration.get(i).toString().substring(al_duration.get(i).toString().indexOf('m')+2, al_duration.get(i).toString().indexOf('s'));
				int s = Integer.parseInt(ss);
				
				if(i==0)
				{
					total_s = s;
				}
			    total_s = total_s + s;
				//System.out.println(s);
				
				
			}
			
			//System.out.println(total_h +" "+ total_m +" "+ total_s);
			int all_sec = 0;
			all_sec = (total_h * (60) * (60)) + (total_m * (60)) + total_s;
			//System.out.println("all_sec:"+all_sec);
			//System.out.println(timeConversion(all_sec));
			
			
			DB.Connect(DataSetup.Logininfo);
			DB.UpdateXLCell(DataSetup.SheetNameofSetupInfo, timeConversion(all_sec), "Overall_Duration", "SL_NO", "Setup1");
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
		//////////
		DB.Connect(DataSetup.Logininfo);
		Overall_Execution_Time = DB.ReadXLData(DataSetup.SheetNameofSetupInfo, "Overall_Duration", "SL_NO", "Setup1");
		Test_Environment = DB.ReadXLData(DataSetup.SheetNameofSetupInfo, "Test_Environment", "SL_NO", "Setup1");
		Functional_Area = DB.ReadXLData(DataSetup.SheetNameofSetupInfo, "Functional_Area", "SL_NO", "Setup1");
		
		
		//Calling following function to generate the suite level HTML report
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        FolderName = sdf.format(cal.getTime());   
        SFDCAutomationFW._CreateDirectory();
        GenerateChart();
        
        DB.Connect(DataSetup.Logininfo);
       // System.out.println("DataSetup.pathOfProjectHomeDirectory:"+DataSetup.pathOfProjectHomeDirectory);
        GenerateTestSummaryReport(DB.ReadXLData("Setup", "Path_Home_Directory", "SL_NO", "Setup1")+"//TestLogs//"+FolderName+"//TestSummaryReport_By_Selenium.html");
		
		File htmlFile = new File(DB.ReadXLData("Setup", "Path_Home_Directory", "SL_NO", "Setup1")+"//TestLogs//"+FolderName+"//TestSummaryReport_By_Selenium.html");

		// open the default web browser for the HTML page
		Desktop.getDesktop().browse(htmlFile.toURI());
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

public static void GenerateTestSummaryReport(String pathOfhtmlfile) throws Exception {
		try
		{
		StringBuilder htmlBuilder = new StringBuilder();

		Total_Number_of_Test_Scripts_Executed = Total_Number_of_Test_Scripts_Passed + Total_Number_of_Test_Scripts_Failed;
		
		htmlBuilder.append("<html>");
		htmlBuilder.append("<head>");
		htmlBuilder.append("<title>Test Summary Report</title>");
		
		htmlBuilder.append("<style type=\"text/css\">");
		htmlBuilder.append("/*----------Text Styles----------*/");
		htmlBuilder.append(".ws6 {font-size: 8px;}");
		htmlBuilder.append(".ws7 {font-size: 9.3px;}");
		htmlBuilder.append(".ws8 {font-size: 11px;}");
		htmlBuilder.append(".ws9 {font-size: 12px;}");
		htmlBuilder.append(".ws10 {font-size: 13px;}");
		htmlBuilder.append(".ws11 {font-size: 15px;}");
		htmlBuilder.append(".ws12 {font-size: 16px;}");
		htmlBuilder.append(".ws14 {font-size: 19px;}");
		htmlBuilder.append(".ws16 {font-size: 21px;}");
		htmlBuilder.append(".ws18 {font-size: 24px;}");
		htmlBuilder.append(".ws20 {font-size: 27px;}");
		htmlBuilder.append(".ws22 {font-size: 29px;}");
		htmlBuilder.append(".ws24 {font-size: 32px;}");
		htmlBuilder.append(".ws26 {font-size: 35px;}");
		htmlBuilder.append(".ws28 {font-size: 37px;}");
		htmlBuilder.append(".ws36 {font-size: 48px;}");
		htmlBuilder.append(".ws48 {font-size: 64px;}");
		htmlBuilder.append(".ws72 {font-size: 96px;}");
		htmlBuilder.append(".wpmd {font-size: 13px;font-family: Arial,Helvetica,Sans-Serif;font-style: normal;font-weight: normal;}");
		htmlBuilder.append("/*----------Para Styles----------*/");
		htmlBuilder.append("DIV,UL,OL /* Left */");
		htmlBuilder.append("{");
		htmlBuilder.append(" margin-top: 0px;");
		htmlBuilder.append(" margin-bottom: 0px;");
		htmlBuilder.append("}");
		htmlBuilder.append("</style>");

		htmlBuilder.append("</head>");
		htmlBuilder.append("<body>");
		///////////////////////////////////////////////////////
		htmlBuilder.append("<td valign=\"top\">");
		htmlBuilder.append("<img src=\"http://www.cognizant.com//dotcom-images/cognizant-logo.jpg\"");
		htmlBuilder.append("</td>");
		htmlBuilder.append("<td valign=\"top\">");	
		htmlBuilder.append("<div>");
		htmlBuilder.append("<TR>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("<div>");
		htmlBuilder.append("<TR>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("</div>");
		
		
		//////////////////////////////////////////////////////////
		//htmlBuilder.append("<div id=\"table1\" style=\"position:absolute; overflow:hidden; left:64px; top:19px; width:1032px; height:79px; z-index:0\">");
		htmlBuilder.append("<div id=\"table1\" style=\"position:absolute; left:50px; top:80px; width:1252px; height:79px; z-index:0\">");
		htmlBuilder.append("<div class=\"wpmd\">");
		htmlBuilder.append("<div><TABLE bgcolor=\"#A2F99F\" border=1 bordercolorlight=\"#C0C0C0\" bordercolordark=\"#A0A0A0\">");
		htmlBuilder.append("<TR valign=top>");
		//htmlBuilder.append("<TD width=1012 height=42 valign=middle><div class=\"wpmd\">");
		htmlBuilder.append("<TD width=1180 height=42 valign=middle><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><font class=\"ws20\"><B>Test Automation Summary Report</B></font></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("</TABLE>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</div></div>");

		//htmlBuilder.append("<div id=\"table2\" style=\"position:absolute; overflow:hidden; left:315px; top:90px; width:558px; height:223px; z-index:1\">");
		htmlBuilder.append("<div id=\"table2\" align=center style=\"position:absolute; left:345px; top:160px; width:558px; height:223px; z-index:1\">");		
		htmlBuilder.append("<div class=\"wpmd\">");
		htmlBuilder.append("<div><TABLE bgcolor=\"#CCFFCC\" border=1 bordercolorlight=\"#C0C0C0\" bordercolordark=\"#A0A0A0\">");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD colspan=2 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Test Setup Information</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>Test Environment</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+Test_Environment+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>Functional Area </div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+Functional_Area+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>Total Number of Test Scripts Executed</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+Total_Number_of_Test_Scripts_Executed+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>Total Number of Test Scripts Passed</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+Total_Number_of_Test_Scripts_Passed+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>Total Number of Test Scripts Failed</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+Total_Number_of_Test_Scripts_Failed+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>Start Time</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+Start_Time+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>End Time</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+End_Time+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>Overall Execution Time</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=259 valign=top><div class=\"wpmd\">");
		
		htmlBuilder.append("<div>"+Overall_Execution_Time+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("</TABLE>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</div></div>");

		///////////////////////////////////////////
		//htmlBuilder.append("<div id=\"table2\" style=\"position:absolute; overflow:hidden; left:315px; top:90px; width:558px; height:223px; z-index:1\">");
				
				htmlBuilder.append("<div id=\"table3a\" align=center style=\"position:absolute; left:910px; top:160px; width:260px; height:30px; z-index:1\">");		
				htmlBuilder.append("<div class=\"wpmd\">");
				htmlBuilder.append("<div><TABLE bgcolor=\"#CCFFCC\" border=1 bordercolorlight=\"#C0C0C0\" bordercolordark=\"#A0A0A0\">");
				htmlBuilder.append("<TR valign=top>");
							
				htmlBuilder.append("<TD colspan=\"4\" width=300  height:300 bgcolor=\"#81DAF5\" valign=top><img src=\"Chart3D_TestResult.png\" width=\"260;  height:300\">");
				
				/*
				htmlBuilder.append("<div align=center><B> Significancedfgdfgdgdgfdfgdddddddddddddddddddddd </B></div>");
				htmlBuilder.append("</div>");
				*/
				htmlBuilder.append("</TD>");
				
				
				htmlBuilder.append("</TR>");
				
				htmlBuilder.append("</TABLE>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</div></div>");
				DB.Connect(DataSetup.Logininfo);
				//htmlBuilder.append("<img src=Chart3D_TestResult.jpeg");
				
				//////////******Trying to put the chart *********************////////////////////////
				
				htmlBuilder.append("<div id=\"table3\" align=center style=\"position:absolute; left:445px; top:400px; width:300px; height:223px; z-index:1\">");		
				htmlBuilder.append("<div class=\"wpmd\">");
				htmlBuilder.append("<div><TABLE bgcolor=\"#CCFFCC\" border=1 bordercolorlight=\"#C0C0C0\" bordercolordark=\"#A0A0A0\">");
				htmlBuilder.append("<TR valign=top>");
							
				htmlBuilder.append("<TD width=273 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div align=center><B> When </B></div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("<TD width=150 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div align=center><B>Change Color</B></div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				
				htmlBuilder.append("</TR>");
				htmlBuilder.append("<TR valign=top>");
				htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div> Duration < 10 min </div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("<TD width=100 bgcolor=\"#00CC00\" valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div></div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("</TR>");
				
				htmlBuilder.append("</TR>");
				htmlBuilder.append("<TR valign=top>");
				htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div> Duration >= 10 min and < 15 min </div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("<TD width=100 bgcolor=\"#ffff00\" valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div></div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("</TR>");


				htmlBuilder.append("</TR>");
				htmlBuilder.append("<TR valign=top>");
				htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div> Duration >= 15 min </div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("<TD width=100 bgcolor=\"#a9a9a9\" valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div></div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("</TR>");
				
				htmlBuilder.append("</TR>");
				htmlBuilder.append("<TR valign=top>");
				htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div> Result = Pass </div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("<TD width=100 bgcolor=\"#006600\" valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div></div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("</TR>");
				
				htmlBuilder.append("</TR>");
				htmlBuilder.append("<TR valign=top>");
				htmlBuilder.append("<TD width=273 valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div> Result = Fail </div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("<TD width=100 bgcolor=\"#ff0000\" valign=top><div class=\"wpmd\">");
				htmlBuilder.append("<div></div>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</TD>");
				htmlBuilder.append("</TR>");
				htmlBuilder.append("</TABLE>");
				htmlBuilder.append("</div>");
				htmlBuilder.append("</div></div>");
				
							
		
		//htmlBuilder.append("<div id=\"table3\" style=\"position:absolute; left:0px; top:320px; width:1178px; height:129px; z-index:2\">");
		htmlBuilder.append("<div id=\"table4\" style=\"position:absolute; left:35px; top:565px; width:1178px; height:129px; z-index:2\">");
		htmlBuilder.append("<div class=\"wpmd\">");
		htmlBuilder.append("<div><TABLE bgcolor=\"#FFFFFF\" border=1 bordercolorlight=\"#C0C0C0\" bordercolordark=\"#808080\">");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD colspan=16 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Overall Test Scripts Results</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=44 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>SL. No.</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=221 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Test Script Name</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=221 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Supported Browser</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");		
		htmlBuilder.append("<TD width=221 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Profiles</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");		
		htmlBuilder.append("<TD width=66 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Result</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=284 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Reason for Failure</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=106 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Screen Shots</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=151 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Start Time</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=124 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>End Time</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("<TD width=120 bgcolor=\"#81DAF5\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div align=center><B>Duration</B></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		/////////////////////////////////////////////////////////
		/*
		 for(int g=0;g<tsr_slnumber.size();g++)
		{
			System.out.println("|"+tsr_slnumber.get(g)+"|"+tsr_testscriptname.get(g)+"|"+tsr_result.get(g)+"|"+tsr_reasonforfail.get(g)+"|"+"|"+tsr_starttime.get(g)+"|"+tsr_endtime.get(g)+"|"+tsr_duration.get(g)+"|");
		}
		*/
		//System.out.println("The size of the TSR arraylist:"+tsr_slnumber.size());
		for(int g=0;g<tsr_slnumber.size();g++)
		{
		
		htmlBuilder.append("<TR valign=top>");
		htmlBuilder.append("<TD width=25 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+tsr_slnumber.get(g)+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		htmlBuilder.append("<TD width=221 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		//System.out.println("Inside the HTML for loop: "+al_testcase_name.get(g));
		htmlBuilder.append("<div>"+al_testcase_name.get(g)+"</div>");
		
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		htmlBuilder.append("<TD width=221 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+al_browser.get(g)+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		htmlBuilder.append("<TD width=221 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+al_profiles.get(g)+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		if (al_status.get(g).equals("Pass"))
		{
			htmlBuilder.append("<TD width=66 height=14 bgcolor=\"#006600\" valign=top><div class=\"wpmd\">");
			
		}
		else if (al_status.get(g).equals("Fail"))
		{
			htmlBuilder.append("<TD width=66 height=14 bgcolor=\"#FF0000\" valign=top><div class=\"wpmd\">");
		}
		
		htmlBuilder.append("<div><font color=\"white\">"+al_status.get(g)+"</font></div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		htmlBuilder.append("<TD width=1700 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+al_reasonforfailure.get(g)+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		htmlBuilder.append("<TD width=106 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		
		/////////////////
		if (al_status.get(g).equals("Pass"))
		{
			htmlBuilder.append("<div>"+tsr_screenshot.get(g)+"</div>");
			
		}
		else if (al_status.get(g).equals("Fail"))
		{
			htmlBuilder.append("<div><a href=\""+tsr_screenshot.get(g)+"\">hyperlink</a></div>");
					
		}
		
		/////////////////
		//htmlBuilder.append("<div>hyperlink</div>");
		
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		htmlBuilder.append("<TD width=250 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+al_startofexecution.get(g)+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		htmlBuilder.append("<TD width=250 height=14 bgcolor=\"#CCFFCC\" valign=top><div class=\"wpmd\">");
		htmlBuilder.append("<div>"+al_endofexecution.get(g)+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		
		String h = al_duration.get(g).toString().substring(al_duration.get(g).toString().indexOf(":")+1, al_duration.get(g).toString().indexOf("m"));
		Integer min = Integer.parseInt(h);
		if (min<10)
		{
			htmlBuilder.append("<TD width=250 height=14 bgcolor=\"#00CC00\" valign=top><div class=\"wpmd\">");
		}
		else if (min>=10 && min < 15)
		{
			htmlBuilder.append("<TD width=250 height=14 bgcolor=\"#ffff00\" valign=top><div class=\"wpmd\">");
		}
		else if(min >= 15)
		{
			htmlBuilder.append("<TD width=250 height=14 bgcolor=\"#a9a9a9\" valign=top><div class=\"wpmd\">");
		}
		
	
		
		htmlBuilder.append("<div>"+al_duration.get(g)+"</div>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</TD>");
		htmlBuilder.append("</TR>");
		
		//////////////////////////

		}



//////////////////////////////////////////////////////////
		htmlBuilder.append("</TABLE>");
		htmlBuilder.append("</div>");
		htmlBuilder.append("</div></div>");
		htmlBuilder.append("</body>");
		htmlBuilder.append("</html>");


		FileWriter writer = new FileWriter(pathOfhtmlfile);
		writer.write(htmlBuilder.toString());
		writer.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
}

private static final int MINUTES_IN_AN_HOUR = 60;
private static final int SECONDS_IN_A_MINUTE = 60;

private static String timeConversion(int totalSeconds) {
    int hours = totalSeconds / MINUTES_IN_AN_HOUR / SECONDS_IN_A_MINUTE;
    int minutes = (totalSeconds - (hoursToSeconds(hours)))
            / SECONDS_IN_A_MINUTE;
    int seconds = totalSeconds
            - ((hoursToSeconds(hours)) + (minutesToSeconds(minutes)));

    return hours + " hours " + minutes + " minutes " + seconds + " seconds";
}

private static int hoursToSeconds(int hours) {
    return hours * MINUTES_IN_AN_HOUR * SECONDS_IN_A_MINUTE;
}

private static int minutesToSeconds(int minutes) {
    return minutes * SECONDS_IN_A_MINUTE;
}
public static void GenerateChart() throws Exception
{
    System.out.println("Total_Number_of_Test_Scripts_Failed"+Total_Number_of_Test_Scripts_Failed);
	  DefaultPieDataset dataset = new DefaultPieDataset( );             
      dataset.setValue( "Pass" , new Double( Total_Number_of_Test_Scripts_Passed ) );             
      dataset.setValue( "Fail" , new Double( Total_Number_of_Test_Scripts_Failed ) );
      
      
      JFreeChart chart = ChartFactory.createPieChart3D( 
         "Pass Fail Chart" ,  // chart title                   
         dataset ,         // data 
         true ,            // include legend                   
         true, 
         false);
      

      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );             
      plot.setStartAngle( 270 );             
      plot.setForegroundAlpha( 0.60f );             
      plot.setInteriorGap( 0.02 );        
      plot.setSectionPaint("Pass", new Color(57,138,23));
      plot.setSectionPaint("Fail", new Color(230,61,78));
      
      int width = 220; /* Width of the image */             
      int height = 160; /* Height of the image */  
      DB.Connect(DataSetup.Logininfo);
      	
      File pieChart3D = new File(DB.ReadXLData("Setup", "Path_Home_Directory", "SL_NO", "Setup1")+"//TestLogs//"+FolderName+"//Chart3D_TestResult.png" );                           
      ChartUtilities.saveChartAsPNG(pieChart3D , chart , width , height);
}
public static void main(String[] args) throws Exception
{
	new GenerateHTMLSummaryReport();
}

}


