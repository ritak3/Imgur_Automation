
package SOURCE_CODE.SFDC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MemberOfRL {

	String RelatedList,Value,ColumnName;
	Integer RowIndex;
	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String xpath;
	List<WebElement> allposblefieldelements;
	WebElement getsingleWebelement;
	
	MemberOfRL(String RL,String CN)
	{
		RelatedList = RL;
		ColumnName = CN;
		myWD = autoFW.myWD;
		RowIndex = -1;
	}
	MemberOfRL(String RL,String CN,Integer RI)
	{
		RelatedList = RL;
		ColumnName = CN;
		RowIndex = RI;
		myWD = autoFW.myWD;
		
	}
	
	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the hyperlink present against the selected column name from repository and row index of the related list item  
	 */
	public boolean Click() throws Exception
	{
		System.out.println("RelatesList:-->"+RelatedList);
		xpath = "//*[text()='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
		try
		{
		
		String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;

	    Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim();
	    myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).click();
   		 
   		System.out.println("Successfully clicked on the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")");
   		autoFW.AddToXLLogs("Successfully clicked on the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")", "Pass");
   		System.out.println("ColumnName:"+ColumnName+";Value:"+Value+";RelatedList:"+RelatedList);
   		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to click on the element under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to click on the element under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			return false;
			
		}
	}
	
	/**
	 * @author Sourav
	 * @param ExpectedValue
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the related list value against the selected column from repository and selected rowindex of the related list item
	 */
	public boolean VerifyValue(String ExpectedValue) throws Exception
	{
		xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(normalize-space(@class),'Header')]/following-sibling::div[1]/descendant::tr[1]";
		try
		{

		String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (normalize-space(text())='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
   		//myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).click();
   		if (RowIndex == -1)
   		{
   			Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();
   			
   		    for(int i=1;i<=CountOfRLItems;i++)
   		    {
   		    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().contains(ExpectedValue.trim()))
   		    	{
   		    		System.out.println("Successfully verified the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.");
   		    		autoFW.AddToXLLogs("Successfully verified the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.", "Pass");
   		    		return true;
   		    	}
   		    }
   		    System.out.println("Could not find the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.");
    		autoFW.AddToXLLogs("Could not find the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.", "Fail");
    		return false;

   		}
   		else
   		{
   			Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim(); 
   			if (Value.equals(ExpectedValue.trim()))
   	   		{
   	   			System.out.println("Successfully Verified the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")");
   	   			autoFW.AddToXLLogs("Successfully verified the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")", "Pass");
   	   			return true;
   	   		}
   	   		else
   	   		{
   	   			System.out.println("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") in related list.");
   	   			autoFW.AddToXLLogs("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") in related list.", "Fail");
   	   			return false;
   	   		}
   		}
   		
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to verify value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to verify value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			return false;
			
		}
	
	}
	/**
	 * @author Sourav
	 * @param CheckedORNotChecked
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the checkbox value is Checked or Not Checked as per supplied parameter under the selected Related List and Columns from repository 
	 */
	public boolean VerifyCheckBoxValue(String CheckedORNotChecked) throws Exception
	{
		xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(normalize-space(@class),'Header')]/following-sibling::div[1]/descendant::tr[1]";
		try
		{

		String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (normalize-space(text())='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
   		//myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).click();
   		Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::img[normalize-space(@alt) != '']")).getAttribute("alt").trim(); 
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
   			System.out.println("Successfully Verified the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")");
   			autoFW.AddToXLLogs("Successfully verified the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")", "Pass");
   			return true;
   		}
   		else
   		{
   			System.out.println("Actual Value("+Value+") and Expected Value("+CheckedORNotChecked+") does not match under the column ("+ColumnName+") in related list.");
   			autoFW.AddToXLLogs("Actual Value("+Value+") and Expected Value("+CheckedORNotChecked+") does not match under the column ("+ColumnName+") in related list.", "Fail");
   			return false;
   		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to verify value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to verify value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			return false;
			
		}
	
	}

	/**
	 * @author Sourav
	 * @param ExpectedValue
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the displayed value under the selected column of a selected related list from repository is as per supplied parameter "ExpectedValue".
	 */
	public boolean VerifyValueContains(String ExpectedValue) throws Exception
	{
		xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(normalize-space(@class),'Header')]/following-sibling::div[1]/descendant::tr[1]";
		try
		{

		String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (normalize-space(text())='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
   		//myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).click();
	    if (RowIndex == -1)
   		{
   			Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();
   			
   		    for(int i=1;i<=CountOfRLItems;i++)
   		    {
   		    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().contains(ExpectedValue.trim()))
   		    	{
   		    		System.out.println("Successfully verified the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.");
   		    		autoFW.AddToXLLogs("Successfully verified the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.", "Pass");
   		    		return true;
   		    	}
   		    }
   		    System.out.println("Could not find the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.");
    		autoFW.AddToXLLogs("Could not find the value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.", "Fail");
    		return false;

   		}
	    else 
	    {
	    	Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim(); 
	    	if (Value.contains(ExpectedValue.trim()))
	    	{
	    		System.out.println("Successfully Verified the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")");
	    		autoFW.AddToXLLogs("Successfully verified the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")", "Pass");
	    		return true;
	    	}
	    	else
	    	{
	    		System.out.println("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") in related list.");
	    		autoFW.AddToXLLogs("Actual Value("+Value+") and Expected Value("+ExpectedValue+") does not match under the column ("+ColumnName+") in related list.", "Fail");
	    		return false;
	    	}
	    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to verify value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to verify value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			return false;
			
		}
	
	}

	
	/**
	 * @author Sourav
	 * @param ExpectedValue
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the absence of displayed value under the selected column of a selected related list from repository is as per supplied parameter "ExpectedValue".
	 */
	public boolean VerifyValueDoesNotContain(String ExpectedValue) throws Exception
	{
		xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(normalize-space(@class),'Header')]/following-sibling::div[1]/descendant::tr[1]";
		try
		{

		String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (normalize-space(text())='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
   		//myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).click();
	    if (RowIndex == -1)
   		{
   			Integer CountOfRLItems =  myWD.findElements(By.xpath(xpath+"/following-sibling::tr")).size();
   			
   		    for(int i=1;i<=CountOfRLItems;i++)
   		    {
   		    	if (myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+i+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim().contains(ExpectedValue.trim()))
   		    	{
   		    		
   		    		System.out.println("The column ("+ColumnName+") under ("+RelatedList+") related list contains the value ("+ExpectedValue+") when not expected.");
   		    		autoFW.AddToXLLogs("The column ("+ColumnName+") under ("+RelatedList+") related list contains the value ("+ExpectedValue+") when not expected.", "Fail");
   		    		return false;
   		    	}
   		    }
   		    System.out.println("Successfully verified the absence of value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.");
    		autoFW.AddToXLLogs("Successfully verified the absence of value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.", "Pass");
    		return true;

   		}
	    else 
	    {
	    	Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim(); 
	    	if (Value.contains(ExpectedValue.trim()))
	    	{
	    		System.out.println("The column ("+ColumnName+") under ("+RelatedList+") related list contains the value ("+ExpectedValue+") when not expected.");
		    	autoFW.AddToXLLogs("The column ("+ColumnName+") under ("+RelatedList+") related list contains the value ("+ExpectedValue+") when not expected.", "Fail");
		    	return false;
	    	}
	    	else
	    	{
	    		System.out.println("Successfully verified the absence of value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.");
	    		autoFW.AddToXLLogs("Successfully verified the absence of value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.", "Pass");
	    		return true;
	    	}
	    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
    		System.out.println("Successfully verified the absence of value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.");
    		autoFW.AddToXLLogs("Successfully verified the absence of value ("+ExpectedValue+") under the column ("+ColumnName+") in ("+RelatedList+") related list.", "Pass");
    		return true;
			
		}
	
	}

	/**
	 * @author Sourav
	 * @return On Success, Returns the value displayed under the selected column of a selected related list from repository. On Failure it returns blank value.  
	 * @throws Exception
	 * @Description Reads the value displayed under the selected column of a selected related list from repository and returns the same on success. And returns blank value on failure
	 */
	public String GetValue() throws Exception
	{
		xpath = "//*[normalize-space(text())='"+RelatedList+"'][local-name()='h3'][1]/ancestor-or-self::div[contains(@class,'Header')]/following-sibling::div[1]/descendant::tr[1]";
		try
		{
		Value = "";
		String xpath_TargetColumnPosition = xpath + "/descendant::*[(local-name()='th' or local-name()='td') and (text()='"+ColumnName+"')]/preceding-sibling::*[local-name()='th' or local-name()='td']";
	    Integer TargetColumnPosition = myWD.findElements(By.xpath(xpath_TargetColumnPosition)).size() + 1;
   		//myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).click();
   		Value = myWD.findElement(By.xpath(xpath+"/following-sibling::tr["+RowIndex+"]/descendant::*[local-name()='td' or local-name()='th']["+TargetColumnPosition+"]/descendant-or-self::*[text()]")).getText().trim(); 
 
   		System.out.println("Successfully read the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")");
   		autoFW.AddToXLLogs("Successfully read the value ("+Value+") under the column ("+ColumnName+") in related list ("+RelatedList+")", "Pass");
   		return Value;
   		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to read value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath);
			autoFW.AddToXLLogs("Unable to read value under the column ("+ColumnName+") in related list ("+RelatedList+") when xpath is:"+xpath, "Fail");
			return "";
			
		}
	
	}
	
}
