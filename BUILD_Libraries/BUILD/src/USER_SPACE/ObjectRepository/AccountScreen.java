package USER_SPACE.ObjectRepository; 
import SOURCE_CODE.SFDC.*; 
import USER_SPACE.TestPrerequisite.DataSetup;

 
 
public class AccountScreen { 
static SFDCAutomationFW sfdc = DataSetup.sfdc; 
static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField AccountOwnerField() throws Exception{ 
	return sfdc.Field("Account Owner"); 
} 
public static MemberOfField RatingField() throws Exception{ 
	return sfdc.Field("Rating"); 
} 
public static MemberOfField AccountNameField() throws Exception{ 
	return sfdc.Field("Account Name"); 
} 
public static MemberOfField PhoneField() throws Exception{ 
	return sfdc.Field("Phone"); 
} 
public static MemberOfField ParentAccountField() throws Exception{ 
	return sfdc.Field("Parent Account"); 
} 
public static MemberOfField FaxField() throws Exception{ 
	return sfdc.Field("Fax"); 
} 
public static MemberOfField AccountNumberField() throws Exception{ 
	return sfdc.Field("Account Number"); 
} 
public static MemberOfField WebsiteField() throws Exception{ 
	return sfdc.Field("Website"); 
} 
public static MemberOfField AccountSiteField() throws Exception{ 
	return sfdc.Field("Account Site"); 
} 
public static MemberOfField TickerSymbolField() throws Exception{ 
	return sfdc.Field("Ticker Symbol"); 
} 
public static MemberOfField TypeField() throws Exception{ 
	return sfdc.Field("Type"); 
} 
public static MemberOfField OwnershipField() throws Exception{ 
	return sfdc.Field("Ownership"); 
} 
public static MemberOfField IndustryField() throws Exception{ 
	return sfdc.Field("Industry"); 
} 
public static MemberOfField EmployeesField() throws Exception{ 
	return sfdc.Field("Employees"); 
} 
public static MemberOfField AnnualRevenueField() throws Exception{ 
	return sfdc.Field("Annual Revenue"); 
} 
public static MemberOfField SICCodeField() throws Exception{ 
	return sfdc.Field("SIC Code"); 
} 
public static MemberOfField BillingAddressField() throws Exception{ 
	return sfdc.Field("Billing Address"); 
} 
public static MemberOfField ShippingAddressField() throws Exception{ 
	return sfdc.Field("Shipping Address"); 
} 
public static MemberOfField CustomerPriorityField() throws Exception{ 
	return sfdc.Field("Customer Priority"); 
} 
public static MemberOfField SLAField() throws Exception{ 
	return sfdc.Field("SLA"); 
} 
public static MemberOfField SLAExpirationDateField() throws Exception{ 
	return sfdc.Field("SLA Expiration Date"); 
} 
public static MemberOfField SLASerialNumberField() throws Exception{ 
	return sfdc.Field("SLA Serial Number"); 
} 
public static MemberOfField NumberofLocationsField() throws Exception{ 
	return sfdc.Field("Number of Locations"); 
} 
public static MemberOfField UpsellOpportunityField() throws Exception{ 
	return sfdc.Field("Upsell Opportunity"); 
} 
public static MemberOfField ActiveField() throws Exception{ 
	return sfdc.Field("Active"); 
} 
public static MemberOfField CreatedByField() throws Exception{ 
	return sfdc.Field("Created By"); 
} 
public static MemberOfField LastModifiedByField() throws Exception{ 
	return sfdc.Field("Last Modified By"); 
} 
public static MemberOfField DescriptionField() throws Exception{ 
	return sfdc.Field("Description"); 
} 
public static MemberOfField CustomLinksField() throws Exception{ 
	return sfdc.Field("Custom Links"); 
} 


//************************ Manually added Fields *****************
public static MemberOfField BillingStreetField() throws Exception{ 
	return sfdc.Field("Billing Street"); 
} 

public static MemberOfField BillingCityField() throws Exception{ 
	return sfdc.Field("Billing City"); 
} 

public static MemberOfField BillingStateProvinceField() throws Exception{ 
	return sfdc.Field("Billing State/Province"); 
} 

public static MemberOfField BillingZipPostalCodeField() throws Exception{ 
	return sfdc.Field("Billing Zip/Postal Code"); 
} 
public static MemberOfField BillingCountryField() throws Exception{ 
	return sfdc.Field("Billing Country"); 
} 

 
// ************************* Functions & Static Classes for Sections ***************************** // 
 
public static Fields_AccountDetail SEC_AccountDetail() throws Exception{ 
return new Fields_AccountDetail("Account Detail");
}
public static class Fields_AccountDetail{
Fields_AccountDetail(String SN)
{
SecName = SN;
}
public static MemberOfSEC AccountOwner() throws Exception 
{ 
return sfdc._SEC(SecName,"Account Owner");
}
public static MemberOfSEC Rating() throws Exception 
{ 
return sfdc._SEC(SecName,"Rating");
}
public static MemberOfSEC AccountName() throws Exception 
{ 
return sfdc._SEC(SecName,"Account Name");
}
public static MemberOfSEC Phone() throws Exception 
{ 
return sfdc._SEC(SecName,"Phone");
}
public static MemberOfSEC ParentAccount() throws Exception 
{ 
return sfdc._SEC(SecName,"Parent Account");
}
public static MemberOfSEC Fax() throws Exception 
{ 
return sfdc._SEC(SecName,"Fax");
}
public static MemberOfSEC AccountNumber() throws Exception 
{ 
return sfdc._SEC(SecName,"Account Number");
}
public static MemberOfSEC Website() throws Exception 
{ 
return sfdc._SEC(SecName,"Website");
}
public static MemberOfSEC AccountSite() throws Exception 
{ 
return sfdc._SEC(SecName,"Account Site");
}
public static MemberOfSEC TickerSymbol() throws Exception 
{ 
return sfdc._SEC(SecName,"Ticker Symbol");
}
public static MemberOfSEC Type() throws Exception 
{ 
return sfdc._SEC(SecName,"Type");
}
public static MemberOfSEC Ownership() throws Exception 
{ 
return sfdc._SEC(SecName,"Ownership");
}
public static MemberOfSEC Industry() throws Exception 
{ 
return sfdc._SEC(SecName,"Industry");
}
public static MemberOfSEC Employees() throws Exception 
{ 
return sfdc._SEC(SecName,"Employees");
}
public static MemberOfSEC AnnualRevenue() throws Exception 
{ 
return sfdc._SEC(SecName,"Annual Revenue");
}
public static MemberOfSEC SICCode() throws Exception 
{ 
return sfdc._SEC(SecName,"SIC Code");
}
public static MemberOfSEC BillingAddress() throws Exception 
{ 
return sfdc._SEC(SecName,"Billing Address");
}
public static MemberOfSEC ShippingAddress() throws Exception 
{ 
return sfdc._SEC(SecName,"Shipping Address");
}
public static MemberOfSEC CustomerPriority() throws Exception 
{ 
return sfdc._SEC(SecName,"Customer Priority");
}
public static MemberOfSEC SLA() throws Exception 
{ 
return sfdc._SEC(SecName,"SLA");
}
public static MemberOfSEC SLAExpirationDate() throws Exception 
{ 
return sfdc._SEC(SecName,"SLA Expiration Date");
}
public static MemberOfSEC SLASerialNumber() throws Exception 
{ 
return sfdc._SEC(SecName,"SLA Serial Number");
}
public static MemberOfSEC NumberofLocations() throws Exception 
{ 
return sfdc._SEC(SecName,"Number of Locations");
}
public static MemberOfSEC UpsellOpportunity() throws Exception 
{ 
return sfdc._SEC(SecName,"Upsell Opportunity");
}
public static MemberOfSEC Active() throws Exception 
{ 
return sfdc._SEC(SecName,"Active");
}
public static MemberOfSEC CreatedBy() throws Exception 
{ 
return sfdc._SEC(SecName,"Created By");
}
public static MemberOfSEC LastModifiedBy() throws Exception 
{ 
return sfdc._SEC(SecName,"Last Modified By");
}
public static MemberOfSEC Description() throws Exception 
{ 
return sfdc._SEC(SecName,"Description");
}
public static MemberOfSEC CustomLinks() throws Exception 
{ 
return sfdc._SEC(SecName,"Custom Links");
}
}

 
 
 // **************** Functions & Static Classes for Related List ******************** 
 
public static Columns_Contacts RL_Contacts() throws Exception{ 
return new Columns_Contacts("Contacts"); 
} 
public static class Columns_Contacts{ 
Columns_Contacts(String RL) 
{ 
RList = RL; 
} 
public static MemberOfRL Action(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Action",RowIndex); 
}
public static MemberOfRL Action() throws Exception 
{ 
return sfdc._RL(RList,"Action"); 
}
public static MemberOfRL ContactName(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Contact Name",RowIndex); 
}
public static MemberOfRL ContactName() throws Exception 
{ 
return sfdc._RL(RList,"Contact Name"); 
}
public static MemberOfRL Title(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Title",RowIndex); 
}
public static MemberOfRL Title() throws Exception 
{ 
return sfdc._RL(RList,"Title"); 
}
public static MemberOfRL Email(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Email",RowIndex); 
}
public static MemberOfRL Email() throws Exception 
{ 
return sfdc._RL(RList,"Email"); 
}
public static MemberOfRL Phone(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Phone",RowIndex); 
}
public static MemberOfRL Phone() throws Exception 
{ 
return sfdc._RL(RList,"Phone"); 
}
}
public static Columns_Opportunities RL_Opportunities() throws Exception{ 
return new Columns_Opportunities("Opportunities"); 
} 
public static class Columns_Opportunities{ 
Columns_Opportunities(String RL) 
{ 
RList = RL; 
} 
public static MemberOfRL Action(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Action",RowIndex); 
}
public static MemberOfRL Action() throws Exception 
{ 
return sfdc._RL(RList,"Action"); 
}
public static MemberOfRL OpportunityName(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Opportunity Name",RowIndex); 
}
public static MemberOfRL OpportunityName() throws Exception 
{ 
return sfdc._RL(RList,"Opportunity Name"); 
}
public static MemberOfRL Stage(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Stage",RowIndex); 
}
public static MemberOfRL Stage() throws Exception 
{ 
return sfdc._RL(RList,"Stage"); 
}
public static MemberOfRL Amount(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Amount",RowIndex); 
}
public static MemberOfRL Amount() throws Exception 
{ 
return sfdc._RL(RList,"Amount"); 
}
public static MemberOfRL CloseDate(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Close Date",RowIndex); 
}
public static MemberOfRL CloseDate() throws Exception 
{ 
return sfdc._RL(RList,"Close Date"); 
}
}
public static Columns_Cases RL_Cases() throws Exception{ 
return new Columns_Cases("Cases"); 
} 
public static class Columns_Cases{ 
Columns_Cases(String RL) 
{ 
RList = RL; 
} 
public static MemberOfRL Action(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Action",RowIndex); 
}
public static MemberOfRL Action() throws Exception 
{ 
return sfdc._RL(RList,"Action"); 
}
public static MemberOfRL Case(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Case",RowIndex); 
}
public static MemberOfRL Case() throws Exception 
{ 
return sfdc._RL(RList,"Case"); 
}
public static MemberOfRL ContactName(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Contact Name",RowIndex); 
}
public static MemberOfRL ContactName() throws Exception 
{ 
return sfdc._RL(RList,"Contact Name"); 
}
public static MemberOfRL Subject(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Subject",RowIndex); 
}
public static MemberOfRL Subject() throws Exception 
{ 
return sfdc._RL(RList,"Subject"); 
}
public static MemberOfRL Priority(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Priority",RowIndex); 
}
public static MemberOfRL Priority() throws Exception 
{ 
return sfdc._RL(RList,"Priority"); 
}
public static MemberOfRL DateOpened(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Date Opened",RowIndex); 
}
public static MemberOfRL DateOpened() throws Exception 
{ 
return sfdc._RL(RList,"Date Opened"); 
}
public static MemberOfRL Status(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Status",RowIndex); 
}
public static MemberOfRL Status() throws Exception 
{ 
return sfdc._RL(RList,"Status"); 
}
public static MemberOfRL Owner(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Owner",RowIndex); 
}
public static MemberOfRL Owner() throws Exception 
{ 
return sfdc._RL(RList,"Owner"); 
}
}
public static Columns_OpenActivities RL_OpenActivities() throws Exception{ 
return new Columns_OpenActivities("Open Activities"); 
} 
public static class Columns_OpenActivities{ 
Columns_OpenActivities(String RL) 
{ 
RList = RL; 
} 
}
public static Columns_ActivityHistory RL_ActivityHistory() throws Exception{ 
return new Columns_ActivityHistory("Activity History"); 
} 
public static class Columns_ActivityHistory{ 
Columns_ActivityHistory(String RL) 
{ 
RList = RL; 
} 
}
public static Columns_NotesAttachments RL_NotesAttachments() throws Exception{ 
return new Columns_NotesAttachments("Notes & Attachments"); 
} 
public static class Columns_NotesAttachments{ 
Columns_NotesAttachments(String RL) 
{ 
RList = RL; 
} 
}
public static Columns_Partners RL_Partners() throws Exception{ 
return new Columns_Partners("Partners"); 
} 
public static class Columns_Partners{ 
Columns_Partners(String RL) 
{ 
RList = RL; 
} 
}

 
 
public static MemberOfButton MergeContactsButton() throws Exception{ 
return sfdc.Button("Merge Contacts"); 
} 
public static MemberOfButton EditButton() throws Exception{ 
return sfdc.Button("Edit"); 
} 
public static MemberOfButton NewOpportunityButton() throws Exception{ 
return sfdc.Button("New Opportunity"); 
} 
public static MemberOfButton NewButton() throws Exception{ 
return sfdc.Button("New"); 
} 
public static MemberOfButton NewContactButton() throws Exception{ 
return sfdc.Button("New Contact"); 
} 
public static MemberOfButton AttachFileButton() throws Exception{ 
return sfdc.Button("Attach File"); 
} 
public static MemberOfButton NewCaseButton() throws Exception{ 
return sfdc.Button("New Case"); 
} 
public static MemberOfButton NewTaskButton() throws Exception{ 
return sfdc.Button("New Task"); 
} 
public static MemberOfButton MailMergeButton() throws Exception{ 
return sfdc.Button("Mail Merge"); 
} 
public static MemberOfButton OKButton() throws Exception{ 
return sfdc.Button("OK"); 
} 
public static MemberOfButton IncludeOfflineButton() throws Exception{ 
return sfdc.Button("Include Offline"); 
} 
public static MemberOfButton CancelButton() throws Exception{ 
return sfdc.Button("Cancel"); 
} 
public static MemberOfButton LogaCallButton() throws Exception{ 
return sfdc.Button("Log a Call"); 
} 
public static MemberOfButton GoButton() throws Exception{ 
return sfdc.Button("Go!"); 
} 
public static MemberOfButton SendanEmailButton() throws Exception{ 
return sfdc.Button("Send an Email"); 
} 
public static MemberOfButton NewNoteButton() throws Exception{ 
return sfdc.Button("New Note"); 
} 
public static MemberOfButton DeleteButton() throws Exception{ 
return sfdc.Button("Delete"); 
} 
public static MemberOfButton NewEventButton() throws Exception{ 
return sfdc.Button("New Event"); 
} 
public static MemberOfButton SaveButton() throws Exception{ 
return sfdc.Button("Save"); 
} 
}
