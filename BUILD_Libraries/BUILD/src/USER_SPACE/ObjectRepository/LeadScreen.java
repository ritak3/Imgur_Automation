package USER_SPACE.ObjectRepository; 
import SOURCE_CODE.SFDC.*; 
import USER_SPACE.TestPrerequisite.*; 

 
 
public class LeadScreen { 
static SFDCAutomationFW sfdc = DataSetup.sfdc; 
static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField LeadOwnerField() throws Exception{ 
	return sfdc.Field("Lead Owner"); 
} 
public static MemberOfField PhoneField() throws Exception{ 
	return sfdc.Field("Phone"); 
} 
public static MemberOfField NameField() throws Exception{ 
	return sfdc.Field("Name"); 
} 
public static MemberOfField FirstNameField() throws Exception{ 
	return sfdc.Field("First Name"); 
} 
public static MemberOfField LastNameField() throws Exception{ 
	return sfdc.Field("Last Name"); 
} 
public static MemberOfField MobileField() throws Exception{ 
	return sfdc.Field("Mobile"); 
} 
public static MemberOfField CompanyField() throws Exception{ 
	return sfdc.Field("Company"); 
} 
public static MemberOfField FaxField() throws Exception{ 
	return sfdc.Field("Fax"); 
} 
public static MemberOfField TitleField() throws Exception{ 
	return sfdc.Field("Title"); 
} 
public static MemberOfField StreetField() throws Exception{ 
	return sfdc.Field("Street"); 
} 
public static MemberOfField CityField() throws Exception{ 
	return sfdc.Field("City"); 
} 
public static MemberOfField StateProvinceField() throws Exception{ 
	return sfdc.Field("State/Province"); 
} 
public static MemberOfField ZipPostalCodeField() throws Exception{ 
	return sfdc.Field("Zip/Postal Code"); 
} 
public static MemberOfField CountryField() throws Exception{ 
	return sfdc.Field("Country"); 
} 
public static MemberOfField EmailField() throws Exception{ 
	return sfdc.Field("Email"); 
} 
public static MemberOfField LeadSourceField() throws Exception{ 
	return sfdc.Field("Lead Source"); 
} 
public static MemberOfField WebsiteField() throws Exception{ 
	return sfdc.Field("Website"); 
} 
public static MemberOfField IndustryField() throws Exception{ 
	return sfdc.Field("Industry"); 
} 
public static MemberOfField LeadStatusField() throws Exception{ 
	return sfdc.Field("Lead Status"); 
} 
public static MemberOfField AnnualRevenueField() throws Exception{ 
	return sfdc.Field("Annual Revenue"); 
} 
public static MemberOfField RatingField() throws Exception{ 
	return sfdc.Field("Rating"); 
} 
public static MemberOfField NoofEmployeesField() throws Exception{ 
	return sfdc.Field("No. of Employees"); 
} 
public static MemberOfField AddressField() throws Exception{ 
	return sfdc.Field("Address"); 
} 
public static MemberOfField ProductInterestField() throws Exception{ 
	return sfdc.Field("Product Interest"); 
} 
public static MemberOfField CurrentGeneratorsField() throws Exception{ 
	return sfdc.Field("Current Generator(s)"); 
} 
public static MemberOfField SICCodeField() throws Exception{ 
	return sfdc.Field("SIC Code"); 
} 
public static MemberOfField PrimaryField() throws Exception{ 
	return sfdc.Field("Primary"); 
} 
public static MemberOfField NumberofLocationsField() throws Exception{ 
	return sfdc.Field("Number of Locations"); 
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

 
// ************************* Functions & Static Classes for Sections ***************************** // 
 
public static Fields_LeadDetail SEC_LeadDetail() throws Exception{ 
return new Fields_LeadDetail("Lead Detail");
}
public static class Fields_LeadDetail{
Fields_LeadDetail(String SN)
{
SecName = SN;
}
public static MemberOfSEC LeadOwner() throws Exception 
{ 
return sfdc._SEC(SecName,"Lead Owner");
}
public static MemberOfSEC Phone() throws Exception 
{ 
return sfdc._SEC(SecName,"Phone");
}
public static MemberOfSEC Name() throws Exception 
{ 
return sfdc._SEC(SecName,"Name");
}
public static MemberOfSEC Mobile() throws Exception 
{ 
return sfdc._SEC(SecName,"Mobile");
}
public static MemberOfSEC Company() throws Exception 
{ 
return sfdc._SEC(SecName,"Company");
}
public static MemberOfSEC Fax() throws Exception 
{ 
return sfdc._SEC(SecName,"Fax");
}
public static MemberOfSEC Title() throws Exception 
{ 
return sfdc._SEC(SecName,"Title");
}
public static MemberOfSEC Email() throws Exception 
{ 
return sfdc._SEC(SecName,"Email");
}
public static MemberOfSEC LeadSource() throws Exception 
{ 
return sfdc._SEC(SecName,"Lead Source");
}
public static MemberOfSEC Website() throws Exception 
{ 
return sfdc._SEC(SecName,"Website");
}
public static MemberOfSEC Industry() throws Exception 
{ 
return sfdc._SEC(SecName,"Industry");
}
public static MemberOfSEC LeadStatus() throws Exception 
{ 
return sfdc._SEC(SecName,"Lead Status");
}
public static MemberOfSEC AnnualRevenue() throws Exception 
{ 
return sfdc._SEC(SecName,"Annual Revenue");
}
public static MemberOfSEC Rating() throws Exception 
{ 
return sfdc._SEC(SecName,"Rating");
}
public static MemberOfSEC NoofEmployees() throws Exception 
{ 
return sfdc._SEC(SecName,"No. of Employees");
}
public static MemberOfSEC Address() throws Exception 
{ 
return sfdc._SEC(SecName,"Address");
}
public static MemberOfSEC ProductInterest() throws Exception 
{ 
return sfdc._SEC(SecName,"Product Interest");
}
public static MemberOfSEC CurrentGenerators() throws Exception 
{ 
return sfdc._SEC(SecName,"Current Generator(s)");
}
public static MemberOfSEC SICCode() throws Exception 
{ 
return sfdc._SEC(SecName,"SIC Code");
}
public static MemberOfSEC Primary() throws Exception 
{ 
return sfdc._SEC(SecName,"Primary");
}
public static MemberOfSEC NumberofLocations() throws Exception 
{ 
return sfdc._SEC(SecName,"Number of Locations");
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
}

 
 
 // **************** Functions & Static Classes for Related List ******************** 
 
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
public static Columns_CampaignHistory RL_CampaignHistory() throws Exception{ 
return new Columns_CampaignHistory("Campaign History"); 
} 
public static class Columns_CampaignHistory{ 
Columns_CampaignHistory(String RL) 
{ 
RList = RL; 
} 
}
public static Columns_HTMLEmailStatus RL_HTMLEmailStatus() throws Exception{ 
return new Columns_HTMLEmailStatus("HTML Email Status"); 
} 
public static class Columns_HTMLEmailStatus{ 
Columns_HTMLEmailStatus(String RL) 
{ 
RList = RL; 
} 
}

 
 
public static MemberOfButton EditButton() throws Exception{ 
return sfdc.Button("Edit"); 
} 
public static MemberOfButton AddtoCampaignButton() throws Exception{ 
return sfdc.Button("Add to Campaign"); 
} 
public static MemberOfButton FindDuplicatesButton() throws Exception{ 
return sfdc.Button("Find Duplicates"); 
} 
public static MemberOfButton NewTaskButton() throws Exception{ 
return sfdc.Button("New Task"); 
} 
public static MemberOfButton MailMergeButton() throws Exception{ 
return sfdc.Button("Mail Merge"); 
} 
public static MemberOfButton NewMeetingRequestButton() throws Exception{ 
return sfdc.Button("New Meeting Request"); 
} 
public static MemberOfButton OKButton() throws Exception{ 
return sfdc.Button("OK"); 
} 
public static MemberOfButton ConvertButton() throws Exception{ 
return sfdc.Button("Convert"); 
} 
public static MemberOfButton CancelButton() throws Exception{ 
return sfdc.Button("Cancel"); 
} 
public static MemberOfButton GoButton() throws Exception{ 
return sfdc.Button("Go!"); 
} 
public static MemberOfButton LogaCallButton() throws Exception{ 
return sfdc.Button("Log a Call"); 
} 
public static MemberOfButton CloneButton() throws Exception{ 
return sfdc.Button("Clone"); 
} 
public static MemberOfButton SendanEmailButton() throws Exception{ 
return sfdc.Button("Send an Email"); 
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
public static MemberOfButton NewButton() throws Exception{ 
return sfdc.Button("New"); 
} 
}
