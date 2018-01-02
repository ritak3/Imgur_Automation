package USER_SPACE.ObjectRepository; 
import SOURCE_CODE.SFDC.*;
import USER_SPACE.TestPrerequisite.DataSetup;


 
 
public class CaseScreen { 
static SFDCAutomationFW sfdc = DataSetup.sfdc; 
static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField CaseOwnerField() throws Exception{ 
	return sfdc.Field("Case Owner"); 
} 
public static MemberOfField StatusField() throws Exception{ 
	return sfdc.Field("Status"); 
} 
public static MemberOfField CaseNumberField() throws Exception{ 
	return sfdc.Field("Case Number"); 
} 
public static MemberOfField PriorityField() throws Exception{ 
	return sfdc.Field("Priority"); 
} 
public static MemberOfField ContactNameField() throws Exception{ 
	return sfdc.Field("Contact Name"); 
} 
public static MemberOfField ContactPhoneField() throws Exception{ 
	return sfdc.Field("Contact Phone"); 
} 
public static MemberOfField AccountNameField() throws Exception{ 
	return sfdc.Field("Account Name"); 
} 
public static MemberOfField ContactEmailField() throws Exception{ 
	return sfdc.Field("Contact Email"); 
} 
public static MemberOfField TypeField() throws Exception{ 
	return sfdc.Field("Type"); 
} 
public static MemberOfField CaseOriginField() throws Exception{ 
	return sfdc.Field("Case Origin"); 
} 
public static MemberOfField CaseReasonField() throws Exception{ 
	return sfdc.Field("Case Reason"); 
} 
public static MemberOfField DateTimeOpenedField() throws Exception{ 
	return sfdc.Field("Date/Time Opened"); 
} 
public static MemberOfField DateTimeClosedField() throws Exception{ 
	return sfdc.Field("Date/Time Closed"); 
} 
public static MemberOfField ProductField() throws Exception{ 
	return sfdc.Field("Product"); 
} 
public static MemberOfField EngineeringReqNumberField() throws Exception{ 
	return sfdc.Field("Engineering Req Number"); 
} 
public static MemberOfField PotentialLiabilityField() throws Exception{ 
	return sfdc.Field("Potential Liability"); 
} 
public static MemberOfField SLAViolationField() throws Exception{ 
	return sfdc.Field("SLA Violation"); 
} 
public static MemberOfField CreatedByField() throws Exception{ 
	return sfdc.Field("Created By"); 
} 
public static MemberOfField LastModifiedByField() throws Exception{ 
	return sfdc.Field("Last Modified By"); 
} 
public static MemberOfField SubjectField() throws Exception{ 
	return sfdc.Field("Subject"); 
} 
public static MemberOfField DescriptionField() throws Exception{ 
	return sfdc.Field("Description"); 
} 
public static MemberOfField CustomLinksField() throws Exception{ 
	return sfdc.Field("Custom Links"); 
} 

 
// ************************* Functions & Static Classes for Sections ***************************** // 
 
public static Fields_CaseDetail SEC_CaseDetail() throws Exception{ 
return new Fields_CaseDetail("Case Detail");
}
public static class Fields_CaseDetail{
Fields_CaseDetail(String SN)
{
SecName = SN;
}
public static MemberOfSEC CaseOwner() throws Exception 
{ 
return sfdc._SEC(SecName,"Case Owner");
}
public static MemberOfSEC Status() throws Exception 
{ 
return sfdc._SEC(SecName,"Status");
}
public static MemberOfSEC CaseNumber() throws Exception 
{ 
return sfdc._SEC(SecName,"Case Number");
}
public static MemberOfSEC Priority() throws Exception 
{ 
return sfdc._SEC(SecName,"Priority");
}
public static MemberOfSEC ContactName() throws Exception 
{ 
return sfdc._SEC(SecName,"Contact Name");
}
public static MemberOfSEC ContactPhone() throws Exception 
{ 
return sfdc._SEC(SecName,"Contact Phone");
}
public static MemberOfSEC AccountName() throws Exception 
{ 
return sfdc._SEC(SecName,"Account Name");
}
public static MemberOfSEC ContactEmail() throws Exception 
{ 
return sfdc._SEC(SecName,"Contact Email");
}
public static MemberOfSEC Type() throws Exception 
{ 
return sfdc._SEC(SecName,"Type");
}
public static MemberOfSEC CaseOrigin() throws Exception 
{ 
return sfdc._SEC(SecName,"Case Origin");
}
public static MemberOfSEC CaseReason() throws Exception 
{ 
return sfdc._SEC(SecName,"Case Reason");
}
public static MemberOfSEC DateTimeOpened() throws Exception 
{ 
return sfdc._SEC(SecName,"Date/Time Opened");
}
public static MemberOfSEC DateTimeClosed() throws Exception 
{ 
return sfdc._SEC(SecName,"Date/Time Closed");
}
public static MemberOfSEC Product() throws Exception 
{ 
return sfdc._SEC(SecName,"Product");
}
public static MemberOfSEC EngineeringReqNumber() throws Exception 
{ 
return sfdc._SEC(SecName,"Engineering Req Number");
}
public static MemberOfSEC PotentialLiability() throws Exception 
{ 
return sfdc._SEC(SecName,"Potential Liability");
}
public static MemberOfSEC SLAViolation() throws Exception 
{ 
return sfdc._SEC(SecName,"SLA Violation");
}
public static MemberOfSEC CreatedBy() throws Exception 
{ 
return sfdc._SEC(SecName,"Created By");
}
public static MemberOfSEC LastModifiedBy() throws Exception 
{ 
return sfdc._SEC(SecName,"Last Modified By");
}
public static MemberOfSEC Subject() throws Exception 
{ 
return sfdc._SEC(SecName,"Subject");
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
 
public static Columns_Solutions RL_Solutions() throws Exception{ 
return new Columns_Solutions("Solutions"); 
} 
public static class Columns_Solutions{ 
Columns_Solutions(String RL) 
{ 
RList = RL; 
} 
public static MemberOfRL NoSolutionsAttached(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"No Solutions Attached",RowIndex); 
}
public static MemberOfRL NoSolutionsAttached() throws Exception 
{ 
return sfdc._RL(RList,"No Solutions Attached"); 
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
public static Columns_CaseComments RL_CaseComments() throws Exception{ 
return new Columns_CaseComments("Case Comments"); 
} 
public static class Columns_CaseComments{ 
Columns_CaseComments(String RL) 
{ 
RList = RL; 
} 
}
public static Columns_Attachments RL_Attachments() throws Exception{ 
return new Columns_Attachments("Attachments"); 
} 
public static class Columns_Attachments{ 
Columns_Attachments(String RL) 
{ 
RList = RL; 
} 
}
public static Columns_CaseHistory RL_CaseHistory() throws Exception{ 
return new Columns_CaseHistory("Case History"); 
} 
public static class Columns_CaseHistory{ 
Columns_CaseHistory(String RL) 
{ 
RList = RL; 
} 
public static MemberOfRL Date(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Date",RowIndex); 
}
public static MemberOfRL Date() throws Exception 
{ 
return sfdc._RL(RList,"Date"); 
}
public static MemberOfRL User(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"User",RowIndex); 
}
public static MemberOfRL User() throws Exception 
{ 
return sfdc._RL(RList,"User"); 
}
public static MemberOfRL Action(Integer RowIndex) throws Exception 
{ 
return sfdc._RL(RList,"Action",RowIndex); 
}
public static MemberOfRL Action() throws Exception 
{ 
return sfdc._RL(RList,"Action"); 
}
}

 
 
public static MemberOfButton CloseCaseButton() throws Exception{ 
return sfdc.Button("Close Case"); 
} 
public static MemberOfButton EditButton() throws Exception{ 
return sfdc.Button("Edit"); 
} 
public static MemberOfButton ViewSuggestedSolutionsButton() throws Exception{ 
return sfdc.Button("View Suggested Solutions"); 
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
public static MemberOfButton FindSolutionButton() throws Exception{ 
return sfdc.Button("Find Solution"); 
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
}
