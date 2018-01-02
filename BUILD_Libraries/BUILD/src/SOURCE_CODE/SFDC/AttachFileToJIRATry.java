package SOURCE_CODE.SFDC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import com.thoughtworks.selenium.webdriven.commands.Click;




public class AttachFileToJIRATry {
	
	
	public static void main(String[] args) throws Exception
	{
		addAttachmentToIssue("TM-1","D:/151215/GLOBALFOUNDRIES/TestLogs/30-12-2015/A091_AccountDisengaged_30-12-2015_11.52.28.081_Pass.xls");
	}
	public static boolean addAttachmentToIssue(String issueKey, String fullfilename) throws IOException{
		 
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	     
	    String jira_attachment_baseURL = "http://localhost:8080";
	    String jira_attachment_authentication = new String(org.apache.commons.codec.binary.Base64.encodeBase64(("mail2souravm"+":"+"Lab@r3s2").getBytes()));
	    HttpPost httppost = new HttpPost(jira_attachment_baseURL+"/rest/api/latest/issue/"+issueKey+"/attachments");
	    httppost.setHeader("X-Atlassian-Token", "nocheck");
	    httppost.setHeader("Authorization", "Basic "+jira_attachment_authentication);
	     
	    File fileToUpload = new File(fullfilename);
	    FileBody fileBody = new FileBody(fileToUpload);
	     
	    HttpEntity entity = MultipartEntityBuilder.create()
	            .addPart("file", fileBody)
	            .build();
	     
	    httppost.setEntity(entity);
	    String mess = "executing request " + httppost.getRequestLine();
	    System.out.println("Logger is:"+mess);
	    
	     
	    CloseableHttpResponse response;
	     
	    try {
	        response = httpclient.execute(httppost);
	    } finally {
	        httpclient.close();
	    }
	     
	    if(response.getStatusLine().getStatusCode() == 200)
	        return true;
	    else
	        return false;
	 
	}
	
}
