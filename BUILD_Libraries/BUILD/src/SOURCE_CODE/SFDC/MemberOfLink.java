package SOURCE_CODE.SFDC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MemberOfLink {

	int count = 0;
	String Link;
	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String xpath;
	List<WebElement> we;

	MemberOfLink(String LN) {
		Link = LN;
		myWD = autoFW.myWD;

	}

	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the supplied hyperlink with Exact match
	 */
	public boolean Click() throws Exception {
		try {
			if (myWD.findElement(By.linkText(Link)).isDisplayed()) {
				myWD.findElement(By.linkText(Link)).click();
				System.out.println("Clicked on the hyperlink(" + Link + ")");
				autoFW.AddToXLLogs("Clicked on the hyperlink(" + Link + ")",
						"Pass");
				return true;
			} else {
				System.out.println("Unable to click on the hyperlink(" + Link
						+ ")");
				autoFW.AddToXLLogs("Unable to click on the hyperlink(" + Link
						+ ")", "Fail");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Unable to click on the hyperlink(" + Link + ")");
			autoFW.AddToXLLogs(
					"Unable to click on the hyperlink(" + Link + ")", "Fail");
			return false;
		}

	}

	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the hyperlink is present in the UI with Exact
	 *              match
	 */
	public boolean IsDisplayed() throws Exception {
		try {
			if (myWD.findElement(By.linkText(Link)).isDisplayed()) {

				System.out.println("Verified the existence of hyperlink ("
						+ Link + ")");
				autoFW.AddToXLLogs("Verified the existence of hyperlink ("
						+ Link + ")", "Pass");
				return true;
			} else {
				System.out
						.println("Unable to find the hyperlink(" + Link + ")");
				autoFW.AddToXLLogs(
						"Unable to find the hyperlink(" + Link + ")", "Fail");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to find the hyperlink(" + Link + ")");
			autoFW.AddToXLLogs("Unable to find the hyperlink(" + Link + ")",
					"Fail");
			return false;
		}

	}

	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the supplied hyperlink with partial match
	 */
	public boolean Click_Partial() throws Exception {
		try {
			if (myWD.findElement(By.partialLinkText(Link)).isDisplayed()) {
				myWD.findElement(By.partialLinkText(Link)).click();
				System.out.println("Clicked on the hyperlink(" + Link + ")");
				autoFW.AddToXLLogs("Clicked on the hyperlink(" + Link + ")",
						"Pass");
				return true;
			} else {
				System.out.println("Unable to click on the hyperlink(" + Link
						+ ")");
				autoFW.AddToXLLogs("Unable to click on the hyperlink(" + Link
						+ ")", "Fail");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Unable to click on the hyperlink(" + Link + ")");
			autoFW.AddToXLLogs(
					"Unable to click on the hyperlink(" + Link + ")", "Fail");
			return false;
		}
	}

	/**
	 * @author Sourav
	 * @param index
	 *            starts with 1
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks by index on the hyperlink displyed in the UI with
	 *              partial match. Use this when there are more than one
	 *              hyperlink with the same name
	 */
	public boolean ClickByIndex_Partial(int index) throws Exception {
		try {
			we = myWD.findElements(By.partialLinkText(Link));
			System.out.println("size is:" + we.size());
			if (we.size() > 1) {
				count++;
				for (WebElement webe : we) {
					if (count == index) {
						webe.click();
						System.out.println("Successfully clicked on the Link("
								+ Link + ").");
						autoFW.AddToXLLogs("Successfully clicked on the Link("
								+ Link + ").", "Pass");
						return true;
					}
					count++;

				}
				System.out.println("Unable to click on link (" + Link
						+ " while ClickByIndex)");
				autoFW.AddToXLLogs("Unable to click on link (" + Link
						+ " while ClickByIndex)", "Fail");
				return false;

			} else {
				System.out.println("Unable to find the link(" + Link
						+ ") while trying to click by Index.");
				autoFW.AddToXLLogs("Unable to find the link(" + Link
						+ ") while trying to click by Index.", "Fail");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: Unable to find the link(" + Link
					+ ") while trying to click by Index.");
			autoFW.AddToXLLogs("Exception:Unable to find the link(" + Link
					+ ") while trying to click by Index.", "Fail");
			return false;
		}
	}

	/**
	 * @author Sourav
	 * @param index
	 *            starts with 1
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks by index on the hyperlink displyed in the UI with
	 *              exact match. Use this when there are more than one hyperlink
	 *              with the same name
	 */
	public boolean ClickByIndex(int index) throws Exception {
		try {
			we = myWD.findElements(By.linkText(Link));
			System.out.println("size is:" + we.size());
			if (we.size() > 1) {
				count++;
				for (WebElement webe : we) {
					if (count == index) {
						webe.click();
						System.out.println("Successfully clicked on the Link("
								+ Link + ").");
						autoFW.AddToXLLogs("Successfully clicked on the Link("
								+ Link + ").", "Pass");
						return true;
					}
					count++;

				}
				System.out.println("Unable to click on link (" + Link
						+ " while ClickByIndex)");
				autoFW.AddToXLLogs("Unable to click on link (" + Link
						+ " while ClickByIndex)", "Fail");
				return false;

			} else {
				System.out.println("Unable to find the link(" + Link
						+ ") while trying to click by Index.");
				autoFW.AddToXLLogs("Unable to find the link(" + Link
						+ ") while trying to click by Index.", "Fail");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:Unable to find the link(" + Link
					+ ") while trying to click by Index.");
			autoFW.AddToXLLogs("Exception:Unable to find the link(" + Link
					+ ") while trying to click by Index.", "Fail");
			return false;
		}

	}

}
