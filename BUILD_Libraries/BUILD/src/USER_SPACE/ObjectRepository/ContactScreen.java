package USER_SPACE.ObjectRepository; 
import SOURCE_CODE.SFDC.*;
import USER_SPACE.TestPrerequisite.DataSetup;

 
 
public class ContactScreen { 
static SFDCAutomationFW sfdc = DataSetup.sfdc; 
static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField ContactOwnerField() throws Exception{ 
	return sfdc.Field("Contact Owner"); 
} 
public static MemberOfField PhoneField() throws Exception{ 
	return sfdc.Field("Phone"); 
} 
public static MemberOfField NameField() throws Exception{ 
	return sfdc.Field("Name"); 
} 
public static MemberOfField HomePhoneField() throws Exception{ 
	return sfdc.Field("Home Phone"); 
} 
public static MemberOfField AccountNameField() throws Exception{ 
	return sfdc.Field("Account Name"); 
} 
public static MemberOfField MobileField() throws Exception{ 
	return sfdc.Field("Mobile"); 
} 
public static MemberOfField TitleField() throws Exception{ 
	return sfdc.Field("Title"); 
} 
public static MemberOfField OtherPhoneField() throws Exception{ 
	return sfdc.Field("Other Phone"); 
} 
public static MemberOfField DepartmentField() throws Exception{ 
	return sfdc.Field("Department"); 
} 
public static MemberOfField FaxField() throws Exception{ 
	return sfdc.Field("Fax"); 
} 
public static MemberOfField BirthdateField() throws Exception{ 
	return sfdc.Field("Birthdate"); 
} 
public static MemberOfField EmailField() throws Exception{ 
	return sfdc.Field("Email"); 
} 
public static MemberOfField ReportsToField() throws Exception{ 
	return sfdc.Field("Reports To"); 
} 
public static MemberOfField AssistantField() throws Exception{ 
	return sfdc.Field("Assistant"); 
} 
public static MemberOfField LeadSourceField() throws Exception{ 
	return sfdc.Field("Lead Source"); 
} 
public static MemberOfField AsstPhoneField() throws Exception{ 
	return sfdc.Field("Asst. Phone"); 
} 
public static MemberOfField MailingAddressField() throws Exception{ 
	return sfdc.Field("Mailing Address"); 
} 
public static MemberOfField OtherAddressField() throws Exception{ 
	return sfdc.Field("Other Address"); 
} 
public static MemberOfField LanguagesField() throws Exception{ 
	return sfdc.Field("Languages"); 
} 
public static MemberOfField LevelField() throws Exception{ 
	return sfdc.Field("Level"); 
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
 
public static Fields_ContactDetail SEC_ContactDetail() throws Exception{ 
return new Fields_ContactDetail("Contact Detail");
}
public static class Fields_ContactDetail{
Fields_ContactDetail(String SN)
{
SecName = SN;
}
public static MemberOfSEC ContactOwner() throws Exception 
{ 
return sfdc._SEC(SecName,"Contact Owner");
}
public static MemberOfSEC Phone() throws Exception 
{ 
return sfdc._SEC(SecName,"Phone");
}
public static MemberOfSEC Name() throws Exception 
{ 
return sfdc._SEC(SecName,"Name");
}
public static MemberOfSEC HomePhone() throws Exception 
{ 
return sfdc._SEC(SecName,"Home Phone");
}
public static MemberOfSEC AccountName() throws Exception 
{ 
return sfdc._SEC(SecName,"Account Name");
}
public static MemberOfSEC Mobile() throws Exception 
{ 
return sfdc._SEC(SecName,"Mobile");
}
public static MemberOfSEC Title() throws Exception 
{ 
return sfdc._SEC(SecName,"Title");
}
public static MemberOfSEC OtherPhone() throws Exception 
{ 
return sfdc._SEC(SecName,"Other Phone");
}
public static MemberOfSEC Department() throws Exception 
{ 
return sfdc._SEC(SecName,"Department");
}
public static MemberOfSEC Fax() throws Exception 
{ 
return sfdc._SEC(SecName,"Fax");
}
public static MemberOfSEC Birthdate() throws Exception 
{ 
return sfdc._SEC(SecName,"Birthdate");
}
public static MemberOfSEC Email() throws Exception 
{ 
return sfdc._SEC(SecName,"Email");
}
public static MemberOfSEC ReportsTo() throws Exception 
{ 
return sfdc._SEC(SecName,"Reports To");
}
public static MemberOfSEC Assistant() throws Exception 
{ 
return sfdc._SEC(SecName,"Assistant");
}
public static MemberOfSEC LeadSource() throws Exception 
{ 
return sfdc._SEC(SecName,"Lead Source");
}
public static MemberOfSEC AsstPhone() throws Exception 
{ 
return sfdc._SEC(SecName,"Asst. Phone");
}
public static MemberOfSEC MailingAddress() throws Exception 
{ 
return sfdc._SEC(SecName,"Mailing Address");
}
public static MemberOfSEC OtherAddress() throws Exception 
{ 
return sfdc._SEC(SecName,"Other Address");
}
public static MemberOfSEC Languages() throws Exception 
{ 
return sfdc._SEC(SecName,"Languages");
}
public static MemberOfSEC Level() throws Exception 
{ 
return sfdc._SEC(SecName,"Level");
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
public static Columns_CampaignHistory RL_CampaignHistory() throws Exception{ 
return new Columns_CampaignHistory("Campaign History"); 
} 
public static class Columns_CampaignHistory{ 
Columns_CampaignHistory(String RL) 
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
public static MemberOfButton NewOpportunityButton() throws Exception{ 
return sfdc.Button("New Opportunity"); 
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
public static MemberOfButton NewMeetingRequestButton() throws Exception{ 
return sfdc.Button("New Meeting Request"); 
} 
public static MemberOfButton RequestUpdateButton() throws Exception{ 
return sfdc.Button("Request Update"); 
} 
public static MemberOfButton OKButton() throws Exception{ 
return sfdc.Button("OK"); 
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
public static MemberOfButton CloneButton() throws Exception{ 
return sfdc.Button("Clone"); 
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
