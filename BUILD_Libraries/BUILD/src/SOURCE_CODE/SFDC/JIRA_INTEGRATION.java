package SOURCE_CODE.SFDC;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import SOURCE_CODE.SFDC.ZAPI.Status;
import USER_SPACE.TestPrerequisite.DataSetup;

public class JIRA_INTEGRATION {
	
	 /** Status IDs enum */
    public static enum Status {
        PASS(1), FAIL(2), WIP(3), BLOCKED(4), UNEXECUTED(5);
        private final int value;
 
        private Status(final int value) {
            this.value = value;
        }
 
        public int getValue() {
            return value;
        }
    }
    /** URLS */
    
    private static boolean USE_PROXY = false;
    private static String PROXY_IP = "x.x.x.x";
    private static int PROXY_PORT = 8080;
    private static HttpHost HTTP_HOST_PROXY = null;
    private static Proxy PROXY = null;
    private static String CREDENTIALS = "";
       
    
    
    /**
     * Send GET request to the specified URL
     * 
     * @param url
     * @throws IOException
     */
    private static JSONObject httpGetJSONObject(final String url) throws Exception {
        return new JSONObject(httpGetJSONString(url));
    }
    /**
     * Get a string from a url.
     * 
     * @param url
     *            the URL to perform the GET method on
     * @return a String representing the body of the http response
     * @throws IOException
     */
    private static String httpGetJSONString(final String url) throws Exception {
        final HttpURLConnection httpCon = createHttpCon(url, "GET");
        final BufferedReader br =
                new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
 
        final StringBuffer httpResponse = new StringBuffer();
        String line = "";
        while (null != (line = br.readLine())) {
            httpResponse.append(line);
        }
 
        return httpResponse.toString();
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
	 /**
     * Updates the specified test execution
     * 
     * @param executionId
     *            the ID of the execution
     * @param status
     *            a ZAPI.Status value
     * @param comment
     *            a comment for the test execution
     * @throws IOException
     *             put may throw IOException
     */
	public static JSONObject Update_Test_Execution_Status(final String executionId, final Status status, final String comment) throws Exception {
        
		// Construct JSON object
        final JSONObject obj = new JSONObject();
        obj.put("status", String.valueOf(status.getValue()));
        obj.put("comment", comment);
        DB.Connect(DataSetup.Logininfo);
        String ZAPI_URL = DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1") + "/rest/zapi/latest/";
        
        return put(ZAPI_URL + "execution/" + executionId + "/execute", obj);
    }
	private static JSONObject put(final String url, final JSONObject obj) throws Exception {
        return sendRequest(url, obj, "PUT");
    }

    /**
     * Send a request with JSON content with the specified method
     * 
     * @param url
     *            - the URL to send the request to
     * @param obj
     *            - the JSON content to send
     * @param method
     *            - e.g. PUT
     * @throws IOException
     */
    private static JSONObject sendRequest(final String url, final JSONObject obj,
            final String method) throws Exception {
        final HttpURLConnection httpCon = createHttpCon(url, method);
 
        if (null != obj) {
            final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
            out.write(obj.toString());
            out.close();
        }
 
        final BufferedReader rd =
                new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        final StringBuffer result = new StringBuffer();
        String line = "";
        while (null != (line = rd.readLine())) {
            result.append(line);
        }
        return new JSONObject(result.toString());
    }
    /**
     * Return a HttpURLConnection object for the specified URL and request method
     * 
     * @param url
     *            the URL to connect to
     * @param method
     *            - e.g. GET
     */
    private static HttpURLConnection createHttpCon(final String url, final String method) throws IOException {
    	try{
        final HttpURLConnection httpCon;
        
        /** HTTP Proxy details */
        
        DB.Connect(DataSetup.Logininfo);
	    PROXY_IP = DB.ReadXLData("JIRA_Integration", "PROXY_IP", "SL_NO", "INT1"); 
        PROXY_PORT = Integer.parseInt(DB.ReadXLData("JIRA_Integration", "PROXY_PORT", "SL_NO", "INT1").substring(0, DB.ReadXLData("JIRA_Integration", "PROXY_PORT", "SL_NO", "INT1").indexOf("."))); 
        PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_IP,PROXY_PORT));
       
     
        /** JIRA credentials: format "username:password" or "" for none. */
        CREDENTIALS = DB.ReadXLData("JIRA_Integration", "JIRA_USERNAME", "SL_NO", "INT1")+":"+DB.ReadXLData("JIRA_Integration", "JIRA_PASSWORD", "SL_NO", "INT1");
        
        System.out.println("PROXY_IP"+PROXY_IP);
        System.out.println("PROXY_PORT"+PROXY_PORT);
        System.out.println("PROXY"+PROXY);
        System.out.println("CREDENTIALS"+CREDENTIALS);
        if (USE_PROXY) {
            httpCon = (HttpURLConnection) new URL(url).openConnection(PROXY);
        } else {
            httpCon = (HttpURLConnection) new URL(url).openConnection();
        }
 
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(method);
 
        if (!CREDENTIALS.isEmpty()) {
            final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
            httpCon.setRequestProperty("Authorization", "Basic " + encoding);
        }
 
        httpCon.setRequestProperty("Content-type", "application/json");
 
        return httpCon;
        
    }catch(Exception e)
    {
    	e.printStackTrace();
    	return null;
    }
    }
    
    /**
     * Deletes all of the attachments on the specified execution
     * 
     * @param executionId
     *            the id of the execution
     * @throws IOException
     *             delete may throw IOException
     */
    public static List<JSONObject>  Delete_All_Attachment_From_JIRA_TestExecution(final String ExecutionID) throws Exception {
        final ArrayList<String> fileIds = new ArrayList<String>();
        DB.Connect(DataSetup.Logininfo);
        String ZAPI_URL = DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1") + "/rest/zapi/latest/";
        // Note the IDs for the files currently attached to the execution
        final JSONObject obj =
                httpGetJSONObject(ZAPI_URL + "attachment/attachmentsByEntity?entityId="
                        + ExecutionID + "&entityType=EXECUTION");
        if (null == obj) {
            throw new IllegalStateException("Response is null");
        }
 
        final JSONArray data = (JSONArray) obj.get("data");
        for (int i = 0; i < data.length(); i++) {
            final JSONObject fileData = data.getJSONObject(i);
            fileIds.add(fileData.getString("fileId"));
        }
 
        // Iterate over attachments
        final ArrayList<JSONObject> responses = new ArrayList<JSONObject>(data.length());
        for (final String fileId : fileIds) {
            responses.add(delete(ZAPI_URL + "attachment/" + fileId));
        }
        return responses;
    }
    /**
     * Send DELETE request to the specified URL
     * 
     * @param url
     *            - the URL to send the request to
     * @throws IOException
     */
    private static JSONObject delete(final String url) throws Exception {
        return sendRequest(url, null, "DELETE");
    }
 
    
    /**
     * Adds attachment to an execution.
     * 
     * @param fileToUpload
     *            - the file to attach
     * @param executionId
     * @throws RuntimeException
     * @throws IOException
     */
    public static void Add_Attachment_To_JIRA_TestExecution(final File FileToUpload, final String ExecutionID) throws RuntimeException, IOException
    {
        try{
    	// set up proxy for http client
        
        DB.Connect(DataSetup.Logininfo);
	    PROXY_IP = DB.ReadXLData("JIRA_Integration", "PROXY_IP", "SL_NO", "INT1"); 
        PROXY_PORT = Integer.parseInt(DB.ReadXLData("JIRA_Integration", "PROXY_PORT", "SL_NO", "INT1").substring(0, DB.ReadXLData("JIRA_Integration", "PROXY_PORT", "SL_NO", "INT1").indexOf("."))); 
        PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_IP,PROXY_PORT));
        HTTP_HOST_PROXY = new HttpHost(PROXY_IP, PROXY_PORT);
        /** JIRA credentials: format "username:password" or "" for none. */
        CREDENTIALS = DB.ReadXLData("JIRA_Integration", "JIRA_USERNAME", "SL_NO", "INT1")+":"+DB.ReadXLData("JIRA_Integration", "JIRA_PASSWORD", "SL_NO", "INT1");
        	
        System.out.println("PROXY_IP:"+PROXY_IP);
        System.out.println("PROXY_PORT"+PROXY_PORT);
        System.out.println("PROXY"+PROXY);
        System.out.println("HTTP_HOST_PROXY"+HTTP_HOST_PROXY);
        System.out.println(USE_PROXY);
        
        final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.useSystemProperties();
        if (USE_PROXY) {
            clientBuilder.setProxy(HTTP_HOST_PROXY);
        }
        final CloseableHttpClient httpClient = clientBuilder.build();
        DB.Connect(DataSetup.Logininfo);
        String ZAPI_URL = DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1") + "/rest/zapi/latest/";
        System.out.println("ZAPI_URL:"+ZAPI_URL);
        final HttpPost httpPost = new HttpPost(ZAPI_URL + "attachment?entityId=" + ExecutionID + "&entityType=EXECUTION");
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
 
        if (!CREDENTIALS.isEmpty()) {
            final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
            httpPost.setHeader("Authorization", "Basic " + encoding);
        }
 
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", FileToUpload, ContentType.APPLICATION_OCTET_STREAM,
        		FileToUpload.getName());
        final HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
 
        final CloseableHttpResponse response = httpClient.execute(httpPost);
        final HttpEntity responseEntity = response.getEntity();
        if (null != responseEntity) {
            EntityUtils.consume(responseEntity);
        }
 
        // ensure file was uploaded correctly
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Error uploading file");
        }
        }catch(Exception e){e.printStackTrace();}
    }

	public static void main(String[] args) throws Exception
	{
		//System.out.println(getVersionID("2","TM"));
		//Update_Test_Execution_Status("8", Status.WIP, "executed by automation"); //Working
		//Add_Attachment_To_JIRA_TestExecution(new File("D:/151215/GLOBALFOUNDRIES/TestLogs/30-12-2015/A091_AccountDisengaged_30-12-2015_11.52.28.081_Pass.xls"),"8"); //Working
		Delete_All_Attachment_From_JIRA_TestExecution("8");
		
	}
    
}
