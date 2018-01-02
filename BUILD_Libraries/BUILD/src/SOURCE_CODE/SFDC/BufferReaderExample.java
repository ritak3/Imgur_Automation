package SOURCE_CODE.SFDC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BufferReaderExample {

	public static void main(String[] args) {
		
		//Tested Successfully for below files
		
		try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfRelatedList.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfRL.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfAllTab.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfButton.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfField.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfLink.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfListView.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfSEC.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfSection.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfTab.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/MemberOfView.java")))
		//try (BufferedReader br = new BufferedReader(new FileReader("D:/WebDriverFWONSFDC/DEMO/src/SOURCE_CODE/SFDC/DB.java")))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.toString().trim().contains("throws Exception") && (sCurrentLine.toString().trim().startsWith("public")))
				{
					System.out.println(sCurrentLine);
					//System.out.println("params are:"+sCurrentLine.substring(sCurrentLine.indexOf("(")+1, sCurrentLine.indexOf(")")).trim());
					String s1 = sCurrentLine.substring(sCurrentLine.indexOf("(")+1, sCurrentLine.indexOf(")")).trim();
					String[] sa1;
					sa1 = s1.split(",");
					//System.out.println(sa1.length);
					
					if(sa1.length<=2)
					{
					for (String retval: sa1){
						if (!retval.equals(""))
						{
				         //System.out.println(retval);
				        
				         System.out.println(retval.trim().substring(retval.trim().indexOf(" ")).trim());
						}
				      }
					}
					else if(sa1.length>2)
					{
						System.out.println("SetCriteria");
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}