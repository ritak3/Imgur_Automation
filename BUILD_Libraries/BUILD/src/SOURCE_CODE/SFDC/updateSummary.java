package SOURCE_CODE.SFDC;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import USER_SPACE.TestPrerequisite.DataSetup;



public class updateSummary {

	public static void main( String[ ] args )throws Exception
	{
		//updateTestExecution("", Status.FAIL, "by automation");
		//JIRA_Status_Transition("TM-4","31");
		//UpdateIssue();
		//httpGet();
		
		
		//JIRA_Status_Transition("TM-4","DONE");
		//JIRA_FieldValueChange("TM-4","labels","Testing Description");
		//JIRA_DropdownValueChange("TM-4","priority","High");
		JIRA_AttachExcelLogToJIRAIssue("TM-4","D:/151215/GLOBALFOUNDRIES/TestLogs/30-12-2015/A091_AccountDisengaged_30-12-2015_11.52.28.081_Pass.xls");
	}
	public static boolean JIRA_Status_Transition(String JIRA_ISSUE_KEY, String CHANGE_STATUS_TO) throws IOException {
		try{
			   
				DB.Connect(DataSetup.Logininfo);
				URL url = new URL(DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1") + "/rest/api/latest/issue/"+JIRA_ISSUE_KEY+"/transitions?expand=transitions.fields");
				
		       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		       conn.setDoOutput(true);
		       conn.setDoInput(true);
		        
		       String credentials = "mail2souravm" + ":" + "Lab@r3s2";
		       String encoding = Base64Converter.encode(credentials.getBytes("UTF-8"));
		       conn.setRequestProperty("Authorization", String.format("Basic %s", encoding));
		        
		       conn.setRequestMethod("POST");
		       conn.setRequestProperty("Content-Type", "application/json");
		        
		       conn.connect();
		       
		       String TOSTATUS_VALUE_ID_FROM_JSON_FILE = "";
		      
		       DB.Connect(DataSetup.Logininfo);
		       
		       if(CHANGE_STATUS_TO.equalsIgnoreCase("TO DO"))
		       {
		    	   TOSTATUS_VALUE_ID_FROM_JSON_FILE = DB.ReadXLData("JIRA_Integration", "ID_OF_STATUS_TODO", "SL_NO", "INT1"); 
		       }
		       else if(CHANGE_STATUS_TO.equalsIgnoreCase("IN PROGRESS"))
		       {
		    	   TOSTATUS_VALUE_ID_FROM_JSON_FILE = DB.ReadXLData("JIRA_Integration", "ID_OF_STATUS_INPROGRESS", "SL_NO", "INT1");
		       }
		       else if(CHANGE_STATUS_TO.equalsIgnoreCase("DONE"))
		       {
		    	   TOSTATUS_VALUE_ID_FROM_JSON_FILE = DB.ReadXLData("JIRA_Integration", "ID_OF_STATUS_DONE", "SL_NO", "INT1");
		       }
		       
				
		      //String st = "{\"transition\": {\"id\": \"11\"}}"; //Change Status to TO DO
		      //String st = "{\"transition\": {\"id\": \"21\"}}"; //Change Status to IN PROGRESS
		       //System.out.println("Value of TOSTATUS_VALUE_ID_FROM_JSON_FILE:Before . Trancation: "+TOSTATUS_VALUE_ID_FROM_JSON_FILE);
		       TOSTATUS_VALUE_ID_FROM_JSON_FILE = TOSTATUS_VALUE_ID_FROM_JSON_FILE.substring(0, TOSTATUS_VALUE_ID_FROM_JSON_FILE.indexOf("."));
		       //System.out.println("Value of TOSTATUS_VALUE_ID_FROM_JSON_FILE:After . Trancation: "+TOSTATUS_VALUE_ID_FROM_JSON_FILE);
		       String st = "{\"transition\": {\"id\": \""+TOSTATUS_VALUE_ID_FROM_JSON_FILE+"\"}}"; //Change Status to DONE
		       
		       System.out.println(st);
		       byte[] outputBytes = st.getBytes("UTF-8");
		       OutputStream os = conn.getOutputStream();
		       os.write(outputBytes);
		    
		    
		       BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		       StringBuilder sb = new StringBuilder();
		       String line;
		       while ((line = rd.readLine()) != null) {
		         sb.append(line);
		       }
		       rd.close();
		    
		       conn.disconnect();
		       System.out.println("sb: "+ sb.toString());
		       return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	public static boolean JIRA_FieldValueChange(String JIRA_ISSUE_KEY,String JIRA_FieldName,String JIRA_FieldValue) throws IOException {
		try{
			
			DB.Connect(DataSetup.Logininfo);
			URL url = new URL(DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1") + "/rest/api/latest/issue/"+JIRA_ISSUE_KEY);
			
			//URL url = new URL("http://localhost:8080/rest/api/latest/issue/TM-1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
	        conn.setDoInput(true);
	         
	        String credentials = "mail2souravm" + ":" + "Lab@r3s2";
	        String encoding = Base64Converter.encode(credentials.getBytes("UTF-8"));
	        conn.setRequestProperty("Authorization", String.format("Basic %s", encoding));
	         
	        conn.setRequestMethod("PUT");
	        conn.setRequestProperty("Content-Type", "application/json");
	         
	        conn.connect();
	        
 
	        String st="{\"fields\":{\""+JIRA_FieldName+"\":\""+JIRA_FieldValue+"\"}}"; //Tested as Working

	        
	        byte[] outputBytes = st.getBytes("UTF-8");
	        OutputStream os = conn.getOutputStream();
	        os.write(outputBytes);
	        os.flush();
	         
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	          sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
		
	}
	public static boolean JIRA_DropdownValueChange(String JIRA_ISSUE_KEY,String JIRA_DropDown_FieldName,String JIRA_DropDown_FieldValue) throws IOException {
		try{
			
			DB.Connect(DataSetup.Logininfo);
			URL url = new URL(DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1") + "/rest/api/latest/issue/"+JIRA_ISSUE_KEY);
			
			//URL url = new URL("http://localhost:8080/rest/api/latest/issue/TM-1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
	        conn.setDoInput(true);
	         
	        String credentials = "mail2souravm" + ":" + "Lab@r3s2";
	        String encoding = Base64Converter.encode(credentials.getBytes("UTF-8"));
	        conn.setRequestProperty("Authorization", String.format("Basic %s", encoding));
	         
	        conn.setRequestMethod("PUT");
	        conn.setRequestProperty("Content-Type", "application/json");
	         
	        conn.connect();
	        
 
	        String st="{\"fields\":{\""+JIRA_DropDown_FieldName+"\":{\"name\":\""+JIRA_DropDown_FieldValue+"\"}}}";  //Tested as working, changing drop down

	        
	        byte[] outputBytes = st.getBytes("UTF-8");
	        OutputStream os = conn.getOutputStream();
	        os.write(outputBytes);
	        os.flush();
	         
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	          sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
		
		
	}
	public static boolean JIRA_AttachExcelLogToJIRAIssue(String JIRA_ISSUE_KEY,String PATH_OF_FILE_TOBE_ATTACHED) throws IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try
		{
		
		DB.Connect(DataSetup.Logininfo);
	    String jira_attachment_baseURL = DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1");
	    String jira_attachment_authentication = new String(org.apache.commons.codec.binary.Base64.encodeBase64((DB.ReadXLData("JIRA_Integration", "JIRA_USERNAME", "SL_NO", "INT1")+":"+DB.ReadXLData("JIRA_Integration", "JIRA_PASSWORD", "SL_NO", "INT1")).getBytes()));
	    HttpPost httppost = new HttpPost(jira_attachment_baseURL+"/rest/api/latest/issue/"+JIRA_ISSUE_KEY+"/attachments");
	    httppost.setHeader("X-Atlassian-Token", "nocheck");
	    httppost.setHeader("Authorization", "Basic "+jira_attachment_authentication);
	     
	    File fileToUpload = new File(PATH_OF_FILE_TOBE_ATTACHED);
	    FileBody fileBody = new FileBody(fileToUpload);
	     
	    HttpEntity entity = MultipartEntityBuilder.create()
	            .addPart("file", fileBody)
	            .build();
	     
	    httppost.setEntity(entity);
	    String mess = "executing request " + httppost.getRequestLine();
	    System.out.println("Logger is:"+mess);
	    
	     
	    CloseableHttpResponse response;
	    response = httpclient.execute(httppost);
	    if(response.getStatusLine().getStatusCode() == 200)
	        return true;
	    else
	        return false;
	    
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
	    {
	        httpclient.close();
	    }
	    
	}
		
	
	public static String httpGet() throws IOException {
	       URL url = new URL("http://localhost:8080/rest/api/latest/issue/TM-4/transitions?expand=transitions.fields");
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	       conn.setDoOutput(true);
	       conn.setDoInput(true);
	        
	       String credentials = "mail2souravm" + ":" + "Lab@r3s2";
	       String encoding = Base64Converter.encode(credentials.getBytes("UTF-8"));
	       conn.setRequestProperty("Authorization", String.format("Basic %s", encoding));
	        
	       conn.setRequestMethod("POST");
	       conn.setRequestProperty("Content-Type", "application/json");
	        
	       conn.connect();
	       
	     
	      //String st = "{\"transition\": {\"id\": \"11\"}}"; //Change Status to TO DO
	      //String st = "{\"transition\": {\"id\": \"21\"}}"; //Change Status to IN PROGRESS
	       String st = "{\"transition\": {\"id\": \"31\"}}"; //Change Status to DONE
	       
	       System.out.println(st);
	       byte[] outputBytes = st.getBytes("UTF-8");
	       OutputStream os = conn.getOutputStream();
	       os.write(outputBytes);
	    
	    
	       BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	       StringBuilder sb = new StringBuilder();
	       String line;
	       while ((line = rd.readLine()) != null) {
	         sb.append(line);
	       }
	       rd.close();
	    
	       conn.disconnect();
	      System.out.println("sb: "+ sb.toString());
	       return sb.toString();
	   }
	
	public static String UpdateIssue() throws IOException 
	{
		try{
			URL url = new URL("http://localhost:8080/rest/api/latest/issue/TM-1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
	        conn.setDoInput(true);
	         
	        String credentials = "mail2souravm" + ":" + "Lab@r3s2";
	        String encoding = Base64Converter.encode(credentials.getBytes("UTF-8"));
	        conn.setRequestProperty("Authorization", String.format("Basic %s", encoding));
	         
	        conn.setRequestMethod("PUT");
	        conn.setRequestProperty("Content-Type", "application/json");
	         
	        conn.connect();
	        
	        //String st="{\"fields\":{\"summary\":\"by sm\"}}"; //Tested as Working 
	        //String st="{\"fields\":{\"description\":\"Unable to update\"}}"; //Tested as Working
	        String st="{\"fields\":{\"priority\":{\"name\":\"High\"}}}";  //Tested as working, changing drop down
	        
	        byte[] outputBytes = st.getBytes("UTF-8");
	        OutputStream os = conn.getOutputStream();
	        os.write(outputBytes);
	        os.flush();
	         
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	          sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        return sb.toString();
		}
		catch(Exception e)
		{
			return "";
		}
		
	}
	
}
