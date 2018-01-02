package SOURCE_CODE.SFDC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MemberOfSEC {

	String SectionName, FieldName;
	Integer RowIndex;
	WebDriver myWD;
	SFDCAutomationFW autoFW;
	String xpath;
	List<WebElement> allposblefieldelements;
	WebElement getsingleWebelement;

	MemberOfSEC(String SN, String FN) {
		SectionName = SN;
		FieldName = FN;
		myWD = autoFW.myWD;

	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Yes or No
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the selected section from repository is present
	 *              in the UI as per supplied parameter
	 * @Date Aug 7, 2014
	 */
	public boolean IsDisplayed(String YesORNo) throws Exception {
		// xpath =
		// "((//*[text()='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		xpath = "//*[text()='" + SectionName + "'][local-name()='h3'][1]";

		// System.out.println("getText():"+myWD.findElement(By.xpath(xpath)).isDisplayed());
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (YesORNo.equalsIgnoreCase("Yes")) {
					if (myWD.findElement(By.xpath(xpath)).getTagName().trim()
							.equalsIgnoreCase("h3")) {
						System.out
								.println("The section ("
										+ SectionName
										+ ") is displayed as expected in the application.");
						autoFW.AddToXLLogs(
								"The section ("
										+ SectionName
										+ ") is displayed as expected in the application.",
								"Pass");
						return true;
					} else {
						System.out.println("The section (" + SectionName
								+ ") is not displayed in the application.");
						autoFW.AddToXLLogs("The section (" + SectionName
								+ ") is not displayed in the application.",
								"Fail");
						return false;
					}
				}

				if (YesORNo.equalsIgnoreCase("No")) {
					// System.out.println("tagnameis:"+myWD.findElement(By.xpath(xpath)).getTagName());
					System.out.println();
					if (!autoFW.WaitForElement(xpath, 1)) {
						System.out
								.println("The section ("
										+ SectionName
										+ ") is not displayed as expected in the application.");
						autoFW.AddToXLLogs(
								"The section ("
										+ SectionName
										+ ") is not displayed as expected in the application.",
								"Pass");
						return true;
					} else {
						System.out
								.println("The section ("
										+ SectionName
										+ ") is displayed in the application which is not expected.");
						autoFW.AddToXLLogs(
								"The section ("
										+ SectionName
										+ ") is displayed in the application which is not expected.",
								"Fail");
						return false;
					}
				} else {
					System.out.println("Could not find the SectionName ("
							+ SectionName + ")");
					autoFW.AddToXLLogs("Could not find the SectionName ("
							+ SectionName + ")", "Fail");
					return false;
				}
			} else {
				System.out.println("Could not find the SectionName ("
						+ SectionName + ")");
				autoFW.AddToXLLogs("Could not find the SectionName ("
						+ SectionName + ")", "Fail");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Yes or No
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the selected field inside the selected section
	 *              from repository is displayed in the UI as per supplied
	 *              parameter
	 * @Date Aug 7, 2014
	 */
	public boolean IsFieldDisplayed(String YesORNo) throws Exception {

		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName + "')][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (YesORNo.equalsIgnoreCase("Yes")) {
					System.out.println("The field (" + FieldName
							+ ") is visible under the section (" + SectionName
							+ ") as expected");
					autoFW.AddToXLLogs("The field (" + FieldName
							+ ") is visible under the section (" + SectionName
							+ ") as expected", "Pass");
					return true;
				} else if (YesORNo.equalsIgnoreCase("No")) {
					System.out.println("The field (" + FieldName
							+ ") is visible under the section (" + SectionName
							+ ") when it is not expected to be visible.");
					autoFW.AddToXLLogs("The field (" + FieldName
							+ ") is visible under the section (" + SectionName
							+ ") when it is not expected to be visible.",
							"Fail");
					return false;
				} else {
					System.out
							.println("Please verify the input parameter for function IsFieldDisplayed()");
					autoFW.AddToXLLogs(
							"Please verify the input parameter for function IsFieldDisplayed()",
							"Fail");
					return false;
				}

			} else {
				if (YesORNo.equalsIgnoreCase("No")) {
					System.out.println("The field (" + FieldName
							+ ") is not visible under the section ("
							+ SectionName + ") as expected.");
					autoFW.AddToXLLogs("The field (" + FieldName
							+ ") is not visible under the section ("
							+ SectionName + ") as expected.", "Pass");
					return true;
				} else if (YesORNo.equalsIgnoreCase("Yes")) {
					System.out.println("The field (" + FieldName
							+ ") is not visible under the section ("
							+ SectionName
							+ ") when it is expected to be visible.");
					autoFW.AddToXLLogs("The field (" + FieldName
							+ ") is not visible under the section ("
							+ SectionName
							+ ") when it is expected to be visible.", "Fail");
					return false;
				} else {
					System.out
							.println("Please verify the input parameter for function IsFieldDisplayed()");
					autoFW.AddToXLLogs(
							"Please verify the input parameter for function IsFieldDisplayed()",
							"Fail");
					return false;
				}

			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Expected value in the field in View Page
	 * @return boolean
	 * @DisplayMode Viewonly
	 * @throws Exception
	 * @Description Verifies if the supplied value of selected field inside
	 *              selected section is displayed as per supplied parameter
	 *              "Value"
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyViewOnlyValueEquals(String Value) throws Exception {
		// xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::td[1]/following-sibling::td[1]";
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (myWD.findElement(By.xpath(xpath)).getText().trim()
						.equals(Value)) {
					System.out.println("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ") inside the section ("
							+ SectionName + ")");
					autoFW.AddToXLLogs("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ") inside the section ("
							+ SectionName + ")", "Pass");
					return true;
				} else {
					System.out.println("Could not find the value("
							+ Value
							+ ") for the field("
							+ FieldName
							+ ") inside section("
							+ SectionName
							+ "). Actual Value:"
							+ myWD.findElement(By.xpath(xpath)).getText()
									.trim());
					autoFW.AddToXLLogs(
							"Could not find the value("
									+ Value
									+ ") for the field("
									+ FieldName
									+ ") inside section("
									+ SectionName
									+ "). Actual Value:"
									+ myWD.findElement(By.xpath(xpath))
											.getText().trim(), "Fail");
					return false;
				}
			} else {
				System.out
						.println("Could not find the element to verify viewonly value when xpath is: "
								+ xpath);
				autoFW.AddToXLLogs(
						"Could not find the element to verify viewonly value when xpath is: "
								+ xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param
	 * @return Returns the value displayed against a field inside selected
	 *         section from repository
	 * @throws Exception
	 * @Description Reads the value displayed in a field inside a section and
	 *              returns the same on success, On failure it returns blank
	 *              value.
	 * @Date Aug 7, 2014
	 */
	public String GetViewOnlyValue() throws Exception {
		String Value = "";

		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				Value = myWD.findElement(By.xpath(xpath)).getText().trim();
				System.out.println("The value of field (" + FieldName
						+ ") is (" + Value + ")");
				autoFW.AddToXLLogs("The value of field (" + FieldName
						+ ") is (" + Value + ")", "Pass");
				return Value;
			} else {
				System.out.println("Unable to read the value of field ("
						+ FieldName + ") when xpath is: " + xpath);
				autoFW.AddToXLLogs("Unable to read the value of field ("
						+ FieldName + ") when xpath is: " + xpath, "Fail");
				return Value;
			}
		} catch (Exception e) {
			System.out.println("Unable to read the value of field ("
					+ FieldName + ") when xpath is: " + xpath);
			autoFW.AddToXLLogs("Unable to read the value of field ("
					+ FieldName + ") when xpath is: " + xpath, "Fail");
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Expected Value
	 * @return boolean
	 * @DisplayMode View Only
	 * @throws Exception
	 * @Description Verifies the field value inside a section contains supplied
	 *              value "Value"
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyViewOnlyValueContains(String Value) throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (myWD.findElement(By.xpath(xpath)).getText().trim()
						.contains(Value)) {
					System.out.println("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ")");
					autoFW.AddToXLLogs("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ") inside the section ("
							+ SectionName + ")", "Pass");
					return true;
				} else {
					System.out.println("Could not find the value("
							+ Value
							+ ") for the field("
							+ FieldName
							+ "). Actual Value:"
							+ myWD.findElement(By.xpath(xpath)).getText()
									.trim());
					autoFW.AddToXLLogs(
							"Could not find the value("
									+ Value
									+ ") for the field("
									+ FieldName
									+ "). Actual Value:"
									+ myWD.findElement(By.xpath(xpath))
											.getText().trim(), "Fail");
					return false;
				}
			} else {
				System.out
						.println("Could not find the element to verify viewonly value when xpath is: "
								+ xpath);
				autoFW.AddToXLLogs(
						"Could not find the element to verify viewonly value when xpath is: "
								+ xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Expected Value
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the field value inside a section starts with
	 *              supplied value "Value"
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyViewOnlyValueStartsWith(String Value) throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (myWD.findElement(By.xpath(xpath)).getText().trim()
						.startsWith(Value)) {
					System.out.println("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ")");
					autoFW.AddToXLLogs("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ") inside the section ("
							+ SectionName + ")", "Pass");
					return true;
				} else {
					System.out.println("Could not find the value("
							+ Value
							+ ") for the field("
							+ FieldName
							+ "). Actual Value:"
							+ myWD.findElement(By.xpath(xpath)).getText()
									.trim());
					autoFW.AddToXLLogs(
							"Could not find the value("
									+ Value
									+ ") for the field("
									+ FieldName
									+ "). Actual Value:"
									+ myWD.findElement(By.xpath(xpath))
											.getText().trim(), "Fail");
					return false;
				}
			} else {
				System.out
						.println("Could not find the element to verify viewonly value when xpath is: "
								+ xpath);
				autoFW.AddToXLLogs(
						"Could not find the element to verify viewonly value when xpath is: "
								+ xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Expected Value
	 * @DisplayMode View Only
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the field value inside a section ends with supplied
	 *              value "Value"
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyViewOnlyValueEndsWith(String Value) throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (myWD.findElement(By.xpath(xpath)).getText().trim()
						.endsWith(Value)) {
					System.out.println("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ")");
					autoFW.AddToXLLogs("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ") inside the section ("
							+ SectionName + ")", "Pass");
					return true;
				} else {
					System.out.println("Could not find the value("
							+ Value
							+ ") for the field("
							+ FieldName
							+ "). Actual Value:"
							+ myWD.findElement(By.xpath(xpath)).getText()
									.trim());
					autoFW.AddToXLLogs(
							"Could not find the value("
									+ Value
									+ ") for the field("
									+ FieldName
									+ "). Actual Value:"
									+ myWD.findElement(By.xpath(xpath))
											.getText().trim(), "Fail");
					return false;
				}
			} else {
				System.out
						.println("Could not find the element to verify viewonly value when xpath is: "
								+ xpath);
				autoFW.AddToXLLogs(
						"Could not find the element to verify viewonly value when xpath is: "
								+ xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Expeceted Value
	 * @DisplayMode Edit Page
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the field value inside a section is equal to the
	 *              supplied value "Value"
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyEditFieldValue(String Value) throws Exception {

		// xpath =
		// "//*[text()='"+SectionName+"'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"+FieldName+"')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		// xpath =
		// "((//*[text()='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		// xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try {
			if (autoFW.WaitForElement(xpath, 10)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				if (getsingleWebelement.getAttribute("value").trim()
						.equals(Value.trim())) {
					System.out
							.println("Successfully verified the value for the field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs("The value(" + Value
							+ ") has been verified successfully for the field("
							+ FieldName + ") inside the section ("
							+ SectionName + ")", "Pass");
					return true;
				} else {
					System.out.println("Could not find the value (" + Value
							+ ") for the field (" + FieldName
							+ "). Actual value is ("
							+ getsingleWebelement.getAttribute("value").trim()
							+ ")");
					autoFW.AddToXLLogs("Could not find the value (" + Value
							+ ") for the field (" + FieldName
							+ "). Actual value is ("
							+ getsingleWebelement.getAttribute("value").trim()
							+ ")", "Fail");
					return false;
				}
			} else {
				System.out
						.println("Could not find the element to verify viewonly value when xpath is: "
								+ xpath);
				autoFW.AddToXLLogs(
						"Could not find the element to verify viewonly value when xpath is: "
								+ xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param
	 * @DisplayMode Edit Page
	 * @return Returns the field value displayed inside a section
	 * @throws Exception
	 * @Description Reads the field value inside a section and returns the same
	 *              on success, on failure it returns blank value
	 * @Date Aug 7, 2014
	 */
	public String GetEditFieldValue() throws Exception {
		String Value = "";
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";

		try {
			if (autoFW.WaitForElement(xpath, 10)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				System.out.println("The value for field (" + FieldName
						+ ") is (" + Value + ")");
				autoFW.AddToXLLogs("The value for field (" + FieldName
						+ ") is (" + Value + ")", "Pass");
				return Value;
			} else {
				System.out.println("Unable to read the value for field ("
						+ FieldName + ") when xpath is: " + xpath);
				autoFW.AddToXLLogs("Unable to read the value for field ("
						+ FieldName + ") when xpath is: " + xpath, "Fail");
				return Value;
			}
		} catch (Exception e) {
			System.out.println("Unable to read the value for field ("
					+ FieldName + ") when xpath is: " + xpath);
			autoFW.AddToXLLogs("Unable to read the value for field ("
					+ FieldName + ") when xpath is: " + xpath, "Fail");
			return "";
		}
	}

	/*******************************************************************************************/
	// Following function verifies if the value of a check box in
	// checked/unchecked //
	// in view details page as well as Edit page //
	// //
	/*******************************************************************************************/
	/**
	 * @author Sourav Mukherjee
	 * @Param Checked/Not Checked
	 * @DisplayMode View Only/Edit Page
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the check box value inside a section is
	 *              displayed as per supplied parameter
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyChkBoxValue(String CheckedORNotChecked)
			throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='img' or local-name()='input')][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (CheckedORNotChecked.equalsIgnoreCase("Checked")) {
					if ((myWD.findElement(By.xpath(xpath))
							.getAttribute("title").equals(""))
							&& (myWD.findElement(By.xpath(xpath)).getAttribute(
									"checked") != null)
							&& (myWD.findElement(By.xpath(xpath)).getAttribute(
									"checked").equals("true"))) {
						System.out
								.println("Successfully verified the value as checked for the checkbox field ("
										+ FieldName + ")");
						autoFW.AddToXLLogs(
								"Successfully verified the value as checked for the checkbox field ("
										+ FieldName + ")", "Pass");
						return true;
					} else if ((myWD.findElement(By.xpath(xpath)).getAttribute(
							"title").equals("Checked"))
							&& (myWD.findElement(By.xpath(xpath)).getAttribute(
									"checked") == null)) {
						System.out
								.println("Successfully verified the value as checked for the checkbox field ("
										+ FieldName + ")");
						autoFW.AddToXLLogs(
								"Successfully verified the value as checked for the checkbox field ("
										+ FieldName + ")", "Pass");
						return true;
					} else {
						System.out
								.println("Could not find the value as checked for the checkbox field ("
										+ FieldName + ")");
						autoFW.AddToXLLogs(
								"Could not find the value as checked for the checkbox field ("
										+ FieldName + ")", "Fail");
						return false;
					}
				}
				if (CheckedORNotChecked.equalsIgnoreCase("Not Checked")) {
					if ((myWD.findElement(By.xpath(xpath))
							.getAttribute("title").equals("Not Checked"))
							&& (myWD.findElement(By.xpath(xpath)).getAttribute(
									"checked") == null)) {
						System.out
								.println("Successfully verified the value as unchecked for the checkbox field ("
										+ FieldName + ")");
						autoFW.AddToXLLogs(
								"Successfully verified the value as unchecked for the checkbox field ("
										+ FieldName + ")", "Pass");
						return true;
					} else if ((myWD.findElement(By.xpath(xpath)).getAttribute(
							"title").equals(""))
							&& (myWD.findElement(By.xpath(xpath)).getAttribute(
									"checked") == null)) {
						System.out
								.println("Successfully verified the value as checked for the checkbox field ("
										+ FieldName + ")");
						autoFW.AddToXLLogs(
								"Successfully verified the value as checked for the checkbox field ("
										+ FieldName + ")", "Pass");
						return true;
					} else {
						System.out
								.println("Could not find the value as unchecked for the checkbox field ("
										+ FieldName + ")");
						autoFW.AddToXLLogs(
								"Could not find the value as unchecked for the checkbox field ("
										+ FieldName + ")", "Fail");
						return false;
					}

				} else {
					System.out
							.println("The input parameter value for the function VerifyChkBoxValue is not expected: "
									+ CheckedORNotChecked);
					autoFW.AddToXLLogs(
							"The input parameter value for the function VerifyChkBoxValue is not expected: "
									+ CheckedORNotChecked, "Fail");
					return false;
				}
			} else {
				System.out
						.println("Unable to Verify checkbox value for the field("
								+ FieldName + ") when xpath is: " + xpath);
				autoFW.AddToXLLogs(
						"Unable to Verify checkbox value for the field("
								+ FieldName + ") when xpath is: " + xpath,
						"Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @author Sourav Mukherjee
	 * @Param
	 * @DisplayMode Edit Page
	 * @return boolean
	 * @throws Exception
	 * @Description Checks the checkbox of a field inside a section
	 * @Date Aug 7, 2014
	 */
	public boolean CheckBoxSelect() throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='img' or local-name()='input')][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if ((myWD.findElement(By.xpath(xpath)).getAttribute("title")
						.equals("") || myWD.findElement(By.xpath(xpath))
						.getAttribute("title").equals("Not Checked"))
						&& (myWD.findElement(By.xpath(xpath)).getAttribute(
								"checked") == null)) {
					myWD.findElement(By.xpath(xpath)).click();
					System.out
							.println("Successfully checked the checkbox for the field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Successfully checked the checkbox for the field ("
									+ FieldName + ")", "Pass");
					return true;
				} else if (((myWD.findElement(By.xpath(xpath)).getAttribute(
						"title").equals("Checked")) && (myWD.findElement(
						By.xpath(xpath)).getAttribute("checked") == null))
						|| ((myWD.findElement(By.xpath(xpath)).getAttribute(
								"title").equals("")) && (myWD.findElement(
								By.xpath(xpath)).getAttribute("checked") != null))) {
					System.out
							.println("Successfully checked the checkbox for the field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Successfully checked the checkbox for the field ("
									+ FieldName + ")", "Pass");
					return true;
				} else {
					System.out
							.println("Unable to check the check box for field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Unable to check the check box for field ("
									+ FieldName + ")", "Fail");
					return false;
				}
			} else {
				System.out.println("Unable to Select checkbox for the field("
						+ FieldName + ") when xpath is: " + xpath);
				autoFW.AddToXLLogs("Unable to Select checkbox for the field("
						+ FieldName + ") when xpath is: " + xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param
	 * @DisplayMode Edit Page
	 * @return boolean
	 * @throws Exception
	 * @Description Unchecks the checkbox field inside a section
	 * @Date Aug 7, 2014
	 */
	public boolean CheckBoxDeSelect() throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='img' or local-name()='input')][1]";

		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				if (((myWD.findElement(By.xpath(xpath)).getAttribute("title")
						.equals("Checked")) && (myWD.findElement(
						By.xpath(xpath)).getAttribute("checked") == null))
						|| ((myWD.findElement(By.xpath(xpath)).getAttribute(
								"title").equals("")) && (myWD.findElement(
								By.xpath(xpath)).getAttribute("checked") != null))) {
					myWD.findElement(By.xpath(xpath)).click();
					System.out
							.println("Successfully unchecked the checkbox for the field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Successfully unchecked the checkbox for the field ("
									+ FieldName + ")", "Pass");
					return true;
				} else if ((myWD.findElement(By.xpath(xpath))
						.getAttribute("title").equals("") || myWD
						.findElement(By.xpath(xpath)).getAttribute("title")
						.equals("Not Checked"))
						&& (myWD.findElement(By.xpath(xpath)).getAttribute(
								"checked") == null)) {
					System.out
							.println("Successfully unchecked the checkbox for the field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Successfully unchecked the checkbox for the field ("
									+ FieldName + ")", "Pass");
					return true;
				} else {
					System.out
							.println("Unable to uncheck the check box for field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Unable to uncheck the check box for field ("
									+ FieldName + ")", "Fail");
					return false;
				}
			} else {
				System.out.println("Unable to DeSelect checkbox for the field("
						+ FieldName + ") when xpath is: " + xpath);
				autoFW.AddToXLLogs("Unable to DeSelect checkbox for the field("
						+ FieldName + ") when xpath is: " + xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param
	 * @DisplayMode Edit page
	 * @return boolean
	 * @throws Exception
	 * @Description Checks the check box field inside a section where check box
	 *              element is precedes the field label
	 * @Date Aug 7, 2014
	 */
	public boolean PrecedingCheckBoxSelect() throws Exception {
		try {
			if (myWD.findElement(
					By.xpath("//label[contains(normalize-space(text()),'"
							+ FieldName + "')]/preceding-sibling::input[1]"))
					.getAttribute("checked") == null) {
				myWD.findElement(
						By.xpath("//label[contains(normalize-space(text()),'"
								+ FieldName + "')]/preceding-sibling::input[1]"))
						.click();
				System.out
						.println("Successfully checked the checkbox for the field ("
								+ FieldName + ")");
				autoFW.AddToXLLogs(
						"Successfully checked the checkbox for the field ("
								+ FieldName + ")", "Pass");
				return true;

			}
			System.out
					.println("Successfully checked the checkbox for the field ("
							+ FieldName + ")");
			autoFW.AddToXLLogs(
					"Successfully checked the checkbox for the field ("
							+ FieldName + ")", "Pass");
			return true;
		} catch (Exception e) {
			System.out.println("Unable to check the check box for field ("
					+ FieldName + ")");
			autoFW.AddToXLLogs("Unable to check the check box for field ("
					+ FieldName + ")", "Fail");
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param
	 * @DisplayMode Edit Page
	 * @return boolean
	 * @throws Exception
	 * @Description UnChecks the check box field inside a section where check
	 *              box element is precedes the field label
	 * @Date Aug 7, 2014
	 */
	public boolean PrecedingCheckBoxDeSelect() throws Exception {
		try {
			if (myWD.findElement(
					By.xpath("//label[contains(normalize-space(text()),'"
							+ FieldName + "')]/preceding-sibling::input[1]"))
					.getAttribute("checked") != null) {
				myWD.findElement(
						By.xpath("//label[contains(normalize-space(text()),'"
								+ FieldName + "')]/preceding-sibling::input[1]"))
						.click();
				System.out
						.println("Successfully unchecked the checkbox for the field ("
								+ FieldName + ")");
				autoFW.AddToXLLogs(
						"Successfully unchecked the checkbox for the field ("
								+ FieldName + ")", "Pass");
				return true;

			}

			System.out
					.println("Successfully unchecked the checkbox for the field ("
							+ FieldName + ")");
			autoFW.AddToXLLogs(
					"Successfully unchecked the checkbox for the field ("
							+ FieldName + ")", "Pass");
			return true;
		} catch (Exception e) {
			System.out.println("Unable to uncheck the check box for field ("
					+ FieldName + ")");
			autoFW.AddToXLLogs("Unable to uncheck the check box for field ("
					+ FieldName + ")", "Fail");
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Value to be entered
	 * @return boolean
	 * @throws Exception
	 * @Description Enters the field(text box,text area) value inside a section.
	 * @Date Aug 7, 2014
	 */
	public boolean Type(String Value) throws Exception {

		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";

		// xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				getsingleWebelement.clear();
				getsingleWebelement.sendKeys(Value);
				System.out.println("Successfully entered the value (" + Value
						+ ") for the field (" + FieldName + ")");
				autoFW.AddToXLLogs("Successfully entered the value (" + Value
						+ ") for the field (" + FieldName + ")", "Pass");
				return true;
			} else {
				System.out.println("Unable to enter the value(" + Value
						+ ") in the field(" + FieldName + ") when xpath is: "
						+ xpath);
				autoFW.AddToXLLogs("Unable to enter the value(" + Value
						+ ") in the field(" + FieldName + ") when xpath is: "
						+ xpath, "Fail");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Value to be selected from SFDC OOB lookup
	 * @DisplayMode Edit page
	 * @return boolean
	 * @throws Exception
	 * @Description Selects the value from SFDC OOB lookup field inside a
	 *              section. If the text box field is editable then it types the
	 *              value in that field then clicks on the lookup icon and
	 *              clicks on the hyperlink displayed in search lookup. In case
	 *              the text box field is read only then directly clicks on the
	 *              lookup icon and searches the expected value in the search
	 *              lookup window and clicks on hyperlink. It also set the focus
	 *              to parent window
	 * @Date Aug 7, 2014
	 */
	public boolean SelectFromLookup(String LookUpValue) throws Exception {

		String parentwindowtitle = "";
		xpath = "//*[normalize-space(text())='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(normalize-space(text()),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/input[1]";
		try {
			if (autoFW.WaitForElement(xpath, 10)) {

				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				parentwindowtitle = myWD.getTitle().trim();

				if (getsingleWebelement.getAttribute("readonly") == null) {

					getsingleWebelement.clear();
					getsingleWebelement.sendKeys(LookUpValue);
					xpath = "//*[text()='"
							+ SectionName
							+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
							+ FieldName
							+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/descendant-or-self::img[contains(normalize-space(@alt),'New Window')]";
					getsingleWebelement = myWD.findElement(By.xpath(xpath));
					getsingleWebelement.click();
					Thread.sleep(2000L);

					autoFW.SelectWindow("Search");
				} else if (getsingleWebelement.getAttribute("readonly")
						.equalsIgnoreCase("true")) {
					xpath = "//*[text()='"
							+ SectionName
							+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
							+ FieldName
							+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/descendant-or-self::img[contains(normalize-space(@alt),'New Window')]";
					getsingleWebelement = myWD.findElement(By.xpath(xpath));
					getsingleWebelement.click();
					Thread.sleep(2000L);
					autoFW.SelectWindow("Search");
					myWD.switchTo()
							.frame(myWD.findElement(By
									.xpath("//frame[contains((@title),'Search')]")));
					myWD.findElement(
							By.xpath("//input[contains(normalize-space(@placeholder),'Search') and normalize-space(@type)='text']"))
							.sendKeys(LookUpValue);
					Thread.sleep(1000L);
					myWD.findElement(
							By.xpath("//input[contains(normalize-space(@placeholder),'Search') and normalize-space(@type)='text']"))
							.sendKeys(Keys.ENTER);

				}

				Thread.sleep(3000L);
				autoFW.SelectWindow("Search");
				myWD.switchTo()
						.frame(myWD.findElement(By
								.xpath("//frame[contains(normalize-space(@title),'Result')]")));
				if (myWD.findElement(By.linkText(LookUpValue)).isDisplayed()) {
					myWD.findElement(By.linkText(LookUpValue)).click();
					autoFW.AddToXLLogs("Successfully picked the value ("
							+ LookUpValue + ") from lookup field (" + FieldName
							+ ")", "Pass");
					System.out.println("Successfully picked the value ("
							+ LookUpValue + ") from lookup field (" + FieldName
							+ ")");
					autoFW.SelectWindow(parentwindowtitle);
					return true;
				} else {

					autoFW.AddToXLLogs("Unable to find any such record ("
							+ LookUpValue + ") from the lookup field ("
							+ FieldName + ")", "Fail");
					System.out.println("Unable to find any such record ("
							+ LookUpValue + ") from the lookup field ("
							+ FieldName + ")");
					autoFW.CloseWindow("Search");
					autoFW.SelectWindow(parentwindowtitle);
					return false;
				}

			} else {

				autoFW.AddToXLLogs("Unable to pick the supplied value("
						+ LookUpValue + ") from lookup field (" + FieldName
						+ ") when xpath is (" + xpath + ")", "Fail");
				System.out.println("Unable to pick the supplied value("
						+ LookUpValue + ") from lookup field (" + FieldName
						+ ") when xpath is (" + xpath + ")");
				autoFW.CloseWindow("Search");
				autoFW.SelectWindow(parentwindowtitle);
				return false;

			}
		} catch (Exception e) {

			autoFW.AddToXLLogs("Unable to pick the supplied value("
					+ LookUpValue + ") from lookup field (" + FieldName
					+ ") when xpath is (" + xpath + ")", "Fail");
			System.out.println("Exception: Unable to pick the supplied value("
					+ LookUpValue + ") from lookup field (" + FieldName
					+ ") when xpath is (" + xpath + ")");
			e.printStackTrace();
			autoFW.CloseWindow("Search");
			autoFW.SelectWindow(parentwindowtitle);
			return false;
		}

		/*
		 * xpath = "//*[text()='"+SectionName+
		 * "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
		 * +FieldName+
		 * "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/input[1]"
		 * ; try { if (autoFW.WaitForElement(xpath,10)) {
		 * 
		 * getsingleWebelement = myWD.findElement(By.xpath(xpath));
		 * getsingleWebelement.clear();
		 * getsingleWebelement.sendKeys(LookUpValue);
		 * //getsingleWebelement.sendKeys("QA_LEADS_APJ01"); xpath =
		 * "//*[text()='"+SectionName+
		 * "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
		 * +FieldName+
		 * "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/descendant-or-self::img[contains(normalize-space(@alt),'New Window')]"
		 * ; getsingleWebelement = myWD.findElement(By.xpath(xpath));
		 * getsingleWebelement.click(); Thread.sleep(2000L);
		 * 
		 * autoFW.SelectWindow("Search"); Thread.sleep(1000L);
		 * 
		 * myWD.switchTo().frame(myWD.findElement(By.xpath(
		 * "//frame[contains((@title),'Result')]"))); if
		 * (myWD.findElement(By.linkText(LookUpValue)).isDisplayed()) {
		 * myWD.findElement(By.linkText(LookUpValue)).click();
		 * autoFW.AddToXLLogs
		 * ("Successfully picked the value ("+LookUpValue+") from lookup field ("
		 * +FieldName+") under the section ("+SectionName+")", "Pass");
		 * System.out.println("Successfully picked the value ("+LookUpValue+
		 * ") from lookup field ("
		 * +FieldName+") under the section ("+SectionName+")"); return true; }
		 * else {
		 * 
		 * autoFW.AddToXLLogs("Unable to find any such record ("+LookUpValue+
		 * ") from the lookup field ("
		 * +FieldName+") under the section ("+SectionName+")", "Fail");
		 * System.out.println("Unable to find any such record ("+LookUpValue+
		 * ") from the lookup field ("
		 * +FieldName+") under the section ("+SectionName+")");
		 * autoFW.CloseWindow("Search"); return false; }
		 * 
		 * 
		 * } else {
		 * 
		 * autoFW.AddToXLLogs("Unable to pick the supplied value("+LookUpValue+
		 * ") from lookup field ("
		 * +FieldName+") under Section ("+SectionName+") when xpath is ("
		 * +xpath+")", "Fail");
		 * System.out.println("Unable to pick the supplied value("
		 * +LookUpValue+") from lookup field ("
		 * +FieldName+") under section ("+SectionName
		 * +") when xpath is ("+xpath+")"); autoFW.CloseWindow("Search"); return
		 * false;
		 * 
		 * } } catch(Exception e) {
		 * 
		 * autoFW.AddToXLLogs("Exception: Unable to pick the supplied value("+
		 * LookUpValue
		 * +") from lookup field ("+FieldName+") under the section ("+
		 * SectionName+") when xpath is ("+xpath+")", "Fail");
		 * System.out.println
		 * ("Exception: Unable to pick the supplied value("+LookUpValue
		 * +") from lookup field ("
		 * +FieldName+") under the section ("+SectionName
		 * +") when xpath is ("+xpath+")"); e.printStackTrace();
		 * autoFW.CloseWindow("Search"); return false; }
		 */
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Value to be selected from picklist/dropdown(Single Select List)
	 * @return boolean
	 * @throws Exception
	 * @Description Selects the picklist field value inside a section by its
	 *              display text as per supplied parameter
	 * @Date Aug 7, 2014
	 */
	public boolean SelectPL(String Value) throws Exception {
		// Working xpath =
		// "((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[local-name()='select']";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				// System.out.println("Inside SelectPL:");

				if (getsingleWebelement.getAttribute("type").trim()
						.equals("select-one")
						|| (getsingleWebelement.getTagName().trim()
								.equals("select"))) {
					Select s = new Select(getsingleWebelement);
					s.selectByVisibleText(Value);
					System.out
							.println("Successfully selected the picklist value ("
									+ Value + ") for field (" + FieldName + ")");
					autoFW.AddToXLLogs(
							"Successfully selected the picklist value ("
									+ Value + ") for field (" + FieldName + ")",
							"Pass");
					return true;
				} else {
					System.out
							.println("Unable to select the picklist value when xpath is :"
									+ xpath);
					autoFW.AddToXLLogs(
							"Unable to select the picklist value when xpath is :"
									+ xpath, "Fail");
					return false;
				}
				// System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");

			} else {
				System.out.println("Unable to select the pick list value("
						+ Value + ") for the field(" + FieldName
						+ ") when xpath is: " + xpath);
				autoFW.AddToXLLogs("Unable to select the pick list value("
						+ Value + ") for the field(" + FieldName
						+ ") when xpath is: " + xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @Param Type the expected selected/defeult value in the picklist field
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies if the already selected pick list value/default
	 *              value for a field inside a section is equal to the supplied
	 *              parameter
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyPLValue(String Value) throws Exception {

		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor::td[1]/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				// System.out.println("Inside SelectPL:");
				if (getsingleWebelement.getAttribute("type").trim()
						.equals("select-one")
						|| (getsingleWebelement.getTagName().trim()
								.equals("select"))) {
					Select s = new Select(getsingleWebelement);
					if (s.getFirstSelectedOption().getText().trim()
							.equals(Value)) {
						System.out
								.println("Successfully verified the selected value ("
										+ Value
										+ ") in the picklist field ("
										+ FieldName + ")");
						autoFW.AddToXLLogs(
								"Successfully verified the selected value ("
										+ Value + ") in the picklist field ("
										+ FieldName + ")", "Pass");
						return true;
					} else {
						System.out.println("The value ("
								+ s.getFirstSelectedOption()
								+ ") displayed in the pick list field ("
								+ FieldName
								+ ") is not expected. Expected value is: ("
								+ Value + ")");
						autoFW.AddToXLLogs(
								"The value ("
										+ s.getFirstSelectedOption()
										+ ") displayed in the pick list field ("
										+ FieldName
										+ ") is not expected. Expected value is: ("
										+ Value + ")", "Fail");
						return false;
					}
				} else {
					System.out
							.println("Unable to find the select element while verifying the pick list value for field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Unable to find the select element while verifying the pick list value for field ("
									+ FieldName + ")", "Fail");
					return false;
				}
			} else {
				System.out.println("Unable to verify the pick list value("
						+ Value + ") for the field(" + FieldName
						+ ") when xpath is: " + xpath);
				autoFW.AddToXLLogs("Unable to verify the pick list value("
						+ Value + ") for the field(" + FieldName
						+ ") when xpath is: " + xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @DisplayMode Edit Page
	 * @Param Semi colon(;) seperated value to be added in the multi select pick
	 *        list field
	 * @return boolean
	 * @throws Exception
	 * @Description Selects and Adds the multi select pick list field values
	 *              inside a section
	 * @Date Aug 7, 2014
	 */
	public boolean MultiSelectAdd(String Value) throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor::td[1]/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][1]/ancestor::select[1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				Select s = new Select(getsingleWebelement);

				System.out.println("Inside multiSelect Add:");

				List<String> eachPLValue = Arrays.asList(Value.split(";"));
				for (String str : eachPLValue) {
					s.selectByVisibleText(str);
					System.out.println("Multiselect pick list values are:"
							+ str.trim());
				}

				getsingleWebelement = myWD
						.findElement(By
								.xpath("//*[text()='"
										+ SectionName
										+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
										+ FieldName
										+ "')][1]/ancestor::td[1]/following-sibling::td[1]/descendant-or-self::*[local-name()='img'][1]"));
				getsingleWebelement.click();
				System.out
						.println("Successfully added multiselect pick list values ("
								+ Value + ") for field (" + FieldName + ")");
				// System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
				autoFW.AddToXLLogs(
						"Successfully added multiselect pick list values ("
								+ Value + ") for field (" + FieldName + ")",
						"Pass");
				return true;
			} else {
				System.out.println("Unable to add pick list value(" + Value
						+ ") for the field(" + FieldName + ") when xpath is: "
						+ xpath);
				autoFW.AddToXLLogs("Unable to add pick list value(" + Value
						+ ") for the field(" + FieldName + ") when xpath is: "
						+ xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @DisplayMode Edit Page
	 * @Param Semi colon(;) seperated value to be removed from the multi select
	 *        pick list field
	 * @return boolean
	 * @throws Exception
	 * @Description Selects and Removes the multi select pick list field values
	 *              inside a section
	 * @Date Aug 7, 2014
	 */
	public boolean MultiSelectRemove(String Value) throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor::td[1]/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][2]/ancestor::select[1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {

				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				Select s = new Select(getsingleWebelement);

				// System.out.println("Inside multiSelect Remove:");

				List<String> eachPLValue = Arrays.asList(Value.split(";"));
				for (String str : eachPLValue) {
					s.selectByVisibleText(str);
					// System.out.println("Multiselect pick list values are:"+str.trim());
				}
				getsingleWebelement = myWD
						.findElement(By
								.xpath("//*[text()='"
										+ SectionName
										+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
										+ FieldName
										+ "')][1]/ancestor::td[1]/following-sibling::td[1]/descendant-or-self::*[local-name()='img'][2]"));
				getsingleWebelement.click();
				System.out
						.println("Successfully removed multiselect pick list values ("
								+ Value + ") for field (" + FieldName + ")");
				// System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
				autoFW.AddToXLLogs(
						"Successfully removed multiselect pick list values ("
								+ Value + ") for field (" + FieldName + ")",
						"Pass");
				return true;
			} else {
				System.out.println("Unable to remove pick list value(" + Value
						+ ") for the field(" + FieldName + ") when xpath is: "
						+ xpath);
				autoFW.AddToXLLogs("Unable to remove pick list value(" + Value
						+ ") for the field(" + FieldName + ") when xpath is: "
						+ xpath, "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Sourav Mukherjee
	 * @DisplayMode Edit Page
	 * @Param Semi Colon(;) seperated values expected to be displayed in the
	 *        available list of multi select pick list field inside a section
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the list of values displayed in the Avaliable List
	 *              of a multi select pick list field inside a section are as
	 *              per supplied parameter
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyMPLAvailable(String Value) throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor::td[1]/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][1]/ancestor::select[1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				Select s = new Select(getsingleWebelement);
				List<WebElement> options = s.getOptions();
				List<String> avail = new ArrayList<String>();
				for (WebElement eachOption : options) {
					avail.add(eachOption.getText().trim());
					// System.out.println("eachOption: "+eachOption.getText().trim());

				}

				List<String> AllavailValues = Arrays.asList(Value.split(";"));
				String failedPLValue = "";
				for (String exp : AllavailValues) {
					if (!avail.contains(exp)) {
						if (failedPLValue != "") {
							failedPLValue = failedPLValue + ";" + exp;
						} else {
							failedPLValue = exp;
						}
					}

				}
				if (failedPLValue == "") {
					System.out
							.println("Successfully verified the Available List of Values from Multiselect picklist field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Successfully verified the Available List of Values from Multiselect picklist field ("
									+ FieldName + ")", "Pass");
					return true;
				} else {
					System.out
							.println("Could not find multi select pick list values ("
									+ failedPLValue
									+ ") in the available list of field("
									+ FieldName + ").");
					autoFW.AddToXLLogs(
							"Could not find multi select pick list values ("
									+ failedPLValue
									+ ") in the available list of field("
									+ FieldName + ").", "Fail");
					return false;
				}

			} else {
				System.out
						.println("Could not find the element for multiselect available when Verifying the available value in the field ("
								+ FieldName + ") ");
				autoFW.AddToXLLogs(
						"Could not find the element for multiselect available when Verifying the available value in the field ("
								+ FieldName + ") ", "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @author Sourav Mukherjee
	 * @DisplayMode Edit Page
	 * @Param Semi Colon(;) seperated values expected to be displayed in the
	 *        chosen list of multi select pick list field inside a section
	 * @return boolean
	 * @throws Exception
	 * @Description Verifies the list of values displayed in the Chosen List of
	 *              a multi select pick list field inside a section are as per
	 *              supplied parameter
	 * @Date Aug 7, 2014
	 */
	public boolean VerifyMPLChosen(String Value) throws Exception {
		xpath = "//*[text()='"
				+ SectionName
				+ "'][local-name()='h3'][1]/ancestor-or-self::div[1]/following-sibling::div[1]/descendant-or-self::*[contains(text(),'"
				+ FieldName
				+ "')][1]/ancestor::td[1]/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][2]/ancestor::select[1]";
		try {
			if (autoFW.WaitForElement(xpath, 1)) {
				getsingleWebelement = myWD.findElement(By.xpath(xpath));
				Select s = new Select(getsingleWebelement);
				List<WebElement> options = s.getOptions();
				List<String> avail = new ArrayList<String>();

				for (WebElement eachOption : options) {
					avail.add(eachOption.getText().trim());
					// System.out.println("eachOption: "+eachOption.getText().trim());
				}

				List<String> AllavailValues = Arrays.asList(Value.split(";"));
				String failedPLValue = "";
				for (String exp : AllavailValues) {
					if (!avail.contains(exp)) {
						if (failedPLValue != "") {
							failedPLValue = failedPLValue + ";" + exp;
						} else {
							failedPLValue = exp;
						}
					}
				}

				if (failedPLValue == "") {
					System.out
							.println("Successfully verified the Chosen List of Values from Multiselect picklist field ("
									+ FieldName + ")");
					autoFW.AddToXLLogs(
							"Successfully verified the Chosen List of Values from Multiselect picklist field ("
									+ FieldName + ")", "Pass");
					return true;
				} else {
					System.out
							.println("Could not find multi select pick list values ("
									+ failedPLValue
									+ ") in the Chosen list of field("
									+ FieldName + ").");
					autoFW.AddToXLLogs(
							"Could not find multi select pick list values ("
									+ failedPLValue
									+ ") in the Chosen list of field("
									+ FieldName + ").", "Fail");
					return false;
				}
			} else {
				System.out
						.println("Could not find the element for multiselect Chosen when Verifying the Chosen values in the field ("
								+ FieldName + ") ");
				autoFW.AddToXLLogs(
						"Could not find the element for multiselect Chosen when Verifying the Chosen values in the field ("
								+ FieldName + ") ", "Fail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to find the element when xpath is:"
					+ xpath);
			autoFW.AddToXLLogs("Unable to find the element when xpath is:"
					+ xpath, "Fail");
			e.printStackTrace();
			return false;
		}
	}
}
