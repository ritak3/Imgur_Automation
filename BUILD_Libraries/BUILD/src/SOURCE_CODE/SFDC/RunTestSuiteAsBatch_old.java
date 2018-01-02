package SOURCE_CODE.SFDC;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import USER_SPACE.TestPrerequisite.DataSetup;

public class RunTestSuiteAsBatch_old {
	
	public static ArrayList al_slno,al_tc,al_brow,al_exe;
	public static String contentOfbatchfiletobegenerated = "";
	
	public static void main(String[] args) throws Exception 
	{
		try
		{
			al_slno = new ArrayList();
			al_tc = new ArrayList();
			al_brow = new ArrayList();
			al_exe = new ArrayList();
			
		Connection conn = DB.Connect(DataSetup.Logininfo);
		Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery( "Select * from ["+DataSetup.SheetNameofTestSuite+"$]" );
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
                          if (i==1)
                          {
                        	  al_slno.add(columnValue);
                          }
                          else if(i==2)
                          {
                        	  al_tc.add(columnValue);
                        	  tc = columnValue;
                        	  //System.out.println("TC is:"+tc);
                          }
                          else if(i==3)
                          {
                        	  al_brow.add(columnValue);
                        	  br = columnValue;
                          }
                          else if(i==4)
                          {
                        	  if (columnValue.trim().equalsIgnoreCase("yes"))
                        	  {
                        		  //System.out.println("tc:"+tc);
                        		  contentOfbatchfiletobegenerated = contentOfbatchfiletobegenerated + "java -classpath run.jar; "+tc + " "+ br +" "+"\n \n";
                        	  }
                        	  al_exe.add(columnValue);
                          }
                     
                     }
                     
                }
            	contentOfbatchfiletobegenerated = contentOfbatchfiletobegenerated + "java -classpath run.jar; SOURCE_CODE.SFDC.GenerateHTMLSummaryReport \n";
            	contentOfbatchfiletobegenerated = contentOfbatchfiletobegenerated + "pause \n";
            	//contentOfbatchfiletobegenerated = contentOfbatchfiletobegenerated + "exit \n";
                st.close();
                conn.close();
                
                System.out.println(contentOfbatchfiletobegenerated);  
                
                File file = new File(DataSetup.SheetNameofTestSuite+".bat");
                file.createNewFile();
                BufferedWriter wr = new BufferedWriter(new FileWriter(file));
                wr.write(contentOfbatchfiletobegenerated);
                wr.close();
                
                Runtime runtime = Runtime.getRuntime();
                try {
                 Process process;
                 //process =runtime.exec(DataSetup.SheetNameofTestSuite+".bat");
                 if(!contentOfbatchfiletobegenerated.equals("")){
                	 System.out.println(contentOfbatchfiletobegenerated);
                 process =runtime.exec("cmd /c start "+DataSetup.SheetNameofTestSuite+".bat");
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
