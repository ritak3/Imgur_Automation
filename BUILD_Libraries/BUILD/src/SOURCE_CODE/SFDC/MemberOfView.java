package SOURCE_CODE.SFDC;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MemberOfView {

	
	WebDriver myWD;
	WebElement getsingleWebelement;
	SFDCAutomationFW autoFW;
	String xpath;
	
	
	MemberOfView()
	{
		
		myWD = SFDCAutomationFW.myWD;
		
	}
	
	/**
	 * @author Sourav Mukherjee
	 * @Param Name of the value to be selected in the SFDC OOB View Pick List Field
	 * @return boolean
	 * @throws Exception
	 * @Description Selects the View value from SFDC OOB View Pick List Field, for example, All Accounts, Todays Leads tec.
	 * @Date Aug 7, 2014
	 */
	public boolean SelectPL(String ViewName) throws Exception
	{
		String ViewLabel = "View:";
		//xpath = "(//*[text()='"+ViewLabel+"'])[1]/following-sibling::span[1]/descendant-or-self::*[local-name()='select']";
		//xpath = "(//*[text()='"+ViewLabel+"'])[1]/following-sibling::span[1]/descendant::*[local-name()='select']";
		//System.out.println(myWD.findElement(By.xpath("")).getTagName().trim());
		try
		{
			if (myWD.findElement(By.xpath("//*[text()='"+ViewLabel+"'][1]/..")).getTagName().trim().equals("span"))
			{
				xpath = "(//*[text()='"+ViewLabel+"'])[1]/following-sibling::span[1]/descendant::*[local-name()='select']";
			}
			else if (myWD.findElement(By.xpath("//*[text()='"+ViewLabel+"'][1]/..")).getTagName().trim().equals("h3"))
			{
				xpath = "(//*[text()='"+ViewLabel+"'])[1]/../following-sibling::*[local-name()='select']";
			}
			System.out.println(myWD.findElement(By.xpath(xpath)).getTagName());
			getsingleWebelement = myWD.findElement(By.xpath(xpath));
			Select s = new Select(getsingleWebelement);
			s.selectByVisibleText(ViewName);
			System.out.println("View ("+ViewName+") has been selected from the View pick list.");
			autoFW.AddToXLLogs("View ("+ViewName+") has been selected from the View pick list.", "Pass");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Unable to select the view("+ViewName+") from the View pick list.");
			autoFW.AddToXLLogs("Unable to select the view("+ViewName+") from the View pick list.", "Fail");
			return false;
		}
		
	}
	
}
