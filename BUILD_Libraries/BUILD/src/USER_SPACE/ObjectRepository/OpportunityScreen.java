package USER_SPACE.ObjectRepository; 
import SOURCE_CODE.SFDC.*; 
import USER_SPACE.TestPrerequisite.*; 

 
 
public class OpportunityScreen { 
static SFDCAutomationFW sfdc = DataSetup.sfdc; 
static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField OpportunityOwnerField() throws Exception{ 
	return sfdc.Field("Opportunity Owner"); 
} 
public static MemberOfField AmountField() throws Exception{ 
	return sfdc.Field("Amount"); 
} 
public static MemberOfField PrivateField() throws Exception{ 
	return sfdc.Field("Private"); 
} 
public static MemberOfField ExpectedRevenueField() throws Exception{ 
	return sfdc.Field("Expected Revenue"); 
} 
public static MemberOfField OpportunityNameField() throws Exception{ 
	return sfdc.Field("Opportunity Name"); 
} 
public static MemberOfField CloseDateField() throws Exception{ 
	return sfdc.Field("Close Date"); 
} 
public static MemberOfField AccountNameField() throws Exception{ 
	return sfdc.Field("Account Name"); 
} 
public static MemberOfField NextStepField() throws Exception{ 
	return sfdc.Field("Next Step"); 
} 
public static MemberOfField TypeField() throws Exception{ 
	return sfdc.Field("Type"); 
} 
public static MemberOfField StageField() throws Exception{ 
	return sfdc.Field("Stage"); 
} 
public static MemberOfField LeadSourceField() throws Exception{ 
	return sfdc.Field("Lead Source"); 
} 
public static MemberOfField ProbabilityField() throws Exception{ 
	return sfdc.Field("Probability (%)"); 
} 
public static MemberOfField PrimaryCampaignSourceField() throws Exception{ 
	return sfdc.Field("Primary Campaign Source"); 
} 
public static MemberOfField OrderNumberField() throws Exception{ 
	return sfdc.Field("Order Number"); 
} 
public static MemberOfField MainCompetitorsField() throws Exception{ 
	return sfdc.Field("Main Competitor(s)"); 
} 
public static MemberOfField CurrentGeneratorsField() throws Exception{ 
	return sfdc.Field("Current Generator(s)"); 
} 
public static MemberOfField DeliveryInstallationStatusField() throws Exception{ 
	return sfdc.Field("Delivery/Installation Status"); 
} 
public static MemberOfField TrackingNumberField() throws Exception{ 
	return sfdc.Field("Tracking Number"); 
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

 
// ************************* Functions & Static Classes for Sections ***************************** // 
 
public static Fields_OpportunityDetail SEC_OpportunityDetail() throws Exception{ 
return new Fields_OpportunityDetail("Opportunity Detail");
}
public static class Fields_OpportunityDetail{
Fields_OpportunityDetail(String SN)
{
SecName = SN;
}
public static MemberOfSEC OpportunityOwner() throws Exception 
{ 
return sfdc._SEC(SecName,"Opportunity Owner");
}
public static MemberOfSEC Amount() throws Exception 
{ 
return sfdc._SEC(SecName,"Amount");
}
public static MemberOfSEC Private() throws Exception 
{ 
return sfdc._SEC(SecName,"Private");
}
public static MemberOfSEC ExpectedRevenue() throws Exception 
{ 
return sfdc._SEC(SecName,"Expected Revenue");
}
public static MemberOfSEC OpportunityName() throws Exception 
{ 
return sfdc._SEC(SecName,"Opportunity Name");
}
public static MemberOfSEC CloseDate() throws Exception 
{ 
return sfdc._SEC(SecName,"Close Date");
}
public static MemberOfSEC AccountName() throws Exception 
{ 
return sfdc._SEC(SecName,"Account Name");
}
public static MemberOfSEC NextStep() throws Exception 
{ 
return sfdc._SEC(SecName,"Next Step");
}
public static MemberOfSEC Type() throws Exception 
{ 
return sfdc._SEC(SecName,"Type");
}
public static MemberOfSEC Stage() throws Exception 
{ 
return sfdc._SEC(SecName,"Stage");
}
public static MemberOfSEC LeadSource() throws Exception 
{ 
return sfdc._SEC(SecName,"Lead Source");
}
public static MemberOfSEC Probability() throws Exception 
{ 
return sfdc._SEC(SecName,"Probability (%)");
}
public static MemberOfSEC PrimaryCampaignSource() throws Exception 
{ 
return sfdc._SEC(SecName,"Primary Campaign Source");
}
public static MemberOfSEC OrderNumber() throws Exception 
{ 
return sfdc._SEC(SecName,"Order Number");
}
public static MemberOfSEC MainCompetitors() throws Exception 
{ 
return sfdc._SEC(SecName,"Main Competitor(s)");
}
public static MemberOfSEC CurrentGenerators() throws Exception 
{ 
return sfdc._SEC(SecName,"Current Generator(s)");
}
public static MemberOfSEC DeliveryInstallationStatus() throws Exception 
{ 
return sfdc._SEC(SecName,"Delivery/Installation Status");
}
public static MemberOfSEC TrackingNumber() throws Exception 
{ 
return sfdc._SEC(SecName,"Tracking Number");
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
 
public static Columns_ProductsStandard RL_ProductsStandard() throws Exception{ 
return new Columns_ProductsStandard("Products (Standard)"); 
} 
public static class Columns_ProductsStandard{ 
Columns_ProductsStandard(String RL) 
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
public static MemberOfRL Product(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Product",RowIndex); 
}
public static MemberOfRL Product() throws Exception 
{ 
return sfdc._RL(RList,"Product"); 
}
public static MemberOfRL Quantity(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Quantity",RowIndex); 
}
public static MemberOfRL Quantity() throws Exception 
{ 
return sfdc._RL(RList,"Quantity"); 
}
public static MemberOfRL SalesPrice(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Sales Price",RowIndex); 
}
public static MemberOfRL SalesPrice() throws Exception 
{ 
return sfdc._RL(RList,"Sales Price"); 
}
public static MemberOfRL Date(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Date",RowIndex); 
}
public static MemberOfRL Date() throws Exception 
{ 
return sfdc._RL(RList,"Date"); 
}
public static MemberOfRL LineDescription(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Line Description",RowIndex); 
}
public static MemberOfRL LineDescription() throws Exception 
{ 
return sfdc._RL(RList,"Line Description"); 
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
public static MemberOfRL Action(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Action",RowIndex); 
}
public static MemberOfRL Action() throws Exception 
{ 
return sfdc._RL(RList,"Action"); 
}
public static MemberOfRL Subject(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Subject",RowIndex); 
}
public static MemberOfRL Subject() throws Exception 
{ 
return sfdc._RL(RList,"Subject"); 
}
public static MemberOfRL Name(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Name",RowIndex); 
}
public static MemberOfRL Name() throws Exception 
{ 
return sfdc._RL(RList,"Name"); 
}
public static MemberOfRL Task(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Task",RowIndex); 
}
public static MemberOfRL Task() throws Exception 
{ 
return sfdc._RL(RList,"Task"); 
}
public static MemberOfRL DueDate(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Due Date",RowIndex); 
}
public static MemberOfRL DueDate() throws Exception 
{ 
return sfdc._RL(RList,"Due Date"); 
}
public static MemberOfRL Status(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Status",RowIndex); 
}
public static MemberOfRL Status() throws Exception 
{ 
return sfdc._RL(RList,"Status"); 
}
public static MemberOfRL Priority(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Priority",RowIndex); 
}
public static MemberOfRL Priority() throws Exception 
{ 
return sfdc._RL(RList,"Priority"); 
}
public static MemberOfRL AssignedTo(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Assigned To",RowIndex); 
}
public static MemberOfRL AssignedTo() throws Exception 
{ 
return sfdc._RL(RList,"Assigned To"); 
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
public static MemberOfRL Action(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Action",RowIndex); 
}
public static MemberOfRL Action() throws Exception 
{ 
return sfdc._RL(RList,"Action"); 
}
public static MemberOfRL Type(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Type",RowIndex); 
}
public static MemberOfRL Type() throws Exception 
{ 
return sfdc._RL(RList,"Type"); 
}
public static MemberOfRL Title(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Title",RowIndex); 
}
public static MemberOfRL Title() throws Exception 
{ 
return sfdc._RL(RList,"Title"); 
}
public static MemberOfRL LastModified(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Last Modified",RowIndex); 
}
public static MemberOfRL LastModified() throws Exception 
{ 
return sfdc._RL(RList,"Last Modified"); 
}
public static MemberOfRL CreatedBy(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Created By",RowIndex); 
}
public static MemberOfRL CreatedBy() throws Exception 
{ 
return sfdc._RL(RList,"Created By"); 
}
}
public static Columns_ContactRoles RL_ContactRoles() throws Exception{ 
return new Columns_ContactRoles("Contact Roles"); 
} 
public static class Columns_ContactRoles{ 
Columns_ContactRoles(String RL) 
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
public static MemberOfRL AccountName(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Account Name",RowIndex); 
}
public static MemberOfRL AccountName() throws Exception 
{ 
return sfdc._RL(RList,"Account Name"); 
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
public static MemberOfRL Role(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Role",RowIndex); 
}
public static MemberOfRL Role() throws Exception 
{ 
return sfdc._RL(RList,"Role"); 
}
public static MemberOfRL Primary(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Primary",RowIndex); 
}
public static MemberOfRL Primary() throws Exception 
{ 
return sfdc._RL(RList,"Primary"); 
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
public static MemberOfRL Action(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Action",RowIndex); 
}
public static MemberOfRL Action() throws Exception 
{ 
return sfdc._RL(RList,"Action"); 
}
public static MemberOfRL Partner(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Partner",RowIndex); 
}
public static MemberOfRL Partner() throws Exception 
{ 
return sfdc._RL(RList,"Partner"); 
}
public static MemberOfRL Role(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Role",RowIndex); 
}
public static MemberOfRL Role() throws Exception 
{ 
return sfdc._RL(RList,"Role"); 
}
public static MemberOfRL Primary(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Primary",RowIndex); 
}
public static MemberOfRL Primary() throws Exception 
{ 
return sfdc._RL(RList,"Primary"); 
}
}
public static Columns_Competitors RL_Competitors() throws Exception{ 
return new Columns_Competitors("Competitors"); 
} 
public static class Columns_Competitors{ 
Columns_Competitors(String RL) 
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
public static MemberOfRL CompetitorName(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Competitor Name",RowIndex); 
}
public static MemberOfRL CompetitorName() throws Exception 
{ 
return sfdc._RL(RList,"Competitor Name"); 
}
public static MemberOfRL Strengths(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Strengths",RowIndex); 
}
public static MemberOfRL Strengths() throws Exception 
{ 
return sfdc._RL(RList,"Strengths"); 
}
public static MemberOfRL Weaknesses(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Weaknesses",RowIndex); 
}
public static MemberOfRL Weaknesses() throws Exception 
{ 
return sfdc._RL(RList,"Weaknesses"); 
}
}
public static Columns_StageHistory RL_StageHistory() throws Exception{ 
return new Columns_StageHistory("Stage History"); 
} 
public static class Columns_StageHistory{ 
Columns_StageHistory(String RL) 
{ 
RList = RL; 
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
public static MemberOfRL Probability(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Probability (%)",RowIndex); 
}
public static MemberOfRL Probability() throws Exception 
{ 
return sfdc._RL(RList,"Probability (%)"); 
}
public static MemberOfRL ExpectedRevenue(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Expected Revenue",RowIndex); 
}
public static MemberOfRL ExpectedRevenue() throws Exception 
{ 
return sfdc._RL(RList,"Expected Revenue"); 
}
public static MemberOfRL CloseDate(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Close Date",RowIndex); 
}
public static MemberOfRL CloseDate() throws Exception 
{ 
return sfdc._RL(RList,"Close Date"); 
}
public static MemberOfRL LastModified(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Last Modified",RowIndex); 
}
public static MemberOfRL LastModified() throws Exception 
{ 
return sfdc._RL(RList,"Last Modified"); 
}
}

 
 
public static MemberOfButton SortButton() throws Exception{ 
return sfdc.Button("Sort"); 
} 
public static MemberOfButton AddProductButton() throws Exception{ 
return sfdc.Button("Add Product"); 
} 
public static MemberOfButton EditButton() throws Exception{ 
return sfdc.Button("Edit"); 
} 
public static MemberOfButton EditAllButton() throws Exception{ 
return sfdc.Button("Edit All"); 
} 
public static MemberOfButton NewButton() throws Exception{ 
return sfdc.Button("New"); 
} 
public static MemberOfButton AttachFileButton() throws Exception{ 
return sfdc.Button("Attach File"); 
} 
public static MemberOfButton NewTaskButton() throws Exception{ 
return sfdc.Button("New Task"); 
} 
public static MemberOfButton MailMergeButton() throws Exception{ 
return sfdc.Button("Mail Merge"); 
} 
public static MemberOfButton ChoosePriceBookButton() throws Exception{ 
return sfdc.Button("Choose Price Book"); 
} 
public static MemberOfButton ViewAllButton() throws Exception{ 
return sfdc.Button("View All"); 
} 
public static MemberOfButton OKButton() throws Exception{ 
return sfdc.Button("OK"); 
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
