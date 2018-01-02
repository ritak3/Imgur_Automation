package SOURCE_CODE.SFDC;

import java.util.ArrayList;
import java.util.HashSet;

import org.openqa.selenium.By;

import USER_SPACE.TestPrerequisite.DataSetup;

public class ScriptToGenerateAUTRepository {

	public static void main(String[] args) throws Exception {
		System.out.println("-----------Begin of TestScript-------------");

		SFDCAutomationFW sfdc = new SFDCAutomationFW("ScriptToGenerateAUTRepository");
		DataSetup.sfdc = sfdc;

		try {

			DB.Connect(DataSetup.Logininfo);
			String URL = DB.ReadXLData("LoginInfo", "URL", "Name","Sourav Mukherjee");

			sfdc.OpenURL(args, URL, DataSetup.Browser);

			sfdc.LoginToSFDC("Sourav Mukherjee");
			sfdc.OpenSFDCRecordAsperURL("https://ap1.salesforce.com/0069000000flGZj");

			Thread.sleep(5000L);

			sfdc._CreateAUTRepository("Opportunity");

		} catch (Exception e) {
			System.out.println("Inside catch in test case.");
		} finally {

			System.out.println("Inside finally in test case.");
			sfdc.GenerateXLLog();
			System.out.println("-----------End of TestScript-------------");
		}
	}

}
