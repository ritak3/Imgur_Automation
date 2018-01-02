package SOURCE_CODE.SFDC;


import USER_SPACE.TestPrerequisite.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class RunTestSuiteAsBatch {
	
	public static ArrayList al_slno,al_tc,al_brow,al_exe,al_dependency,al_description;
	public static String contentOfbatchfiletobegenerated = "";
	
	public static void main(String[] args) throws Exception 
	{
		try
		{
			al_slno = new ArrayList();
			al_tc = new ArrayList();
			al_brow = new ArrayList();
			al_exe = new ArrayList();
			al_dependency = new ArrayList();
			al_description = new ArrayList();
			
			String ModuleName = "";	
			DB.Connect(DataSetup.Logininfo);
	        ModuleName = DB.ReadXLData("Setup", "Functional_Area", "SL_NO", "Setup1");
	        	
		Connection conn = DB.Connect(DataSetup.Logininfo);
		Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery( "Select * from [EXE_"+ModuleName+"$]" );
        //System.out.println("sample:"+rs);
        ResultSetMetaData rsmd = rs.getMetaData();
        //System.out.println("samplersd:"+rsmd);
        int numberOfColumns = rsmd.getColumnCount();
        String br = "";
        
        
        //System.out.println("numberOfColumns:"+numberOfColumns);

        
            while (rs.next()) {
            		
            	 	 String tc = "";
            		 for (int i = 1; i <= numberOfColumns; i++) 
                     {                    	 	
                          String columnValue = rs.getString(i);
                          //System.out.println("column value is:"+columnValue);
                          if (i==1)
                          {
                        	  al_slno.add(columnValue);
                        	  //System.out.println("i="+i+", columnValue:"+columnValue);
                          }
                          else if(i==2)
                          {
                        	  al_tc.add(columnValue);
                        	  tc = columnValue;
                        	 // System.out.println("i="+i+", columnValue:"+columnValue);
                        	  //System.out.println("TC is:"+tc);
                          }
                          else if(i==3)
                          {
                        	  al_brow.add(columnValue);
                        	  //System.out.println("i="+i+", columnValue:"+columnValue);
                          }
                          else if(i==4)
                          {
                        	  al_dependency.add(columnValue);
                        	  br = columnValue;
                        	  //System.out.println("i="+i+", columnValue:"+columnValue);
                        	  
                          }
                          else if(i==5)
                          {                        	  
                        	  if (columnValue.trim().equalsIgnoreCase("yes"))
                        	  {
                        		  //System.out.println("tc:"+tc);
                        		  contentOfbatchfiletobegenerated = contentOfbatchfiletobegenerated + "java -classpath run.jar; "+"USER_SPACE.TestScripts."+ModuleName+"."+tc + " "+ br +" "+"\n \n";
                        		 // System.out.println("i="+i+", columnValue:"+columnValue);
                        	  }
                        	  al_exe.add(columnValue);
                          }
                          else if(i==6)
                          {
                        	  al_description.add(columnValue);
                        	  //System.out.println("i="+i+", columnValue:"+columnValue);
                          }
                     
                     }
                     
                }
            	contentOfbatchfiletobegenerated = contentOfbatchfiletobegenerated + "java -classpath run.jar; SOURCE_CODE.SFDC.GenerateHTMLSummaryReport \n";
            	contentOfbatchfiletobegenerated = contentOfbatchfiletobegenerated + "pause \n";
                st.close();
                conn.close();
                
                //System.out.println(contentOfbatchfiletobegenerated);  
                
                File file = new File("EXE_"+ModuleName+".bat");
                file.createNewFile();
                BufferedWriter wr = new BufferedWriter(new FileWriter(file));
                wr.write(contentOfbatchfiletobegenerated);
                wr.close();
                
                Runtime runtime = Runtime.getRuntime();
                try {
                 Process process;
                 //process =runtime.exec(DataSetup.SheetNameofTestSuite+".bat");
                 if(!contentOfbatchfiletobegenerated.equals("")){
                	 //System.out.println(contentOfbatchfiletobegenerated);
                 process =runtime.exec("cmd /c start "+"EXE_"+ModuleName+".bat");
                 }
                } catch (IOException e) {
                 e.printStackTrace();
                }
                
                
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
}
