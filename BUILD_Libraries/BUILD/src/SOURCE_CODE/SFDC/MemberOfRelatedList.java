
package SOURCE_CODE.SFDC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Cognizant
 *
 */
/**
 * @author Cognizant
 *
 */
/**
 * @author Cognizant
 *
 */
public class MemberOfRelatedList {

	String RelatedList,Value;
	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String xpath;
	List<WebElement> allposblefieldelements;
	WebElement getsingleWebelement;
	
	MemberOfRelatedList(String RL)
	{
		RelatedList = RL;
		myWD = autoFW.myWD;
		
	}
	
	/**
	 * @author Sourav
	 * @param TargetColumn
	 * @param KeyColumn
	 * @param KeyColumnValue
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the displayed hyperlink value in the related list present under a "TargetColumn" where "KeyColumn" = "KeyColumnValue"
	 */
	public boolean Click(String TargetColumn,String KeyColumn,String KeyColumnValue) throws Exception
	{
				
		try
		{
		String xpath_KeyColumnPosition ="",xpath_TargetColumnPosition="";
		
		System.out.println("TagName is: "+myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName());
		
		if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
		{
			xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		    xpath_TargetColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		}
		else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
		{
			
			//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath_KeyColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+KeyColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+TargetColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
		}	

		System.out.println("xpath is: "+xpath);	
		System.out.println("xpath_KeyColumnPosition is: "+xpath_KeyColumnPosition);
		System.out.println("xpath_TargetColumnPosition is: "+xpath_TargetColumnPosition);
		
		
		Integer KeyColumnPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1; 
		
		//System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		
		Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();  
		//System.out.println("CountOfRLItems:"+CountOfRLItems);
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
	    //System.out.println("TargetColumnPosition:"+TargetColumnPosition);
	    
	    System.out.println("KeyColumnPosition"+KeyColumnPosition +" CountOfRLItems:"+CountOfRLItems +" TargetColumnPosition:"+TargetColumnPosition);
	    Integer RowPositionOfKeyColumnValue;
	    
	    for(int i=1;i<=CountOfRLItems;i++)
	    {
	    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().equals(KeyColumnValue))
	    	{
	    		
	    		System.out.println("Match KeyColumnValue:"+KeyColumnValue);
	    		RowPositionOfKeyColumnValue = myWD.findElements(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]/ancestor::tr[1]/preceding-sibling::tr")).size() + 1;

	    		System.out.println("RowPositionOfKeyColumnValue:"+RowPositionOfKeyColumnValue);
	    		//System.out.println("tagname of target element:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getTagName());
	    		myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).click();

	    		System.out.println("xpah of clicking item:"+xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]");
	    		
	    		System.out.println("Successfully clicked on ("+TargetColumn+") for related list item ("+KeyColumnValue+") in the ("+RelatedList+")related list");
	    		autoFW.AddToXLLogs("Successfully clicked on ("+TargetColumn+") for related list item ("+KeyColumnValue+") in the ("+RelatedList+")related list", "Pass");
	    		return true;
	    		//RowPositionOfKeyColumnValue = 
	    	}
	    	System.out.println("No Match");
	    }
	    
	   /* 
	    Integer KeyColumnValueRowPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1;
		System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		System.out.println("CountOfRLItems"+CountOfRLItems);
		*/
		
		System.out.println("Unable to click on the element in the ("+RelatedList+") related list.");
		autoFW.AddToXLLogs("Unable to click on the element in the ("+RelatedList+") related list.", "Fail");
		return false;
		}
		catch(Exception e)
		{
			System.out.println("Unable to click on the element in the ("+RelatedList+") related list when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to click on the element in the ("+RelatedList+") related list when xpath is:"+xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @author Sourav
	 * @param TargetColumn
	 * @param TargetColumnValue
	 * @param KeyColumn
	 * @param KeyColumnValue
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the displayed hyperlink value(say Del/Edit) specified in TargetcolumnValue parameter in the related list present under a "TargetColumn" where "KeyColumn" = "KeyColumnValue"
	 */
	public boolean Click(String TargetColumnValue,String TargetColumn,String KeyColumn,String KeyColumnValue) throws Exception
	{
				
		try
		{
		String xpath_KeyColumnPosition ="",xpath_TargetColumnPosition="";
		
		//System.out.println("TagName is: "+myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName());
		
		if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
		{
			xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		    xpath_TargetColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		}
		else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
		{
			
			//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath_KeyColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+KeyColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+TargetColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
		}	
		
		/*
		System.out.println("xpath is: "+xpath);	
		System.out.println("xpath_KeyColumnPosition is: "+xpath_KeyColumnPosition);
		System.out.println("xpath_TargetColumnPosition is: "+xpath_TargetColumnPosition);
		*/
		
		Integer KeyColumnPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1; 
		
		//System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		
		Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();  
		//System.out.println("CountOfRLItems:"+CountOfRLItems);
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
	    //System.out.println("TargetColumnPosition:"+TargetColumnPosition);
	    
	    //System.out.println("KeyColumnPosition"+KeyColumnPosition +" CountOfRLItems:"+CountOfRLItems +" TargetColumnPosition:"+TargetColumnPosition);
	    Integer RowPositionOfKeyColumnValue;
	    
	    for(int i=1;i<=CountOfRLItems;i++)
	    {
	    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().equals(KeyColumnValue))
	    	{
	    		
	    		//System.out.println("Match KeyColumnValue:"+KeyColumnValue);
	    		RowPositionOfKeyColumnValue = myWD.findElements(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]/ancestor::tr[1]/preceding-sibling::tr")).size() + 1;

	    		//System.out.println("RowPositionOfKeyColumnValue:"+RowPositionOfKeyColumnValue);
	    		//System.out.println("tagname of target element:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getTagName());
	    		myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[contains(normalize-space(text()),'"+TargetColumnValue+"')]")).click();

	    		//System.out.println("xpah of clicking item:"+xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]");
	    		
	    		System.out.println("Successfully clicked on ("+TargetColumnValue+") under ("+TargetColumn+") column for related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list");
	    		autoFW.AddToXLLogs("Successfully clicked on ("+TargetColumnValue+") under ("+TargetColumn+") column for related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list", "Pass");
	    		return true;
	    		//RowPositionOfKeyColumnValue = 
	    	}
	    	System.out.println("No Match");
	    }
	    
	   /* 
	    Integer KeyColumnValueRowPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1;
		System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		System.out.println("CountOfRLItems"+CountOfRLItems);
		*/
		
		System.out.println("Unable to click on the element in the ("+RelatedList+") related list.");
		autoFW.AddToXLLogs("Unable to click on the element in the ("+RelatedList+") related list.", "Fail");
		return false;
		}
		catch(Exception e)
		{
			System.out.println("Unable to click on the element in the ("+RelatedList+") related list when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to click on the element in the ("+RelatedList+") related list when xpath is:"+xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	/**
	 * @author Sourav
	 * @param TargetColumn
	 * @param ExpectedValueInTargetColumn
	 * @param KeyColumn
	 * @param KeyColumnValue
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the Related List Value under the "TargetColumn" is equal to "ExpectedValueInTargetColumn" where "KeyColumn" = "KeyColumnValue".
	 */
	public boolean VerifyValue(String TargetColumn,String ExpectedValueInTargetColumn,String KeyColumn,String KeyColumnValue) throws Exception
	{
		
	try
	{
		
		//xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
		//String xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		//String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		String xpath_KeyColumnPosition ="",xpath_TargetColumnPosition="";
		
		//System.out.println("TagName is: "+myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName());
		
		if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
		{
			xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		    xpath_TargetColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		}
		else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
		{
			
			//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath_KeyColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+KeyColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+TargetColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
		}	

		//System.out.println("xpath is: "+xpath);	
		//System.out.println("xpath_KeyColumnPosition is: "+xpath_KeyColumnPosition);
		//System.out.println("xpath_TargetColumnPosition is: "+xpath_TargetColumnPosition);
		
		
		
		Integer KeyColumnPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1; 
		//System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();  
		//System.out.println("CountOfRLItems:"+CountOfRLItems);
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
	    //System.out.println("TargetColumnPosition:"+TargetColumnPosition);
	    Integer RowPositionOfKeyColumnValue;
	    for(int i=1;i<=CountOfRLItems;i++)
	    {
	    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().equals(KeyColumnValue))
	    	{
	    		RowPositionOfKeyColumnValue = myWD.findElements(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]/ancestor::tr[1]/preceding-sibling::tr")).size() + 1;
	    		//System.out.println("RowPositionOfKeyColumnValue:"+RowPositionOfKeyColumnValue);
	    		//System.out.println("tagname of target element:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getTagName());
	    		if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().equals(ExpectedValueInTargetColumn.trim()))
	    		{
	    			System.out.println("Successfully verified the value ("+ExpectedValueInTargetColumn+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.");
	    			autoFW.AddToXLLogs("Successfully verified the value ("+ExpectedValueInTargetColumn+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.", "Pass");
	    			return true;
	    		}
	    		else
	    		{
	    			System.out.println("The expected value does not match for the related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list.Actual Value is:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim());
	    			autoFW.AddToXLLogs("The expected value does not match for the related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list.Actual Value is:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim(), "Fail");
	    			return false;
	    		}
	    			//RowPositionOfKeyColumnValue = 
	    	}
	    }
	    
	   /* 
	    Integer KeyColumnValueRowPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1;
		System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		System.out.println("CountOfRLItems"+CountOfRLItems);
		*/
		
		System.out.println("Unable to verify the element in the ("+RelatedList+")related list.");
		autoFW.AddToXLLogs("Unable to verify the element in the ("+RelatedList+")related list.", "Fail");
		return false;
	}
	catch(Exception e)
	{
		System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
		autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
		e.printStackTrace();
		return false;
	}
	}
	
	/**
	 * @author Sourav
	 * @param TargetColumn
	 * @param ExpectedValueInTargetColumn
	 * @param KeyColumn
	 * @param KeyColumnValue
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the Related List Value under the "TargetColumn" contains "ExpectedValueInTargetColumn" where "KeyColumn" = "KeyColumnValue".
	 */
	public boolean VerifyValueContains(String TargetColumn,String ExpectedValueInTargetColumn,String KeyColumn,String KeyColumnValue) throws Exception
	{
		
	try
	{
		
		//xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
		//String xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		//String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		
		String xpath_KeyColumnPosition ="",xpath_TargetColumnPosition="";
		
		System.out.println("TagName is: "+myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName());
		
		if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
		{
			xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		    xpath_TargetColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		}
		else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
		{
			
			//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath_KeyColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+KeyColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+TargetColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
		}	

		System.out.println("xpath is: "+xpath);	
		System.out.println("xpath_KeyColumnPosition is: "+xpath_KeyColumnPosition);
		System.out.println("xpath_TargetColumnPosition is: "+xpath_TargetColumnPosition);
				
		
		Integer KeyColumnPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1; 
		//System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();  
		//System.out.println("CountOfRLItems:"+CountOfRLItems);
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
	    //System.out.println("TargetColumnPosition:"+TargetColumnPosition);
	    Integer RowPositionOfKeyColumnValue;
	    for(int i=1;i<=CountOfRLItems;i++)
	    {
	    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().equals(KeyColumnValue))
	    	{
	    		RowPositionOfKeyColumnValue = myWD.findElements(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]/ancestor::tr[1]/preceding-sibling::tr")).size() + 1;
	    		//System.out.println("RowPositionOfKeyColumnValue:"+RowPositionOfKeyColumnValue);
	    		//System.out.println("tagname of target element:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getTagName());
	    		if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().contains(ExpectedValueInTargetColumn.trim()))
	    		{
	    			System.out.println("Successfully verified the value ("+ExpectedValueInTargetColumn+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.");
	    			autoFW.AddToXLLogs("Successfully verified the value ("+ExpectedValueInTargetColumn+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.", "Pass");
	    			return true;
	    		}
	    		else
	    		{
	    			System.out.println("The expected value does not match for the related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list.Actual Value is:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim());
	    			autoFW.AddToXLLogs("The expected value does not match for the related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list.Actual Value is:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim(), "Fail");
	    			return false;
	    		}
	    			//RowPositionOfKeyColumnValue = 
	    	}
	    }
	    
	   /* 
	    Integer KeyColumnValueRowPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1;
		System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		System.out.println("CountOfRLItems"+CountOfRLItems);
		*/
		
		System.out.println("Unable to verify the element in the ("+RelatedList+")related list.");
		autoFW.AddToXLLogs("Unable to verify the element in the ("+RelatedList+")related list.", "Fail");
		return false;
	}
	catch(Exception e)
	{
		System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
		autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
		e.printStackTrace();
		return false;
	}
	}
	
	/**
	 * @author Sourav
	 * @param TargetColumn
	 * @param CheckedORNotChecked
	 * @param KeyColumn
	 * @param KeyColumnValue
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the check box value in the related list under the "TargetColumn" is "CheckedORNotChecked" where "KeyColumn" = "KeyColumnValue".
	 */
	public boolean VerifyCheckBoxValue(String TargetColumn,String CheckedORNotChecked,String KeyColumn,String KeyColumnValue) throws Exception
	{
		
	try
	{	
		//xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
		//String xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		//String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";

		String xpath_KeyColumnPosition ="",xpath_TargetColumnPosition="";
		
		System.out.println("TagName is: "+myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName());
		
		if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
		{
			xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		    xpath_TargetColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		}
		else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
		{
			
			//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath_KeyColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+KeyColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+TargetColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
		}	

		System.out.println("xpath is: "+xpath);	
		System.out.println("xpath_KeyColumnPosition is: "+xpath_KeyColumnPosition);
		System.out.println("xpath_TargetColumnPosition is: "+xpath_TargetColumnPosition);
					
		
		Integer KeyColumnPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1; 
		//System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();  
		//System.out.println("CountOfRLItems:"+CountOfRLItems);
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
	    //System.out.println("TargetColumnPosition:"+TargetColumnPosition);
	    Integer RowPositionOfKeyColumnValue;
	    for(int i=1;i<=CountOfRLItems;i++)
	    {
	    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().equals(KeyColumnValue))
	    	{
	    		RowPositionOfKeyColumnValue = myWD.findElements(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]/ancestor::tr[1]/preceding-sibling::tr")).size() + 1;
	    		//System.out.println("RowPositionOfKeyColumnValue:"+RowPositionOfKeyColumnValue);
	    		//System.out.println("tagname of target element:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getTagName());
	    		String Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::img[normalize-space(@alt) != '']")).getAttribute("alt").trim();
	    		if (CheckedORNotChecked.contains("Not") || CheckedORNotChecked.contains("not")) 
	       		{
	       			CheckedORNotChecked = "Not Checked";
	       		}
	       		if (Value.contains("Not") || Value.contains("not")) 
	       		{
	       			Value = "Not Checked";
	       		}
	    		
	    		if (Value.equalsIgnoreCase(CheckedORNotChecked.trim()))
	    		{
	    			System.out.println("Successfully verified the value ("+CheckedORNotChecked+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.");
	    			autoFW.AddToXLLogs("Successfully verified the value ("+CheckedORNotChecked+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.", "Pass");
	    			return true;
	    		}
	    		else
	    		{
	    			System.out.println("The expected value does not match for the related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list.Actual Value is:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim());
	    			autoFW.AddToXLLogs("The expected value does not match for the related list item ("+KeyColumnValue+") in the ("+RelatedList+") related list.Actual Value is:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim(), "Fail");
	    			return false;
	    		}
	    			//RowPositionOfKeyColumnValue = 
	    	}
	    }
	    
	   /* 
	    Integer KeyColumnValueRowPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1;
		System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		System.out.println("CountOfRLItems"+CountOfRLItems);
		*/
		
		System.out.println("Unable to verify the element in the ("+RelatedList+")related list.");
		autoFW.AddToXLLogs("Unable to verify the element in the ("+RelatedList+")related list.", "Fail");
		return false;
	}
	catch(Exception e)
	{
		System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
		autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
		e.printStackTrace();
		return false;
	}
	}
	
	/**
	 * @author Sourav
	 * @param TargetColumn
	 * @param KeyColumn
	 * @param KeyColumnValue
	 * @return Returns the value under a related list column "TargetColumn" on success, returns blank value on failure
	 * @throws Exception
	 * @Description Reads the value present in a related list undet the column "TargetColumn" where "KeyColumn" = "KeyColumnValue" and returns the same value
	 *
	 */
	public String GetValue(String TargetColumn,String KeyColumn,String KeyColumnValue) throws Exception
	{
		
	try
	{	
		//xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
		//String xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		//String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		
		String xpath_KeyColumnPosition ="",xpath_TargetColumnPosition="";
		System.out.println("TagName is: "+myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName());
		
		if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
		{
			xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			xpath_KeyColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+KeyColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		    xpath_TargetColumnPosition = xpath +"/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+TargetColumn+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
		}
		else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
		{
			
			//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
			xpath_KeyColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+KeyColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+TargetColumn+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
		}	

		System.out.println("xpath is: "+xpath);	
		System.out.println("xpath_KeyColumnPosition is: "+xpath_KeyColumnPosition);
		System.out.println("xpath_TargetColumnPosition is: "+xpath_TargetColumnPosition);
				

		
		
		
		Integer KeyColumnPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1; 
		//System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();  
		//System.out.println("CountOfRLItems:"+CountOfRLItems);
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
	    //System.out.println("TargetColumnPosition:"+TargetColumnPosition);
	    Integer RowPositionOfKeyColumnValue;
	    for(int i=1;i<=CountOfRLItems;i++)
	    {
	    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().equals(KeyColumnValue))
	    	{
	    		RowPositionOfKeyColumnValue = myWD.findElements(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+KeyColumnPosition+"]/descendant-or-self::*[text()]/ancestor::tr[1]/preceding-sibling::tr")).size() + 1;
	    		//System.out.println("RowPositionOfKeyColumnValue:"+RowPositionOfKeyColumnValue);
	    		//System.out.println("tagname of target element:"+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getTagName());
	    		if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).isDisplayed())
	    		{
	    			
	    			System.out.println("The value ("+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim()+") gets displayed under the column ("+TargetColumn+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.");
	    			autoFW.AddToXLLogs("The value ("+myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim()+") gets displayed under the column ("+TargetColumn+") for related list item ("+KeyColumnValue+") in ("+RelatedList+") related list.", "Pass");
	    			return (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+(RowPositionOfKeyColumnValue-1)+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim());
	    		}
	    		
	    	}
	    }
	    
	   /* 
	    Integer KeyColumnValueRowPosition = myWD.findElements(By.xpath(xpath_KeyColumnPosition)).size() + 1;
		System.out.println("KeyColumnPosition:"+KeyColumnPosition);
		System.out.println("CountOfRLItems"+CountOfRLItems);
		*/
		
		System.out.println("Unable to read the value under the column ("+TargetColumn+") from ("+RelatedList+") related list.");
		autoFW.AddToXLLogs("Unable to read the value under the column ("+TargetColumn+") from ("+RelatedList+") related list.", "Fail");
		return "";
	}
	catch(Exception e)
	{
		System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
		autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
		e.printStackTrace();
		return "";
	}
	}
	
	/**
	 * @author Sourav
	 * @param RowIndex starts from 1
	 * @param ColumnName
	 * @return On Success, Returns the value under a column "ColumnName" where Related list item positioned at supplied RowIndex. On failure returns blank value
	 * @throws Exception
	 * @Description Reads the value under a specified column "ColumnName" in specified "RowIndex" in a related list and returns the value on Success, returns blank value on failure 
	 */
	public String GetValueByRowIndex(Integer RowIndex,String ColumnName) throws Exception
	{
		
		
		try
		{
			//xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			//String xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
			String xpath_ColumnName="";
			if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
			{
				xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
				xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
			    
			}
			else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
			{
				
				//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
				xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
				xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+ColumnName+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			}	
			
		    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_ColumnName)).size() + 1;
		    Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim();			
		    System.out.println("The value under column ("+ColumnName+") in related list item position ("+RowIndex+") is ("+Value+")");
   			autoFW.AddToXLLogs("The value under column ("+ColumnName+") in related list item position ("+RowIndex+") is ("+Value+")", "Pass");
		    return (Value);
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			e.printStackTrace();
			return "";
		}	
	}
	
	
	
	/**
	 * @author Sourav
	 * @param ExpectedValue
	 * @param RowIndex
	 * @param ColumnName
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the value under a column "ColumnName" of a related list is equal to "ExpectedValue" where related list item index is equal to "RowIndex"
	 */
	public boolean VerifyValueByRowIndex(String ExpectedValue,Integer RowIndex,String ColumnName) throws Exception
	{
		
		
		try
		{
			//xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			//String xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";

			String xpath_ColumnName="";
			if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
			{
				xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
				xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
			    
			}
			else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
			{
				
				//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
				xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
				xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+ColumnName+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			}
			
		    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_ColumnName)).size() + 1;
		    Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim();			
		    if (Value.trim().equals(ExpectedValue.trim()))
		    {
		    	System.out.println("Successfully verified the value ("+Value+") under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+").");
		   		autoFW.AddToXLLogs("Successfully verified the value ("+Value+") under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+").", "Pass");
		   		return true;
		    }
		    else
		    {
		    	System.out.println("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+")");
		    	autoFW.AddToXLLogs("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+")", "Fail");
		    	return false;
		    }
		   
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			e.printStackTrace();
			return false;
			
		}	
	}

	
	/**
	 * @author Sourav
	 * @param ExpectedValue
	 * @param RowIndex
	 * @param ColumnName
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the value under a column "ColumnName" is equal to "ExpectedValue" for the Line Item index "RowIndex"
	 */
	public boolean VerifyValueContainsByRowIndex(String ExpectedValue,Integer RowIndex,String ColumnName) throws Exception
	{
		
		
		try
		{
			//xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
			//String xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";

			String xpath_ColumnName="";
			if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
			{
				xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
				xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
			    
			}
			else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
			{
				
				//xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[contains(normalize-space(@class),'listRelatedObject')][2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
				xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
				xpath_ColumnName = xpath + "/descendant::*[(local-name()='th' or local-name()='td' or local-name()='a') and (normalize-space(text())='"+ColumnName+"')]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/preceding-sibling::*[local-name()='td' or local-name()='th']";
			}

			Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_ColumnName)).size() + 1;
		    Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim();			
		    if (Value.trim().contains(ExpectedValue.trim()))
		    {
		    	System.out.println("Successfully verified the value ("+Value+") under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+").");
		   		autoFW.AddToXLLogs("Successfully verified the value ("+Value+") under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+").", "Pass");
		   		return true;
		    }
		    else
		    {
		    	System.out.println("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+")");
		    	autoFW.AddToXLLogs("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") and RowIndex ("+RowIndex+") in related list ("+RelatedList+")", "Fail");
		    	return false;
		    }
		   
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			e.printStackTrace();
			return true;
		}	
	}
	
	
	/**
	 * @author Sourav
	 * @param YesORNo
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the supplied related list is displayed in the UI or not as per parameter passed in YesORNo
	 */
	public boolean IsDisplayed(String YesORNo) throws Exception
	{
		xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3'][1]";
		//System.out.println();
		
		if (YesORNo.equalsIgnoreCase("Yes"))
		{
			try
			{
			if((myWD.findElement(By.xpath(xpath)).isDisplayed()))
			{
				System.out.println("Successfully verified the existence of the related list ("+RelatedList+").");
				autoFW.AddToXLLogs("Successfully verified the existence of the related list ("+RelatedList+").", "Pass");
				return true;	
			}
			else
			{
				System.out.println("Unable to find the related list ("+RelatedList+") in the application.");
				autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") in the application.", "Fail");
				return false;
			}
			}catch(Exception e)
			{
				System.out.println("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath);
				autoFW.AddToXLLogs("Unable to find the related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
				e.printStackTrace();
				return false;
			}
		}
		else if (YesORNo.equalsIgnoreCase("No"))
		{
			try
			{
			if((myWD.findElement(By.xpath(xpath)).isDisplayed()))
			{
				System.out.println("The related list ("+RelatedList+") is displayed in the UI when it is not expected to be.");
				autoFW.AddToXLLogs("The related list ("+RelatedList+") is displayed in the UI when it is not expected to be.", "Fail");
				return false;
			}
			else
			{
				System.out.println("Successfully verified that the related list ("+RelatedList+") is not displayed in the UI.");
				autoFW.AddToXLLogs("Successfully verified that the related list ("+RelatedList+") is not displayed in the UI.", "Pass");
				return true;
			}
			}catch(Exception e2)
			{
				System.out.println("Successfully verified that the related list ("+RelatedList+") is not displayed in the UI.");
				autoFW.AddToXLLogs("Successfully verified that the related list ("+RelatedList+") is not displayed in the UI.", "Pass");
				//e2.printStackTrace();
				return true;
			}
		}
		else
		{
			System.out.println("The input parameter supplied in IsDisplayed() function is not correct. It should be either Yes or No");
			autoFW.AddToXLLogs("The input parameter supplied in IsDisplayed() function is not correct. It should be either Yes or No", "Fail");
			return false;
		}
						
	}
	
	
	
	/**
	 * @author Cognizant
	 * @Description Calculates the total number of related list item
	 * @return the total number of related list item
	 * @throws Exception
	 */
	public int CalculateCountofRelatedList() throws Exception
	{
		int numberofrelatedkistitem = 0;
		
		try
		{
			if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h3"))
			{
				xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
				numberofrelatedkistitem = myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();
			}
			else if (myWD.findElement(By.xpath("//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3' or local-name()='h1'][1]")).getTagName().equalsIgnoreCase("h1"))
			{
				xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h1'][1]/ancestor-or-self::div[contains(normalize-space(@class),'PageTitle')][1]/following-sibling::div[2]/descendant::div[contains(normalize-space(@class),'pbBody')][1]/table/tbody/tr[contains(normalize-space(@class),'headerRow')]";
				numberofrelatedkistitem = myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();
			}
			System.out.println("The count of related list item for the Related List ("+RelatedList+") is ("+numberofrelatedkistitem+")");
			autoFW.AddToXLLogs("The count of related list item for the Related List ("+RelatedList+") is ("+numberofrelatedkistitem+")", "Pass");
			return numberofrelatedkistitem;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to retrive the number of related list item");
			autoFW.AddToXLLogs("Unable to retrive the number of related list item", "Fail");			
			return numberofrelatedkistitem;
		}				
	}
}
