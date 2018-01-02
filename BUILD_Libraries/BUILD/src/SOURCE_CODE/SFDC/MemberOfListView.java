package SOURCE_CODE.SFDC;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MemberOfListView {

	String ColumnName;
	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String xpath;
	List<WebElement> allposblefieldelements;
	WebElement getsingleWebelement;
	
	MemberOfListView(String CN)
	{
		ColumnName = CN;
		myWD = autoFW.myWD;
		
	}
	
	/**********************************************************************/
	// This function clicks on the value having hyperlink under a 		  //
	// particular column from the list view. If the vlaue is not available//
	// in the first list it navigates till the end of the list			  //
	//																	  //
	/**********************************************************************/
	
	/**
	 * @author Sourav
	 * @param ValueUnderColumn
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the hyperlink displayed under a column of SFDC OOB list Views. For example, All Accounts, Todays Leads so on.
	 */
	public boolean Click(String ValueUnderColumn) throws Exception
	{
		try{
			
			System.out.println("The ValueUnderColumn"+ValueUnderColumn);
			Integer position = (myWD.findElements(By.xpath("//div[contains(@class,'-viewport')]/descendant::tr[1]/descendant::*[text()='"+ColumnName+"']/ancestor::td[1]/preceding-sibling::td")).size()) + 1;        
	        
			System.out.println("Position is:"+position);
	       
	        //Getting total displayed row count
	        Integer RecordCount = myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/following-sibling::div")).size() + 1;
	        System.out.println("RecordCount is:"+RecordCount);
	        
	        for(int i=1;i<=RecordCount;i++)
	        {
	        	//List<WebElement> we= myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/../div["+i+"]/descendant::tr[1]/descendant::td["+position+"]/descendant::*[contains(text(),'"+ValueUnderColumn+"')]"));
	        	List<WebElement> we= myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/../div["+i+"]/descendant::tr[1]/descendant::td["+position+"]/descendant-or-self::*[text()]"));	        	
	        	for(WebElement e:we)
	        	{
	        		if(e.getText().trim().equals(ValueUnderColumn))
	        		{
	        			System.out.println(e.getText());
	        			e.click();
	        			i = RecordCount;
	        			System.out.println("Successfully clicked on the value ("+ValueUnderColumn+") from the list view.");
	        			autoFW.AddToXLLogs("Successfully clicked on the value ("+ValueUnderColumn+") from the list view.", "Pass");
	        			return true;
	        		}
	        		//System.out.println("inside for loop"+e.getText());
	        	}
	        	
	        	if(i==RecordCount)
	        	{
	        		if(myWD.findElement(By.xpath("//img[@alt='Next']")).getAttribute("class").trim().equalsIgnoreCase("next"))
	        		{
	        			myWD.findElement(By.xpath("//img[@alt='Next']")).click();
	        			i = 1;
	        			Thread.sleep(4000L);
	        			RecordCount = myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/following-sibling::div")).size() + 1;
	        			System.out.println("The recordcount is:"+RecordCount);
	        		}
	        		else
	        		{
	        			System.out.println("Unable to click on ("+ValueUnderColumn+") from the available records in list view.");
		        		autoFW.AddToXLLogs("Unable to click on ("+ValueUnderColumn+") from the available records in list view.", "Fail");
	        			return false;	
	        		}
	        		
	        	}
	        		
	        	
	        }
	        System.out.println("Unable to click on ("+ValueUnderColumn+") from the available records in list view.");
	        autoFW.AddToXLLogs("Unable to click on ("+ValueUnderColumn+") from the available records in list view.", "Fail");
			return false;	
		}catch(Exception e)
		{
			System.out.println("Unable to click on ("+ValueUnderColumn+") from the available records in list view.");
	        autoFW.AddToXLLogs("Unable to click on ("+ValueUnderColumn+") from the available records in list view.", "Fail");
	        e.printStackTrace();
			return false;	
		}
}
	
	
	/**
	 * @author Cognizant
	 * @param RowIndex starts with 1
	 * @return the value to be clicked on success, returns blank on failure
	 * @throws Exception
	 */
	public String ClickByRowIndex(int RowIndex) throws Exception
	{
		try{
			
			
			Integer position = (myWD.findElements(By.xpath("//div[contains(@class,'-viewport')]/descendant::tr[1]/descendant::*[text()='"+ColumnName+"']/ancestor::td[1]/preceding-sibling::td")).size()) + 1;        
	        
			System.out.println("Position is:"+position);
	       
	        //Getting total displayed row count
	        Integer RecordCount = myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/following-sibling::div")).size() + 1;
	        System.out.println("RecordCount is:"+RecordCount);
	        String Value = myWD.findElement(By.xpath("//div[contains(@class,'-row-first')]/../div["+RowIndex+"]/descendant::tr[1]/descendant::td["+position+"]/descendant-or-self::*[text()][1]")).getText();
	        myWD.findElement(By.xpath("//div[contains(@class,'-row-first')]/../div["+RowIndex+"]/descendant::tr[1]/descendant::td["+position+"]/descendant-or-self::*[text()][1]")).click();
	        System.out.println("Clicked on the link("+Value+") displayed under the column ("+ColumnName+")");
	        autoFW.AddToXLLogs("Clicked on the link("+Value+") displayed under the column ("+ColumnName+")", "Pass");
	        return Value;
	        
		}catch(Exception e)
		{
			System.out.println("Unable to find any value under the column ("+ColumnName+")");
	        autoFW.AddToXLLogs("Unable to find any value under the column ("+ColumnName+")", "Fail");
	        e.printStackTrace();
			return "";	
		}
}
	/**********************************************************************/
	// This function verifies the value having hyperlink under a 		  //
	// particular column from the list view. If the vlaue is not available//
	// in the first list it navigates till the end of the list			  //
	//																	  //
	/**********************************************************************/
	
	/**
	 * @author Sourav
	 * @param ValueUnderColumn
	 * @return boolean
	 * @throws Exception
	 * @Description This function verifies the value having hyperlink under a particular column from the list view. If the vlaue is not available in the first list it navigates till the end of the list.
	 */
	public boolean VerifyValue(String ValueUnderColumn) throws Exception
	{
		
		//System.out.println("The ValueUnderColumn"+ValueUnderColumn);
		//myWD.findElement(By.linkText(ValueUnderColumn)).click();
		//return true;
		
		try
		{
		
		System.out.println(myWD.findElement(By.xpath("//img[@alt='Next']")).getAttribute("class"));
		
		 Integer position = (myWD.findElements(By.xpath("//div[contains(@class,'-viewport')]/descendant::tr[1]/descendant::*[text()='"+ColumnName+"']/ancestor::td[1]/preceding-sibling::td")).size()) + 1;        
	        
		 System.out.println("Position is:"+position);
	       
	        //Getting total displayed row count
	        Integer RecordCount = myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/following-sibling::div")).size() + 1;
	        System.out.println("RecordCount is:"+RecordCount);
	        
	        for(int i=1;i<=RecordCount;i++)
	        {
	        	//List<WebElement> we= myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/../div["+i+"]/descendant::tr[1]/descendant::td["+position+"]/descendant::*[contains(text(),'"+ValueUnderColumn+"')]"));
	        	List<WebElement> we= myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/../div["+i+"]/descendant::tr[1]/descendant::td["+position+"]/descendant-or-self::*[text()]"));	        	
	        	for(WebElement e:we)
	        	{
	        		if(e.getText().trim().equals(ValueUnderColumn))
	        		{
	        			System.out.println(e.getText());
	        			//e.click();
	        			i = RecordCount;
	        			System.out.println("Successfully verified the presence of value ("+ValueUnderColumn+") under column ("+ColumnName+") in the list view.");
	        			autoFW.AddToXLLogs("Successfully verified the presence of value ("+ValueUnderColumn+") under column ("+ColumnName+") in the list view.", "Pass");
	        			return true;
	        		}
	        		//System.out.println("inside for loop"+e.getText());
	        	}
	        	
	        	if(i==RecordCount)
	        	{
	        		if(myWD.findElement(By.xpath("//img[@alt='Next']")).getAttribute("class").trim().equalsIgnoreCase("next"))
	        		{
	        			myWD.findElement(By.xpath("//img[@alt='Next']")).click();
	        			i = 1;
	        			Thread.sleep(4000L);
	        			RecordCount = myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/following-sibling::div")).size() + 1;
	        			System.out.println("The recordcount is:"+RecordCount);
	        		}
	        		else
	        		{
	        			System.out.println("Could not find the value ("+ValueUnderColumn+") under the column ("+ColumnName+") from the available records in list view.");
	        			autoFW.AddToXLLogs("Could not find the value ("+ValueUnderColumn+") under the column ("+ColumnName+") from the available records in list view.", "Fail");
	        			return false;	
	        		}
	        		
	        	}
	        		
	        	
	        }
	        System.out.println("Could not find the value ("+ValueUnderColumn+") under the column ("+ColumnName+") from the available records in list view.");
			autoFW.AddToXLLogs("Could not find the value ("+ValueUnderColumn+") under the column ("+ColumnName+") from the available records in list view.", "Fail");
			return false;	
	        
		}
		catch(Exception e)
		{
			System.out.println("Could not find the value ("+ValueUnderColumn+") under the column ("+ColumnName+") from the available records in list view.");
			autoFW.AddToXLLogs("Could not find the value ("+ValueUnderColumn+") under the column ("+ColumnName+") from the available records in list view.", "Fail");
			e.printStackTrace();
			return false;	
		}
	}
	
	/**
	 * @author Cognizant
	 * @param RowIndex starts with 1
	 * @return the value to be clicked on success, returns blank on failure
	 * @throws Exception
	 */
	public String GetValueByRowIndex(int RowIndex) throws Exception
	{
		try{
			
			
			Integer position = (myWD.findElements(By.xpath("//div[contains(@class,'-viewport')]/descendant::tr[1]/descendant::*[text()='"+ColumnName+"']/ancestor::td[1]/preceding-sibling::td")).size()) + 1;        
	        
			System.out.println("Position is:"+position);
	       
	        //Getting total displayed row count
	        Integer RecordCount = myWD.findElements(By.xpath("//div[contains(@class,'-row-first')]/following-sibling::div")).size() + 1;
	        System.out.println("RecordCount is:"+RecordCount);
	        String Value = myWD.findElement(By.xpath("//div[contains(@class,'-row-first')]/../div["+RowIndex+"]/descendant::tr[1]/descendant::td["+position+"]/descendant-or-self::*[text()][1]")).getText();
	        //myWD.findElement(By.xpath("//div[contains(@class,'-row-first')]/../div["+RowIndex+"]/descendant::tr[1]/descendant::td["+position+"]/descendant-or-self::*[text()][1]")).click();
	        System.out.println("The value displayed under the column ("+ColumnName+") is ("+Value+")");
	        autoFW.AddToXLLogs("The value displayed under the column ("+ColumnName+") is ("+Value+")", "Pass");
	        return Value;
	        
		}catch(Exception e)
		{
			System.out.println("Unable to find any value under the column ("+ColumnName+")");
	        autoFW.AddToXLLogs("Unable to find any value under the column ("+ColumnName+")", "Fail");
	        e.printStackTrace();
			return "";	
		}
}
}
